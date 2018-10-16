package egovframework.com.mapper;


import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.com.cmm.LoginVO;
import egovframework.let.sym.mnu.service.MenuInfo;
import egovframework.let.sym.mnu.service.MenuInfoVO;


@Mapper("MenuInfoManagerMapper")
public interface MenuInfoManagerMapper {

	public List<MenuInfoVO> selectManuInfoManageListByPagination(MenuInfoVO searchVO);
	
	public  List<MenuInfoVO> selectMenuInfoManageCombo();
	
	public  List<MenuInfo> selectMenuComboLst(String didId);
	
	public  List<MenuInfoVO> selectLeftMenuLst( LoginVO loginVO   );
	
	public MenuInfoVO  selectMenurInfoManageDetail(String menuId);
	
	public int selectMenuInfoManageListTotCnt_S(MenuInfoVO searchVO);

	public int insertMenuInfoManage(MenuInfo vo);
	
	public int updateMenuInfoManage(MenuInfo vo);
	
	public int deleteMenuInfoManage(String menuId);
	
}
