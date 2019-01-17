package org.penpi.core.commons.file;

import org.penpi.subsys.entity.FileInf;

/**
 * 文件资源对应精简数据结构.
 * 
 * @author JIM
 * @see FileInf
 */
public class FileInfDto {

	public static final String FILE_URL = "fileUrl";

	private Integer fileInfId;
	private String fileNm;
	private String fileTypeNm; // 扩展名决定了显示图标
	private String filePath;
	private String fileUrl;

	public FileInfDto() {
	}

	public Integer getFileInfId() {
		return fileInfId;
	}

	public void setFileInfId(Integer fileInfId) {
		this.fileInfId = fileInfId;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getFileTypeNm() {
		return fileTypeNm;
	}

	public void setFileTypeNm(String fileTypeNm) {
		this.fileTypeNm = fileTypeNm;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}
