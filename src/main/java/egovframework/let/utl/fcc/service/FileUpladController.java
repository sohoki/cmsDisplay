package egovframework.let.utl.fcc.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.DemuxerTrack;
import org.jcodec.common.NIOUtils;
import org.jcodec.common.SeekableByteChannel;
import org.jcodec.common.model.Picture;
import org.jcodec.containers.mp4.demuxer.MP4Demuxer;
import org.jcodec.scale.AWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sts.cnt.service.ContentFileInfo;
import egovframework.let.sts.cnt.service.ContentFileInfoManageService;
import egovframework.let.sts.cnt.service.ContentFileInfoVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;


//동영상 이미지 캡처ㅜ

@Controller
public class FileUpladController {

	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUpladController.class);
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	    
    @Resource(name="ContentFileInfo")
	private ContentFileInfoManageService conFileService;
    
    @Resource(name="egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;    
    
    
	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String dragAndDrop(Model model) {         
        return "fileUpload";         
    }
	
	@RequestMapping(value="/backoffice/sub/agentManage/mp3Detail.do")
	public String mp3Detail (@ModelAttribute("loginVO") LoginVO loginVO
								, @ModelAttribute("ContentFileInfo")  ContentFileInfo vo                    
								, HttpServletRequest request
								, BindingResult bindingResult		            
					            , ModelMap model
					            ) { //Multipart로 받는다.
		try{
		model.addAttribute("regist", vo);		
	    if (vo.getMode() == null) {vo.setMode("Ins");}	    
		if (!vo.getMode().equals("Ins")){
			//파일 상세 정보 알아보기
			vo = conFileService.selectFileDetail(vo.getAtchFileId());
			model.addAttribute("regist", vo);	
		}
		
		}catch(Exception e){
			System.out.println("Error:" + e.toString());
		}
		return "/backoffice/popup/playlistDetail";		
	}

	@RequestMapping(value="/backoffice/sub/agentManage/mp3Upload.do")	
	public String mp3Upload( @ModelAttribute("ContentFileInfo")  ContentFileInfo vo
			                       , MultipartHttpServletRequest multipartRequest
    		                       , HttpServletRequest request
    		                       , BindingResult bindingResult
    		                       , ModelMap model
    		                       ) { //Multipart로 받는다.
		model.addAttribute("regist", vo);		
		Iterator<String> itr =  multipartRequest.getFileNames();
        
        String filePath = propertiesService.getString("Globals.fileStorePath") ;        
        String streFileNm = request.getParameter("orignlFileNm") != null ? request.getParameter("orignlFileNm") : "";
        String fileNm = request.getParameter("fileNm") != null ? request.getParameter("fileNm") : "";
        
        System.out.println("fileNm:"+fileNm);
        
        while (itr.hasNext()) { //받은 파일들을 모두 돌린다.
                        
             
            MultipartFile mpf = multipartRequest.getFile(itr.next());      
            String originalFilename = mpf.getOriginalFilename();
            String fileFullPath = "";
            String Ext = "";       	      
            
            try {
            	//디렉톨 생성 여부 확인 
            	String inDate   = new java.text.SimpleDateFormat("yyyyMM").format(new java.util.Date());            	
            	File filedir = new File(filePath+inDate);
            	
            	if (!filedir.isDirectory()){
            		filedir.mkdir();
            	}
                //파일 저장
            	fileFullPath = filePath+inDate+"/" + originalFilename;            	
            	File file_s =  new File(fileFullPath );
            	Ext = fileExt(file_s,".");            	            	
            	String atchFileId = egovFileIdGnrService.getNextStringId();
            	file_s = rename(file_s, atchFileId+"."+Ext);
            	
                mpf.transferTo(file_s); //파일저장 실제로는 service에서 처리                
                
                
                String  thumnail = getDurationWithMp3Spi(file_s);
                               
                vo.setAtchFileId(atchFileId);
        		//vo.setFileStreCours(filePath+"/"+inDate);
				//20170204 수정
                System.out.println("mp3duration:"+thumnail);
                
                if (thumnail != null && !thumnail.equals("F")){
                	
                    String[] fileInfos = 	thumnail.split("/");
                    System.out.println("mp3duration:"+fileInfos[1].toString());
                    System.out.println("mp3duration:"+fileInfos[0].toString());
                	vo.setFileThumnail(fileInfos[1].toString()  );
                	vo.setPlayTime(fileInfos[0].toString());                	
                	
                }
                
				vo.setFileStreCours(filePath+inDate+"/");
        		vo.setStreFileNm(file_s.getName());
        		vo.setOrignlFileNm(streFileNm);
        		vo.setFileExtsn(fileExt(file_s,"."));        		
        		vo.setFileSize( fileSize( file_s ));
                vo.setFileOrder( Integer.toString( Integer.parseInt(atchFileId.replace("FILE_",""))));
                vo.setUseYn("Y");
                conFileService.insertFileManage(vo);
                model.addAttribute("status", Globals.STATUS_SUCCESS);
                
            } catch (Exception e) { 
            	
            	model.addAttribute("status", Globals.STATUS_FAIL);
                e.printStackTrace();
            }
            
       }          
        return "/backoffice/popup/playlistDetail";        
	}

	@RequestMapping(value = "/backoffice/sub/conManage/fileUpload") //ajax에서 호출하는 부분
	@ResponseBody
    public Map upload(MultipartHttpServletRequest multipartRequest
    		                       , HttpServletRequest request
    		                       ) { //Multipart로 받는다.
          
        Iterator<String> itr =  multipartRequest.getFileNames();
         
        String filePath = propertiesService.getString("Globals.fileStorePath") ;        
        String Message = "";
        
        String regGroupId = request.getParameter("groupId");
        
        Map<String, String> map = new HashMap<String, String>();
        while (itr.hasNext()) { //받은 파일들을 모두 돌린다.
                        
             
            MultipartFile mpf = multipartRequest.getFile(itr.next());      
            String originalFilename = mpf.getOriginalFilename(); //파일명
            String fileFullPath = "";
            String Ext = "";        	      
            
            try {
            	//디렉톨 생성 여부 확인 
            	String inDate   = new java.text.SimpleDateFormat("yyyyMM").format(new java.util.Date());
            	String regDate   = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
            	File filedir = new File(filePath+inDate);
            	
            	if (!filedir.isDirectory()){
            		filedir.mkdir();
            	}
                //파일 저장
            	fileFullPath = filePath+inDate+"/"+ originalFilename;
            	File file_s =  new File(fileFullPath );            	
            	Ext = fileExt(file_s,".");            	            	
            	String atchFileId = egovFileIdGnrService.getNextStringId();
            	file_s = rename(file_s, atchFileId+"."+Ext);            	
                mpf.transferTo(file_s); //파일저장 실제로는 service에서 처리                
                
                ContentFileInfo vo = new ContentFileInfo();
                
                String  thumnail = null;
                boolean nowConMusicPOP = false;
                // DB 에 저장
                //동영상 파일시 썸네일 파일 생성 
                if (fileExt(file_s,".").equals("mp4") || fileExt(file_s,".").equals("avi") || fileExt(file_s,".").equals("webm") ){
                	thumnail = getImageFromFrame(file_s.toString(),  filedir.toString());
                	nowConMusicPOP = false;
                } else if(fileExt(file_s,".").equals("mp3") ||fileExt(file_s,".").equals("wav") || fileExt(file_s,".").equals("mid")) {
                	thumnail = getDurationWithMp3Spi(file_s);
                	
                    if (thumnail != null && !thumnail.equals("F")){
                    	
                        String[] fileInfos = 	thumnail.split("/");
                        System.out.println("mp3duration:"+fileInfos[1].toString());
                        
                        System.out.println("mp3duration:"+fileInfos[0].toString());
                    	vo.setFileThumnail("no_image.png");
                    	vo.setPlayTime(fileInfos[0].toString());                	
                    	nowConMusicPOP = true;
                    }
                	
                }
                
                vo.setAtchFileId(atchFileId);
                if (thumnail != null && !thumnail.equals("")&& !thumnail.equals("Fail")  && !nowConMusicPOP){
                	
                    String[] fileInfos = 	thumnail.split(":");
                	vo.setFileThumnail(fileInfos[0].toString()  );
                	vo.setPlayTime(fileInfos[1].toString());        
                }
                
				vo.setFileStreCours(filePath+inDate+"/");
        		vo.setStreFileNm(file_s.getName());
        		vo.setOrignlFileNm(originalFilename);
        		vo.setFileExtsn(fileExt(file_s,"."));        		
        		vo.setFileSize( fileSize(  file_s ));
                vo.setFileOrder( Integer.toString( Integer.parseInt(atchFileId.replace("FILE_",""))));
                ContentFileInfoVO contentFileInfoVO = new ContentFileInfoVO();
                contentFileInfoVO.setStreFileNm(file_s.getName());
                contentFileInfoVO.setOrignlFileNm(originalFilename);
                contentFileInfoVO.setSearchKeyword(file_s.getName());
                contentFileInfoVO.setSearchCondition("atchFileId");
                contentFileInfoVO.setMediaType(modifyExtension(originalFilename));
                 
                vo.setGroupId(regGroupId);

                if(!contentFileInfoVO.getMediaType().equals("MUSIC")){
                	contentFileInfoVO.setFileGubun("");
                	contentFileInfoVO.setNotConType("MUSIC");
                } else {
                	contentFileInfoVO.setFileGubun("");
                	contentFileInfoVO.setNotConType("CONTENT");
                }
                
                if(conFileService.selectFilePageListByPaginationTotCnt_S(contentFileInfoVO) < 1) {
                	conFileService.insertFileManage(vo);
                	map.put("message", "success");
                	map.put("originalFilename", originalFilename);
                } else {
                	map.put("message", "fail");
                	map.put("originalFilename", originalFilename);
                }
            } catch (Exception e) {                
                e.printStackTrace();
                map.put("message", "fail");
            }
            
       }
        return map;
    }
	public String fileSize(File f){
		String fileSize = "";
		if (f.exists()){
			fileSize =  Long.toString(f.length());
		}else {
			fileSize = "0";
		}
		return fileSize;
	}
	
	
	//썸네일 이미지 
	private static String getImageFromFrame(String _fileNm, String _filePath) {
		
		
		
	    Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String fileName = _fileNm;// videoFile.getAbsolutePath();
		String baseName = fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.lastIndexOf("."));
		String savePath = fileName.substring(0, fileName.lastIndexOf(File.separator));
		
        String thumnailFull = _filePath+ File.separator + baseName+"_thumnail.png";        
        String thumnail = baseName+"_thumnail.png";
		
		String duration = "";
		double frameNumber = 0d;
		try {
			File videoFile = new File(fileName);
			
			SeekableByteChannel bc = NIOUtils.readableFileChannel(videoFile);
			MP4Demuxer dm = new MP4Demuxer(bc);
			DemuxerTrack vt = dm.getVideoTrack();
			frameNumber = vt.getMeta().getTotalDuration() / 5.0;
			
			
			
			System.out.println(vt.getMeta().getTotalDuration());
			duration =  Double.toString(vt.getMeta().getTotalDuration()) ;
			System.out.println(frameNumber);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Picture frame = FrameGrab.getNativeFrame(new File(fileName), frameNumber);
			BufferedImage img = AWTUtil.toBufferedImage(frame);
			File pngFile = new File(thumnailFull);
			if (!pngFile.exists()) {
				pngFile.createNewFile();
			}
			ImageIO.write(img, "png", pngFile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JCodecException e) {
			e.printStackTrace();
		}
		
		return thumnail+":"+duration;

	}
	private String fileExt(File f, String _seq){
		String ext = "";
		 int dot =  f.getName().lastIndexOf(_seq);
		 if (dot != -1) {                              //확장자가 없을때		
		      ext =  f.getName().substring(dot + 1);
		} else {                                     //확장자가 있을때		      
		      ext = "";
		}
		return ext;
	}
	public File rename(File f, String fileNm) {             //File f는 원본 파일
	 
		//if (createNewFile(f)) return f;        //생성된 f가 중복되지 않으면 리턴
		
	    String name = f.getName();
	    String body = null;
	    String ext = null;
	    
	    
	    int dot = name.lastIndexOf(".");
	    if (dot != -1) {                              //확장자가 없을때
	    	body = fileNm;
		    ext = "";
	    } else {                                     //확장자가 있을때	      
	      body  = fileNm;
	      ext = name.substring(dot);
	    }	    
	    int count = 0;
	    //중복된 파일이 있을때
	    //파일이름뒤에 a숫자.확장자 이렇게 들어가게 되는데 숫자는 9999까지 된다.
	    
	    if ( fileExites(body) && count == 0   ) {
	    	  String newName = body + ext;
		      f = new File(f.getParent(), newName);
		      LOGGER.debug("파일이 없을때  filenm:"+ newName);
	    }else {	    
		    while (!createNewFile(f) && count < 9999) {  
		      count++;
		      String newName = body+"[" + count +"]" + ext;
		      LOGGER.debug("파일이 있을때  filenm:"+ newName);
		      f = new File(f.getParent(), newName);
		    }
		
	    }
	    
	    return f;
	 }
	//파일 업로드 확인 
	public  String uploadFileNm(List<MultipartFile> mpf, String filePath){
		
		String fileNm = "";
		String ext = "";
        try {
        	
        	for (MultipartFile mFile : mpf) {
        		
        		
	            String originalFilename = mFile.getOriginalFilename(); //파일명	     	            
	            String fileFullPath = filePath + originalFilename;
	        	File file_s =  new File(fileFullPath);	        	
	        	 
	     	    int dot = originalFilename.lastIndexOf(".");
	     	    if (dot != -1) {                              //확장자가 없을때	     	    
	     	      ext = originalFilename.substring(dot);
	     	    } else {                                     //확장자가 있을때	     	    
	     	      ext = "";
	     	    }		     	    
	     	    fileNm = UUID.randomUUID().toString().replace("-", "") + ext;	     	  
	        	file_s = rename(file_s, fileNm);
	        	mFile.transferTo(file_s);
				fileNm = file_s.getName();
        	}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug("uploadFileNm IllegalStateException :" + e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug("uploadFileNm IOException :" + e.toString());
		} catch (Exception ex){			
			LOGGER.debug("uploadFileNm Exception :" + ex.toString());
		}        
		return fileNm;
	}
    //파일 생성 
	private boolean createNewFile(File f) { 
	    try {
	      return f.createNewFile();                        //존재하는 파일이 아니면
	    }catch (IOException ignored) {
	      return false;
	    }
	}
	//mp3 재생시간 
	private static String getDurationWithMp3Spi(File file) throws UnsupportedAudioFileException, IOException {

	    AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
	    
	    String mp3duration = null;
	    
	    if (fileFormat instanceof TAudioFileFormat) {
	        Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
	        String key = "duration";
	        Long microseconds = (Long) properties.get(key);
	        int mili = (int) (microseconds / 1000);
	        int sec = (mili / 1000);
	        int sec_M = (mili / 1000) % 60;
	        int min = (mili / 1000) / 60;
	        
	        mp3duration = Integer.toString(sec)+"/"+ Integer.toString(min)+":"+ Integer.toString(sec_M);

	    } else {
	    	mp3duration = "F";
	        throw new UnsupportedAudioFileException();	        
	    }	    
	    return mp3duration;

	}
	private boolean fileExites( String fileNm){
		try{
			File f = new File (propertiesService.getString("Globals.fileStorePath")+"/"+fileNm);
			if (f.exists()){
				return false;
			}else {
				return true;
			}
		}catch (Exception e){
			LOGGER.debug("fileExites Error:" + e.toString());
			return false;
		}
		
	}
	
	//파일 삭제 
    public  boolean deleteFile (String fileNm ) {
    	
    	try{        		        	
    		File delFile = new File ( fileNm);
    		delFile.delete();
    		return true;    		
    	}catch(Exception e){
    		LOGGER.debug("file Delete error{0}", e.toString());
    		return false;
    	}    	
    }
    // 파일 확장자 변환
    private String modifyExtension(String extension){
    	LOGGER.info("extension : " +  extension);
    	String type = extension.split("\\.")[1].toString().toLowerCase();
    	LOGGER.info("type : " + type);
    	if("mp4".equals(type) || "avi".equals(type) || "mpeg".equals(type)) {
    		extension = "MEDIA";
		} else if("jpg".equals(type) || "jpeg".equals(type) || "gif".equals(type) || "png".equals(type) || "bmp".equals(type)) {
			extension = "IMAGE";
		} else if("mp3".equals(type) || "wav".equals(type) || "mid".equals(type)) {
			extension = "MUSIC";
		} else {
			extension = "MUSIC";
		}
    	return extension;
    }
    
    
	
}
