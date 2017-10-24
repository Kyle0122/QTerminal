package com.Samaritan.QTerminal;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tools {

	public Tools() {
	}
	
	public static void ExecCmd(final String Cmd){
		Runnable R = new Runnable(){
			@Override
			public void run() {
				Exec(Cmd);
			}
		};
		
		new Thread(R).start();
	}
	
	private static void Exec(String Cmd){
		try {
			Runtime R = Runtime.getRuntime();
			Process proc = R.exec(Cmd);
			//BufferedReader Bfr =new BufferedReader(new InputStreamReader(proc.getInputStream()));
			InputStreamReader Dim = new InputStreamReader(proc.getInputStream(),"UTF-8");
			int M;
			while((M = Dim.read())!=-1){
				System.out.print((char)M);
			}
			Dim.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
