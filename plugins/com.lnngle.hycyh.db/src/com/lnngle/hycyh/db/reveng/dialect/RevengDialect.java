package com.lnngle.hycyh.db.reveng.dialect;

import java.util.Iterator;
import java.util.Map;

import com.lnngle.hycyh.db.jdbc.ConnectionProvider;

public interface RevengDialect {

	/**
	 * Configure the metadatadialect. 
	 * @param connectionProvider a {@link ConnectionProvider} 
	 */
	public void configure(ConnectionProvider connectionProvider);

	/**
	 * Return iterator over the tables that mathces catalog, schema and table
	 * 
	 * @param catalog name or null
	 * @param schema  name or null
	 * @param table   name or null
	 * @return iterator with map elements that has "TABLE_NAME", "TABLE_SCHEMA",
	 *         "TABLE_CAT", "TABLE_TYPE" keys.
	 */
	Iterator<Map<String, Object>> getTables(String catalog, String schema, String table);

	/**
	 * Close the iterator.
	 * 
	 * @param iterator an iterator returned from one of methods on this dialect
	 */
	void close(Iterator<?> iterator);

	/**
	 * Return iterator over the indexes that mathces catalog, schema and table
	 * 
	 * @param catalog name or null
	 * @param schema  name or null
	 * @param table   name or null
	 * @return iterator with map elements that has "TABLE_NAME", "TABLE_SCHEMA",
	 *         "TABLE_CAT", "INDEX_NAME", "COLUMN_NAME", "NON_UNIQUE", "TYPE" keys.
	 */
	Iterator<Map<String, Object>> getIndexInfo(String catalog, String schema, String table);

	/**
	 * Return iterator over the columns that mathces catalog, schema and table
	 * 
	 * @param catalog name or null
	 * @param schema  name or null
	 * @param table   name or null
	 * @param column  name or null
	 * @return iterator with map elements that has "TABLE_NAME", "TABLE_SCHEMA",
	 *         "TABLE_CAT", "DATA_TYPE", "TYPE_NAME", "COLUMN_NAME", "NULLABLE",
	 *         "COLUMN_SIZE", "DECIMAL_DIGITS"
	 */
	Iterator<Map<String, Object>> getColumns(String catalog, String schema, String table, String column);

	/**
	 * Return iterator over the columns that mathces catalog, schema and table
	 * 
	 * @param catalog name or null
	 * @param schema  name or null
	 * @param table   name or null
	 * @return iterator with map elements that has "TABLE_NAME", "TABLE_SCHEMA",
	 *         "TABLE_CAT", "COLUMN_NAME", "KEY_SEQ", "PK_NAME",
	 */
	Iterator<Map<String, Object>> getPrimaryKeys(String catalog, String schema, String name);

	/**
	 * Return iterator over the exported foreign keys that mathces catalog, schema
	 * and table
	 * 
	 * @param catalog name or null
	 * @param schema  name or null
	 * @param table   name or null
	 * @return iterator with map elements that has "TABLE_NAME", "TABLE_SCHEMA",
	 *         "TABLE_CAT", "FKTABLE_CAT", "FKTABLE_SCHEM", "FKTABLE_NAME",
	 *         "FK_NAME", "KEY_SEQ"
	 */
	Iterator<Map<String, Object>> getExportedKeys(String catalog, String schema, String table);

	/**
	 * Does this name need quoting
	 * 
	 * @param name
	 * @return
	 */
	boolean needQuote(String name);

	/**
	 * Close any resources this dialect might have used.
	 */
	void close();

	/**
	 * Use database (possible native) metadata to suggest identifier strategy.
	 * 
	 * @param catalog
	 * @param schema
	 * @param name
	 * @return iterator with map elements that has "TABLE_NAME", "TABLE_SCHEMA",
	 *         "TABLE_CAT", "HIBERNATE_STRATEGY" (null if no possible to determine
	 *         strategy, otherwise return hibernate identifier strategy
	 *         name/classname)
	 */
	public Iterator<Map<String, Object>> getSuggestedPrimaryKeyStrategyName(String catalog, String schema,
			String table);
}
