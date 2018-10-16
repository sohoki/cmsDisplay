package egovframework.let.sts.brd.service;

import java.util.List;

public interface BasciBrodInfoManageService {

    List<BasciBrodInfoVO> selectBasciBrodLst(BasciBrodInfoVO searchVO) throws Exception;
	
	BasciBrodInfo selectBasciBrod(String basicCode) throws Exception;
	
	String selectMaxBrodCode() throws Exception;
	
	String selectBasciCode()throws Exception;
	
	List<BasciBrodInfo> selectBasicBrodCombo()throws Exception;
	
	
	int selectBasciBrodPageCnt(BasciBrodInfoVO searchVO) throws Exception;
	
	int insertBasciBrod(BasciBrodInfo vo)throws Exception;
	
	int insertBasciBrodCopy(BasciBrodInfo vo)throws Exception;
	
	int updateBasciBrod(BasciBrodInfo vo)throws Exception;
	
	int updateBasciBrodCnt(String basicCode)throws Exception;
	
	int updateBasciBrodCntPlus(String basicCode)throws Exception;
	
	int updateBasciBrodCntMins(String basicCode)throws Exception;
	
	int deleteBasciBrod (String basicCode)throws Exception;
	
}
