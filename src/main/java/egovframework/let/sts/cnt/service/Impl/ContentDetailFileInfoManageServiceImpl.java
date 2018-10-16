package egovframework.let.sts.cnt.service.Impl;


import egovframework.let.sts.cnt.service.ContentDetailFileInfo;
import egovframework.let.sts.cnt.service.ContentDetailFileInfoVO;

import egovframework.let.sts.cnt.service.ContentDetailFileInfoManageService;

import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.ContentDetailFileInfoManagerMapper;


@Service("ContentDetailFileInfo")
public class ContentDetailFileInfoManageServiceImpl  extends EgovAbstractServiceImpl implements ContentDetailFileInfoManageService {

	
	@Resource(name="ContentDetailFileInfoManagerMapper")
	 private ContentDetailFileInfoManagerMapper contentFileDetail;
	
	
	@Override
	public List<ContentDetailFileInfoVO> selectContentDetailFileLst(
			ContentDetailFileInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.selectContentDetailFileLst(searchVO);
	}

	@Override
	public int selectPageSeqCheckFilePageCnt(ContentDetailFileInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.selectPageSeqCheckFilePageCnt(searchVO);
	}

	@Override
	public int insertContentDetailFileManage(ContentDetailFileInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.insertContentDetailFileManage(vo);
	}

	@Override
	public int updateContentDetailFileManage(ContentDetailFileInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.updateContentDetailFileManage(vo);
	}

	@Override
	public int deleteContentDetailFileManage(String  fileSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.deleteContentDetailFileManage(fileSeq);
	}

	@Override
	public ContentDetailFileInfoVO selectContentDetailFileInfo(String atchFileId)
			throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.selectContentDetailFileInfo(atchFileId);
	}

	@Override
	public int updateContentOrderDetailFileManage(ContentDetailFileInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.updateContentOrderDetailFileManage(vo);
	}

	@Override
	public int selectMaxfileSeq(ContentDetailFileInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.selectMaxfileSeq(vo);
	}

	@Override
	public ContentDetailFileInfoVO selectContentDetailFileInfoFileSeq(String fileSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.selectContentDetailFileInfoFileSeq(fileSeq);
	}

	@Override
	public int updateContentDetailFileTimeIntervalManage(
			ContentDetailFileInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.updateContentDetailFileTimeIntervalManage(vo);
	}

	@Override
	public String selectDetailContentSumTime(String detailSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.selectDetailContentSumTime(detailSeq);
	}

	@Override
	public int selectTimeIntevalNullCheck(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentFileDetail.selectTimeIntevalNullCheck(conSeq);
	}
	

}
