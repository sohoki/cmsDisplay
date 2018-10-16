package egovframework.let.sts.brd.service.impl;


import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;







import egovframework.let.sts.brd.service.BrodOrganization;
import egovframework.let.sts.brd.service.BrodOrganizationVO;
import egovframework.let.sts.brd.service.BrodOrganizationManagerService;
import egovframework.com.mapper.BrodOrganizationManagerMapper;

@Service("BrodOrganizationService")
public class BrodOrganizationManagerServiceImpl extends EgovAbstractServiceImpl implements  BrodOrganizationManagerService {

	@Resource(name="BrodOrganizationMapper")
	private BrodOrganizationManagerMapper brodOrgMapper;

	@Override
	public List<BrodOrganizationVO> selectBrodOrganizationLst(
			BrodOrganizationVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return brodOrgMapper.selectBrodOrganizationLst(searchVO);
	}

	@Override
	public BrodOrganizationVO selectBrodOrganizationInfo(String orgSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return brodOrgMapper.selectBrodOrganizationInfo(orgSeq);
	}

	@Override
	public int selectOrganizationPageCnt(BrodOrganizationVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return brodOrgMapper.selectOrganizationPageCnt(searchVO);
	}

	@Override
	public int insertBrodOrganization(BrodOrganization vo) throws Exception {
		// TODO Auto-generated method stub
		return brodOrgMapper.insertBrodOrganization(vo);
	}

	@Override
	public int updateBrodOrganization(BrodOrganization vo) throws Exception {
		// TODO Auto-generated method stub
		return brodOrgMapper.insertBrodOrganization(vo);
	}

	@Override
	public int deleteBrodOrganization(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodOrgMapper.deleteBrodOrganization(brodCode);
	}

	@Override
	public int deleteBrodOrganizationCenterId(BrodOrganization vo)
			throws Exception {
		// TODO Auto-generated method stub
		return brodOrgMapper.deleteBrodOrganizationCenterId(vo);
	}

	@Override
	public List<BrodOrganization> selectBrodOrgnizationPage(BrodOrganization vo)
			throws Exception {
		// TODO Auto-generated method stub
		return brodOrgMapper.selectBrodOrgnizationPage(vo);
	}

	@Override
	public int deleteContentToOrg(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodOrgMapper.deleteContentToOrg(brodCode);
	}

	@Override
	public List<BrodOrganization> selectBrodOrgnizationDid(BrodOrganization vo)
			throws Exception {
		// TODO Auto-generated method stub
		return brodOrgMapper.selectBrodOrgnizationDid(vo);
	}

	
	
}
