package egovframework.let.sts.cnt.service;

import java.util.List;

public interface ContentDetailFileInfoManageService {

	List<ContentDetailFileInfoVO> selectContentDetailFileLst (ContentDetailFileInfoVO searchVO) throws Exception;
	
	ContentDetailFileInfoVO selectContentDetailFileInfo(String atchFileId) throws Exception;
	
	
	ContentDetailFileInfoVO selectContentDetailFileInfoFileSeq(String fileSeq) throws Exception;
	
	String selectDetailContentSumTime(String detailSeq) throws Exception;
	
	
	
	int selectPageSeqCheckFilePageCnt (ContentDetailFileInfoVO searchVO) throws Exception;
	
	int selectTimeIntevalNullCheck(String conSeq) throws Exception;
	
	int selectMaxfileSeq(ContentDetailFileInfo vo) throws Exception;
	
	int insertContentDetailFileManage(ContentDetailFileInfo vo) throws Exception;
	
	int updateContentDetailFileManage(ContentDetailFileInfo vo) throws Exception;
	
	int updateContentOrderDetailFileManage(ContentDetailFileInfo vo) throws Exception;
	
	int updateContentDetailFileTimeIntervalManage(ContentDetailFileInfo vo) throws Exception;
	
	int deleteContentDetailFileManage(String  fileSeq) throws Exception;
	
	
}
