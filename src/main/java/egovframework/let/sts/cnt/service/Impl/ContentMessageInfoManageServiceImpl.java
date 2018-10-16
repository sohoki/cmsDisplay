package egovframework.let.sts.cnt.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sts.cnt.service.ContentMessageInfo;
import egovframework.let.sts.cnt.service.ContentMessageInfoVO;
import egovframework.let.sts.cnt.service.ContentMessageInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.com.mapper.ContentMessageInfoManagerMapper;

@Service("MessageInfoManageService")
public class ContentMessageInfoManageServiceImpl extends EgovAbstractServiceImpl implements ContentMessageInfoManageService {

	
	@Resource(name="MessageInfoManagerMapper")
	private ContentMessageInfoManagerMapper messageInfo;

	@Override
	public List<ContentMessageInfoVO> selectContentMessageInfoListByPagination(
			ContentMessageInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return messageInfo.selectContentMessageInfoListByPagination(searchVO);
	}

	@Override
	public int selectContentMessageInfoListTotCnt_S(
			ContentMessageInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return messageInfo.selectContentMessageInfoListTotCnt_S(searchVO);
	}

	@Override
	public ContentMessageInfo selectContentMessageInfoDetail(String sendMsgId)
			throws Exception {
		// TODO Auto-generated method stub
		return messageInfo.selectContentMessageInfoDetail(sendMsgId);
	}

	@Override
	public int insertContentMessageInfo(ContentMessageInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return messageInfo.insertContentMessageInfo(vo);
	}

	@Override
	public int updateContentMessageInfo(ContentMessageInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return messageInfo.updateContentMessageInfo(vo);
	}

	@Override
	public int updateContentMessageInfoClientManage(String sendMsgId)
			throws Exception {
		// TODO Auto-generated method stub
		return messageInfo.updateContentMessageInfoClientManage(sendMsgId);
	}

	@Override
	public int deleteContentMessageInfo(String sendDidId) throws Exception {
		// TODO Auto-generated method stub
		return messageInfo.deleteContentMessageInfo(sendDidId);
	}

	@Override
	public int updateContentMessageInfoMsgId(ContentMessageInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return messageInfo.updateContentMessageInfoMsgId(vo);
	}

	@Override
	public int deleteContentMessageInfoMsgId(String sendMsgId) throws Exception {
		// TODO Auto-generated method stub
		return messageInfo.deleteContentMessageInfoMsgId(sendMsgId);
	}

	@Override
	public List<ContentMessageInfo> selectContentMessageInfoDidList(String didId)
			throws Exception {
		// TODO Auto-generated method stub
		return messageInfo.selectContentMessageInfoDidList(didId);
	}

	
	
	
	

}
