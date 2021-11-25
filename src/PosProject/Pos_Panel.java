package  PosProject;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;
import javax.swing.table.*;

public class Pos_Panel extends JPanel {
	JButton[] MenuBtn = new JButton[15];
	String[] menu = {
			"양키캔들", "우드윅", "아큐스", "서카홈",
			"에코아", "하트앤홈", "네스트", "코코캔들",
			"오피시나", "알로라", "마이센소", "차량용",
			"소품", "리필 50ml", "리필 100ml"};
	int[] price = {
			19000, 19000, 45000, 18000,
			36000, 19000, 25000, 15000,
			75000, 39000, 35000, 12000,
			3000, 12000, 22000};
	JButton[] CalBtn = new JButton[16];
	String[] CalStr = {
				"7", "8", "9", "AC",
				"4", "5", "6", "CE",
				"1", "2", "3", "계산",
				"0", "00", "000", "Reset"};
	JTextField tf1 = new JTextField(30);
	JTextField tf2 = new JTextField(30);
	JTextField tf3 = new JTextField(30);
	JTextField tf4 = new JTextField(10);
	JTextField poTf = new JTextField(30);
	JButton[] StrBtn = new JButton[4];
	JButton[] optionBtn = new JButton[3];
	JButton pointBtn = new JButton("포인트 사용");
	String[] option = {"회원 관리", "음성 서비스", "결제 내역"};
	String[] Str = {"선택취소", "전체취소", "카드결제", "현금결제"};
	String [] columns = {"메뉴", "수량", "가격"};
	String [][] Data;
	int count = 1;
	DefaultTableModel model = new DefaultTableModel(Data, columns);
	JTable table = new JTable(model);
	Color color = new Color(0xE0E0E0);
	
	class Screen extends JPanel {
		Screen() {
			setBackground(color);
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			table.setRowHeight(40);
			table.getTableHeader().setFont(new Font("나눔고딕", Font.BOLD, 15));
			table.setFont(new Font("나눔고딕", Font.PLAIN, 14));
			add(new JScrollPane(table));
		}
	}
	
	class MenuBtn extends JPanel {
		MenuBtn() {
			setLayout(new GridLayout(6, 3, 3, 3));
			setBackground(color);
			for(int i=0; i<MenuBtn.length; i++) {
				MenuBtn[i] = new JButton(menu[i]);
				MenuBtn[i].setFont(new Font("나눔고딕", Font.PLAIN, 17));
				add(MenuBtn[i]);
			}
		}
	}
	
	class StrBtn extends JPanel {
		StrBtn() {
			setBackground(color);
			setLayout(new GridLayout(1, 4, 3, 3));
			
			for(int i=0; i<Str.length; i++) {
				StrBtn[i] = new JButton(Str[i]);
				StrBtn[i].setFont(new Font("나눔고딕", Font.BOLD, 17));
				add(StrBtn[i]);
			}
		}
	}
	
	class optionBtn extends JPanel {
		optionBtn() {
			setBackground(color);
			setLayout(new GridLayout(1, 3, 3, 3));
			
			for(int i=0; i<option.length; i++) {
				optionBtn[i] = new JButton(option[i]);
				optionBtn[i].setFont(new Font("나눔고딕", Font.BOLD, 18));
				add(optionBtn[i]);
			}
		}
	}
	
	class Calculator extends JPanel {
		Calculator() {
			setBackground(color);
			setLayout(new GridLayout(4, 4, 3, 3));
			
			for(int i=0; i<CalStr.length; i++) {
				CalBtn[i] = new JButton(CalStr[i]);
				CalBtn[i].setFont(new Font("나눔고딕", Font.BOLD, 20));
				add(CalBtn[i]);
					
				// 버튼 색 설정
				if(i == 3 || i == 7) {
					CalBtn[i].setForeground(Color.RED);
				}
				else if(i == 11) {
					CalBtn[i].setForeground(Color.BLUE);
				}
				else if(i == 15) {
					CalBtn[i].setForeground(Color.BLACK);
					CalBtn[i].setFont(new Font("나눔고딕", Font.BOLD, 16));
				}
				else {
					CalBtn[i].setForeground(Color.GRAY);
				}
			}
		}
	}

