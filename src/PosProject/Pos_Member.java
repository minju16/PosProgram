package PosProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;

public class Pos_Member extends JFrame {
	String [] columns = {"이름", "전화번호", "포인트"};
	String [][] Data;
	DefaultTableModel model = new DefaultTableModel(Data, columns);
	JTable table = new JTable(model);
	JTextField Name = new JTextField(10);
	JTextField Phone = new JTextField(10);
	JLabel la1 = new JLabel("이름");
	JLabel la2 = new JLabel("전화번호");
	JButton addBtn = new JButton("추가");
	JButton cancelBtn = new JButton("삭제");
	Color color = new Color(0xE0E0E0);
	String[] temp_member;		// 회원 임시 배열
	String[] member = new String[1000];  // 최대 1000명까지만
	int i = 0;
	int point = 0;

	class Member extends JPanel {
		Member() {
			setBackground(color);
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			table.setRowHeight(40);
			table.getTableHeader().setFont(new Font("나눔고딕", Font.BOLD, 15));
			table.setFont(new Font("나눔고딕", Font.PLAIN, 14));
			add(new JScrollPane(table));
		}
	}
	
	class memSave extends JPanel {
		memSave() {
			addBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					String inputStr[] = new String[3];
					inputStr[0] = Name.getText();
					inputStr[1] = Phone.getText();
					int point = 0;

					m.addRow(new Object[]{inputStr[0], inputStr[1], point});
					
					Name.setText("");
					Phone.setText("");
					
					String filePath1 = "C:\\javaproject\\member.txt";
					try {
						BufferedWriter bw1 = new BufferedWriter(new FileWriter(filePath1, true));			// 파일에 새로입력
						bw1.write(inputStr[0] +"/"+ inputStr[1] +"/"+ point);
						bw1.newLine();
						bw1.close();
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			});
		}
	}
	
	class memDelete extends JPanel {
		memDelete() {
			cancelBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "회원을 삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.CLOSED_OPTION) {
						return;
					}
					else if(result == JOptionPane.YES_OPTION) {
					DefaultTableModel m1 = (DefaultTableModel)table.getModel();
					int SelectedRowIndex = table.getSelectedRow();
					m1.removeRow(SelectedRowIndex);
					JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.", "회원 삭제", JOptionPane.INFORMATION_MESSAGE);
					
					String filePath1 = "C:\\javaproject\\member.txt";
					String[] temp_member;		// 회원 임시 배열
					String[] member = new String[1000];  // 최대 1000명까지만
					try {
						BufferedReader br1 = new BufferedReader(new FileReader(filePath1));
						String currentLine1;
						int i = 0;
						int index1 = 0;
						while((currentLine1 = br1.readLine()) != null) {
							temp_member = currentLine1.split("/");
							if (temp_member[0] != "") {
								String trimmedLine1 = currentLine1.trim();
								if(index1++ == SelectedRowIndex) {
									continue;
								}
								member[i] = temp_member[0] + "/" + temp_member[1] + "/" + temp_member[2];
							}
							i++;
						}
						
						BufferedWriter bw1 = new BufferedWriter(new FileWriter(filePath1));			// 파일에 새로입력
						for(int j=0;j<member.length;j++) {
							if(member[j] != null) {
								System.out.println(member[j]);
								bw1.write(member[j]);
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
			
		}
	}
	
	public Pos_Member() {
		super("회원 관리");
		setLayout(null);
		setVisible(true);
		setBounds(550, 0, 700, 700);
		getContentPane().setBackground(color);
		
		Member mem = new Member();
		mem.setSize(600, 450);
		mem.setLocation(25, 25);
		add(mem);
		memSave ms = new memSave();
		memDelete md = new memDelete();
		
		Name.setBounds(170, 500, 150, 50);
		Phone.setBounds(170, 550, 150, 50);
		la1.setBounds(100, 500, 70, 50);
		la2.setBounds(100, 550, 70, 50);
		addBtn.setBounds(370, 525, 70, 50);
		cancelBtn.setBounds(470, 525, 70, 50);
		Name.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		Phone.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		addBtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		cancelBtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		la1.setFont(new Font("나눔고딕", Font.BOLD, 15));
		la2.setFont(new Font("나눔고딕", Font.BOLD, 15));
		add(Name);
		add(Phone);
		add(la1);
		add(la2);
		add(addBtn);
		add(cancelBtn);
	
		String filePath1 = "C:\\javaproject\\member.txt";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(filePath1));
			String currentLine1;
			while((currentLine1 = br1.readLine()) != null) {
				temp_member = currentLine1.split("/");
				if (temp_member[0] != "") {
						model.addRow(new Object[] {temp_member[0], temp_member[1], temp_member[2]});
					}
			}
			br1.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			}
	}
}