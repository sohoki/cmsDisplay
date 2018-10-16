package egovframework.let.sts.cnt.service.Impl;

import egovframework.let.sts.cnt.service.ContentFileInfo;
import egovframework.let.sts.cnt.service.ContentFileInfoVO;
import egovframework.let.sts.cnt.service.ContentFileInfoManageService;

import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.ContentFileManagerMapper;

@Service("ContentFileInfo")
public class ContentFileInfoManageServiceImpl extends EgovAbstractServiceImpl implements ContentFileInfoManageService {

	@Resource(name="ContentFileManagerMapper")
	private ContentFileManagerMapper conFileManager;

	@Override
	public List<ContentFileInfoVO> selectFilePageListByPagination(
			ContentFileInfoVO searchVO) {
		// TODO Auto-generated method stub
		
		return conFileManager.selectFilePageListByPagination(searchVO);
	}

	@Override
	public List<ContentFileInfoVO> selectFileContentList(String conSeq) {
		// TODO Auto-generated method stub
		return conFileManager.selectFileContentList(conSeq);
	}

	@Override
	public int selectFilePageListByPaginationTotCnt_S(ContentFileInfoVO searchVO) {
		// TODO Auto-generated method stub
		return conFileManager.selectFilePageListByPaginationTotCnt_S(searchVO);
	}

	@Override
	public int selectFileListTotCnt_S(String conSeq) {
		// TODO Auto-generated method stub
		return conFileManager.selectFileListTotCnt_S(conSeq);
	}

	@Override
	public ContentFileInfoVO selectFileDetail(String atchFileId) {
		// TODO Auto-generated method stub
		return conFileManager.selectFileDetail(atchFileId);
	}

	@Override
	public int insertFileManage(ContentFileInfo vo) {
		// TODO Auto-generated method stub
		return conFileManager.insertFileManage(vo);
	}

	@Override
	public int updateFileManage(ContentFileInfo vo) {
		// TODO Auto-generated method stub
		return conFileManager.updateFileManage(vo);
	}
	
	@Override
	public int updateFileDetailInfo(ContentFileInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.updateFileDetailInfo(vo);
	}

	@Override
	public int deleteFileManage(String atchFileId) {
		// TODO Auto-generated method stub
		return conFileManager.deleteFileManage(atchFileId);
	}

	@Override
	public List<ContentFileInfoVO> selectFileContentPageList(	String detailSeq) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectFileContentPageList(detailSeq);
	}

	@Override
	public int selectFileContentTotCnt_S(String detailSeq) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectFileContentTotCnt_S(detailSeq);
	}

	@Override
	public int deleteFileConSeq(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.deleteFileConSeq(conSeq);
	}

	@Override
	public List<ContentFileInfoVO> selectFileContentLstDid(
			ContentFileInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectFileContentLstDid(searchVO);
	}

	@Override
	public int updateFileManageUseYn(ContentFileInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.updateFileManageUseYn(vo);
	}

	@Override
	public List<ContentFileInfo> selectFileListCombo() throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectFileListCombo();
	}

	@Override
	public List<ContentFileInfoVO> selectBasicFilePageListByPagination(
			ContentFileInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectBasicFilePageListByPagination(searchVO);
	}

	@Override
	public int selectBasicFilePageListByPaginationTotCnt_S(
			ContentFileInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectBasicFilePageListByPaginationTotCnt_S(searchVO);
	}

	@Override
	public List<ContentFileInfoVO> selectMediaConnList(
			String atchFileId) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectMediaConnList(atchFileId);
	}

	
	
	
}
