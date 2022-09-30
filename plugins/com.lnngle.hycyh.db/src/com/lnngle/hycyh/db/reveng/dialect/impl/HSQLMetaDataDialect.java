package com.lnngle.hycyh.db.reveng.dialect.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import com.lnngle.hycyh.db.reveng.dialect.ResultSetIterator;
import com.lnngle.hycyh.db.util.TableNameQualifier;

public class HSQLMetaDataDialect extends JDBCMetaDataDialect {

	private String quote(String columnName) {
		if (columnName == null)
			return columnName;
		if (needQuote(columnName)) {
			if (columnName.length() > 1 && columnName.charAt(0) == '\"'
					&& columnName.charAt(columnName.length() - 1) == '\"') {
				return columnName; // avoid double quoting
			}
			return "\"" + columnName + "\"";
		} else {
			return columnName;
		}
	}

	public Iterator<Map<String, Object>> getSuggestedPrimaryKeyStrategyName(String catalog, String schema,
			String table) {
		try {
			catalog = caseForSearch(catalog);
			schema = caseForSearch(schema);
			table = caseForSearch(table);

			// log.debug("geSuggestedPrimaryKeyStrategyName(" + catalog + "." + schema + "."
			// + table + ")");

			final String sc = schema;
			final String cat = catalog;
			return new ResultSetIterator(getMetaData().getTables(catalog, schema, table, new String[] { "TABLE" })) {

				Map<String, Object> element = new HashMap<String, Object>();

				protected Map<String, Object> convertRow(ResultSet tableRs) throws SQLException {
					String table = tableRs.getString("TABLE_NAME");
					String fullTableName = TableNameQualifier.qualify(quote(cat), quote(sc), quote(table));

					String sql = "SELECT * FROM " + fullTableName + " WHERE 0>1"; // can't use FALSE constant since it
																					// would not work with older HSQL
																					// versions. (JBIDE-5957)
					boolean isAutoIncrement = false;

					PreparedStatement statement = null;
					try {
						statement = getConnection().prepareStatement(sql);
						element.clear();
						element.put("TABLE_NAME", table);
						element.put("TABLE_SCHEM", sc);
						element.put("TABLE_CAT", null);

						ResultSet rs = statement.executeQuery();
						ResultSetMetaData rsmd = rs.getMetaData();
						for (int i = 0; i < rsmd.getColumnCount(); i++) {
							isAutoIncrement = rsmd.isAutoIncrement(i + 1);
							if (isAutoIncrement)
								break;
						}

					} catch (SQLException e) {
						// log error and set HIBERNATE_STRATEGY to null
						log.log(Level.ALL, "Error while getting suggested primary key strategy for " + fullTableName
								+ ". Falling back to default strategy.", e);
					} finally {
						if (statement != null) {
							try {
								statement.close();
							} catch (SQLException e) {
								throw new RuntimeException("Problem while closing prepared statement", e);
							}
						}
					}

					if (isAutoIncrement) {
						element.put("HIBERNATE_STRATEGY", "identity");
					} else {
						element.put("HIBERNATE_STRATEGY", null);
					}
					return element;
				}

				protected Throwable handleSQLException(SQLException e) {
					// schemaRs and catalogRs are only used for error reporting if
					// we get an exception
					throw new RuntimeException(
							"Could not get list of suggested identity strategies from database. Probably a JDBC driver problem. ",
							e);
				}
			};
		} catch (SQLException e) {
			throw new RuntimeException(
					"Could not get list of suggested identity strategies from database. Probably a JDBC driver problem. ",
					e);
		}
	}
}
