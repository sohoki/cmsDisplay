package egovframework.com.mapper;


import java.util.List;

import egovframework.let.sym.cnt.service.CenterInfoAnniversary;
import egovframework.let.sym.cnt.service.CenterInfoAnniversaryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CenterAnniManager")
public interface CenterAnniManagerMapper {

	
	
	public List<CenterInfoAnniversaryVO> selectCenterAnniManageListByPagination(CenterInfoAnniversaryVO searchVO);
	
	public CenterInfoAnniversaryVO selectCenterAnniManageDetail(String centerAnniDay);
	
	public int selectCenterAnniManageListTotCnt_S(CenterInfoAnniversaryVO searchVO);
	
	public int selectCenterAnniRetgCheck(CenterInfoAnniversaryVO searchVO);
	
	public int insertCenterAnniManage(CenterInfoAnniversary vo);
	
	public int updateCenterAnniManage(CenterInfoAnniversary vo);
	
	public int deleteCenterAnniManage(String centerAnniDay);
	
	public int deleteCenterID(String centerId);
}
