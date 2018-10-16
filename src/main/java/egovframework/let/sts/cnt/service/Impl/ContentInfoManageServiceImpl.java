package egovframework.let.sts.cnt.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sts.cnt.service.ContentInfo;
import egovframework.let.sts.cnt.service.ContentInfoVO;
import egovframework.let.sts.cnt.service.ContentInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.com.mapper.ContentInfoManagerMapper;


@Service("ContentInfoManageService")
public class ContentInfoManageServiceImpl extends EgovAbstractServiceImpl implements  ContentInfoManageService {

	
	@Resource(name="ContentInfoManagerMapper")
	private ContentInfoManagerMapper contentInfoMapper;
	
	
	@Override
	public int selectContentInfoManageListTotCnt_S(
			ContentInfoVO SearchVO) throws Exception {
		// TODO Auto-generated method stub
		return contentInfoMapper.selectContentInfoManageListTotCnt_S(SearchVO);
	}

	@Override
	public List<ContentInfoVO>  selectContentInfoManageListByPagination(ContentInfoVO SearchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return contentInfoMapper.selectContentInfoManageListByPagination(SearchVO);
	}

	@Override
	public ContentInfoVO selectContentInfoManageDetail(String conSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return contentInfoMapper.selectContentInfoManageDetail(conSeq);
	}

	@Override
	public int insertContentInfoManage(ContentInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return contentInfoMapper.insertContentInfoManage(vo);
	}

	@Override
	public int updateContentInfoManage(ContentInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return contentInfoMapper.updateContentInfoManage(vo);
	}

	@Override
	public int deleteContentInfoManage(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentInfoMapper.deleteContentInfoManage(conSeq);
	}

	@Override
	public List<ContentInfo> selectNextCombo(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("확인:"+conSeq);
		return contentInfoMapper.selectNextCombo(conSeq);
	}

	@Override
	public List<ContentInfo> selectSearcHCombo(String searchKeyword)
			throws Exception {
		// TODO Auto-generated method stub
		return contentInfoMapper.selectSearcHCombo(searchKeyword);
	}
	
	
	
	

}
