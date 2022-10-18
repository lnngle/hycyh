package com.lnngle.hycyh.generator.process.impl;

import com.lnngle.hycyh.generator.config.FreemarkerConfiguration;
import com.lnngle.hycyh.generator.process.Processer;

import freemarker.template.Configuration;

public abstract class AbstractProcesser implements Processer  {

	private Configuration configuration;

	public Configuration getConfiguration() {
		if (this.configuration == null) {
			this.configuration = FreemarkerConfiguration.getConfiguration();
		}
		return this.configuration;
	}

}
