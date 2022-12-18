package com.edusoln.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.edusoln.base.CommonMethods;

public class PropertyReader {
			
	public static String getPropValue(String key) {
		Properties prop=new Properties();
		File file=new File (CommonMethods.homepath+"\\src\\test\\resources\\Config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result=prop.getProperty(key);
		return result;
		
	}

}
