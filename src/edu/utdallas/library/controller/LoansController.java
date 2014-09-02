package edu.utdallas.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.utdallas.library.model.Model;
import edu.utdallas.library.util.Util;
import edu.utdallas.library.view.CheckInDialog;
import edu.utdallas.library.view.MainFrame;

public class LoansController {
	private MainFrame view;
	private Model model;

	public LoansController(MainFrame view, Model model) {
		super();
		this.view = view;
		this.model = model;
	}

	public void control() {
		view.getBtnSearchLoans().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				populateLoanTable();
			}
		});

		view.gettFloans().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				populateLoanTable();
			}
		});

		view.getBtnCheckIn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rowIndex = view.getTableLoan().getSelectedRow();
				if (rowIndex != -1) {
					String loan_id = (String) view.getTableLoan().getModel().getValueAt(rowIndex,0);
					String title = (String) view.getTableLoan().getModel().getValueAt(rowIndex,2);
					String borrowerName = (String) view.getTableLoan().getModel().getValueAt(rowIndex,5);
				
					CheckInDialog myDialog = new CheckInDialog(view, true,title ,borrowerName);
					if(myDialog.getAnswer())
					{
						model.insertDateIn(loan_id, myDialog.getDate());
						populateLoanTable();
					}
				}
			}
		});
	}

	protected void populateLoanTable() {
		
		String input = view.gettFloans().getText();
		String q;
		if (view.getRdbtnBorrowerNameLoans().isSelected()) {
			q = "SELECT loan_id, book_loans.book_id,book.title, book_loans.branch_id, "
					+ "book_loans.card_no,borrower.fname,borrower.lname,  book_loans.date_out,"
					+ "book_loans.date_due FROM book, book_loans, borrower "
					+ "WHERE book.book_id=book_loans.book_id  "
					+ "AND borrower.card_no = book_loans.card_no AND isnull(book_loans.date_in)"
					+ " AND (borrower.fname LIKE '%" + input + "%' OR "
					+ " borrower.lname LIKE '%" + input + "%');";

		} else if (view.getRdbtnCardNoLoans().isSelected()) {
			q = "SELECT loan_id,book_loans.book_id,book.title, book_loans.branch_id, "
					+ "book_loans.card_no,borrower.fname,borrower.lname,  book_loans.date_out,"
					+ "book_loans.date_due FROM book, book_loans, borrower "
					+ "WHERE book.book_id=book_loans.book_id  "
					+ "AND borrower.card_no = book_loans.card_no AND isnull(book_loans.date_in)"
					+ " AND (borrower.fname LIKE '%" + input + "%' OR "
					+ " borrower.card_no LIKE '%" + input + "%');";

		} else {
			q = "SELECT loan_id, book_loans.book_id,book.title, book_loans.branch_id, "
					+ "book_loans.card_no,borrower.fname,borrower.lname,  book_loans.date_out,"
					+ "book_loans.date_due FROM book, book_loans, borrower "
					+ "WHERE book.book_id=book_loans.book_id  "
					+ "AND borrower.card_no = book_loans.card_no AND isnull(book_loans.date_in) "
					+ " AND (borrower.fname LIKE '%" + input + "%' OR "
					+ " book_loans.book_id LIKE '%" + input + "%');";

		}
		//Util.out(q);
		view.getTableLoan().setModel(model.getLoansTableModel(q));

	}
}
