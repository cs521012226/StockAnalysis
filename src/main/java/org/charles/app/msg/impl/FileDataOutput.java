package org.charles.app.msg.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.charles.app.msg.DataOutput;

/**
 * 文件输出
 * @author Charles
 *
 */
public class FileDataOutput implements DataOutput {
	
	private File file;
	private String data;
	
	public FileDataOutput(String data, File file){
		this.data = data;
		this.file = file;
	}
	
	public FileDataOutput(String data, String path){
		this(data, new File(path));
	}

	@Override
	public void execute() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream(file));
			pw.println(data);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(pw != null){
				pw.flush();
				pw.close();
			}
		}
	}
	
}
