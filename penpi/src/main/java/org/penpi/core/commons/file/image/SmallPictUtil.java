package org.penpi.core.commons.file.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.penpi.subsys.Global;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class SmallPictUtil {

	private static final float DEFAULT_JPG_QUALITY = 0.85f;

	public static final String DEFAULT_OUTPUT_FORMAT = "jpg";

	private SmallPictUtil() {}
	
	private static Logger logger = Logger.getLogger(SmallPictUtil.class);

	/**
	 * 平滑缩放，不要处理大图像.
	 * @param sourceImage
	 * @param width
	 * @param height
	 * @return
	 */
	private static BufferedImage scaleSmooth(BufferedImage sourceImage, int width, int height) {
		BufferedImage thumbnail = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = thumbnail.createGraphics();
		g.drawImage(sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, width, height, null);
		g.dispose();
		return thumbnail;
	}
	
	/**
	 * @param width 小图宽
	 * @param height 小图高
	 * @param filePath 原图PATH
	 * @return 小图大小KB，-1表示文件已存在
	 */
	public static int generateSmallPict(int width, int height, String filePath, boolean ifInnerCut) {
		File destFile = new File(
			new StringBuilder(FilenameUtils.removeExtension(filePath.replaceFirst(Global.UPLOAD_DIR, Global.SMALL_PICT_DIR)))
				.append("_")
				.append(width)
				.append(Global.SMALL_PICT_SIZE_SPLIT_CHAR)
				.append(height)
				.append(".")
				.append(DEFAULT_OUTPUT_FORMAT)
				.toString()
		);
		if(destFile.exists()){ //文件存在，不生成新图
			return -1;
		}
		if(!destFile.getParentFile().exists() && !destFile.getParentFile().mkdirs()) {
			logger.error("permission denied, create directory failed");
			throw new RuntimeException("permission denied, create directory failed");
		}

		try {
			BufferedImage sourceImage = ImageIO.read(new File(filePath));
			Builder<BufferedImage> builder = null;
			double sourceAspectRatio = (double)sourceImage.getWidth() / (double)sourceImage.getHeight();
			double targetAspectRatio = (double)width/(double)height;
			if(ifInnerCut) { //内裁切
				if(sourceImage.getWidth() < width || sourceImage.getHeight() < height || sourceAspectRatio == targetAspectRatio) { //强制拉伸
					builder = Thumbnails.of(sourceImage).forceSize(width, height);
				}else {
					if(sourceAspectRatio < targetAspectRatio) {
						if(width < Global.PICT_QUALITY_SCALE_SIZE || height < Global.PICT_QUALITY_SCALE_SIZE) { //如果是小图则采用高精度缩放
							sourceImage = scaleSmooth(sourceImage, width, (int)Math.round(width/sourceAspectRatio));
						}else { //大图采用快速缩放
							sourceImage = Thumbnails.of(filePath).width(width).asBufferedImage();
						}
					}else {
						if(width < Global.PICT_QUALITY_SCALE_SIZE || height < Global.PICT_QUALITY_SCALE_SIZE) { //如果是小图则采用高精度缩放
							sourceImage = scaleSmooth(sourceImage, (int)Math.round(height * sourceAspectRatio), height);
						}else {
							sourceImage = Thumbnails.of(filePath).height(height).asBufferedImage();
						}
					}
					builder = Thumbnails.of(sourceImage).size(width, height).crop(Positions.CENTER);
				}
			}else { //外裁切
				if((sourceImage.getWidth() < width && sourceImage.getHeight() < height) || sourceAspectRatio == targetAspectRatio) { //强制拉伸
					builder = Thumbnails.of(sourceImage).forceSize(width, height);
				}else {
					if(sourceAspectRatio < targetAspectRatio) {
						if(width < Global.PICT_QUALITY_SCALE_SIZE || height < Global.PICT_QUALITY_SCALE_SIZE) { //如果是小图则采用高精度缩放
							sourceImage = scaleSmooth(sourceImage, (int)Math.round(height * sourceAspectRatio), height);
						}else { //大图采用快速缩放
							sourceImage = Thumbnails.of(filePath).height(height).asBufferedImage();
						}
					}else {
						if(width < Global.PICT_QUALITY_SCALE_SIZE || height < Global.PICT_QUALITY_SCALE_SIZE) { //如果是小图则采用高精度缩放
							sourceImage = scaleSmooth(sourceImage, width, (int)Math.round(width/sourceAspectRatio));
						}else {
							sourceImage = Thumbnails.of(filePath).width(width).asBufferedImage();
						}
					}
					builder = Thumbnails.of(sourceImage).size(width, height);

					int fillWidth = width - sourceImage.getWidth();
					int fillHeight = height - sourceImage.getHeight();
					builder.addFilter(new Border(fillHeight/2, fillWidth/2+(fillWidth%2), fillHeight/2+(fillHeight%2), fillWidth/2, Color.WHITE));
				}
			}
			builder.outputQuality(DEFAULT_JPG_QUALITY).outputFormat(DEFAULT_OUTPUT_FORMAT).toFile(destFile);

			
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		return new Long(destFile.length() / 1024).intValue();
	}
	
}
