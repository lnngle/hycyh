package com.lnngle.hycyh.generator.process;

import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;

public interface Processer {
	
	Configuration getConfiguration();
	void process(String templateName, Map<String, Object> data, Writer output);
}
