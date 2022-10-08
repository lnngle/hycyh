package com.lnngle.hycyh.db.reveng.dialect;

import java.util.Properties;

import com.lnngle.hycyh.db.jdbc.ConnectionProvider;
import com.lnngle.hycyh.db.jdbc.impl.DriverManagerConnectionProvider;
import com.lnngle.hycyh.db.reveng.dialect.impl.H2MetaDataDialect;
import com.lnngle.hycyh.db.reveng.dialect.impl.HSQLMetaDataDialect;
import com.lnngle.hycyh.db.reveng.dialect.impl.JDBCMetaDataDialect;
import com.lnngle.hycyh.db.reveng.dialect.impl.MySQLMetaDataDialect;
import com.lnngle.hycyh.db.reveng.dialect.impl.OracleMetaDataDialect;
import com.lnngle.hycyh.db.reveng.dialect.impl.SQLServerMetaDataDialect;

public class RevengDialectFactory {
	public static RevengDialect create(Properties cfg) {
		String url = cfg.getProperty("url");
		String driverClassName = cfg.getProperty("driverClassName");
		String username = cfg.getProperty("username");
		String password = cfg.getProperty("password");

		ConnectionProvider connectionProvider = new DriverManagerConnectionProvider(url, driverClassName, username,
				password);
		RevengDialect revengDialect = null;
		if (driverClassName != null) {
			if (driverClassName.toLowerCase().contains("oracle")) {
				revengDialect = new OracleMetaDataDialect();
			}
			else if (driverClassName.toLowerCase().contains("mysql")) {
				revengDialect =  new MySQLMetaDataDialect();
			}
			else if (driverClassName.toLowerCase().contains("h2")) {
				revengDialect =  new H2MetaDataDialect();
			}
			else if (driverClassName.toLowerCase().contains("hsql")) {
				revengDialect =  new HSQLMetaDataDialect();
			}
			else if (driverClassName.toLowerCase().contains("sqlserver")) {
				revengDialect =  new SQLServerMetaDataDialect();
			} else {
				revengDialect = new JDBCMetaDataDialect();
			}
		}
		
		if (revengDialect != null) {
			revengDialect.configure(connectionProvider);
			return revengDialect;
		} else {
			return null;
		}
		
	}
}
