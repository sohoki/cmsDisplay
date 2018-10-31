package egovframework.let.sym.cnt.service;


import java.util.List;

public interface  CenterInfoManageService {

	List<CenterInfoVO>  selectCenterInfoManageListByPagination(CenterInfoVO searchVo);
	
	List<CenterInfoVO>  selectCenterInfoManageCombo(CenterInfoVO searchVo);
	
	CenterInfoVO selectCenterInfoManageDetail(String centerId);
	
	String selectCenterTimeInfo(CenterInfo vo);
	
	String selectCenterInfoBrod(String centerId) throws Exception;
	
	List<CenterInfo> selectCenterBrodCombo(String centerId);
	
	int selectCenterInfoManageListTotCnt_S(CenterInfoVO searchVo);
	
	int insertCenterInfoManage(CenterInfo vo);
	
	int updateCenterInfoManage(CenterInfo vo);
	
	int deleteCenterInfoManage(String centerId);

	List<CenterInfoVO> selectGroupInCenterInfo(CenterInfoVO searchVo);
	
	
}
