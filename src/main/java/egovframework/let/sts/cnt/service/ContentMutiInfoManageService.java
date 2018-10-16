package egovframework.let.sts.cnt.service;


import java.util.List;


public interface ContentMutiInfoManageService {

	  List<ContentMutiInfoVO> selectContentMutiInfoManageListByPagination(ContentMutiInfoVO searchVO) throws Exception;
	  
	  int selectContentMutiInfoManageListTotCnt_S(ContentMutiInfoVO searchVO) throws Exception;
	  
	  
	  List<ContentMutiInfoVO> selectDIDContentLst(ContentMutiInfoVO searchVO) throws Exception;
	  
	  List<ContentMutiInfoVO> selectNextContentMutiInfo(String conSeq) throws Exception;
	  
	  ContentMutiInfoVO selectContentMutiInfoManageDetail(String conSeq) throws Exception;
	  
	  ContentMutiInfoVO selectContentMutiInfoManageView(String conSeq) throws Exception;
	  
	  List<ContentMutiInfo> selectNextSeqList(String conSeq) throws Exception;
	  
	  String selectContentFileInfo (String conSeq) throws Exception;
	  
	  String selectContentFileInfoLocal(String conSeq) throws Exception;
	  
	  String selectMaxTimeInterval(String conSeq) throws Exception;
	  
	  String selectMaxSeqInfo() throws Exception;
      
	  int insertContentMutiInfoManage(ContentMutiInfo vo)throws Exception;
	  
	  int updateContentMutiInfoManage(ContentMutiInfo vo)throws Exception;
	  
	  int updateContentMutiFile (ContentMutiInfo vo) throws Exception;
	  
	  int updateContentMutiFileLocal (ContentMutiInfo vo) throws Exception;
	  
	  int deleteContentMutiInfoManage(String conSeq)throws Exception;

	  
	
}
