package com.lnngle.hycyh.generator.test.process.impl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lnngle.hycyh.generator.config.ProcesserConfig;
import com.lnngle.hycyh.generator.config.TemplateKeys;
import com.lnngle.hycyh.generator.process.impl.DefaultProcesser;
import com.lnngle.hycyh.generator.test.TestConstants;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

public class DefaultProcesserTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testProcess() {

		DefaultProcesser process = new DefaultProcesser();
		try {
			ProcesserConfig config = new ProcesserConfig();
			Map<String, Object> data = new HashMap<>();
			
			JSON json = JSONUtil.readJSON(new File(TestConstants.MODEL_DATA_FILE), Charset.defaultCharset());
			Map<String, Object>[] modelData = json.toBean(Map[].class);
			data.put(TemplateKeys.MODEL_DATA, modelData[0]);
			
			Map<String, Object> templateData = new HashMap<>();
			templateData.put("model.ftl", "model.java");
			data.put(TemplateKeys.TEMPLATE_DATA, templateData);
			
			config.setData(data);
			
			File templateDir = new File(TestConstants.MODEL_TEMPLATE_DIR);
			config.setTemplateDir(templateDir);
			
			File outputDir = new File(TestConstants.MODEL_OUTPUT_DIR);
			if (!outputDir.exists()) {
				outputDir.mkdir();
			}
			config.setOutputDir(outputDir);

			process.process(config);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}
}