	public Pos_Panel() {
		setLayout(null);
		setBackground(color);
		MenuBtn mbtn = new MenuBtn();
		StrBtn sbtn = new StrBtn();
		Screen sc = new Screen();
		optionBtn opbtn = new optionBtn();
		Calculator cal = new Calculator();
		DigitalClock dc = new DigitalClock();
		
		// 금액란
		JLabel la1 = new JLabel("결제 금액");
		JLabel la2 = new JLabel("받은 금액");
		JLabel la3 = new JLabel("거스름돈");
		la1.setBounds(50, 470, 90, 50);
		la1.setFont(new Font("나눔고딕", Font.BOLD, 20));
		la2.setBounds(50, 540, 90, 50);
		la2.setFont(new Font("나눔고딕", Font.PLAIN, 20));
		la3.setBounds(50, 610, 90, 50);
		la3.setFont(new Font("나눔고딕", Font.PLAIN, 20));
		add(la1);
		add(la2);
		add(la3);
		tf1.setBounds(150, 470, 350, 70);
		tf2.setBounds(150, 540, 350, 70);
		tf3.setBounds(150, 610, 350, 70);
		tf1.setEditable(false);
		tf2.setEditable(false);
		tf3.setEditable(false); // 결제 금액 수정 불가
		add(tf1);
		add(tf2);
		add(tf3);
		
		sc.setBounds(25, 25, 500, 530);
		add(sc);
		mbtn.setBounds(550, 25, 470, 500);
		add(mbtn);
		sbtn.setBounds(550, 835, 470, 100);
		add(sbtn);
		opbtn.setBounds(50, 835, 450, 100);
		add(opbtn);		
		cal.setBounds(550, 530, 470, 300);
		add(cal);
		dc.setBounds(50, 740, 450, 50);
		add(dc);
		pointBtn.setBounds(50, 790, 110, 40);
		pointBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		add(pointBtn);
		poTf.setBounds(170, 790, 150, 40);
		poTf.setFont(new Font("나눔고딕", Font.BOLD, 14));
		add(poTf);
		
		// 메뉴 추가
		for(int i=0; i<MenuBtn.length; i++) {
			final int index = i;
			MenuBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.addRow(new Object[]{menu[index], count, price[index]});
		
					int rowCount = table.getRowCount();
					int sum = 0;
					for(int i=0; i<rowCount; i++) {
						sum += (int)table.getValueAt(i, 2);
					}
					tf1.setText(String.valueOf(sum));
					tf1.setFont(new Font("나눔고딕", Font.BOLD, 20));
				}
			});
		}
		
			// 선택 취소
		StrBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				
				m.removeRow(table.getSelectedRow());
				
				int rowCount = table.getRowCount();
				int select = table.getSelectedRowCount();
				int sum1 = 0;
				int sum2 = 0;
				for(int i=0; i<rowCount; i++) {
					sum1 += (int)table.getValueAt(i, 2);
				}
				for (int i=0; i<select; i++) {
					sum2 += (int)table.getValueAt(i, 2);
				}
				tf1.setText(String.valueOf(sum1 - sum2));
				tf1.setFont(new Font("나눔고딕", Font.BOLD, 20));
			}
		});
		
		
		// 전체 취소
		StrBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				
				m.setRowCount(0);
				tf1.setText(String.valueOf(""));
				tf2.setText(String.valueOf(""));
				tf3.setText(String.valueOf(""));
			}
		});
		
		// 카드 결제
		StrBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				int rowCount = table.getRowCount();
				int sum = 0;
				for(int i=0; i<rowCount; i++) {
					sum += (int)table.getValueAt(i, 2);
				}
				
				tf1.setText(String.valueOf(sum));
				tf2.setText(String.valueOf(sum));
				tf3.setText(String.valueOf("0"));
				tf1.setFont(new Font("나눔고딕", Font.BOLD, 20));
				tf2.setFont(new Font("나눔고딕", Font.PLAIN, 20));
				tf3.setFont(new Font("나눔고딕", Font.PLAIN, 20));
				
				if(action.equals("카드결제")) {
					if(sum > 0) {
						int result = JOptionPane.showConfirmDialog(null, "포인트를 적립하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.CLOSED_OPTION) {
							return;
						}
						else if(result == JOptionPane.YES_OPTION) {
							String name = JOptionPane.showInputDialog("회원 이름을 입력해 주세요.");
							if(name != null) {
								String phone = JOptionPane.showInputDialog("회원 전화번호를 입력해 주세요.");
								if(phone != null) {
									JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.", "결제 완료", JOptionPane.INFORMATION_MESSAGE);
									DefaultTableModel m = (DefaultTableModel)table.getModel();
									m.setRowCount(0);
									tf1.setText(String.valueOf(""));
									tf2.setText(String.valueOf(""));
									tf3.setText(String.valueOf(""));
									poTf.setText("");
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
									String filePath = "C:\\javaproject\\member.txt";
									String[] temp_member;		// 회원 임시 배열
									String[] member = new String[1000];  // 최대 1000명까지만
									try {
										BufferedReader br1 = new BufferedReader(new FileReader(filePath));
										String currentLine1;
										int point = 0;
										int i = 0;
										while((currentLine1 = br1.readLine()) != null) {
											temp_member = currentLine1.split("/");
											if (temp_member[0] != "") {
												if(name.equals(temp_member[0]) && phone.equals(temp_member[1])) {  // 이름하고 번호 둘 다 같을 때만 적립
													point = (int) (sum * 0.05) + Integer.parseInt(temp_member[2]);  // 포인트 5% 적립, 누적
												}else {
													point = Integer.parseInt(temp_member[2]);						// 이름이나 번호 둘 중 하나라도 다르면 기존 포인트 유지
												}
												member[i] = temp_member[0] + "/" + temp_member[1] + "/" + point;
											}
											i++;
										}
										
										BufferedWriter bw1 = new BufferedWriter(new FileWriter(filePath));			// 파일에 새로 입력
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
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////						
										
									String filePath1 = "C:\\javaproject\\receipt.txt";
									
									try {
										Date today = Calendar.getInstance().getTime();
										SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
										String reportDate = date.format(today);
										String dateToPrintToFile = reportDate;
										
										BufferedWriter bf1 = new BufferedWriter(new FileWriter(filePath1, true));
										
										bf1.write(dateToPrintToFile + "/" + sum);
										bf1.newLine();
										bf1.close();
									} catch (IOException ex) {
										ex.printStackTrace();
										}
								}
								else {
									JOptionPane.showMessageDialog(null, "전화번호를 입력해 주세요.", "Error!", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", "Error!", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						else if(result == JOptionPane.NO_OPTION){
							JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.", "결제 완료", JOptionPane.INFORMATION_MESSAGE);
							DefaultTableModel m = (DefaultTableModel)table.getModel();
							m.setRowCount(0);
							tf1.setText(String.valueOf(""));
							tf2.setText(String.valueOf(""));
							tf3.setText(String.valueOf(""));
							poTf.setText("");
							
							String filePath1 = "C:\\javaproject\\receipt.txt";
							
							try {
								Date today = Calendar.getInstance().getTime();
								SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
								String reportDate = date.format(today);
								String dateToPrintToFile = reportDate;
								
								BufferedWriter bf1 = new BufferedWriter(new FileWriter(filePath1, true));
								
								bf1.write(dateToPrintToFile + "/" + sum);
								bf1.newLine();
								bf1.close();
							} catch (IOException ex) {
								ex.printStackTrace();
								}
						}
					}
					else if (sum == 0) {
						JOptionPane.showMessageDialog(null, "결제 내역을 확인해 주세요.", "결제 실패", JOptionPane.ERROR_MESSAGE);
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.setRowCount(0);
						tf1.setText(String.valueOf(""));
						tf2.setText(String.valueOf(""));
						tf3.setText(String.valueOf(""));
						poTf.setText("");
					}
				}
			}
		});
		
		// 현금 결제
		StrBtn[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				int rowCount = table.getRowCount();
				int sum = 0;
				for(int i=0; i<rowCount; i++) {
					sum += (int)table.getValueAt(i, 2);
				}
				tf1.setText(String.valueOf(sum));
				tf1.setFont(new Font("나눔고딕", Font.BOLD, 20));
				tf2.setFont(new Font("나눔고딕", Font.PLAIN, 20));
				tf3.setFont(new Font("나눔고딕", Font.PLAIN, 20));
//				/////////
//				if(poTf.getText() != null) {  // 포인트를 쓰는 경우
//					int point1 = Integer.parseInt(poTf.getText());
//					if(tf2.getText().length() != 0) {     // 만약 받은 금액에 숫자가 있을 경우
//						int money = Integer.parseInt(tf2.getText());
//						
//						tf1.setText(String.valueOf(sum - point1));
//						tf3.setText(String.valueOf(money - sum - point1));  // 받은 금액 - 결제 금액 - 포인트
//						tf1.setFont(new Font("나눔고딕", Font.BOLD, 20));
//						tf2.setFont(new Font("나눔고딕", Font.PLAIN, 20));
//						tf3.setFont(new Font("나눔고딕", Font.PLAIN, 20));
//					}
//					else {   // 받은 금액에 숫자가 없는 경우
//					tf1.setText(String.valueOf(sum - point1));
//					tf2.setText(String.valueOf(""));
//					tf3.setText(String.valueOf(""));
//					tf1.setFont(new Font("나눔고딕", Font.BOLD, 20));
//					tf2.setFont(new Font("나눔고딕", Font.PLAIN, 20));
//					tf3.setFont(new Font("나눔고딕", Font.PLAIN, 20));
//					}
//				}
//				else {  // 포인트를 쓰지 않는 경우
//					tf1.setText(String.valueOf(sum));
//					tf2.setText(String.valueOf(""));
//					tf3.setText(String.valueOf(""));
//					tf1.setFont(new Font("나눔고딕", Font.BOLD, 20));
//					tf2.setFont(new Font("나눔고딕", Font.PLAIN, 20));
//					tf3.setFont(new Font("나눔고딕", Font.PLAIN, 20));
//				}
		
			 	if(action.equals("현금결제")) {
					if(sum > 0) {
						int result = JOptionPane.showConfirmDialog(null, "포인트를 적립하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.CLOSED_OPTION) {
							return;
						}
						else if(result == JOptionPane.YES_OPTION) {
							String name = JOptionPane.showInputDialog("회원 이름을 입력해 주세요.");
							if(name != null) {
								String phone = JOptionPane.showInputDialog("회원 전화번호를 입력해 주세요.");
								if(phone != null) {
										JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.", "결제 완료", JOptionPane.INFORMATION_MESSAGE);
										DefaultTableModel m = (DefaultTableModel)table.getModel();
										m.setRowCount(0);
										tf1.setText(String.valueOf(""));
										tf2.setText(String.valueOf(""));
										tf3.setText(String.valueOf(""));
										poTf.setText("");

									String filePath = "C:\\javaproject\\member.txt";
									String[] temp_member;		// 회원 임시 배열
									String[] member = new String[1000];  // 최대 1000명까지만
								try {
									BufferedReader br1 = new BufferedReader(new FileReader(filePath));
									String currentLine1;
									int point = 0;
									int i = 0;
									while((currentLine1 = br1.readLine()) != null) {
										temp_member = currentLine1.split("/");
										if (temp_member[0] != "") {
											if(name.equals(temp_member[0]) && phone.equals(temp_member[1])) {  // 이름하고 번호 둘 다 같을 때만 적립
												point = (int) (sum * 0.05) + Integer.parseInt(temp_member[2]);  // 포인트 5% 적립, 누적
											}else {
												point = Integer.parseInt(temp_member[2]);						// 이름이나 번호 둘 중 하나라도 다르면 기존 포인트 유지
											}
											member[i] = temp_member[0] + "/" + temp_member[1] + "/" + point;
										}
										i++;
									}

									BufferedWriter bw1 = new BufferedWriter(new FileWriter(filePath));			// 파일에 새로 입력
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

								String filePath1 = "C:\\javaproject\\receipt.txt";
								
								try {
									Date today = Calendar.getInstance().getTime();
									SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
									String reportDate = date.format(today);
									String dateToPrintToFile = reportDate;
									
									BufferedWriter bf1 = new BufferedWriter(new FileWriter(filePath1, true));
									
									bf1.write(dateToPrintToFile + "/" + sum);
									bf1.newLine();
									bf1.close();
					} catch (IOException ex) {
						ex.printStackTrace();
						}
					}
							else {
								JOptionPane.showMessageDialog(null, "전화번호를 입력해 주세요.", "Error!", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", "Error!", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					else if(result == JOptionPane.NO_OPTION){
						JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.", "결제 완료", JOptionPane.INFORMATION_MESSAGE);
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.setRowCount(0);
						tf1.setText(String.valueOf(""));
						tf2.setText(String.valueOf(""));
						tf3.setText(String.valueOf(""));
						poTf.setText("");
						
						String filePath1 = "C:\\javaproject\\receipt.txt";
						
						try {
							Date today = Calendar.getInstance().getTime();
							SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
							String reportDate = date.format(today);
							String dateToPrintToFile = reportDate;
							
							BufferedWriter bf1 = new BufferedWriter(new FileWriter(filePath1, true));
							
							bf1.write(dateToPrintToFile + "/" + sum);
							bf1.newLine();
							bf1.close();
						} catch (IOException ex) {
							ex.printStackTrace();
							}
						}
					}
				else if (sum == 0) {
					JOptionPane.showMessageDialog(null, "결제 내역을 확인해 주세요.", "결제 실패", JOptionPane.ERROR_MESSAGE);
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.setRowCount(0);
					tf1.setText(String.valueOf(""));
					tf2.setText(String.valueOf(""));
					tf3.setText(String.valueOf(""));
					poTf.setText("");
					}
			 	}
			}	
		});
		
		// 숫자 버튼
		for(int i=0; i<CalBtn.length; i++) {
			CalBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton calbtn = (JButton)e.getSource();
					if(calbtn.getActionCommand().equals("CE")) {
						if(tf2.getText().length() > 0)
						tf2.setText(tf2.getText().substring(0, tf2.getText().length()-1));
						return;
					}
					else if(calbtn.getActionCommand().equals("AC")) {
						tf2.setText("");
						tf3.setText("");
						return;
					}
					else if(calbtn.getActionCommand().equals("계산")) {
						int money = Integer.parseInt(tf2.getText());
						int sum = Integer.parseInt(tf1.getText());
						
						tf1.setText(String.valueOf(sum));
						tf3.setText(String.valueOf(money - sum));
						tf1.setFont(new Font("나눔고딕", Font.BOLD, 20));
						tf2.setFont(new Font("나눔고딕", Font.PLAIN, 20));
						tf3.setFont(new Font("나눔고딕", Font.PLAIN, 20));
						return;
					}
					else if(calbtn.getActionCommand().equals("Reset")) {						
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.setRowCount(0);
						tf1.setText(String.valueOf(""));
						tf2.setText(String.valueOf(""));
						tf3.setText(String.valueOf(""));
						return;
					}
					
					for(int i=0; i<CalBtn.length; i++)
						{
							if(e.getSource() == CalBtn[i]) {
								tf2.setText(tf2.getText() + CalBtn[i].getText());
								tf2.setFont(new Font("나눔고딕", Font.PLAIN, 20));
								return;
							}
						}
				}
			});
		}
		
		// 포인트 사용
		pointBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "포인트를 사용하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION) {
					return;
				}
				else if(result == JOptionPane.YES_OPTION) {
					String name = JOptionPane.showInputDialog("회원 이름을 입력해 주세요.");
					if(name != null) {
						String phone = JOptionPane.showInputDialog("회원 전화번호를 입력해 주세요.");
						if(phone != null) {
							String pointTf = JOptionPane.showInputDialog("사용할 포인트를 입력해 주세요.");
							if(pointTf != null) {
								JOptionPane.showMessageDialog(null, "포인트가 사용되었습니다.", "포인트 사용", JOptionPane.INFORMATION_MESSAGE);
								int rowCount = table.getRowCount();
								int sum = 0;
								for(int i=0; i<rowCount; i++) {
									sum += (int)table.getValueAt(i, 2);
								}
								int point1 = Integer.parseInt(pointTf);
								
								tf1.setText(String.valueOf(sum - point1));
								tf2.setText(String.valueOf(""));
								tf3.setText(String.valueOf(""));
								poTf.setText(String.valueOf(pointTf));
								tf1.setFont(new Font("나눔고딕", Font.BOLD, 20));
								tf2.setFont(new Font("나눔고딕", Font.PLAIN, 20));
								tf3.setFont(new Font("나눔고딕", Font.PLAIN, 20));
								poTf.setFont(new Font("나눔고딕", Font.PLAIN, 15));

							String filePath = "C:\\javaproject\\member.txt";
							String[] temp_member;		// 회원 임시 배열
							String[] member = new String[1000];  // 최대 1000명까지만
						try {
							BufferedReader br1 = new BufferedReader(new FileReader(filePath));
							String currentLine1;
							int point = 0;
							int i = 0;
							while((currentLine1 = br1.readLine()) != null) {
								temp_member = currentLine1.split("/");
								if (temp_member[0] != "") {
									if(name.equals(temp_member[0]) && phone.equals(temp_member[1])) {  // 이름하고 번호 둘 다 같을 때만 적립
										point = Integer.parseInt(temp_member[2]) - Integer.parseInt(pointTf);  // 포인트 사용
									}else {
										point = Integer.parseInt(temp_member[2]);						// 이름이나 번호 둘 중 하나라도 다르면 기존 포인트 유지
									}
									member[i] = temp_member[0] + "/" + temp_member[1] + "/" + point;
								}
								i++;
							}

							BufferedWriter bw1 = new BufferedWriter(new FileWriter(filePath));			// 파일에 새로 입력
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
								JOptionPane.showMessageDialog(null, "사용할 포인트를 입력해 주세요.", "Error!", JOptionPane.ERROR_MESSAGE);
								return;
							}
			}
					else {
						JOptionPane.showMessageDialog(null, "전화번호를 입력해 주세요.", "Error!", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else if(result == JOptionPane.NO_OPTION){
				return;
			}
			}
		});
		
		// 회원 관리 Frame 열기
		optionBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add(new Pos_Member());
			}
		});
		
		// 음성 서비스 Frame 열기
		optionBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add(new Pos_Voice());
			}
		});
		
		// 결제 내역 Frame 열기
		optionBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add(new Pos_Receipt());
			}
		});
	}
	
	public class DigitalClock extends JPanel implements Runnable {
	    private Thread thread;
	    private JLabel label;
	    private SimpleDateFormat sf;

	    public DigitalClock() {
	        setLayout(new FlowLayout());
	        
	        label = new JLabel();
	        label.setFont(new Font("나눔고딕", Font.BOLD, 20));
	        sf = new SimpleDateFormat("현재 날짜 yyyy-MM-dd      현재 시간 HH:mm:ss");
	        if (thread == null) {
	            thread = new Thread(this);
	            thread.start();
	        }
	        add(label);
	        setBackground(color);
	        setVisible(true);
	    }
	    public void run() {
	        while (true) {
	           label.setText(sf.format(new Date()));
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e){}
	        }
	    }
	}
}