package egovframework.com.mapper;

import java.util.List;
import egovframework.let.sts.brd.service.BrodOrganization;
import egovframework.let.sts.brd.service.BrodOrganizationVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BrodOrganizationMapper")
public interface BrodOrganizationManagerMapper {
	
	
	public List<BrodOrganizationVO> selectBrodOrganizationLst(BrodOrganizationVO searchVO);
	
	public BrodOrganizationVO selectBrodOrganizationInfo(String orgSeq);
	
	public List<BrodOrganization> selectBrodOrgnizationPage(BrodOrganization  vo);
	
	public List<BrodOrganization> selectBrodOrgnizationDid(BrodOrganization vo);
	
	public int selectOrganizationPageCnt(BrodOrganizationVO searchVO);
	
	public int insertBrodOrganization(BrodOrganization vo);
	
	public int updateBrodOrganization(BrodOrganization vo);
	
	public int deleteBrodOrganization(String brodCode);
	
	public int deleteBrodOrganizationCenterId(BrodOrganization vo);
	
	public int deleteContentToOrg(String brodCode);

}
