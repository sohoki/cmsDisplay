package egovframework.let.sts.cnt.service;

import java.util.List;

public interface ContentInfoManageService {


	        int selectContentInfoManageListTotCnt_S(ContentInfoVO SearchVO) throws Exception;
			
            List<ContentInfoVO>    selectContentInfoManageListByPagination(ContentInfoVO SearchVO) throws Exception;
			
            List<ContentInfo>  selectNextCombo(String conSeq) throws Exception;
            
            List<ContentInfo> selectSearcHCombo (String searchKeyword) throws Exception;
            
			ContentInfoVO selectContentInfoManageDetail(String conSeq) throws Exception;
			
			int insertContentInfoManage(ContentInfo vo) throws Exception;
			
			int updateContentInfoManage(ContentInfo vo) throws Exception;
			
			int deleteContentInfoManage (String conSeq) throws Exception;

	
	
}
