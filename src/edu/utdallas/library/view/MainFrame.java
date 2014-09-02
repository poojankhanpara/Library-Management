package edu.utdallas.library.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import edu.utdallas.library.model.Model;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JInternalFrame;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.ListSelectionModel;
import java.awt.Font;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 11L;
	private JPanel contentPane;
	private JTable tableBook;
	private JTable tableBorrower;
	

	private JTextField tfBorrower;
	private JButton btnSearchBorrower;
	private JRadioButton rdbtnFirstName;
	private JRadioButton rdbtnLastName;
	private JRadioButton rdbtnCardNo;
	private JTextField tFfirstName;
	private JTextField tFlastname;
	private JTextField tFaddress;
	private JTextField tFphone;
	private JButton btnSave;
	private JButton btnBookSearch;
	private JButton btnCheckout;
	private JTextField tFloans;
	private JRadioButton rdbtnIsbnLoans;
	private JRadioButton rdbtnCardNoLoans;
	private JRadioButton rdbtnBorrowerNameLoans;
	private JButton btnSearchLoans;
	private JTable tableLoan;
	private JScrollPane scrollPane_2;
	private JButton btnCheckIn;
	private JTextField tFbookidSearch;
	private JTextField tFtitleSearch;
	private JTextField tFauthornameSearch;
	
	public JButton getBtnCheckout() {
		return btnCheckout;
	}
	public JTextField gettFloans() {
		return tFloans;
	}
	public JRadioButton getRdbtnIsbnLoans() {
		return rdbtnIsbnLoans;
	}
	public JRadioButton getRdbtnCardNoLoans() {
		return rdbtnCardNoLoans;
	}
	public JRadioButton getRdbtnBorrowerNameLoans() {
		return rdbtnBorrowerNameLoans;
	}
	public JButton getBtnSearchLoans() {
		return btnSearchLoans;
	}
	public JTable getTableBook() {
		return tableBook;
	}
	public JTable getTableLoan() {
		return tableLoan;
	}
	public JTable getTableBorrower() {
		return tableBorrower;
	}
	public JButton getBtnSearchBorrower() {
		return btnSearchBorrower;
	}
	public JRadioButton getRdbtnFirstName() {
		return rdbtnFirstName;
	}
	public JRadioButton getRdbtnLastName() {
		return rdbtnLastName;
	}
	public JButton getBtnCheckIn() {
		return btnCheckIn;
	}
	public JRadioButton getRdbtnCardNo() {
		return rdbtnCardNo;
	}
	
	public JTextField getTfBorrower() {
		return tfBorrower;
	}
	public JTextField gettFfirstName() {
		return tFfirstName;
	}
	public JTextField gettFlastname() {
		return tFlastname;
	}
	public JTextField gettFaddress() {
		return tFaddress;
	}
	public JTextField gettFphone() {
		return tFphone;
	}
	public JButton getBtnSave() {
		return btnSave;
	}
	public JButton getBtnBookSearch() {
		return btnBookSearch;
	}
	public JTextField gettFbookidSearch() {
		return tFbookidSearch;
	}
	public JTextField gettFtitleSearch() {
		return tFtitleSearch;
	}
	public JTextField gettFauthornameSearch() {
		return tFauthornameSearch;
	}
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 397);
		setSize(865,619);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelSearch = new JPanel(); // rows, column
		tabbedPane.addTab("Search book", null, panelSearch, null);
		panelSearch.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 91, 615, 374);
		panelSearch.add(scrollPane);
		
		tableBook = new JTable();
		scrollPane.setViewportView(tableBook);
		
		btnBookSearch = new JButton("Search");
		btnBookSearch.setBounds(655, 42, 104, 31);
		panelSearch.add(btnBookSearch);
		
		btnCheckout = new JButton("Check out");
		btnCheckout.setBounds(655, 91, 104, 81);
		panelSearch.add(btnCheckout);
		
		tFbookidSearch = new JTextField();
		tFbookidSearch.setBounds(30, 47, 138, 20);
		panelSearch.add(tFbookidSearch);
		tFbookidSearch.setColumns(10);
		
		tFtitleSearch = new JTextField();
		tFtitleSearch.setBounds(178, 47, 280, 20);
		panelSearch.add(tFtitleSearch);
		tFtitleSearch.setColumns(10);
		
		tFauthornameSearch = new JTextField();
		tFauthornameSearch.setBounds(468, 47, 177, 20);
		panelSearch.add(tFauthornameSearch);
		tFauthornameSearch.setColumns(10);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIsbn.setBounds(30, 22, 46, 14);
		panelSearch.add(lblIsbn);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTitle.setBounds(178, 22, 46, 14);
		panelSearch.add(lblTitle);
		
		JLabel lblAuthorName = new JLabel("Author Name:");
		lblAuthorName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAuthorName.setBounds(468, 23, 92, 14);
		panelSearch.add(lblAuthorName);
		
		ButtonGroup bgbook = new ButtonGroup();
		
		ButtonGroup bgLoans = new ButtonGroup();
		
		JPanel panelCheckIn = new JPanel();
		tabbedPane.addTab("Checked-out books", null, panelCheckIn, null);
		panelCheckIn.setLayout(null);
		
		tFloans = new JTextField();
		tFloans.setBounds(20, 56, 452, 20);
		panelCheckIn.add(tFloans);
		tFloans.setColumns(10);
		
		btnSearchLoans = new JButton("Search Loans");
		btnSearchLoans.setBounds(511, 55, 132, 23);
		panelCheckIn.add(btnSearchLoans);
		rdbtnIsbnLoans = new JRadioButton("ISBN");
		rdbtnIsbnLoans.setBounds(20, 83, 81, 23);
		panelCheckIn.add(rdbtnIsbnLoans);
		bgLoans.add(rdbtnIsbnLoans);
		
		rdbtnCardNoLoans = new JRadioButton("Card no");
		rdbtnCardNoLoans.setBounds(103, 83, 81, 23);
		panelCheckIn.add(rdbtnCardNoLoans);
		bgLoans.add(rdbtnCardNoLoans);
		
		rdbtnBorrowerNameLoans = new JRadioButton("Borrower Name",true);
		rdbtnBorrowerNameLoans.setBounds(186, 83, 132, 23);
		panelCheckIn.add(rdbtnBorrowerNameLoans);
		bgLoans.add(rdbtnBorrowerNameLoans);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 131, 658, 365);
		panelCheckIn.add(scrollPane_2);
		
		tableLoan = new JTable();
		tableLoan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLoan.setBorder(null);
		scrollPane_2.setViewportView(tableLoan);
		
		btnCheckIn = new JButton("Check In");
		btnCheckIn.setBounds(688, 168, 89, 75);
		panelCheckIn.add(btnCheckIn);
		
		JPanel panelBorrower = new JPanel();
		tabbedPane.addTab("Manage Borrowers", null, panelBorrower, null);
		panelBorrower.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 85, 472, 427);
		panelBorrower.add(scrollPane_1);
		
		tableBorrower = new JTable();
		tableBorrower.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableBorrower.setBorder(null);
		scrollPane_1.setViewportView(tableBorrower);
		
		tfBorrower = new JTextField();
		tfBorrower.setBounds(10, 30, 371, 20);
		panelBorrower.add(tfBorrower);
		tfBorrower.setColumns(10);
		
		btnSearchBorrower = new JButton("Search");
		btnSearchBorrower.setBounds(390, 29, 89, 23);
		panelBorrower.add(btnSearchBorrower);
		
		ButtonGroup rdGrpBorrower = new ButtonGroup();
		rdbtnCardNo = new JRadioButton("Card No",true);
		rdbtnCardNo.setBounds(6, 57, 109, 23);
		
		rdbtnFirstName = new JRadioButton("First Name");
		rdbtnFirstName.setBounds(117, 57, 109, 23);
		
		rdbtnLastName = new JRadioButton("Last Name");
		rdbtnLastName.setBounds(228, 57, 109, 23);
		
		rdGrpBorrower.add(rdbtnLastName);
		rdGrpBorrower.add(rdbtnCardNo);
		rdGrpBorrower.add(rdbtnFirstName);
		
		panelBorrower.add(rdbtnCardNo);
		panelBorrower.add(rdbtnFirstName);
		panelBorrower.add(rdbtnLastName);
		
		JInternalFrame internalFrame = new JInternalFrame("Add Borrower");
		internalFrame.setBounds(492, 85, 267, 427);
		panelBorrower.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name: *");
		lblFirstName.setBounds(10, 59, 80, 14);
		internalFrame.getContentPane().add(lblFirstName);
		
		JLabel label_1 = new JLabel("Last Name: *");
		label_1.setBounds(10, 84, 80, 14);
		internalFrame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Address: *");
		label_2.setBounds(10, 109, 80, 14);
		internalFrame.getContentPane().add(label_2);
		
		tFfirstName = new JTextField();
		tFfirstName.setBounds(85, 56, 156, 20);
		internalFrame.getContentPane().add(tFfirstName);
		tFfirstName.setColumns(10);
		
		tFlastname = new JTextField();
		tFlastname.setBounds(85, 84, 156, 20);
		internalFrame.getContentPane().add(tFlastname);
		tFlastname.setColumns(10);
		
		tFaddress = new JTextField();
		tFaddress.setColumns(10);
		tFaddress.setBounds(85, 109, 156, 20);
		internalFrame.getContentPane().add(tFaddress);
		
		tFphone = new JTextField();
		tFphone.setColumns(10);
		tFphone.setBounds(85, 134, 156, 20);
		internalFrame.getContentPane().add(tFphone);
		
		btnSave = new JButton("Add");
		btnSave.setBounds(85, 170, 70, 23);
		internalFrame.getContentPane().add(btnSave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tFfirstName.setText("");
				tFlastname.setText("");
				tFaddress.setText("");
				tFphone.setText("");
			}
		});
		btnClear.setBounds(171, 170, 70, 23);
		internalFrame.getContentPane().add(btnClear);
		
		JLabel label = new JLabel("Phone: ");
		label.setBounds(10, 134, 80, 14);
		internalFrame.getContentPane().add(label);
		
		internalFrame.setVisible(true);
		}
}
