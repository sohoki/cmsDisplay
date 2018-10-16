package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sym.mnu.service.TMenuInfo;
import egovframework.let.sym.mnu.service.TMenuInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.com.cmm.LoginVO;


@Mapper("TMenuInfoManagerMapper")
public interface TMenuInfoManagerMapper {

	
    public List<TMenuInfoVO> selectManuInfoManageListByPagination(TMenuInfoVO searchVO);
	
	public  List<TMenuInfoVO> selectMenuInfoManageCombo();
	
	public  List<TMenuInfo> selectTMenuComboLst(String didId);
	
	public TMenuInfo  selectMenurInfoManageDetail(String menuId);
	
	public  List<TMenuInfoVO> selectLeftTMenuLst(LoginVO loginVO);
	
	public int selectMenuInfoManageListTotCnt_S(TMenuInfoVO searchVO);

	public int insertMenuInfoManage(TMenuInfo vo);
	
	public int updateMenuInfoManage(TMenuInfo vo);
	
	public int deleteMenuInfoManage(String menuId);
	
	
}
