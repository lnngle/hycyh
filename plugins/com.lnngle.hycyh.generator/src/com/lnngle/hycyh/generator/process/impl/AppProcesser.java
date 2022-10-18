package com.lnngle.hycyh.generator.process.impl;

import java.io.File;
import java.util.List;

import com.lnngle.hycyh.generator.config.FtlFileFilter;
import com.lnngle.hycyh.generator.config.ProcesserConfig;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Template;

public class AppProcesser extends AbstractProcesser {

	@Override
	public void process(ProcesserConfig processerConfig) {
		try {
			this.getConfiguration().setDirectoryForTemplateLoading(processerConfig.getTemplateDir());
			List<File> files = FileUtil.loopFiles(processerConfig.getTemplateDir(), new FtlFileFilter());
			for (File file : files) {
				Template template = this.getConfiguration().getTemplate(file.getName());
				template.process(processerConfig.getData(), processerConfig.getOutput());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
