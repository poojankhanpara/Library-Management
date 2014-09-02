package edu.utdallas.library.controller;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import edu.utdallas.library.model.Model;
import edu.utdallas.library.util.Queries;
import edu.utdallas.library.util.Util;
import edu.utdallas.library.view.MainFrame;

public class Main {
	
	/**
	 * Launches the Library application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Creating View
					MainFrame view = new MainFrame();
					
					//code doesn't work, I don't know why!
					//ImageIcon ii = new ImageIcon(view.getClass().getResource("\images\icon.jpg"));
					//Util.out(ii.getDescription());
					//view.setIconImage(ii.getImage());
					view.setSize(800, 600);
					view.setTitle("Library Management");
					view.setVisible(true);
					
					// Creating Model
					Model model = new Model();
					view.getTableBorrower().setModel(model.getBorrowersTableModel(Queries.BORROWER_DEFAULT));
					view.getTableLoan().setModel(model.getLoansTableModel(Queries.LOANS_DEFAULT));
					view.getTableBook().setModel(model.getBookSearchTableModel(Queries.BOOK_SEARCH));
					// Creating controllers
					BorrowerController borrowerController = new BorrowerController(view, model);
					borrowerController.control();
					
					BookSearchController bookController = new BookSearchController(model, view);
					bookController.control();
					
					LoansController loanController = new LoansController(view, model);
					loanController.control();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} //main method ends
}
