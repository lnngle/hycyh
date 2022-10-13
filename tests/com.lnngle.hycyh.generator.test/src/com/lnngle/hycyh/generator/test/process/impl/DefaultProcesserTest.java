package com.lnngle.hycyh.generator.test.process.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.Map;

import org.junit.Test;

import com.lnngle.hycyh.generator.process.impl.DefaultProcesser;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import freemarker.template.Template;

public class DefaultProcesserTest {

	@Test
	public void testProcess() {
		DefaultProcesser process = new DefaultProcesser();
		process.setConfiguration("F:\\ch\\test\\templates");
		try {
			String templateName = "model.ftl";
			Template template = process.getConfiguration().getTemplate(templateName);
			assertNotNull(template);
			
			JSONArray array = JSONUtil.readJSONArray(new File("F:\\ch\\test\\data\\table-test.json"), Charset.defaultCharset());
			Object[] objects = array.toArray();
			Map<String, Object> item = (Map<String, Object>) objects[0];
			FileWriter fileWrite = new FileWriter("F:\\ch\\test\\files\\" + item.get("className") + ".java");
			process.process(templateName, item, fileWrite);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
