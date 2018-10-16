package egovframework.let.sts.brd.service;

import java.util.List;

public interface BrodOrganizationManagerService {
	
    List<BrodOrganizationVO> selectBrodOrganizationLst(BrodOrganizationVO searchVO) throws Exception;
	
	BrodOrganizationVO selectBrodOrganizationInfo(String orgSeq)throws Exception;
	
	List<BrodOrganization> selectBrodOrgnizationPage(BrodOrganization vo) throws Exception;
	
	List<BrodOrganization> selectBrodOrgnizationDid(BrodOrganization vo)throws Exception;
	
	int selectOrganizationPageCnt(BrodOrganizationVO searchVO)throws Exception;
	
	int insertBrodOrganization(BrodOrganization vo)throws Exception;
	
	int updateBrodOrganization(BrodOrganization vo)throws Exception;
	
	int deleteBrodOrganization(String brodCode)throws Exception;
	
	int deleteBrodOrganizationCenterId(BrodOrganization vo) throws Exception;
	
	int deleteContentToOrg(String brodCode) throws Exception;
}
