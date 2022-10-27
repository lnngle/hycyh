package com.lnngle.hycyh.generator.process.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import com.lnngle.hycyh.generator.config.FtlFileFilter;
import com.lnngle.hycyh.generator.config.ProcesserConfig;
import com.lnngle.hycyh.generator.config.TemplateKeys;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import freemarker.template.Template;

public class AppProcesser extends AbstractProcesser {

	@SuppressWarnings("unchecked")
	@Override
	public void process(ProcesserConfig processerConfig) {
		try {
			this.getConfiguration().setDirectoryForTemplateLoading(processerConfig.getTemplateDir());
			Map<String, Object> data = processerConfig.getData();
			Map<String, Object> appData = (Map<String, Object>) data.get(TemplateKeys.APP_DATA);
			File outputDir = processerConfig.getOutputDir();
			List<File> files = FileUtil.loopFiles(processerConfig.getTemplateDir());
			for (File file : files) {
				String srcPath = file.getPath().substring(processerConfig.getTemplateDir().getPath().length());
				String destPath = this.toDestPath(outputDir, srcPath, appData);
				String srcName = file.getName();
				if (srcName.endsWith(FtlFileFilter.FTL_EXT)) {
					Template template = this.getConfiguration().getTemplate(srcName);
					File outFile = FileUtil.file(destPath);
					FileWriter fileWriter = new FileWriter(outFile);
					this.generateFile(template, appData, fileWriter);
				} else {
					File outFile = FileUtil.touch(destPath);
					this.copyFile(file, outFile, true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private String toDestPath(File outputDir, String srcPath, Map<String, Object> appData) {
		String destPath = outputDir.getPath() + srcPath;
		String value;
		for (Map.Entry<?, ?> entry : appData.entrySet()) {
			value = StrUtil.utf8Str(entry.getValue());
			if (null == value) {
				continue;
			}
			destPath = CharSequenceUtil.replace(destPath, "{" + entry.getKey() + "}", value.replace(".", File.separator));
		}
		if (destPath.endsWith(FtlFileFilter.FTL_EXT)) {
			destPath = destPath.substring(0, destPath.length() - FtlFileFilter.FTL_EXT.length());
		}
		return destPath;
	}
	
}
