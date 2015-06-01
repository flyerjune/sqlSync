package org.softworld.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.swing.JOptionPane;

import org.joda.time.DateTime;

public class UtilTool {
	private static Properties p = null;
	public static int INTREST_LEN = 4;//如果数据是利息时，余额保留小数后4位，其他默认3位;

	public static boolean IsValid(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		return true;
	}

	public static String obj2Str(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}


	public static double formatIntrest(double num) {
		BigDecimal bg = new BigDecimal(num);
		double f1 = bg.setScale(INTREST_LEN, BigDecimal.ROUND_HALF_UP).doubleValue(); // 设置两位的double精度
		return f1;
	}

	public static String validStr(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	// 从数组中随机选择一个字符
	public static String getRandomStr(String[] types, Random random) {
		int len = types.length;
		String str = "";
		if (len > 0) {
			int oper = random.nextInt(len);
			str = types[oper];
		}
		return str;
	}

	// 根据长度自动生成数字
	public static String getRandomNum(int numLen, String ran) {
		if (ran == null || ran.length() != numLen) {
			ran = "";
		} else {
			return ran;
		}
		Random random = new Random();
		while (numLen-- > 0) {
			ran = ran + String.valueOf(random.nextInt(9));
		}
		return ran;
	}

	public static Properties getProperties() {
		if (p == null) {
//			String path = System.getProperty("user.dir");
//			File directory = new File("");
//			
//			
//			String path ="resource/softworld.properties";
//			path = directory.getAbsolutePath();
			InputStream is;
			p = new Properties();
			try {
//				is = new BufferedInputStream(
//						new FileInputStream(new File(path)));
				String path = ClassLoader.getSystemResource("resource/softworld.properties").getPath();
				is = new BufferedInputStream(
						new FileInputStream(new File(path))); 
				p.load(is);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}

	public static String getHibPath() {
		String path = System.getProperty("user.dir");
		// String relativePath = getProperty("HIB_PATH");
		path = path + "\\resource\\DdCfg\\hibernate.cfg.xml";
		return path;
	}

	public static String getProperty(String key) {
		String val = "";
		Object obj = getProperties().get(key);
		if (obj != null) {
			val = obj.toString();
		}
		return val;
	}

	public static boolean hasSuffix(String fileName) {
		if (fileName.indexOf(".") > 0) {
			return true;
		}
		return false;
	}

	public static void setSysClipboardText(String writeMe) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection(writeMe);
		clip.setContents(tText, null);
	}

	public static void main(String[] args) {
		Properties p = UtilTool.getProperties();
		String qq = UtilTool.getProperty("VALID_DATE");

	}

	public static DateTime str2DateTime(String dateTime) {
		Date tmpDate;
		DateTime dt = null;
		try {
			tmpDate = new SimpleDateFormat("yyyyMMdd").parse(dateTime
					.toString());
			dt = new DateTime(tmpDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;
	}

	public static boolean comfirmDlg(String msg, String title) {
		int result = JOptionPane.showConfirmDialog(null, msg, title,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public static String getUniqId() {
		String timeId = new SimpleDateFormat("yyMMddhhssSSS")
				.format(new Date());
		return timeId;
	}


	public static String[] removeFirstCol(String[] lbls) {
		String[] newLbls = new String[lbls.length - 1];
		for (int i = 0; i < lbls.length-1; i++) {
			newLbls[i] = lbls[i + 1];
		}
		return newLbls;
	}
	
	public static String getIndexStr(String strArray,int idx){
		String str = "";
		String[] strArr = strArray.split("\\|");
		if(strArr.length>=idx){
			str = strArr[idx];
		}
		return str;
	}
	

}
