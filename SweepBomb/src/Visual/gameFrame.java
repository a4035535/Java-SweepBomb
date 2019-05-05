package Visual;


import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
import javax.swing.border.LineBorder;

import java.util.Vector;

import Model.block;
import Model.bombField;

public class gameFrame {
	private JFrame frmdemo = new JFrame();
	private bombField bf;
	private JPanel panel_1=new JPanel();
	private Vector<JButtonS> buttons=new Vector<JButtonS>();
	private JLabel label;
	//根据bf内setting值，调整按钮数量和窗口大小
	private void setButtons(){
		buttons=new Vector<JButtonS>();
		for(int i=0;i<bf.getSize();i++) {
			JButtonS temp=new JButtonS();
			temp.setID(i);
			Dimension preferredSize = new Dimension(40,40);
			temp.setPreferredSize(preferredSize);
			temp.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(e.getButton()==e.BUTTON1) {
						int key =bf.blockShow(((JButtonS)e.getSource()).getID());
						if (key==-1) gameOver();
						else if (key==-2) ;
						else if (key==1) freshFrame();
						if(bf.winCheck()) {
							gameWin();
							return;
						}
					}
					else if(e.getButton()==e.BUTTON3) {
						if(!bf.getBomb(((JButtonS)e.getSource()).getID()).isShow())
						bf.getBomb(((JButtonS)e.getSource()).getID()).setFlag();
						
						freshFrame();
					}
				}
			});
			buttons.add(temp);
			}
		addButtons();
	}
	//游戏结束、获胜时显示
	private void gameOver() {
		for(int i=0;i<bf.getSize();i++) {
			if(bf.getBomb(i).isBomb()) {
				buttons.get(i).setIcon(new ImageIcon(gameFrame.class.getResource("/Visual/image/bomb.png")));
			}
		}
		JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.ERROR_MESSAGE);
		reStart();
	}
	private void gameWin() {
		JOptionPane.showMessageDialog(null, "You Win!", "You Win!", JOptionPane.OK_OPTION);
		reStart();
	}
	//添加按钮，即雷区方块
	private void addButtons(){
		frmdemo.remove(panel_1);
		panel_1=new JPanel();
		int[] setting=bf.getSetting();
		int x=setting[0],y=setting[1];
		panel_1.setLayout(new GridLayout(y,x,0,0));
		if(x<16)
			frmdemo.setBounds(100, 100, 640,y*40+70);
		else
			frmdemo.setBounds(100, 100, x*40,y*40+70);
		for(int i=0;i<x*y;i++) {
			panel_1.add(buttons.get(i));
		}
		frmdemo.add(panel_1);
	}
	//构造函数，以bf为传入值，初始化窗口并生成按钮。
	public gameFrame(bombField bf) {
		this.bf=bf;
		setButtons();
		initialize();
		frmdemo.setVisible(true);
	}
	//刷新界面，返回值为1成功
	private int freshFrame() {
		int[] setting=bf.getSetting();
		for(int i=0;i<setting[0]*setting[1];i++) {
			JButtonS b=buttons.get(i);
			block bl=bf.getBomb(i);
			if (bl.isFlag()) {
				b.setIcon(new ImageIcon(gameFrame.class.getResource("/Visual/image/FLAG.png")));
			}else b.setIcon(null);
			
			if(bl.isShow()) {
				b.setBorder(BorderFactory.createLoweredBevelBorder());
				if(bl.getAround()==0)
					b.setText("");
				else
					b.setText(bl.getAround()+"");
			}else {
				b.setText("");
				b.setBorder(BorderFactory.createRaisedBevelBorder());
			}
			if(bf.getSetting()[2]-bf.getFlagNum()>=10) {
				label.setText(('0'+Integer.toString(bf.getSetting()[2]-bf.getFlagNum()).toString()));
			}else if(bf.getSetting()[2]-bf.getFlagNum()>0) {
				label.setText(("00"+Integer.toString(bf.getSetting()[2]-bf.getFlagNum()).toString()));
			}
			else {
				label.setText("000");
			}
		}
		return 1;
	}
	//重启游戏
	private void reStart() {
		bf.reStart();
		freshFrame();
	}
	//初始化界面
	private void initialize() {
		frmdemo.setResizable(false);
		frmdemo.setAutoRequestFocus(false);
		frmdemo.getContentPane().setBackground(SystemColor.menu);
		frmdemo.setTitle("\u626B\u96F7DEMO");
		frmdemo.setBounds(100, 100, 640,360+70);//360=90*40，即最小方块高度，70为交互栏高度
		frmdemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmdemo.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		frmdemo.getContentPane().add(panel_4, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JButton btnNewButton_1 = new JButton("\u521D\u7EA7");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bf.setLevel(0);
				setButtons();
				reStart();
			}
		});
		panel_5.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		
		JButton button = new JButton("\u4E2D\u7EA7");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bf.setLevel(1);
				setButtons();
				reStart();
			}
		});
		panel_5.add(button);
		button.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		
		
		JButton button_1 = new JButton("\u9AD8\u7EA7");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bf.setLevel(2);
				setButtons();
				reStart();
			}
		});
		panel_5.add(button_1);
		button_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		
		JPanel panel = new JPanel();
		panel_4.add(panel);
				FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 1, 0);
				panel.setLayout(fl_panel);
				
				JPanel panel_6 = new JPanel();
				panel.add(panel_6);
				
				JPanel panel_2 = new JPanel();
				panel_6.add(panel_2);
				panel_2.setBackground(Color.DARK_GRAY);
				panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
				panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
				
				label = new JLabel(('0'+Integer.toString(bf.getSetting()[2]).toString()));
				panel_2.add(label);
				label.setBackground(Color.DARK_GRAY);
				label.setForeground(Color.RED);
				label.setFont(new Font("微軟正黑體", Font.BOLD, 30));
				
				JButton btnNewButton = new JButton("");
				panel_6.add(btnNewButton);
				btnNewButton.setIcon(new ImageIcon(gameFrame.class.getResource("/Visual/image/001.png")));
				btnNewButton.setPreferredSize(new Dimension(50,50));
				btnNewButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						reStart();
					}
				});
				btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
				addButtons();
				frmdemo.getContentPane().add(panel_1, BorderLayout.CENTER);
				
	}
}

