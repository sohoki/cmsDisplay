package egovframework.let.uat.uia.service.impl;


import java.util.List;




import egovframework.let.uat.uia.service.GnrMber;
import egovframework.let.uat.uia.service.GnrMberVO;
import egovframework.let.uat.uia.service.EgovUserManagerService;
import egovframework.com.mapper.EgovUserManagerMapper;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



@Service("UserManagerService")
public class EgovUserManagerServiceImpl extends EgovAbstractServiceImpl implements EgovUserManagerService {

	
	
	@Resource(name="UserManagerMapper")
    private EgovUserManagerMapper userManagerMapper;
	
	@Override
	public int deleteUserManage(String mberId) throws Exception {
		return userManagerMapper.deleteUserManage(mberId);
	}

	@Override
	public int insertUserManage(GnrMber vo) throws Exception {
		return userManagerMapper.insertUserManage(vo);
	}

	@Override
	public int updateUserManage(GnrMber gnrmber) throws Exception {
		return userManagerMapper.updateUserManage(gnrmber);
	}

	@Override
	public GnrMberVO selectUserManageDetail(GnrMberVO vo) throws Exception {
		return userManagerMapper.selectUserManageDetail(vo.getMberId());
	}

	@Override
	public List<?> selectUserManageListByPagination(GnrMberVO searchVO) throws Exception {
		return userManagerMapper.selectUserManageListByPagination(searchVO);
	}

	@Override
	public int selectUserManageListTotCnt_S(GnrMberVO searchVO) throws Exception {
		return userManagerMapper.selectUserManageListTotCnt_S(searchVO);
	}

	@Override
	public int selectUserMangerIDCheck(String code) throws Exception {
		return  userManagerMapper.selectUserMangerIDCheck(code)  ;
	}


	
	
}
