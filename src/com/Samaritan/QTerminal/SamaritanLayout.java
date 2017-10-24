package com.Samaritan.QTerminal;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;


public class SamaritanLayout implements LayoutManager {
	
	Component Center;
	public static final String CENTER = "Center";
	
	
	public SamaritanLayout() {
	}
	
	public SamaritanLayout(Component C) {
		this.Center = C;
	}
	
	@Override
	public void addLayoutComponent(String name, Component comp) {
		if(name.equals(CENTER)){
			this.Center = comp;
		}
	}

	@Override
	public void removeLayoutComponent(Component comp) {

	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return null;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return new Dimension(400,300);
	}

	@Override
	public void layoutContainer(Container parent) {
		Dimension Dim = Center.getPreferredSize();
		if(Dim.width ==0){
			Dim.width = parent.getWidth();
		}
		if(Dim.height ==0){
			Dim.height = parent.getHeight();
		}
		int H = (parent.getHeight() - Dim.height)/2;
		int W = (parent.getWidth() - Dim.width)/2;
		Center.setBounds(W, H, Dim.width, Dim.height);
		
		System.out.println(parent.toString()+"Has Been Sized To  "+W+' '+H);
		System.out.println("Request: "+Dim.width+' '+Dim.height);
	}

}
