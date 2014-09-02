package edu.utdallas.library.util;

public class Queries {
	public static final String BORROWER_DEFAULT = "SELECT card_no, fname, lname, address, phone FROM borrower;";
	public static final String LOANS_DEFAULT = "SELECT loan_id, book_loans.book_id,book.title, book_loans.branch_id, "
			+ "book_loans.card_no,borrower.fname,borrower.lname,  book_loans.date_out,"
			+ "book_loans.date_due FROM book, book_loans, borrower "
			+ "WHERE book.book_id=book_loans.book_id  "
			+ "AND borrower.card_no = book_loans.card_no AND isnull(book_loans.date_in)";
	public static final String BOOK_SEARCH = "SELECT book.book_id, book.title,group_concat(book_authors.author_name SEPARATOR ', ') AS authors, " +
			"book_copies.branch_id, book_copies.no_of_copies," +
			"(book_copies.no_of_copies - " +
			"(SELECT count(*) FROM book_loans WHERE book.book_id=book_loans.book_id " +
			"AND book_copies.branch_id=book_loans.branch_id AND isnull(book_loans.date_in))) AS 'available_copies' " +
			"FROM book, book_copies,book_authors where book.book_id=book_copies.book_id " +
			"AND book.book_id=book_authors.book_id  " +
			"GROUP BY branch_id,book_id  ORDER BY (book.book_id) ASC;";
}
