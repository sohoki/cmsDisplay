package egovframework.let.sym.cnt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.let.sym.cnt.service.CenterInfo;
import egovframework.let.sym.cnt.service.CenterInfoVO;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;





import javax.annotation.Resource;

import egovframework.com.mapper.CenterInfoManagerMapper;


@Service("CenterInfoManageService")
public class CenterInfoManageServiceImpl  extends EgovAbstractServiceImpl implements  CenterInfoManageService{

	
    @Resource(name="CenterInfoManagerMapper")
    private CenterInfoManagerMapper centerInfoManagerMapper;
	
	
	@Override
	public List<CenterInfoVO> selectCenterInfoManageListByPagination(
			CenterInfoVO searchVo) {
		return centerInfoManagerMapper.selectCenterInfoManageListByPagination(searchVo);
	}

	@Override
	public List<CenterInfoVO> selectCenterInfoManageCombo(CenterInfoVO searchVO) {
		return centerInfoManagerMapper.selectCenterInfoManageCombo(searchVO);
	}

	@Override
	public CenterInfoVO selectCenterInfoManageDetail(String centerId) {
		return centerInfoManagerMapper.selectCenterInfoManageDetail(centerId);
	}

	@Override
	public int selectCenterInfoManageListTotCnt_S(CenterInfoVO searchVo) {
		return centerInfoManagerMapper.selectCenterInfoManageListTotCnt_S(searchVo);
	}

	@Override
	public int insertCenterInfoManage(CenterInfo vo) {
		return centerInfoManagerMapper.insertCenterInfoManage(vo);
	}

	@Override
	public int updateCenterInfoManage(CenterInfo vo) {
		return centerInfoManagerMapper.updateCenterInfoManage(vo);
	}

	@Override
	public int deleteCenterInfoManage(String centerId) {
		return centerInfoManagerMapper.deleteCenterInfoManage(centerId);
	}

	@Override
	public String selectCenterTimeInfo(CenterInfo vo) {
		// TODO Auto-generated method stub
		return centerInfoManagerMapper.selectCenterTimeInfo(vo);
	}

	@Override
	public List<CenterInfo> selectCenterBrodCombo(String centerId) {
		// TODO Auto-generated method stub
		return centerInfoManagerMapper.selectCenterBrodCombo(centerId);
	}

	@Override
	public String selectCenterInfoBrod(String centerId) throws Exception {
		// TODO Auto-generated method stub
		return centerInfoManagerMapper.selectCenterInfoBrod(centerId);
	}

	
	@Override
	public List<CenterInfoVO> selectGroupInCenterInfo(
			CenterInfoVO searchVo) {
		return centerInfoManagerMapper.selectGroupInCenterInfo(searchVo);
	}
	
}
