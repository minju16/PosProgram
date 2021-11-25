package PosProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

import javax.swing.*;
import javax.swing.table.*;

public class Pos_Receipt extends JFrame {
	String [] columns = {"날짜", "시간", "결제 금액"};
	String [][] Data;
	JButton delete = new JButton("환불 처리");
	JButton searchBtn = new JButton("검색");
	DefaultTableModel model2 = new DefaultTableModel(Data, columns);
	JTable table2 = new JTable(model2);
	Date today = new Date();
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
	Color color = new Color(0xE0E0E0);
	JTextField search = new JTextField(20);
	JLabel la = new JLabel("검색 내용");
	
	class Receipt extends JPanel {
		Receipt() {
			setBackground(color);
			DefaultTableModel m2 = (DefaultTableModel)table2.getModel();
			table2.setRowHeight(40);
			table2.getTableHeader().setFont(new Font("나눔고딕", Font.BOLD, 15));
			table2.setFont(new Font("나눔고딕", Font.PLAIN, 14));
			add(new JScrollPane(table2));
			
			String filePath = "C:\\javaproject\\receipt.txt";
			String[] temp_receipt;		// 결제 내역 임시 배열
			String[] receipt = new String[1000];  // 최대 1000개까지만
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(filePath));
			String currentLine1;
			while((currentLine1 = br1.readLine()) != null) {
				temp_receipt = currentLine1.split("/");
				if (temp_receipt[0] != "") {
						m2.addRow(new Object[] {temp_receipt[0], temp_receipt[1], temp_receipt[2]});
					}
				}
			br1.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
			
			delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "환불하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.CLOSED_OPTION) {
						return;
					}
					else if(result == JOptionPane.YES_OPTION) {
						DefaultTableModel m2 = (DefaultTableModel)table2.getModel();
						int SelectedRowIndex = table2.getSelectedRow();
						m2.removeRow(SelectedRowIndex);
						JOptionPane.showMessageDialog(null, "환불 처리되었습니다.", "환불 처리 완료", JOptionPane.INFORMATION_MESSAGE);
						
						String filePath1 = "C:\\javaproject\\receipt.txt";
						String[] temp_receipt;		// 결제 내역 임시 배열
						String[] receipt = new String[1000];   // 최대 1000개까지만
						try {
							BufferedReader br1 = new BufferedReader(new FileReader(filePath1));
							String currentLine1;
							int i = 0;
							int index1 = 0;
							while((currentLine1 = br1.readLine()) != null) {
								temp_receipt = currentLine1.split("/");
								if (temp_receipt[0] != "") {
									String trimmedLine1 = currentLine1.trim();
									if(index1++ == SelectedRowIndex) {
										continue;
									}
									receipt[i] = temp_receipt[0] + "/" + temp_receipt[1] + "/" + temp_receipt[2];
								}
								i++;
							}
							
							BufferedWriter bw1 = new BufferedWriter(new FileWriter(filePath1));		// 파일에 새로 입력
							for(int j=0;j<receipt.length;j++) {
								if(receipt[j] != null) {
									System.out.println(receipt[j]);
									bw1.write(receipt[j]);
									bw1.newLine();
								}
							}
							br1.close();
							bw1.close();
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					}
					else {
						return;
					}
				}
			});
			
			//검색 버튼
			searchBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == searchBtn) {
						if(search.getText() != null) {
							String val = search.getText();
							TableRowSorter<TableModel> trs = new TableRowSorter<>(table2.getModel());
							table2.setRowSorter(trs);
							trs.setRowFilter(RowFilter.regexFilter(val));
							search.setText("");
						}
						else {
							JOptionPane.showMessageDialog(null, "검색 창을 확인해 주세요.", "검색 실패", JOptionPane.ERROR_MESSAGE);
						}	
					}
				}
			});
		}
	}

	public Pos_Receipt() {
		super("결제 내역");
		setLayout(null);
		setVisible(true);
		setBounds(550, 0, 660, 610);
		getContentPane().setBackground(color);
		
		Receipt rec = new Receipt();
		rec.setSize(470, 450);
		rec.setLocation(70, 25);
		rec.setVisible(true);
		add(rec);
		
		delete.setSize(90, 55);
		delete.setLocation(500, 480);
		delete.setFont(new Font("나눔고딕", Font.BOLD, 14));
		delete.setVisible(true);
		add(delete);
		
		la.setSize(70, 40);
		la.setLocation(70, 485);
		la.setFont(new Font("나눔고딕", Font.BOLD, 15));
		la.setVisible(true);
		add(la);
		
		search.setSize(230, 40);
		search.setLocation(140, 485);
		search.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		search.setVisible(true);
		add(search);
		
		searchBtn.setSize(90, 55);
		searchBtn.setLocation(385, 480);
		searchBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		searchBtn.setVisible(true);
		add(searchBtn);
		
	}
}