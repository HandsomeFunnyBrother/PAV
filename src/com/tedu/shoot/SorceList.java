package com.tedu.shoot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.tedu.Dao.*;

public class SorceList extends JDialog {

	private JTable table;
	private Vector<String> tableColumnV;
	private Vector tableValueV;
	private DefaultTableModel tableModel;
	protected JButton cancle;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		try {
			SorceList Indialog = new SorceList();
			Indialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SorceList(){
		super();
		
		setModal(true);
		setTitle("���а�");
		setBounds(0, 0, 300,200);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  
	    setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
	    setResizable(false);// ���ô��ڲ����Ըı��С
	    
	    JPanel npanel = new JPanel();
	    this.add(npanel,BorderLayout.NORTH);
	    
	    final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	    tableColumnV = new Vector<String>();
	    tableColumnV.add("����");
	    tableColumnV.add("���");
		tableColumnV.add("�ɼ�");
		
		tableValueV = new Vector();
		
		
		JDBC.closeConnection();
		tableModel = new DefaultTableModel(tableValueV, tableColumnV);
		
		table = new MTable(tableModel);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		scrollPane.setViewportView(table);
		cancle = new JButton("����");
		cancle.addActionListener(new CancleButtonActionlistenner());
		JPanel spanel = new JPanel();
		spanel.add(cancle);
		this.add(spanel,BorderLayout.SOUTH);
		getTable();
	}
	class CancleButtonActionlistenner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dispose();
		}
		
	}
	public void getTable(){
		tableValueV.addAll(Dao.getInstance().sScoreList());
		tableModel.setDataVector(tableValueV, tableColumnV);
	}
}
