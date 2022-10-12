package com.lnngle.hycyh.db.test;

import java.util.Properties;

import com.lnngle.hycyh.db.config.DatabaseKeys;

public class TestData {

	public static Properties getDatabaseConfig() {
		String url = "jdbc:mariadb://192.168.143.143:3306/db_otms_dev?useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true";
		String driverClassName = "org.mariadb.jdbc.Driver";
		String username = "du_otms_dev";
		String password = "nfc2021dev";
		
		Properties cfg = new Properties();
		cfg.setProperty(DatabaseKeys.DATASOURCE_URL, url);
		cfg.setProperty(DatabaseKeys.DATASOURCE_DRIVERCLASSNAME, driverClassName);
		cfg.setProperty(DatabaseKeys.DATASOURCE_USERNAME, username);
		cfg.setProperty(DatabaseKeys.DATASOURCE_PASSWORD, password);
		
		return cfg;
	}

}
