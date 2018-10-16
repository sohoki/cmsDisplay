package egovframework.let.sts.cnt.service.Impl;

import java.util.List;

import egovframework.let.sts.cnt.service.ContentDetailInfo;
import egovframework.let.sts.cnt.service.ContentDetailInfoService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.ContentDetailManagerMapper;

@Service ("ContentDetailInfoService")
public class ContentDetailInfoServiceImpl extends EgovAbstractServiceImpl implements  ContentDetailInfoService {

	 @Resource(name="ContentDetailManagerMapper")
	 private ContentDetailManagerMapper contentDetail;

	@Override
	public ContentDetailInfo selectContentDetail(String detailSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectContentDetail(detailSeq);
	}

	@Override
	public int insertContentDetailManage(ContentDetailInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.insertContentDetailManage(vo);
	}

	@Override
	public int updateContentDetailManage(ContentDetailInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.updateContentDetailManage(vo);
	}

	@Override
	public int deleteContentDetailManage(String detailSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.deleteContentDetailManage(detailSeq);
	}

	@Override
	public int selectMaxDetail() throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectMaxDetail();
	}

	@Override
	public List<ContentDetailInfo> selectContentDetailLst(String conSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectContentDetailLst(conSeq);
	}

	@Override
	public int selectPageSeqCheck(String conSeq, String detailOrder)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectPageSeqCheck(conSeq, detailOrder);
	}

	@Override
	public int selectPageSeqCheckPage01(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectPageSeqCheckPage01(conSeq);
	}

	@Override
	public int selectPageSeqCheckPage02(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectPageSeqCheckPage02(conSeq);
	}

	@Override
	public int deleteContentDetailConSeq(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.deleteContentDetailConSeq(conSeq);
	}

	@Override
	public int selectPageSeqCheckPage01Cnt(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectPageSeqCheckPage01Cnt(conSeq);
	}

	@Override
	public int selectPageSeqCheckPage02Cnt(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectPageSeqCheckPage02Cnt(conSeq);
	}

	@Override
	public List<ContentDetailInfo> selectContentDetailDidPage(ContentDetailInfo searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectContentDetailDidPage(searchVO);
	}

	@Override
	public int selectConDetailCnt(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectConDetailCnt(conSeq);
	}

	@Override
	public List<ContentDetailInfo> selectConDetailCombo(String conSeq)
			throws Exception {
		// TODO Auto-generated method stub		
		return contentDetail.selectConDetailCombo(conSeq);
	}

	@Override
	public int updateContentDetailTimeManage(String detailSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.updateContentDetailTimeManage(detailSeq);
	}
	
	
}
