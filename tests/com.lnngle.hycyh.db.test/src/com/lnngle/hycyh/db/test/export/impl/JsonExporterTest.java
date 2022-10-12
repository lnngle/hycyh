package com.lnngle.hycyh.db.test.export.impl;

import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

import com.lnngle.hycyh.db.config.ExporterConfig;
import com.lnngle.hycyh.db.export.impl.JsonExporter;
import com.lnngle.hycyh.db.test.TestData;

public class JsonExporterTest {

	@Test
	public void testExport() {
		JsonExporter exporter = new JsonExporter();
		ExporterConfig config = new ExporterConfig();
		config.setDatabaseConfig(TestData.getDatabaseConfig());
		Writer writer = new StringWriter();
		config.setOutput(writer);
		exporter.export(config);
		System.out.println(writer.toString());
	}

}
