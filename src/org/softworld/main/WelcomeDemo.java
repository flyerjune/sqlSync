package org.softworld.main;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softworld.util.DateChooser;
import org.softworld.util.UtilFrame;
import org.softworld.util.UtilTool;

public class WelcomeDemo extends JFrame implements ActionListener {

	final static Logger logger = LoggerFactory.getLogger(WelcomeDemo.class);

	private String[] lblNames = { "影片排期同步："};

	public WelcomeDemo() {
		super("影片排期同步（展示版）");
		PropertyConfigurator.configure(ClassLoader.getSystemResource("resource/log4j.properties"));
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("影片排期");
		JMenu settingMenu = new JMenu("系统设置");
		JMenuItem blankMenuItem = new JMenuItem("空白");
		menu.add(blankMenuItem);
		menuBar.add(menu);
		menuBar.add(settingMenu);
		setJMenuBar(menuBar);

		Font ft = new Font("华文行楷", Font.TRUETYPE_FONT, 32);
		// 第一栏
		JLabel lbl = new  JLabel("影片排期同步：");
		lbl.setFont(ft);
		add(lbl);
		
		
		// 第二栏
		JPanel pnl = new JPanel();
		JLabel beginLbl = new  JLabel("开始日期：");
		JLabel endLbl = new  JLabel("结束日期：");
		JTextField beginDateFld = new JTextField();
		JTextField endDateFld = new JTextField();
		DateChooser dcBgn = new DateChooser(beginDateFld);
		DateChooser dcEnd = new DateChooser(endDateFld);
		beginLbl.setHorizontalAlignment(JLabel.RIGHT);
		endLbl.setHorizontalAlignment(JLabel.RIGHT);
		pnl.add(beginLbl);
		pnl.add(dcBgn);
		pnl.add(endLbl);
		pnl.add(dcEnd);
		JLabel bankLbl = new  JLabel("");
		pnl.add(bankLbl);
		pnl.add(bankLbl);
		add(pnl);
		GridLayout layout = new GridLayout(1, 6);
		pnl.setLayout(layout);
		
		// 第三栏
		JButton syncStart = new JButton("开始同步");
		syncStart.setActionCommand("sync");
		syncStart.addActionListener(this);
		add(syncStart);
		add(bankLbl);
		
		// 第四栏
		lbl = new  JLabel("网络售票座位同步：");
		lbl.setFont(ft);
		add(lbl);
		
		// 第五栏
		JButton execStart = new JButton("开始执行");
		execStart.setActionCommand("exec");
		execStart.addActionListener(this);
		add(execStart);
		
		// 第六栏
		String validDate = UtilTool.getProperty("VALID_DATE");
		JLabel validDateLbl = new JLabel("有效期："+validDate);
		validDateLbl.setHorizontalAlignment(JLabel.RIGHT);
		add(validDateLbl);
		
		
		//汇总生成
		layout = new GridLayout(10, 4);
		getContentPane().setLayout(layout);
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UtilFrame.centreWindow(this);
		this.setResizable(false);
		show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		WelcomeDemo demo = new WelcomeDemo();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("sync")) {
			logger.info("正在使用同步影片排期数据");
			
			
		} else if (arg0.getActionCommand().equals("exec")) {
			logger.info("正在使用同步网络售票数据");
			
			
		}
		
	}

}
