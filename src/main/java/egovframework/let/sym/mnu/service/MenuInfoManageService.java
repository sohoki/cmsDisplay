package egovframework.let.sym.mnu.service;



import java.util.List;

import egovframework.com.cmm.LoginVO;


public interface MenuInfoManageService {
	
       	
	List<MenuInfoVO> selectManuInfoManageListByPagination(MenuInfoVO searchVO) throws Exception;
	
	List<MenuInfoVO> selectMenuInfoManageCombo ( ) throws Exception;
	
	List<MenuInfo> selectMenuComboLst (String didId) throws Exception;
	
	List<MenuInfoVO> selectLeftMenuLst (LoginVO loginVO) throws Exception;
	
	
	MenuInfoVO selectMenurInfoManageDetail(String menuId) throws Exception;
	
	int selectMenuInfoManageListTotCnt_S(MenuInfoVO searchVO) throws Exception;

	int insertMenuInfoManage(MenuInfo vo) throws Exception;
	
	int updateMenuInfoManage(MenuInfo vo) throws Exception;
	
	int deleteMenuInfoManage(String menuId) throws Exception;
	

}
