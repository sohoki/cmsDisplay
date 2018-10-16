package egovframework.let.sts.brd.service;

import java.util.List;

public interface BrodAnniversaryManagerService {
	
	
	
    List<BrodAnniversaryVO> selectBrodAnniverLst(BrodAnniversary vo) throws Exception;
	
	BrodAnniversaryVO selectBrodAnniver(String brodAnnSeq)throws Exception;
	
	String selectBrodAnnMaxSeq()throws Exception;
	
	int selectBrodAnniverPageCnt(String brodCode)throws Exception;
	
	int insertBrodAnniver(BrodAnniversary vo)throws Exception;
	
	int insertBrodAnniverCopy(BrodAnniversary vo)throws Exception;
	
	int insertBrodAnniverCenterCopy(BrodAnniversary vo) throws Exception;
	
	int insertBrodAnniverBasicBrodCodeCopy(String brodCode) throws Exception;
	
	int updateBrodAnniver(BrodAnniversary vo)throws Exception;
	
	int deleteBrodAnniver(String brodAnnSeq)throws Exception;
	
	int deleteBrodAnniverBrodAll(String brodCode)throws Exception;
	
	int deleteBrodAnniverBrod (String brodCode)throws Exception;

	int deleteBrodAnnBasicBrod(String brodCode) throws Exception;
}
