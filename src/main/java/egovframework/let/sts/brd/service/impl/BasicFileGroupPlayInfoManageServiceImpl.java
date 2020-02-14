package egovframework.let.sts.brd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.BasicFileGroupPlayInfoManageMapper;
import egovframework.let.sts.brd.service.BasicFileGroupPlayInfo;
import egovframework.let.sts.brd.service.BasicFileGroupPlayInfoVO;
import egovframework.let.sts.brd.service.BasicFileGroupPlayInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("BasicFileGroupPlayInfoManageService")
public class BasicFileGroupPlayInfoManageServiceImpl extends EgovAbstractServiceImpl implements BasicFileGroupPlayInfoManageService {
	
	
	@Resource(name="BasicFileGroupPlayInfoManageMapper")
	private BasicFileGroupPlayInfoManageMapper playMapper;

	

	@Override
	public int updateFilePlay(BasicFileGroupPlayInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		int cnt = playMapper.selectFileInserCheck(vo);
		int ret = 0;
		if (cnt > 0 ){
			ret = playMapper.updateFilePlay(vo);
		}else {
			ret = playMapper.insertFilePlay(vo);
		}
		return ret;
	}

	@Override
	public int deleteFilePlay(BasicFileGroupPlayInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return playMapper.deleteFilePlay(vo);
	}

	@Override
	public List<BasicFileGroupPlayInfoVO> selectPlayListInfo(
			BasicFileGroupPlayInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return playMapper.selectPlayListInfo(searchVO);
	}

	@Override
	public List<BasicFileGroupPlayInfoVO> selectPlayListInfoNotCenter(
			BasicFileGroupPlayInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return playMapper.selectPlayListInfoNotCenter(searchVO);
	}
	

}
