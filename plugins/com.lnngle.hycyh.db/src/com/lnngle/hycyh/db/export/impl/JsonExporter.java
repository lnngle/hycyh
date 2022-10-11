package com.lnngle.hycyh.db.export.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.lnngle.hycyh.db.config.DatabaseKeys;
import com.lnngle.hycyh.db.config.ExporterConfig;
import com.lnngle.hycyh.db.export.Exporter;
import com.lnngle.hycyh.db.reveng.dialect.RevengDialect;
import com.lnngle.hycyh.db.reveng.dialect.RevengDialectFactory;

public class JsonExporter implements Exporter {

	@Override
	public void export(ExporterConfig exporterConfig) {
		Properties databaseConfig = exporterConfig.getDatabaseConfig();
		String tableCat = databaseConfig.getProperty(DatabaseKeys.TABLE_CAT);
		String tableSchem = databaseConfig.getProperty(DatabaseKeys.TABLE_SCHEM);
		String tableName = databaseConfig.getProperty(DatabaseKeys.TABLE_NAME);
		
		RevengDialect revengDialect = RevengDialectFactory.create(databaseConfig);
		Iterator<Map<String, Object>> tables = revengDialect.getTables(tableCat, tableSchem, tableName);
		while (tables.hasNext()) {
			Map<String, Object> table = tables.next();
			String tName = (String) table.get(DatabaseKeys.TABLE_NAME);
			String tRemarks = (String) table.get(DatabaseKeys.TABLE_REMARKS);
			String tType = (String) table.get(DatabaseKeys.TABLE_TYPE);
			
			Iterator<Map<String, Object>> columns = revengDialect.getColumns(tableCat, tableSchem, tName, null);
			while (columns.hasNext()) {
				Map<String, Object> column = columns.next();
				String cName = (String) table.get(DatabaseKeys.COLUMN_NAME);
				String cRemarks = (String) table.get(DatabaseKeys.COLUMN_REMARKS);
				Integer cDataType = (Integer) table.get(DatabaseKeys.COLUMN_DATA_TYPE);
				String cTypeName = (String) table.get(DatabaseKeys.COLUMN_TYPE_NAME);
				Integer cSize = (Integer) table.get(DatabaseKeys.COLUMN_SIZE);
				Integer cDecimalDigits = (Integer) table.get(DatabaseKeys.COLUMN_DECIMAL_DIGITS);
				Integer cNullable = (Integer) table.get(DatabaseKeys.COLUMN_NULLABLE);
			}
		}
		
	}

}
