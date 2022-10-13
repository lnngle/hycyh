package com.lnngle.hycyh.generator.process.impl;

import java.io.Writer;
import java.util.Map;

import freemarker.template.Template;

public class DefaultProcesser extends AbstractProcesser {

	@Override
	public void process(String templateName, Map<String, Object> data, Writer output) {
		try {
			Template template = this.getConfiguration().getTemplate(templateName);
			template.process(data, output);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
