package edu.utdallas.library.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import edu.utdallas.library.util.Util;

/**
 * 
 * @author Poojan Database related methods.
 */
public class Model {
	private Connection conn;
	private Statement stmt;
	private Statement updateableStmt;

	/**
	 * closes the connection object.
	 */
	public void finalize() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Model() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/",
					"root", "1234");
			stmt = conn.createStatement();
			stmt.execute("use library;");
			updateableStmt = conn
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
	}

	public DefaultTableModel getBorrowersTableModel(String query) {
		try {
			// Taking data from db and putting in vector
			ResultSet rs = stmt.executeQuery(query);
			String sep = ",";
			String card_no, fname, lname, address, phone, record;
			Vector<String> records = new Vector<String>();
			while (rs.next()) {
				card_no = rs.getString("card_no");
				fname = rs.getString("fname");
				lname = rs.getString("lname");
				address = rs.getString("address");
				phone = rs.getString("phone");

				record = card_no + sep + fname + " " + lname + sep + address
						+ sep + phone;
				records.add(record);
				// Util.out(record);
			}
			Util.out("borrower records exists: "+records.size());
			rs.close();

			// Creating TableModel
			String columnNames[] = { "Card No", "Name", "Address",
					"Phone number" };
			String rows[][] = new String[records.size()][3];

			int i = 0;
			for (Iterator<String> iterator = records.iterator(); iterator
					.hasNext();) {
				String string = iterator.next();
				rows[i++] = string.split(sep);
			}

			@SuppressWarnings("serial")
			DefaultTableModel dtmodel = new DefaultTableModel(rows, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			return dtmodel;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Book Search model
	 * 
	 * @param q
	 * @return
	 */
	public DefaultTableModel getBookSearchTableModel(String query) {
		try {
			//Util.out(query);
			// Taking data from db and putting in vector
			ResultSet rs = stmt.executeQuery(query);
			String sep = "\t";
			String book_id, title, authors,record,available_ratio;
			int branch_id, total_copies, available;
			Vector<String> records = new Vector<String>();
			while (rs.next()) {
				book_id = rs.getString("book_id");
				title = rs.getString("title");
				authors = rs.getString("authors");
				branch_id = rs.getInt("branch_id");
				total_copies = rs.getInt("no_of_copies");
				available = rs.getInt("available_copies");

				available_ratio = ""+available+" / "+total_copies;
				
				record = book_id+sep+title+sep+authors+sep+branch_id+sep+available_ratio;
				records.add(record);
				// Util.out(record);
			}
			Util.out("book records exists: " + records.size());
			rs.close();

			// Creating TableModel
			String columnNames[] = { "ISBN no", "Title", "Authors",
					"Branch id", "available / total" };
			String rows[][] = new String[records.size()][4];

			int i = 0;
			for (Iterator<String> iterator = records.iterator(); iterator
					.hasNext();) {
				String string = iterator.next();
				rows[i++] = string.split(sep);
			}

			@SuppressWarnings("serial")
			DefaultTableModel dtmodel = new DefaultTableModel(rows, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			return dtmodel;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * For getting Table model from Loans table
	 */
	public DefaultTableModel getLoansTableModel(String query) {
		try {
			// Taking data from db and putting in vector
			ResultSet rs = stmt.executeQuery(query);
			String sep = ",";
			String book_id, fname, lname, title, name, record;
			Date date_out, due_date;
			int branch_id, card_no, loan_id;
			Vector<String> records = new Vector<String>();
			while (rs.next()) {
				loan_id = rs.getInt("loan_id");
				book_id = rs.getString("book_id");
				title = rs.getString("title");
				branch_id = rs.getInt("branch_id");
				card_no = rs.getInt("card_no");
				fname = rs.getString("fname");
				lname = rs.getString("lname");
				date_out = rs.getDate("date_out");
				due_date = rs.getDate("date_due");

				name = fname + " " + lname;
				record = loan_id + sep + book_id + sep
						+ title.substring(0, title.length() - 1) + sep
						+ branch_id + sep + card_no + sep + name + sep
						+ date_out + sep + due_date;
				records.add(record);
				//Util.out(record);
			}
			Util.out("loans records exists: " + records.size());
			rs.close();

			// Creating TableModel
			String columnNames[] = { "Loan Id", "ISBN", "Title", "Branch Id",
					"Card no", "Name", "Date out", "Due Date" };
			String rows[][] = new String[records.size()][7];
			int i = 0;
			for (Iterator<String> iterator = records.iterator(); iterator
					.hasNext();) {
				String string = iterator.next();
				rows[i++] = string.split(sep);
			}

			@SuppressWarnings("serial")
			DefaultTableModel dtmodel = new DefaultTableModel(rows, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			return dtmodel;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * For inserting Borrowers.
	 * 
	 * @param q
	 * @return
	 * @throws SQLException 
	 */
	public boolean insert(String q) throws SQLException {
		Statement statement;
			statement = conn.createStatement();
			int insertCount = statement.executeUpdate(q);
			if (insertCount == 1)
				return true;
			else
				return false;
	}

	/**
	 * Inserts date_in in the tuple with the loan_id when the user returns the book.
	 * @param loan_id
	 * @param date
	 */
	public void insertDateIn(String loan_id, java.util.Date date) {
		try {
			String q = "SELECT * FROM book_loans WHERE" + " loan_id=" + loan_id
					+ ";";
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			ResultSet rs = updateableStmt.executeQuery(q);
			if (rs.next()) {
				rs.updateDate("date_in", sqlDate);
				rs.updateRow();
				rs.close();
				//Util.out("update the date of "+loan_id+ " to "+sqlDate.toString());
			} else {
				System.err
						.println("something went wrong in the insertDateIn method!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public int getRowCountUsingSelect(String query)
	{
		try {
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			return(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
