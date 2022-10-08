package com.lnngle.hycyh.db.test.reveng.dialect;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.lnngle.hycyh.db.reveng.dialect.RevengDialect;
import com.lnngle.hycyh.db.reveng.dialect.RevengDialectFactory;

public class RevengDialectFactoryTest {
	@Test
	public void testCreate() {
		String url = "jdbc:mariadb://192.168.143.143:3306/db_otms_dev?useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true";
		String driverClassName = "org.mariadb.jdbc.Driver";
		String username = "du_otms_dev";
		String password = "nfc2021dev";
		
		Properties cfg = new Properties();
		cfg.setProperty("url", url);
		cfg.setProperty("driverClassName", driverClassName);
		cfg.setProperty("username", username);
		cfg.setProperty("password", password);
		RevengDialect revengDialect = RevengDialectFactory.create(cfg);
		assertNotNull(revengDialect);
		Iterator<Map<String, Object>> tables = revengDialect.getTables(null, null, "tbl_clf_manufacturer");
		assertNotNull(tables);
		while (tables.hasNext()) {
			System.out.println(tables.next());
			Iterator<Map<String, Object>> columns = revengDialect.getColumns(null, null, "tbl_clf_manufacturer", null);
			while (columns.hasNext()) {
				System.out.println(columns.next());				
			}
		}
		revengDialect.close();
	}
}
