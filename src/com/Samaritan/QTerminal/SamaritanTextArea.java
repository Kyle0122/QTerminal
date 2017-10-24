package com.Samaritan.QTerminal;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D;


public class SamaritanTextArea extends JComponent {
	
	Dimension CurrentSize;
	TriAngDrawer TADrawer;
	UnderLineDrawer ULDrawer;
	CenterTextDrawer TTDrawer;
	SamaritanKeyListener SKLstener;
	boolean isFocused = false;
	
	Color SavedTempColor;
	
	SamaritanTextArea(){
		
		this.CurrentSize = this.getPreferredSize();
		this.TADrawer = new TriAngDrawer();
		this.ULDrawer = new UnderLineDrawer();
		this.TTDrawer = new CenterTextDrawer();
		this.SKLstener = new SamaritanKeyListener(this.TTDrawer);
		
		this.setFocusable(true);
		
		this.addKeyListener(SKLstener);
		
		this.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				isFocused = true;
				System.out.println("GotFocus!");
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				isFocused = false;
				System.out.println("LostFocus!");
			}
		});
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(0,100);
	}
	
	@Override
	public void paintComponent(Graphics G) {
		
		this.SavedTempColor = G.getColor();
		
		this.TADrawer.paint(G);
		this.ULDrawer.paint(G);
		this.TTDrawer.paint(G);
		
		G.setColor(this.SavedTempColor);
	}

	public void addActionListener(ActionListener Lis){
		this.SKLstener.addActionListener(Lis);
	}
	
	@Override
	public void setBounds(int x, int y, int W, int H) {
		this.CurrentSize = new Dimension(W,H);
		this.TADrawer.setSize(CurrentSize);
		
		super.setBounds(x, y, W, H);
		
	}
	
	private class TriAngDrawer{
		
		int[] TriAngXP;
		int[] TriAngYP;
		int PaintLoop;
		
		TriAngDrawer(){
			PaintLoop = 25;
			TriAngXP = new int[3];
			TriAngYP = new int[3];
			
		}
		
		public void paint(Graphics G){
			
			if(isFocused){
				if(PaintLoop ==0){PaintLoop = 25;}
				PaintLoop--;
			}else {
				PaintLoop = 10;
			}
			
			int temp = (int)(Math.abs(PaintLoop-10));
			
			if(temp>10){
				temp = 10;
			}
			
			G.setColor(new Color(250+temp/2,90+16*temp,90+16*temp));
			G.fillPolygon(this.TriAngXP,this.TriAngYP,3);
			
		}
		
		public void setSize(Dimension Dim){
			
			TriAngXP[0] = Dim.width/2;
			TriAngXP[1] = Dim.width/2-30;
			TriAngXP[2] = Dim.width/2+30;
			TriAngYP[0] = Dim.height/2+10;
			TriAngYP[1] = Dim.height/2+40;
			TriAngYP[2] = Dim.height/2+40;
		}
	}
	
	private class UnderLineDrawer{
		int Length;
		
		UnderLineDrawer(){
			Length = 0;
		}
		
		public void IncreaseBy(int a){
			this.Length +=a;
		}
		
		public void paint(Graphics G){
			
			G.setColor(Color.BLACK);
			if (Length <40){Length = 40;}
			int tempx = (CurrentSize.width-Length)/2;
			int tempy = CurrentSize.height/2-2;
			
			G.fillRect(tempx, tempy, Length, 4);
		}
		
	}
	
	private class CenterTextDrawer implements TextDrawer{
		
		String Text = "";
		
		@Override
		public void paint(Graphics G){
			G.setFont(new Font("SansSerif", Font.BOLD, 36));
			

			FontMetrics FM = G.getFontMetrics();
			Rectangle2D Range = FM.getStringBounds(Text, G);
			
			G.drawString(Text, (int)(CurrentSize.width-Range.getWidth())/2, CurrentSize.height/2-10);
			
			int temp = (int)(Range.getWidth())-ULDrawer.Length;
			if(temp >=10){
				ULDrawer.IncreaseBy(10);
			}else if(temp <=-10){
				ULDrawer.IncreaseBy(-10);
			}else if(temp >=6){
				ULDrawer.IncreaseBy(6);
			}else if(temp <=-6){
				ULDrawer.IncreaseBy(-6);
			}else if(temp >=1){
				ULDrawer.IncreaseBy(1);
			}else if(temp <=-1){
				ULDrawer.IncreaseBy(-1);
			}
		}

		@Override
		public void setText(String A) {
			Text = A;
		}

		@Override
		public void append(String A) {
			Text = Text+A;
		}
		
		@Override
		public void append(char A) {
			Text = Text+A;
		}
		
		@Override
		public String getText() {
			return Text;
		}

		@Override
		public void Delete() {
			if(Text.length()>0){
				Text = Text.substring(0, Text.length()-1);
			}
		}

		@Override
		public int getLength() {
			return Text.length();
		}

		
		
	}
}
