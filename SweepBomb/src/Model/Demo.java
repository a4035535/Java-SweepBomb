package Model;

import java.awt.EventQueue;

import Visual.gameFrame;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bombField bs=new bombField();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameFrame window = new gameFrame(bs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
