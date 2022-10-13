package com.lnngle.hycyh.generator.process.impl;

import com.lnngle.hycyh.generator.config.FreemarkerConfiguration;
import com.lnngle.hycyh.generator.process.Processer;

import freemarker.template.Configuration;

public abstract class AbstractProcesser implements Processer  {

	private Configuration configuration;

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String templatePath) {
		this.configuration = FreemarkerConfiguration.getConfiguration(templatePath);
	}

}
