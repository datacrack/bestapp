package Frame.Panel;

import java.awt.Button;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class stu_table extends JPanel {
	public JTable table;
	/**
	 * Create the panel.
	 */
	public stu_table() {
		setBackground(new Color(255, 255, 240));
		this.setBounds(141, 4, 785, 572);
	
		this.setLayout(null);
		
		
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(new Color(255, 255, 240));
		scrollPane.setBounds(0, 0, 785, 512);
		add(scrollPane);
	}

}
