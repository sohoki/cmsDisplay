package egovframework.let.sts.cnt.service;

import java.util.List;

public interface ContentDetailInfoService {

	
	List<ContentDetailInfo> selectContentDetailLst (String conSeq )throws Exception;
	
    ContentDetailInfo selectContentDetail(String detailSeq )throws Exception;
    
    List<ContentDetailInfo> selectConDetailCombo(String conSeq )throws Exception;
    
    List<ContentDetailInfo> selectContentDetailDidPage(ContentDetailInfo  searchVO )throws Exception;
	
	int insertContentDetailManage (ContentDetailInfo vo)throws Exception;
	
	int updateContentDetailManage(ContentDetailInfo vo)throws Exception;
	
	int updateContentDetailTimeManage(String detailSeq )throws Exception;
	
	 int deleteContentDetailManage(String detailSeq )throws Exception;
	 	 
	 int selectMaxDetail() throws Exception;
	 
	 
	 int selectPageSeqCheck(String conSeq, String detailOrder) throws Exception;
	 
	 int selectPageSeqCheckPage01(String conSeq) throws Exception;
	 
	 int selectPageSeqCheckPage02(String conSeq) throws Exception;
	 
	 
	 int selectPageSeqCheckPage01Cnt(String conSeq) throws Exception;
	 
	 int selectPageSeqCheckPage02Cnt(String conSeq) throws Exception;
	 
	 
	 int selectConDetailCnt (String conSeq) throws Exception; 
	 
	 int deleteContentDetailConSeq(String conSeq) throws Exception;
	
}
