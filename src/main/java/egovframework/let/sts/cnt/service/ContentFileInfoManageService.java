package egovframework.let.sts.cnt.service;

import java.util.List;

public interface ContentFileInfoManageService {

	List<ContentFileInfoVO> selectFilePageListByPagination(ContentFileInfoVO searchVO) throws Exception;
	
	List<ContentFileInfoVO> selectFileContentList (String conSeq)throws Exception;
	
	List<ContentFileInfoVO>  selectFileContentPageList (String detailSeq)throws Exception;
	
	
	List<ContentFileInfoVO> selectFileContentLstDid(ContentFileInfoVO searchVO) throws Exception;
	
	List<ContentFileInfo> selectFileListCombo() throws Exception;
	
    List<ContentFileInfoVO>  selectBasicFilePageListByPagination (ContentFileInfoVO  searchVO)throws Exception;
	//½Å±Ô
    List<ContentFileInfoVO>  selectBasicFileDetailPageListByPagination (ContentFileInfoVO  searchVO)throws Exception;
    
    
	int selectBasicFilePageListByPaginationTotCnt_S(ContentFileInfoVO  searchVO)throws Exception;
	
	int selectFilePageListByPaginationTotCnt_S (ContentFileInfoVO searchVO)throws Exception;
	
	int  selectFileListTotCnt_S (String conSeq) throws Exception;
	
	List<ContentFileInfoVO>  selectMediaConnList (String atchFileId) throws Exception;
	
	ContentFileInfoVO selectFileDetail   (String atchFileId) throws Exception;

	int insertFileManage (ContentFileInfo vo) throws Exception;
	
	int updateFileManage (ContentFileInfo vo) throws Exception;
	
	int updateFileManageUseYn(ContentFileInfo vo) throws Exception;
	
	int updateFileDetailInfo(ContentFileInfo vo) throws Exception; 
	
	int deleteFileManage (String atchFileId) throws Exception;
	
	int deleteFileConSeq(String conSeq) throws Exception;
	
	
	int selectFileContentTotCnt_S(String detailSeq) throws Exception;
	
	
	
	
}
