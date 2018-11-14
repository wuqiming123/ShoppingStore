package com.mgshopping.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;


public class DruidUtils {
	private static DruidDataSource druidDataSource = null;
	
	static {
		Properties properties = loadProperties("db.properties");
		try {
			druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public DruidUtils() {}

	private static Properties loadProperties(String filename) {
		// TODO Auto-generated method stub
		String webRootPath = null;
		if (null == filename || filename.equals(""))
			throw new IllegalArgumentException(
					"Properties file path can not be null : " + filename);
		webRootPath = DruidUtils.class.getClassLoader().getResource("").getPath();
		webRootPath = new File(webRootPath).getParent();
		InputStream inputStream = null;
		Properties p = null;
		try {
			inputStream = new FileInputStream(new File(webRootPath+ File.separator + filename));
			p = new Properties();
			p.load(inputStream);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Properties file not found: "+ filename);
		} catch (IOException e) {
			throw new IllegalArgumentException(
					"Properties file can not be loading: " + filename);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
	public static DruidDataSource  getDataSource() {
		return druidDataSource;
	}
	
	public static Connection getConnection() throws SQLException {
		return druidDataSource.getConnection();
		
	}
}
