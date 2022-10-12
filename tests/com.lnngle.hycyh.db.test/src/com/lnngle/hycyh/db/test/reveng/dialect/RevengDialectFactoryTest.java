package com.lnngle.hycyh.db.test.reveng.dialect;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import com.lnngle.hycyh.db.reveng.dialect.RevengDialect;
import com.lnngle.hycyh.db.reveng.dialect.RevengDialectFactory;
import com.lnngle.hycyh.db.test.TestData;

public class RevengDialectFactoryTest {
	@Test
	public void testCreate() {
		RevengDialect revengDialect = RevengDialectFactory.create(TestData.getDatabaseConfig());
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
