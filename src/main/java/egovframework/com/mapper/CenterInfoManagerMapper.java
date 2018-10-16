package egovframework.com.mapper;


import java.util.List;

import egovframework.let.sym.cnt.service.CenterInfo;
import egovframework.let.sym.cnt.service.CenterInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("CenterInfoManagerMapper")
public interface CenterInfoManagerMapper {
	
	public List<CenterInfoVO> selectCenterInfoManageListByPagination(CenterInfoVO vo);
	
	public List<CenterInfoVO> selectCenterInfoManageCombo(CenterInfoVO vo);
	
	public CenterInfoVO selectCenterInfoManageDetail(String centerId);
	
	public List<CenterInfo> selectCenterBrodCombo(String centerId);
	
	public String selectCenterTimeInfo(CenterInfo vo);
	
	public String selectCenterInfoBrod(String centerId);
	
	public int selectCenterInfoManageListTotCnt_S(CenterInfoVO vo);
	
	public int insertCenterInfoManage(CenterInfo vo);
	
	public int updateCenterInfoManage(CenterInfo vo);
	
	public int deleteCenterInfoManage (String centerId);
	

}
