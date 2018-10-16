package egovframework.let.sts.cnt.service.Impl;

import java.util.List;













import egovframework.let.sts.cnt.service.ContentMutiInfo;
import egovframework.let.sts.cnt.service.ContentMutiInfoVO;
import egovframework.let.sts.cnt.service.ContentMutiInfoManageService;
import egovframework.com.mapper.ContentMutiInfoManagerMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service("ContentMuti")
public class ContentMutimanageServiceImpl extends  EgovAbstractServiceImpl implements ContentMutiInfoManageService {

	@Resource(name="ContentMutiInfoManagerMapper")
	private ContentMutiInfoManagerMapper conMulti;

	@Override
	public List<ContentMutiInfoVO> selectContentMutiInfoManageListByPagination(
			ContentMutiInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectContentMutiInfoManageListByPagination(searchVO);
	}

	@Override
	public int selectContentMutiInfoManageListTotCnt_S(
			ContentMutiInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectContentMutiInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public ContentMutiInfoVO selectContentMutiInfoManageDetail(String conSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectContentMutiInfoManageDetail(conSeq);
	}

	@Override
	public int insertContentMutiInfoManage(ContentMutiInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.insertContentMutiInfoManage(vo);
	}

	@Override
	public int updateContentMutiInfoManage(ContentMutiInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.updateContentMutiInfoManage(vo);
	}

	@Override
	public int deleteContentMutiInfoManage(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.deleteContentMutiInfoManage(conSeq);
	}

	@Override
	public ContentMutiInfoVO selectContentMutiInfoManageView(String conSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectContentMutiInfoManageView(conSeq);
	}

	@Override
	public List<ContentMutiInfoVO> selectDIDContentLst(
			ContentMutiInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectDIDContentLst(searchVO);
	}

	@Override
	public int updateContentMutiFile(ContentMutiInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.updateContentMutiFile(vo);
	}

	@Override
	public String selectContentFileInfo(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectContentFileInfo(conSeq);
	}

	@Override
	public List<ContentMutiInfoVO> selectNextContentMutiInfo(String conSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectNextContentMutiInfo(conSeq);
	}

	@Override
	public List<ContentMutiInfo> selectNextSeqList(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectNextSeqList(conSeq) ;
	}

	@Override
	public String selectMaxTimeInterval(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectMaxTimeInterval(conSeq);
	}

	@Override
	public int updateContentMutiFileLocal(ContentMutiInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.updateContentMutiFileLocal(vo);
	}

	@Override
	public String selectContentFileInfoLocal(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectContentFileInfoLocal(conSeq);
	}

	@Override
	public String selectMaxSeqInfo() throws Exception {
		// TODO Auto-generated method stub
		return conMulti.selectMaxSeqInfo();
	}

	
	
}
