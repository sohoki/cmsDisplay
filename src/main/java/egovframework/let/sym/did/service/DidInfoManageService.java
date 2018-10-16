package egovframework.let.sym.did.service;


import java.util.List;

public interface DidInfoManageService {

	List<DidInfoVO>  selectDidInfoManageListByPagination(DidInfoVO searchVO) throws Exception;
	
	List<DidInfoVO> selectDidInfoManageCombo() throws Exception;
	
	DidInfoVO selectDidrInfoManageDetail(String didId) throws Exception;
	
	DidInfoVO selectDidrInfoManageDetailView(String didId) throws Exception;
	
	
	List<DidInfoVO>  selectDidManagerInfoManageListByPagination(DidInfoVO searchVO) throws Exception;
	
	int selectDidManagerInfoManageListTotCnt_S (DidInfoVO searchVO) throws Exception;
	
	
	String selectDIDMac(String didId) throws Exception;
	
	int selectDidInfoManageListTotCnt_S(DidInfoVO searchVO) throws Exception;
	
    List selectDidDetailContentInfo(String didId) throws Exception;

    String selectLastInsertDid(String centerId) throws Exception;
	
	
	int insertDidInfoManage(DidInfo vo) throws Exception;
	
	int updateDidInfoManage(DidInfo vo) throws Exception;
	
	int updateDidMac(DidInfo vo) throws Exception;
	
	int updateDidState(DidInfo vo)throws Exception;
	
	int updateDidEndTime (DidInfo vo)throws Exception;
	
	int deleteDidInfoManage(String  didId) throws Exception;
	
	
	
	
	
	List<DidInfoVO>  selectIntegrateManageListByPagination(DidInfoVO searchVO) throws Exception;
	
	List<DidInfoVO>  selectIntegrateRoleList(DidInfoVO searchVO) throws Exception;
	List<DidInfoVO>  selectIntegrateCenterList(DidInfoVO searchVO) throws Exception;
	List<DidInfoVO>  selectIntegrateDeviceList(DidInfoVO searchVO) throws Exception;
	
	
}
