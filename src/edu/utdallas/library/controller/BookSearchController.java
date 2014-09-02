package edu.utdallas.library.controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import edu.utdallas.library.model.Model;
import edu.utdallas.library.util.Util;
import edu.utdallas.library.view.CheckOutDialog;
import edu.utdallas.library.view.MainFrame;

public class BookSearchController {
	private Model model;
	private MainFrame view;

	public BookSearchController(Model model, MainFrame view) {
		super();
		this.model = model;
		this.view = view;
	}

	public void control() {
		view.getBtnBookSearch().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				populateBookSearch();
			}
		});
		view.gettFbookidSearch().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				populateBookSearch();
			}
		});
		view.gettFtitleSearch().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				populateBookSearch();
			}
		});
		view.gettFauthornameSearch().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				populateBookSearch();
			}
		});

		view.getBtnCheckout().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rowIndex = view.getTableBook().getSelectedRow();
				// checking whether user has selected a row
				if (rowIndex != -1) {
					String book_id, title, iq, sq;
					int branch_id, card_no;
					String date_out, due_date;

					branch_id = Integer.parseInt((String) view.getTableBook()
							.getModel().getValueAt(rowIndex, 3));
					book_id = (String) view.getTableBook().getModel()
							.getValueAt(rowIndex, 0);
					title = (String) view.getTableBook().getModel()
							.getValueAt(rowIndex, 1);

					// Check whether that book is present in the branch or not
					sq = "SELECT book_copies.no_of_copies-count(*) AS count FROM book_loans,book_copies "
							+ "WHERE isnull(book_loans.date_in) AND book_loans.book_id=book_copies.book_id "
							+ "AND book_loans.branch_id=book_copies.branch_id  "
							+ "AND book_loans.branch_id="
							+ branch_id
							+ " AND book_loans.book_id='" + book_id + "';";
					int available_books = model.getRowCountUsingSelect(sq);
					//Util.out(available_books+"-->available");
					if (available_books > 0) 
					{
						CheckOutDialog dialog = new CheckOutDialog(view, true,
								title);
						if (dialog.getAnswer()) 
						{
							card_no = dialog.getCard_no();
							date_out = dialog.getOutDate();
							due_date = dialog.getDueDate();
							sq = "select count(*) as count from book_loans where isnull(date_in) AND card_no="
									+ card_no + ";";
							int no_of_books_per_user = model
									.getRowCountUsingSelect(sq);
							if (no_of_books_per_user < 3) 
							{
								iq = "insert into book_loans (book_id, branch_id, card_no, date_out,date_due,date_in)values('"
										+ book_id
										+ "',"
										+ branch_id
										+ ","
										+ card_no
										+ ",'"
										+ date_out
										+ "','"
										+ due_date + "',null);";
								//Util.out(iq);
								try 
								{
									if (model.insert(iq)) 
									{
										JOptionPane
												.showMessageDialog(
														view,
														"Book: "
																+ title
																+ " successfully checked out!");
										populateBookSearch();
									} 
									else 
									{
										JOptionPane.showMessageDialog(view,
												"Something went wrong!");
									}
								} // try ends
								catch (HeadlessException | SQLException e) 
								{
									JOptionPane
											.showMessageDialog(view,
													"Something is wrong with the borrower card no!");
								} // catch ends 

							} // user has >3 books
							else 
							{
								JOptionPane
										.showMessageDialog(
												view,
												"User with card no: "
														+ card_no
														+ " has already borrowed 3 books");
							}
						} 
					}
					else 
					{
						JOptionPane
						.showMessageDialog(view,
								"The book is not present in that library branch!");
		
					}
				}
			}
		});
	}

	public void populateBookSearch() {
		String book_id = view.gettFbookidSearch().getText();
		String title = view.gettFtitleSearch().getText();
		String author_name = view.gettFauthornameSearch().getText();
		String q = null;
	
			q = "SELECT book_id, title,authors,branch_id,no_of_copies,available_copies "
					+ "FROM (select book.book_id, book.title,group_concat(book_authors.author_name SEPARATOR ', ') AS authors, "
					+ "book_copies.branch_id, book_copies.no_of_copies, "
					+ "(book_copies.no_of_copies - (SELECT count(*) FROM book_loans WHERE book.book_id=book_loans.book_id "
					+ "AND book_copies.branch_id=book_loans.branch_id AND isnull(book_loans.date_in))) AS 'available_copies' "
					+ "FROM book, book_copies,book_authors where book.book_id=book_copies.book_id "
					+ "AND book.book_id=book_authors.book_id GROUP BY branch_id,book_id  "
					+ "ORDER BY (book.book_id) ASC) AS X WHERE X.authors LIKE '%"
					+ author_name + "%' AND X.book_id LIKE '%"+book_id+"%' AND X.title LIKE '%"+title+"%';";
			Util.out(q);
		view.getTableBook().setModel(model.getBookSearchTableModel(q));

	}
}
