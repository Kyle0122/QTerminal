package com.Samaritan.QTerminal;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class QTerminal extends JFrame {
	JPanel MainPanel;
	SamaritanTextArea SText;
	
	public QTerminal(String title) throws HeadlessException {
		super(title);
		this.MainPanel = new JPanel(new SamaritanLayout());
		this.SText = new SamaritanTextArea();
	}
	
	protected void Lunch(){
		
		this.setSize(800, 600);
		this.MainPanel.setBackground(Color.white);
		this.setContentPane(MainPanel);
		
		this.setMinimumSize(this.getLayout().minimumLayoutSize(this.MainPanel));
		
		new Timer(40, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
			
		}).start();
		
		this.add(SamaritanLayout.CENTER,this.SText);
		this.SText.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					String Cmd = e.getActionCommand();
					System.out.println(Cmd);
					if(e.getID()!=-1){
						Tools.ExecCmd(Cmd);
					}else{
						System.exit(0);
					}
					
			}
		});
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new QTerminal("QTerminal").Lunch();
	}

	@Override
	public void update(Graphics g) {
		Image BufferImage = createImage(this.getSize().width,this.getSize().height);
		Graphics Temp = BufferImage.getGraphics();
		super.update(Temp);
		g.drawImage(BufferImage, 0,0, this);
	}

}
