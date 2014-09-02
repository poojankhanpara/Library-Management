package edu.utdallas.library.view;

import javax.swing.JDialog;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckOutDialog extends JDialog implements ActionListener {
	private JPanel myPanel = null;
	private JButton yesButton = null;
	private JButton noButton = null;
	private boolean answer = false;
	private int card_no;
	private String date_out = null;
	private String due_date = null;

	final SimpleDateFormat format;
	final JXDatePicker dateOutPicker;
	private JTextField tFcard_no;

	public String getOutDate() {
		return date_out;
	}

	public int getCard_no() {
		return card_no;
	}

	public String getDueDate() {
		return due_date;
	}

	public boolean getAnswer() {
		return answer;
	}

	public CheckOutDialog(JFrame frame, boolean modal, String title) {
		super(frame, modal);

		myPanel = new JPanel();
		getContentPane().add(myPanel);

		myPanel.add(new JLabel("Card no:"));
		format = new SimpleDateFormat("yyyy-MM-dd");
		tFcard_no = new JTextField(6);
		myPanel.add(tFcard_no);

		myPanel.add(new JLabel(" is checking '"+title+"' out on:"));
		due_date = format.format(new Date(System.currentTimeMillis()+ 86400000 * 14));

		final JLabel duelabel = new JLabel(" and it is due on: "+due_date.toString());
		
		dateOutPicker = new JXDatePicker(new Date());
		dateOutPicker.setFormats(format);
		myPanel.add(dateOutPicker);
		myPanel.add(duelabel);
		dateOutPicker.setToolTipText("Check out Date");
		dateOutPicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				due_date=format.format(new Date(
						dateOutPicker.getDate().getTime() + 86400000 * 14));		
				duelabel.setText(" and it is due on: "+due_date);
			}
		});



		yesButton = new JButton("Yes");
		yesButton.addActionListener(this);
		myPanel.add(yesButton);

		noButton = new JButton("No");
		noButton.addActionListener(this);
		myPanel.add(noButton);

		pack();

		setLocationRelativeTo(frame);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (yesButton == e.getSource()) {
			date_out = format.format(dateOutPicker.getDate());
			due_date = format.format(new Date(dateOutPicker.getDate().getTime() + 86400000 * 14));		
			
			try {
				card_no = Integer.parseInt(tFcard_no.getText());
				answer = true;
				setVisible(false);
			} catch (NumberFormatException nfe) {
				answer = false;
			}
		} else if (noButton == e.getSource()) {
			answer = false;
			setVisible(false);
		}
	}
}