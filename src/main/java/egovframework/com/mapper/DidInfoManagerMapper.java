package egovframework.com.mapper;


import java.util.List;

import egovframework.let.sym.did.service.DidInfo;
import egovframework.let.sym.did.service.DidInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("DidInfoManagerMapper")
public interface DidInfoManagerMapper {

	public List<DidInfoVO>  selectDidInfoManageListByPagination(DidInfoVO searchVO);
	public List<DidInfoVO> selectDidInfoManageCombo();
	public DidInfoVO selectDidrInfoManageDetail(String didId);
	public DidInfoVO selectDidrInfoManageDetailView(String didId);
	
	//DID 현황
	public List<DidInfoVO>  selectDidManagerInfoManageListByPagination(DidInfoVO searchVO);
	
	public int selectDidManagerInfoManageListTotCnt_S(DidInfoVO searchVO);
	
	
	public String selectDIDMac (String didId);
	
	public int selectDidInfoManageListTotCnt_S(DidInfoVO searchVO);
	
	public List selectDidDetailContentInfo(String s);

    public String selectLastInsertDid(String s);
	
	public int insertDidInfoManage(DidInfo vo);
	
	public int updateDidInfoManage(DidInfo vo);
	
	public int updateDidMac(DidInfo vo);
	
	public int updateDidState(DidInfo vo);
	
	public int updateDidEndTime (DidInfo vo);
	
	public int deleteDidInfoManage(String didId);
	
	
	
	
	public List<DidInfoVO>  selectIntegrateManageListByPagination(DidInfoVO searchVO);
	
	public List<DidInfoVO> selectIntegrateRoleList(DidInfoVO searchVO);
	public List<DidInfoVO> selectIntegrateCenterList(DidInfoVO searchVO);
	public List<DidInfoVO> selectIntegrateDeviceList(DidInfoVO searchVO);
	
}
