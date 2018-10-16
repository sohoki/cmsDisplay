package egovframework.let.sym.ccm.zip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import egovframework.let.sym.ccm.zip.service.RdnmadZipVO;
import egovframework.let.sym.ccm.zip.service.RdnmadZipManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.com.mapper.RdnmadZipManagerMapper;

import org.springframework.stereotype.Service;

@Service("RdnmadZipManageService")
public class RdnmadZipManageServiceImpl extends EgovAbstractServiceImpl implements RdnmadZipManageService {

	
	@Resource(name="RdnmadZipManagerMapper")
	private RdnmadZipManagerMapper rdnmadZip;
	
	@Override
	public List<RdnmadZipVO> selectRdnmadZipManageListByPagination(
			RdnmadZipVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return rdnmadZip.selectRdnmadZipManageListByPagination(searchVO);
	}

	@Override
	public int selectRdnmadZipManageListTotCnt_S(RdnmadZipVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return rdnmadZip.selectRdnmadZipManageListTotCnt_S(searchVO);
	}

	
}
