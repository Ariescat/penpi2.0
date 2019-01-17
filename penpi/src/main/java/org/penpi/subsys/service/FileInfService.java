package org.penpi.subsys.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.penpi.core.commons.base.BaseRepository;
import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.file.FileInfDto;
import org.penpi.core.commons.file.image.SmallPictUtil;
import org.penpi.core.commons.helper.FileUrlHelper;
import org.penpi.core.commons.helper.SmallPictImageQueueHelper;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.queue.data.SmallPictEventData;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.subsys.Global;
import org.penpi.subsys.code.BoolCodeEnum;
import org.penpi.subsys.entity.FileInf;
import org.penpi.subsys.entity.SmallPict;
import org.penpi.subsys.entity.SmallPictSetup;
import org.penpi.subsys.repository.FileInfRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileInfService extends BaseService<FileInf, Integer, FileInfRepository> {

	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private static final int TEMP_FILE_CACHE_SIZE = 1000;
	
	private SmallPictImageQueueHelper smallPictImageQueueHelper;
	
	@Autowired
	public void setSmallPictImageQueueHelper(SmallPictImageQueueHelper smallPictImageQueueHelper) {
		this.smallPictImageQueueHelper = smallPictImageQueueHelper;
	}

	/**
	 * key值采用sessionId+时间标记的格式，例如"9dff284715064894881984838e-13352353"
	 */
	private Map<String, FileInfDto> tempFileCache = new LinkedHashMap<String, FileInfDto>(1000){
		private static final long serialVersionUID = 1L;
		
		@Override
		protected boolean removeEldestEntry(Map.Entry<String, FileInfDto> eldest) {
		    // 当前记录数大于设置的最大的记录数，删除最旧记录（即最近访问最少的记录）
		    return size() > TEMP_FILE_CACHE_SIZE;
		}
	};

	private static int seed = 0;
	
	/**
	 * 将文件保存到临时目录.
	 * @param fileName
	 * @param fileStream
	 * @return
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public FileInfDto saveTempFile(String fileName, InputStream fileStream) throws FileNotFoundException, UnsupportedEncodingException{
		FileOutputStream fileOutputStream = null;
		try{
			String fileTypeNm = FilenameUtils.getExtension(fileName);
			String fileUUID = UUID.randomUUID().toString();
			String filePath = new StringBuilder(Global.TEMP_DIR).append(File.separator).append(fileUUID).append(".").append(fileTypeNm).toString();
			StringBuilder outputFilePath = new StringBuilder(WebContextHolder.getWarPath()).append(File.separator).append(filePath);
			fileOutputStream = new FileOutputStream(outputFilePath.toString());
			IOUtils.copy(fileStream, fileOutputStream);
			FileInfDto fileInfDto = new FileInfDto();
			fileInfDto.setFileNm(fileName);
			fileInfDto.setFilePath(filePath);
			fileInfDto.setFileTypeNm(fileTypeNm);

			//TODO 下面语句可以添加CDN或流量地址转换
			fileInfDto.setFileUrl(new StringBuilder(WebContextHolder.getContextPath()).append("/").append(Global.TEMP_DIR).append("/").append(fileUUID).append(".").append(fileTypeNm).toString());
			synchronized(this) {
				fileInfDto.setFileInfId(- (((int)(System.currentTimeMillis() & 0xffffffL) << 7) + seed)); //保证在一个会话里时间ID不一样，为负数的是临时ID
				seed = (seed + 1) & 0xaf;
			}
			tempFileCache.put(WebContextHolder.getSessionContextStore().getSessionId() + fileInfDto.getFileInfId(), fileInfDto);
			return fileInfDto;
		}catch(IOException ex){
			throw new RuntimeException(ex);
		}finally{
			if(fileOutputStream != null){
				IOUtils.closeQuietly(fileOutputStream);
			}
		}
	}
	
	/**
	 * 根据ID获得文件信息列表，如果是负数从临时文件缓存读取，否则从数据库读取
	 * @param fileInfIds
	 * @return
	 */
	public List<FileInfDto> loadFileInfDtoList(String fileInfIds){
		List<FileInfDto> result = new ArrayList<FileInfDto>();
		for(String fileInfIdStr: fileInfIds.split(Global.DEFAULT_TEXT_SPLIT_CHAR)) {
			if(fileInfIdStr.startsWith("-")) {
				FileInfDto fileInfDto = tempFileCache.get(WebContextHolder.getSessionContextStore().getSessionId() + fileInfIdStr);
				if(fileInfDto != null) {
					result.add(fileInfDto);
				}
			}else {
				FileInfDto fileInfDto = new FileInfDto();
				FileInf fileInf = SpringContextHelper.getBean(getClass()).getById(Integer.parseInt(fileInfIdStr));
				BeanUtils.copyProperties(fileInf, fileInfDto);
				
				//TODO 下面语句可以添加CDN或流量地址转换
				fileInfDto.setFileUrl(new StringBuilder(WebContextHolder.getContextPath()).append("/").append(fileInf.getFilePath().replace(File.separatorChar, '/')).toString());
				result.add(fileInfDto);
			}
		}
		return result;
	}

	public static final class SyncFileInfResult{
		private Map<String, String> replaceMap;
		private String fileInfIds;

		public SyncFileInfResult(Map<String, String> replaceMap, String fileInfIds) {
			this.replaceMap = replaceMap;
			this.fileInfIds = fileInfIds;
		}

		public Map<String, String> getReplaceMap() {
			return replaceMap;
		}
		public void setReplaceMap(Map<String, String> replaceMap) {
			this.replaceMap = replaceMap;
		}
		public String getFileInfIds() {
			return fileInfIds;
		}
		public void setFileInfIds(String fileInfIds) {
			this.fileInfIds = fileInfIds;
		}
	};

	/**
	 * 根据对象来同步更新所有的文件字段，包括图片ID集合的字段，图片ID字段。返回所有要更新的URL地址
	 * @param newEntity
	 * @param savedEntity
	 * @param entityClass
	 * @return replaceMap 
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String, String> syncBusinessObject(Integer businessObjectId, Object newEntity, Object savedEntity, Class<?> entityClass){
		Map<String, String> replaceMap = new HashMap<String, String>();
		for(Field field: entityClass.getDeclaredFields()) {
			if(Character.isUpperCase(field.getName().charAt(0))) {
				continue;
			}
			if(field.getName().endsWith(Global.PICT_ID_FIELD_SUFFIX) 
					|| field.getName().endsWith(Global.PICT_IDS_FIELD_SUFFIX)
					|| field.getName().equals(Global.FILEINF_ID_FIELD_SUFFIX)
					|| field.getName().equals(Global.FILEINF_IDS_FIELD_SUFFIX)
			){
				try {
					Object newValue = BeanUtils.getPropertyDescriptor(entityClass, field.getName()).getReadMethod().invoke(newEntity);
					Object oldValue = (savedEntity == null ? null : BeanUtils.getPropertyDescriptor(entityClass, field.getName()).getReadMethod().invoke(savedEntity));
					SyncFileInfResult syncFileInfResult = syncFileInfList(entityClass.getSimpleName(), businessObjectId, oldValue == null ? "" : oldValue.toString(), newValue == null ? "" : newValue.toString());
					if(field.getName().endsWith(Global.PICT_ID_FIELD_SUFFIX) || field.getName().equals(Global.FILEINF_ID_FIELD_SUFFIX)) {
						if(StringUtils.isEmpty(syncFileInfResult.getFileInfIds())) {
							BeanUtils.getPropertyDescriptor(entityClass, field.getName()).getWriteMethod().invoke(newEntity, (Object)null);
						}else {
							BeanUtils.getPropertyDescriptor(entityClass, field.getName()).getWriteMethod().invoke(newEntity, new Integer(syncFileInfResult.getFileInfIds()));
						}
						BaseService<Serializable, Integer, BaseRepository<Serializable, Integer>> baseService = (BaseService<Serializable, Integer, BaseRepository<Serializable, Integer>>) SpringContextHelper
								.getBean(Character.toLowerCase(newEntity.getClass().getSimpleName().charAt(0)) + newEntity.getClass().getSimpleName().substring(1) + "Service");
						baseService.saveAndFlush((Serializable)newEntity);
					}else {
						BeanUtils.getPropertyDescriptor(entityClass, field.getName()).getWriteMethod().invoke(newEntity, syncFileInfResult.getFileInfIds());
					}
					replaceMap.putAll(syncFileInfResult.getReplaceMap());

					//处理所有的小图
					if(field.getName().endsWith(Global.PICT_ID_FIELD_SUFFIX) || field.getName().endsWith(Global.PICT_IDS_FIELD_SUFFIX)){
						syncFileSmallPicts(entityClass.getSimpleName(), field.getName(), syncFileInfResult.getFileInfIds(),field.getName().endsWith(Global.PICT_IDS_FIELD_SUFFIX));
					}

				} catch (BeansException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					new RuntimeException(e);
				}
			}
		}
		return replaceMap;
	}

	/**
	 * 同步多个资源文件集合.
	 * 如果ID不在已有里的，就添加；如果有在的，就不管；如果不在的，就删除。
	 * @param businessClassNm 文件类名称，删除时应将对应的记录下的文件也清理
	 * @param businessObjectId 业务对象ID，新增时保存用
	 * @param savedFileInfIds 原保存的文件ID列表字符串
	 * @param newFileInfIds 当前保存更新后的文件ID列表 
	 * @return SyncFileInfResult 包括替换用的map，将文档内容里的temp url替换为upload url，以及替换后新增的文件ID列表
	 * 
	 * @Deprecated 使用syncBusinessObject
	 */
	private SyncFileInfResult syncFileInfList(String businessClassNm, Integer businessObjectId, String savedFileInfIds, String newFileInfIds){

		List<FileInfDto> fileInfBarDtoList = SpringContextHelper.getBean(FileInfService.class).loadFileInfDtoList(newFileInfIds);
		
		List<FileInf> savedFileInfList = new ArrayList<FileInf>();
		if(StringUtils.isNotBlank(savedFileInfIds)) {
			for(String oldFileInfId: savedFileInfIds.split(Global.DEFAULT_TEXT_SPLIT_CHAR)) {
				savedFileInfList.add(this.getById(new Integer(oldFileInfId)));
			}
		}
		
		List<Integer> savedFileInfIdList = new ArrayList<Integer>();
		for(FileInf savedFileInf : savedFileInfList){
			savedFileInfIdList.add(savedFileInf.getFileInfId());
		}
		
		List<FileInfDto> addFileInfBarDto = new ArrayList<FileInfDto>();
		
		List<Integer> updateFileInfIdList = new ArrayList<Integer>();
		for(FileInfDto fileInfBarDto : fileInfBarDtoList){
			if(fileInfBarDto.getFileInfId() < 0){ //新增
				addFileInfBarDto.add(fileInfBarDto);
			}else{
				updateFileInfIdList.add(fileInfBarDto.getFileInfId());
			}
		}
		
		Collection<Integer> deleteList = CollectionUtils.subtract(savedFileInfIdList, updateFileInfIdList);
		for(Integer deleteId: deleteList){
			FileInf fileInf = SpringContextHelper.getBean(getClass()).getById(deleteId);
			FileUtils.deleteQuietly(new File(WebContextHolder.getWarPath() + File.separator + fileInf.getFilePath()));
			
			SpringContextHelper.getBean(getClass()).delete(fileInf);

			//删除所有小图
			for(SmallPict smallPict: SpringContextHelper.getBean(SmallPictSearchService.class).findByKey(SmallPict.FILE_INF_ID, fileInf.getFileInfId())) {
				String smallPictPath = new StringBuilder(FilenameUtils.removeExtension(fileInf.getFilePath().replaceFirst(Global.UPLOAD_DIR, Global.SMALL_PICT_DIR)))
				.append("_")
				.append(smallPict.getSmallPictWidth())
				.append(Global.SMALL_PICT_SIZE_SPLIT_CHAR)
				.append(smallPict.getSmallPictHeight())
				.append(".")
				.append(SmallPictUtil.DEFAULT_OUTPUT_FORMAT)
				.toString();
				FileUtils.deleteQuietly(new File(WebContextHolder.getWarPath() + File.separator + smallPictPath));
				
				SpringContextHelper.getBean(SmallPictService.class).delete(smallPict);
			}
		}
		
		Map<String, String> replaceMap = new HashMap<String, String>();
		Map<Integer, Integer> replaceIdMap = new HashMap<Integer, Integer>();
		Date now = new Date();
		for(FileInfDto fileInfBarDto: addFileInfBarDto){
			File tempFile = new File(WebContextHolder.getWarPath() + File.separator + fileInfBarDto.getFilePath());
			Calendar calender = Calendar.getInstance();
			
			String moveDirPath = new StringBuilder(Global.UPLOAD_DIR)
				.append(File.separator)
				.append(calender.get(Calendar.YEAR))
				.append(File.separator)
				.append(calender.get(Calendar.MONTH) + 1)
				.append(File.separator)
				.append(calender.get(Calendar.DAY_OF_MONTH)).toString();
			try {
				//本地文件的策略
				FileUtils.moveFileToDirectory(tempFile, new File(WebContextHolder.getWarPath() + File.separator + moveDirPath), true);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			FileInf fileInf = new FileInf();
			fileInf.setFileNm(fileInfBarDto.getFileNm());
			fileInf.setFileTypeNm(fileInfBarDto.getFileTypeNm());
			fileInf.setBusinessClassNm(businessClassNm);
			boolean isPict = fileInf.getFileTypeNm().equalsIgnoreCase("png")|| fileInf.getFileTypeNm().equalsIgnoreCase("jpg")|| fileInf.getFileTypeNm().equalsIgnoreCase("gif"); 
			fileInf.setIfPict(BoolCodeEnum.fromValue(isPict).toCode());
			fileInf.setFileTime(now);
			fileInf.setFileSizeKb(new Long(tempFile.length() / 1024).intValue());
			fileInf.setFilePath(moveDirPath + File.separator + FilenameUtils.getName(fileInfBarDto.getFilePath()));
			fileInf.setBusinessObjectId(businessObjectId);
			SpringContextHelper.getBean(this.getClass()).save(fileInf);
			
			//小图逻辑：同步生成150x150内裁切小图，然后异步生成其它小图
			if(isPict) {
				SmallPict smallPict = new SmallPict();
				int fileSizeKb = SmallPictUtil.generateSmallPict(
					Global.DEFAULT_SMALL_PICT_SIZE, 
					Global.DEFAULT_SMALL_PICT_SIZE, 
					WebContextHolder.getWarPath() + File.separator + fileInf.getFilePath(),
					true
				);
				if(fileSizeKb != -1) { //原位置有图片则忽略
					smallPict.setFileInfId(fileInf.getFileInfId());
					smallPict.setFileSizeKb(fileSizeKb);
					smallPict.setSmallPictWidth(Global.DEFAULT_SMALL_PICT_SIZE);
					smallPict.setSmallPictHeight(Global.DEFAULT_SMALL_PICT_SIZE);
					smallPict.setFileTime(new Date());
					SpringContextHelper.getBean(SmallPictService.class).save(smallPict);
				}
			}

			replaceIdMap.put(fileInfBarDto.getFileInfId(), fileInf.getFileInfId());
			
			replaceMap.put(fileInfBarDto.getFileUrl(), FileUrlHelper.getFileSystemUrl(fileInf.getFilePath()));
		}
		List<String> fileInfIds = new ArrayList<String>();
		for(FileInfDto fileInfDto: fileInfBarDtoList) {
			if(fileInfDto.getFileInfId() < 0) { //add
				fileInfIds.add(replaceIdMap.get(fileInfDto.getFileInfId()).toString());
			}else {
				fileInfIds.add(fileInfDto.getFileInfId().toString());
			}
		}
		return new SyncFileInfResult(replaceMap, StringUtils.join(fileInfIds, Global.DEFAULT_TEXT_SPLIT_CHAR));
	}

	private void syncFileSmallPicts(String className, String fieldName, String fileInfIds, boolean isMulti) {
		if(StringUtils.isNotBlank(fileInfIds)) {
			for(String fileIdStr: fileInfIds.split(Global.DEFAULT_TEXT_SPLIT_CHAR)) {
				String filePath = SpringContextHelper.getBean(this.getClass()).getById(new Integer(fileIdStr)).getFilePath();
				List<SmallPictSetup> smallPictSetupList = SpringContextHelper.getBean(SmallPictSetupService.class).findByBusinessClassNmAndBusinessFieldNm(className, fieldName);
				for(SmallPictSetup smallPictSetup: smallPictSetupList){
					if(StringUtils.isNotBlank(smallPictSetup.getSmallPictSpec())) {
						for(String specSetupStr: smallPictSetup.getSmallPictSpec().split(Global.DEFAULT_TEXT_SPLIT_CHAR)) { //解析配置字符串，例如120x80o,150x100,175x200i
							boolean ifInnerCut = true;
							int outSpecIndex = specSetupStr.indexOf('o');
							if(outSpecIndex > -1) {
								ifInnerCut = false;
							}
							String[] sizeArray = specSetupStr.replaceAll("i|o", "").split(Global.SMALL_PICT_SIZE_SPLIT_CHAR);
							smallPictImageQueueHelper.publishEvent(new SmallPictEventData(smallPictSetup.getSmallPictSetupId(), new Integer(fileIdStr), filePath, Integer.parseInt(sizeArray[0]), Integer.parseInt(sizeArray[1]), ifInnerCut));
						}
					}
				}
			}
		}
		
	}

	/**
	 * 获得文件的对应服务器地址，支持负数（临时文件）.
	 * @param fileInfId 如果是负数则和sessionId一并获得临时会话的文件地址，否则获得文件库的文件地址。
	 * @param sessionId
	 * @return
	 */
	public String getFilePath(Integer fileInfId, String sessionId) {
		if (fileInfId < 0) {
			FileInfDto fileInfDto = tempFileCache.get(sessionId + fileInfId);
			if (fileInfDto == null) {
				return null;
			} else {
				return fileInfDto.getFilePath();
			}
		} else {
			return SpringContextHelper.getBean(this.getClass()).getById(fileInfId).getFilePath();
		}
	}

	public String getPictUrl(Integer fileInfId) { //获取图片地址，如果图片没有，则返回150x150的暂无图像地址
		if(fileInfId == null) {
			return new StringBuilder(WebContextHolder.getContextPath()).append('/')
					.append(Global.SMALL_PICT_DIR).append('/')
					.append(Global.SMALL_PICT_DEFAULT_DIR).append('/')
					.append(Global.SMALL_PICT_COMMON_DIR)
					.append('/').append(Global.DEFAULT_SMALL_PICT_SIZE)
					.append(Global.SMALL_PICT_SIZE_SPLIT_CHAR).append(Global.DEFAULT_SMALL_PICT_SIZE)
					.append(".png")
					.toString();
		}else {
			return WebContextHolder.getContextPath() + '/'+ SpringContextHelper.getBean(FileInfService.class).getById(fileInfId).getFilePath().replace('\\', '/');
		}
	}

	
	/**
	 * 如果图片ID为空或没有该记录，则返回默认的指定大小的图片.
	 * 默认图片放到/war/smallpict/default/common下，以 长x宽 的格式存放，例如100x100.jpg
	 * @param fileInfId
	 * @param width
	 * @param height
	 * @return
	 */
	public String getSmallPictUrl(Integer fileInfId, int width, int height) {
		return SpringContextHelper.getBean(this.getClass()).getSmallPictUrl(fileInfId, width, height, Global.DEFAULT_SMALL_PICT);
	}

	/**
	 * 获得小图的服务器相对地址
	 * @param fileInfId 文件ID
	 * @param width 小图宽
	 * @param height 小图高
	 * @param defaultPictPath 默认的（在war/smallpict/default/下的地址，支持按目录存放，目录使用url路径/ ）
	 * @return 小图的地址
	 */
	public String getSmallPictUrl(Integer fileInfId, int width, int height, String defaultPictPath) {
		FileInf fileInf = null;
		if(fileInfId != null) {
			fileInf = SpringContextHelper.getBean(this.getClass()).getById(fileInfId);
		}
		if(fileInf == null) {
			return new StringBuilder(WebContextHolder.getContextPath()).append('/')
					.append(Global.INIT_IMAGE_DIR).append('/')
					.append(Global.SMALL_PICT_DEFAULT_DIR).append('/')
					.append(defaultPictPath)
					.toString();
		}	
		String filePath = SpringContextHelper.getBean(this.getClass()).getById(fileInfId).getFilePath();
		int extSeperatorIndex = filePath.lastIndexOf(".");
		return new StringBuilder(WebContextHolder.getContextPath())
				.append('/').append(
					filePath.replace(File.separatorChar, '/')
					.substring(0, extSeperatorIndex)
					.replaceFirst(Global.UPLOAD_DIR, Global.SMALL_PICT_DIR)
				).append('_').append(width).append(Global.SMALL_PICT_SIZE_SPLIT_CHAR).append(height).append(".jpg").toString();
	}

	/**
	 * 异步处理所有的预置图片 @Async
	 * 注： 后期发现异步处理会导致该索引无法建立
	 * 1、通过FileInfSearchService getPriority处理
	 * 2、抛弃@Async
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	@Transactional
	public void processInitFile() {
		Date now = new Date();
		Iterable<FileInf> iter = SpringContextHelper.getBean(FileInfService.class).listAll();
		for(FileInf fileInf: iter){
			log.info(new StringBuilder("处理预置图片 ")
					.append(fileInf.getBusinessClassNm()).append(" ")
					.append(fileInf.getBusinessObjectId()).append(" ")
					.append(fileInf.getBusinessFieldNm()).toString());
			try {
				BaseService<Serializable, Integer, BaseRepository<Serializable, Integer>> baseService = (BaseService<Serializable, Integer, BaseRepository<Serializable, Integer>>) SpringContextHelper
						.getBean(Character.toLowerCase(fileInf.getBusinessClassNm().charAt(0)) + fileInf.getBusinessClassNm().substring(1) + "Service");
				Serializable serializable = baseService.getById(fileInf.getBusinessObjectId());
				if (serializable != null) {
					BeanUtils.getPropertyDescriptor(serializable.getClass(), fileInf.getBusinessFieldNm())
							.getWriteMethod().invoke(serializable, fileInf.getFileInfId());
				}
			} catch (BeansException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				log.error(e);
			}
			Calendar calender = Calendar.getInstance();
			String initImageDir = WebContextHolder.getWarPath() + File.separator + Global.INIT_IMAGE_DIR + File.separator;
			File sourceFile = new File(initImageDir + fileInf.getFilePath().replace('\\', File.separatorChar));
			String copyToDirPath = new StringBuilder(Global.UPLOAD_DIR)
				.append(File.separator)
				.append(calender.get(Calendar.YEAR))
				.append(File.separator)
				.append(calender.get(Calendar.MONTH) + 1)
				.append(File.separator)
				.append(calender.get(Calendar.DAY_OF_MONTH)).toString();
			try {
				//本地文件的策略
				FileUtils.copyFileToDirectory(sourceFile, new File(WebContextHolder.getWarPath() + File.separator + copyToDirPath));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			String fileName = fileInf.getFilePath().substring(fileInf.getFilePath().lastIndexOf("\\") + 1);
			fileInf.setFileNm(fileName);
			fileInf.setFileTypeNm(fileName.substring(fileName.lastIndexOf('.') + 1));
			boolean isPict = fileInf.getFileTypeNm().equalsIgnoreCase("png")
					|| fileInf.getFileTypeNm().equalsIgnoreCase("jpg")
					|| fileInf.getFileTypeNm().equalsIgnoreCase("gif");
			fileInf.setIfPict(BoolCodeEnum.fromValue(isPict).toCode());
			fileInf.setFileTime(now);
			fileInf.setFileSizeKb(new Long(sourceFile.length() / 1024).intValue());
			fileInf.setFilePath(copyToDirPath + File.separator + fileName);
			SpringContextHelper.getBean(this.getClass()).save(fileInf);

			//小图逻辑：同步生成150x150内裁切小图，然后异步生成其它小图
			if(isPict) {
				SmallPict smallPict = new SmallPict();
				int fileSizeKb = SmallPictUtil.generateSmallPict(
					Global.DEFAULT_SMALL_PICT_SIZE, 
					Global.DEFAULT_SMALL_PICT_SIZE, 
					WebContextHolder.getWarPath() + File.separator + fileInf.getFilePath(),
					true
				);
				if(fileSizeKb != -1) { //原位置有图片则忽略
					smallPict.setFileInfId(fileInf.getFileInfId());
					smallPict.setFileSizeKb(fileSizeKb);
					smallPict.setSmallPictWidth(Global.DEFAULT_SMALL_PICT_SIZE);
					smallPict.setSmallPictHeight(Global.DEFAULT_SMALL_PICT_SIZE);
					smallPict.setFileTime(new Date());
					SpringContextHelper.getBean(SmallPictService.class).save(smallPict);
				} 
				this.syncFileSmallPicts(fileInf.getBusinessClassNm(), fileInf.getBusinessFieldNm().toString(), fileInf.getFileInfId().toString(), false); //只处理单图片逻辑
			}
		}

	}
	
	
 
}

