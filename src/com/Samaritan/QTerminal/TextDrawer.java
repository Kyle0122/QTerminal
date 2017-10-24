package com.Samaritan.QTerminal;

import java.awt.Graphics;

public interface TextDrawer {
	public void paint(Graphics G);
	
	public void setText(String A);
	
	public void append(String  a);
	public void append(char A);
	
	public void Delete();
	
	public String getText();
	
	public int getLength();
}
