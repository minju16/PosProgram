package PosProject;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.sound.sampled.*;

public class Pos_Voice extends JFrame {
	Color color = new Color(0xE0E0E0);
	JRadioButton[] rb = new JRadioButton[3];
	ButtonGroup rbGroup = new ButtonGroup();
	JButton Bt1 = new JButton("선물 포장");
	JButton Bt2 = new JButton("회원 등록");
	String[] str = {"영어", "일본어", "중국어"};
	File file;
	Clip clip;
	AudioInputStream stream;
	AudioFormat format;
	DataLine.Info info;
	
	Pos_Voice() {
		setTitle("음성 서비스");
		setLayout(null);
		setBounds(650, 150, 480, 540);
		getContentPane().setBackground(color);
		setVisible(true);
		
		Bt1.setBounds(265, 0, 200, 250);
		Bt1.setFont(new Font("나눔고딕", Font.BOLD, 17));
		Bt1.setVisible(true);
		add(Bt1);
		Bt2.setBounds(265, 250, 200, 250);
		Bt2.setFont(new Font("나눔고딕", Font.BOLD, 17));
		Bt2.setVisible(true);
		add(Bt2);
		
		for(int i=0; i<rb.length; i++) {
			rb[i] = new JRadioButton(str[i]);
			rb[i].setFont(new Font("나눔고딕", Font.BOLD, 17));
			rb[i].setBackground(color);
			add(rb[i]);
			rbGroup.add(rb[i]);
		}
		rb[0].setBounds(80, 80, 90, 60);
		rb[1].setBounds(80, 220, 90, 60);
		rb[2].setBounds(80, 360, 90, 60);
		
		Bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rb[0].isSelected()) {
					file = new File("audio/giftEng.wav");
					try {
						stream = AudioSystem.getAudioInputStream(file);
						format = stream.getFormat();
						info = new DataLine.Info(Clip.class, format);
						clip = (Clip)AudioSystem.getLine(info);
						clip.open(stream);
						clip.start();
					} catch(Exception ex) {
						System.out.println("err : " + ex);
					}
				}
				else if(rb[1].isSelected()) {
					file = new File("audio/giftJap.wav");
					try {
						stream = AudioSystem.getAudioInputStream(file);
						format = stream.getFormat();
						info = new DataLine.Info(Clip.class, format);
						clip = (Clip)AudioSystem.getLine(info);
						clip.open(stream);
						clip.start();
					} catch(Exception ex) {
						System.out.println("err : " + ex);
					}
				}
				else if(rb[2].isSelected()) {
					file = new File("audio/giftChi.wav");
					try {
						stream = AudioSystem.getAudioInputStream(file);
						format = stream.getFormat();
						info = new DataLine.Info(Clip.class, format);
						clip = (Clip)AudioSystem.getLine(info);
						clip.open(stream);
						clip.start();
					} catch(Exception ex) {
						System.out.println("err : " + ex);
					}
				}
			}
		});
		
		Bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rb[0].isSelected()) {
					file = new File("audio/memberEng.wav");
					try {
						stream = AudioSystem.getAudioInputStream(file);
						format = stream.getFormat();
						info = new DataLine.Info(Clip.class, format);
						clip = (Clip)AudioSystem.getLine(info);
						clip.open(stream);
						clip.start();
					} catch(Exception ex) {
						System.out.println("err : " + ex);
					}
				}
				else if(rb[1].isSelected()) {
					file = new File("audio/memberJap.wav");
					try {
						stream = AudioSystem.getAudioInputStream(file);
						format = stream.getFormat();
						info = new DataLine.Info(Clip.class, format);
						clip = (Clip)AudioSystem.getLine(info);
						clip.open(stream);
						clip.start();
					} catch(Exception ex) {
						System.out.println("err : " + ex);
					}
					
				}
				else if(rb[2].isSelected()) {
					file = new File("audio/memberChi.wav");
					try {
						stream = AudioSystem.getAudioInputStream(file);
						format = stream.getFormat();
						info = new DataLine.Info(Clip.class, format);
						clip = (Clip)AudioSystem.getLine(info);
						clip.open(stream);
						clip.start();
					} catch(Exception ex) {
						System.out.println("err : " + ex);
					}
				}
			}
		});
	}
}