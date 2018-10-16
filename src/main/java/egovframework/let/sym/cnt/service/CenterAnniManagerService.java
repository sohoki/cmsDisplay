package egovframework.let.sym.cnt.service;

import java.util.List;

public interface CenterAnniManagerService {
	
	
    List<CenterInfoAnniversaryVO> selectCenterAnniManageListByPagination(CenterInfoAnniversaryVO searchVO) throws Exception;
	
	CenterInfoAnniversaryVO selectCenterAnniManageDetail(String centerAnniDay) throws Exception;
	
	int selectCenterAnniManageListTotCnt_S(CenterInfoAnniversaryVO searchVO) throws Exception;
	
	int selectCenterAnniRetgCheck(CenterInfoAnniversaryVO searchVO) throws Exception;
	
	int insertCenterAnniManage(CenterInfoAnniversary vo) throws Exception;
	
	int updateCenterAnniManage(CenterInfoAnniversary vo) throws Exception;
	
	int deleteCenterAnniManage(String centerAnniDay) throws Exception;

	int deleteCenterID(String centerId)throws Exception;
}
