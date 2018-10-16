package egovframework.let.sts.pic.service.Impl;


import java.util.List;

import egovframework.let.sts.pic.service.DidMoniterPic;
import egovframework.let.sts.pic.service.DidMoniterPicVO;
import egovframework.let.sts.pic.service.DidMoniterPicService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import egovframework.com.mapper.DidMoniterPicManagerMapper;


@Service("DidMoniterPicService")
public class DidMoniterPicServiceImpl extends EgovAbstractServiceImpl implements DidMoniterPicService  {

	@Resource(name="DidMoniterPicManagerMappe")
	private DidMoniterPicManagerMapper didMoniterMapper;
	
	@Override
	public List<DidMoniterPicVO> selectDidMoniterPicManageListByPagination(
			DidMoniterPicVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return didMoniterMapper.selectDidMoniterPicManageListByPagination(searchVO);
	}

	@Override
	public int selectDidMoniterPicManageListTotCnt_S(DidMoniterPicVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return didMoniterMapper.selectDidMoniterPicManageListTotCnt_S(searchVO);
	}

	@Override
	public int insertDidMoniterPicManage(DidMoniterPic vo) throws Exception {
		// TODO Auto-generated method stub
		return didMoniterMapper.insertDidMoniterPicManage(vo);
	}

	
	
}
