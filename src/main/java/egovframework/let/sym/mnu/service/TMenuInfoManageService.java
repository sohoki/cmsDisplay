package egovframework.let.sym.mnu.service;


import java.util.List;

import egovframework.com.cmm.LoginVO;

public interface TMenuInfoManageService {

	
    List<TMenuInfoVO> selectManuInfoManageListByPagination(TMenuInfoVO searchVO) throws Exception;
	
	List<TMenuInfoVO> selectMenuInfoManageCombo () throws Exception;
	
	List<TMenuInfo> selectTMenuComboLst ( String didId ) throws Exception;
	
	List<TMenuInfoVO> selectLeftTMenuLst (LoginVO loginVO  ) throws Exception;
	
	
	
	TMenuInfo selectMenurInfoManageDetail(String menuId) throws Exception;
	
	
	int selectMenuInfoManageListTotCnt_S(TMenuInfoVO searchVO) throws Exception;

	int insertMenuInfoManage(TMenuInfo vo) throws Exception;
	
	int updateMenuInfoManage(TMenuInfo vo) throws Exception;
	
	int deleteMenuInfoManage(String menuId) throws Exception;
	
	
	
}
