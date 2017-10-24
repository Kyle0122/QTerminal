package com.Samaritan.QTerminal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SamaritanKeyListener implements KeyListener {
	TextDrawer Drawer;
	ActionListener ActLis;

	public SamaritanKeyListener(TextDrawer TD) {
		this.Drawer = TD;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			Drawer.Delete();
			return;
		}
		switch(e.getKeyCode()){
		case KeyEvent.VK_CONTROL: return;
		case KeyEvent.VK_SHIFT: return;
		case KeyEvent.VK_ALT: return;
		case KeyEvent.VK_CAPS_LOCK: return;
		case KeyEvent.VK_ESCAPE: return;
		case KeyEvent.VK_UP: return;
		case KeyEvent.VK_DOWN: return;
		case KeyEvent.VK_LEFT: return;
		case KeyEvent.VK_RIGHT: return;
		
		case KeyEvent.VK_ENTER:
			String DrawerT = this.Drawer.getText();
			if(!DrawerT.equals("exit")){
				this.ActLis.actionPerformed(new ActionEvent(this, 0, DrawerT));
				this.Drawer.setText("");
				return;
			}else {
				this.ActLis.actionPerformed(new ActionEvent(this, -1, DrawerT));
				return;
			}
		}
		
		char A = e.getKeyChar();
		
		this.Drawer.append(A);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
	
	public void addActionListener(ActionListener Lis){
		this.ActLis = Lis;
	}
}
