package com.tripleZ.android.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import android.content.Context;
import android.os.Environment;

/**
 * 日志记录类（txt，只是记录用户操作）
 * 
 * @author Jing
 * 
 */
public class LogUtil {
	public String filename;
	/**
	 * 记录日志
	 */
	public static void writeLog(Context context,LogUtil lu, String message) {
			try {
				File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/antilost/" + lu.getFilename());
				if (!f.exists()){       
		            f.createNewFile();      
		        }
				String nowTime = DateUtil.getSecondStr(new Date());
				String info = "";
				OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f,true) , "gbk");
				info = nowTime + "  " + message + "\r\n";
				BufferedWriter writer=new BufferedWriter(write);
				writer.write(info);      
		        writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * 删除日志
	 */
	public static void deleteLog(String f) {
		File file = new File(f);
		if(file.exists()){
			file.delete();
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
