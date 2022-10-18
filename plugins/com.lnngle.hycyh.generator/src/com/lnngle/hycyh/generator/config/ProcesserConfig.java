package com.lnngle.hycyh.generator.config;

import java.io.File;
import java.io.Writer;
import java.util.Map;

public class ProcesserConfig {

	private File templateDir;
	private Map<String, Object> data;
	private Writer output;

	public File getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(File templateDir) {
		this.templateDir = templateDir;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Writer getOutput() {
		return output;
	}

	public void setOutput(Writer output) {
		this.output = output;
	}
}
