package egovframework.let.sym.sch.service.Impl;


import java.util.List;

import org.springframework.stereotype.Service;





import egovframework.let.sym.sch.service.ScheduleInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.let.sym.sch.service.ContentSendHistoryInfoVO;
import egovframework.let.sym.sch.service.ContentSendHistoryInfo;
import egovframework.let.sym.sch.service.ContentSendHistoryInfoManagerService;

import javax.annotation.Resource;

import egovframework.com.mapper.ContentSendHistoryInfoManagerMapper;


@Service("ContentSendHistoryInfoManagerService")
public class ContentSendHistoryInfoManagerServiceImpl extends EgovAbstractServiceImpl implements ContentSendHistoryInfoManagerService {

	@Resource(name="ContentHistoryInfoManagerMapper")
	private ContentSendHistoryInfoManagerMapper SendHistoryInfo; 
	
	@Override
	public List<ScheduleInfoVO> selectContentSendHistoryInfoManage(
			ContentSendHistoryInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return SendHistoryInfo.selectContentSendHistoryInfoManage(searchVO);
	}

	@Override
	public int insertContentSendHistoryInfoManage(ContentSendHistoryInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return SendHistoryInfo.insertContentSendHistoryInfoManage(vo);
	}

	@Override
	public int updateContentSendHistoryInfoManage(ContentSendHistoryInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return SendHistoryInfo.updateContentSendHistoryInfoManage(vo);
	}

	@Override
	public int updateContentSendHistoryDidInfoManage(String hisSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return SendHistoryInfo.updateContentSendHistoryDidInfoManage(hisSeq);
	}

	@Override
	public int deleteContentSendHistoryInfoManage(String schCode)
			throws Exception {
		// TODO Auto-generated method stub
		return SendHistoryInfo.deleteContentSendHistoryInfoManage(schCode);
	}

	@Override
	public int updateContentSendHistoryConInfoManage(String hisSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return SendHistoryInfo.updateContentSendHistoryConInfoManage(hisSeq);
	}

	@Override
	public int updateContentSendHistoryConPageInfoManage(String hisSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return SendHistoryInfo.updateContentSendHistoryConPageInfoManage(hisSeq);
	}

	@Override
	public int updateContentSendHistoryConFileInfoManage(String hisSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return SendHistoryInfo.updateContentSendHistoryConFileInfoManage(hisSeq);
	}

	@Override
	public int updateContentSchUpdateCheckReset(String hisSeq) throws Exception {
		// TODO Auto-generated method stub
		return SendHistoryInfo.updateContentSchUpdateCheckReset(hisSeq);
	}

}
