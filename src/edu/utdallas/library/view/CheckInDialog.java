package edu.utdallas.library.view;

import javax.swing.JDialog;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckInDialog extends JDialog implements ActionListener {
	private JPanel myPanel = null;
	private JButton yesButton = null;
	private JButton noButton = null;
	private boolean answer = false;
	private java.util.Date date = null;
	final SimpleDateFormat format;
	final JXDatePicker picker;

	public java.util.Date getDate() {
		return date;
	}

	public boolean getAnswer() {
		return answer;
	}

	public CheckInDialog(JFrame frame, boolean modal, String bookTitle,
			String borrowerName) {
		super(frame, modal);

		myPanel = new JPanel();
		getContentPane().add(myPanel);

		myPanel.add(new JLabel("" + bookTitle + " was returned by "
				+ borrowerName + " on:"));

		format = new SimpleDateFormat("MM-dd-yyyy");
		picker = new JXDatePicker(new Date());
		picker.setFormats(format);
		myPanel.add(picker);

		myPanel.setToolTipText("Select the check in date and press yes");
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
			date = picker.getDate();
			answer = true;
			setVisible(false);
		} else if (noButton == e.getSource()) {
			date = picker.getDate();
			answer = false;
			setVisible(false);
		}
	}
}