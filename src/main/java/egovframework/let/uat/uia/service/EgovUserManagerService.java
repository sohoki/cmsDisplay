package egovframework.let.uat.uia.service;


import java.util.List;

import egovframework.let.uat.uia.service.GnrMber;
import egovframework.let.uat.uia.service.GnrMberVO;


public interface EgovUserManagerService {
	
	
	int deleteUserManage(String mberId) throws Exception;
	int insertUserManage(GnrMber vo) throws Exception;
	int updateUserManage(GnrMber gnrmber) throws Exception;


	GnrMberVO selectUserManageDetail(GnrMberVO vo) throws Exception;
	
	List<?> selectUserManageListByPagination(GnrMberVO searchVO) throws Exception;    
    int selectUserManageListTotCnt_S(GnrMberVO searchVO) throws Exception;

    int selectUserMangerIDCheck(String code) throws Exception;
    
    int selectPwSearchUserInfo(GnrMberVO vo) throws Exception;
    
    int updateUserPassword(GnrMber vo) throws Exception;
}
