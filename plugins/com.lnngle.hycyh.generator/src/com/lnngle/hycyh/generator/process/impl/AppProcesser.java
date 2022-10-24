package com.lnngle.hycyh.generator.process.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import com.lnngle.hycyh.generator.config.FtlFileFilter;
import com.lnngle.hycyh.generator.config.ProcesserConfig;
import com.lnngle.hycyh.generator.config.TemplateKeys;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Template;

public class AppProcesser extends AbstractProcesser {

	@SuppressWarnings("unchecked")
	@Override
	public void process(ProcesserConfig processerConfig) {
		try {
			this.getConfiguration().setDirectoryForTemplateLoading(processerConfig.getTemplateDir());
			Map<String, Object> data = processerConfig.getData();
			Map<String, Object> modelData = (Map<String, Object>) data.get(TemplateKeys.DATA_MODEL);
			Map<String, Object> templateData = (Map<String, Object>) data.get(TemplateKeys.DATA_TEMPLATE);
			File outputDir = processerConfig.getOutputDir();
			List<File> files = FileUtil.loopFiles(processerConfig.getTemplateDir());
			for (File file : files) {
				String name = file.getName();
				if (name.endsWith(FtlFileFilter.FTL_EXT)) {
					Template template = this.getConfiguration().getTemplate(name);
					String path = (String) templateData.get(name);
					File outFile = FileUtil.file(outputDir, path);
					FileWriter fileWriter = new FileWriter(outFile);
					this.generateFile(template, modelData, fileWriter);
				} else {
					File outFile = FileUtil.file(outputDir, name);
					this.copyFile(file, outFile, true);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
