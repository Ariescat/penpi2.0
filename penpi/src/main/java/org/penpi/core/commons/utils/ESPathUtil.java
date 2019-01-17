package org.penpi.core.commons.utils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.penpi.core.commons.web.WebContextHolder;

/**
 * ES目录获取和清理工具.
 * 
 * @author drugbean
 *
 */
public class ESPathUtil {

	private static final String ES_HOME_DIR = "eshome";

	private static final String ES_DATA_DIR = "esdata";

	private static Pattern uuidPattern = Pattern.compile("^\\w*-\\w*-\\w*-\\w*-\\w*$");

	public static String getHomeDir() {
		return WebContextHolder.getWarPath() + File.separator + "WEB-INF" + File.separator + ES_HOME_DIR;
	}

	public static String getDataDir() {
		return getHomeDir() + File.separator + ES_DATA_DIR;
	}

	public static String cleanAndRandomCluster() {
		File dataDir = new File(getDataDir());
		if (dataDir.exists() && dataDir.isDirectory()) {
			File[] files = dataDir.listFiles();
			for (File file : files) {
				if (file.isDirectory() && uuidPattern.matcher(file.getName()).find()) { // 清理以前生成的UUID目录
					try {
						FileUtils.deleteDirectory(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return java.util.UUID.randomUUID().toString();
	}
}
