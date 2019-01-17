package org.penpi.core.commons.queue.data;

public class SmallPictEventData {
	
	private int smallPictSetupId;
	
	private int fileInfId;
	
	private String filePath;
	
	private int width;
	
	private int height;
	
	/**
	 * 是否内裁切，否的话为外裁切
	 */
	private boolean innerCut;


	public SmallPictEventData(int smallPictSetupId, int fileInfId, String filePath, int width, int height, boolean innerCut) {
		this.smallPictSetupId = smallPictSetupId;
		this.fileInfId = fileInfId;
		this.filePath = filePath;
		this.width = width;
		this.height = height;
		this.innerCut = innerCut;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	public boolean isInnerCut() {
		return innerCut;
	}

	public void setInnerCut(boolean innerCut) {
		this.innerCut = innerCut;
	}

	public int getSmallPictSetupId() {
		return smallPictSetupId;
	}

	public void setSmallPictSetupId(int smallPictSetupId) {
		this.smallPictSetupId = smallPictSetupId;
	}

	public int getFileInfId() {
		return fileInfId;
	}

	public void setFileInfId(int fileInfId) {
		this.fileInfId = fileInfId;
	}

	@Override
	public String toString() {
		return "SmallPictEventData [filePath=" + filePath + ", width=" + width + ", height=" + height + ", innerCut="
				+ innerCut + "]";
	}
	
}
