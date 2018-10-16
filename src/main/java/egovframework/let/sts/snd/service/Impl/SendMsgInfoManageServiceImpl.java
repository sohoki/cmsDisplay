package egovframework.let.sts.snd.service.Impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sts.snd.service.SendMsgInfo;
import egovframework.let.sts.snd.service.SendMsgInfoVO;
import egovframework.let.sts.snd.service.SendMsgInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.com.mapper.SendMsgInfoManagerMapper;

@Service("SendMsgInfoManageService")
public class SendMsgInfoManageServiceImpl extends EgovAbstractServiceImpl implements SendMsgInfoManageService {

	@Resource (name="SendMsgInfoManagerMapper")
	private SendMsgInfoManagerMapper sendMsgInfoManagerMapper;
	
	@Override
	public List<SendMsgInfoVO> selectSendMsgInfoManageListByPagination(
			SendMsgInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.selectSendMsgInfoManageListByPagination(searchVO);
	}

	@Override
	public int selectSendMsgInfoManageListTotCnt_S(SendMsgInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.selectSendMsgInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public int insertSendMsgInfoManage(SendMsgInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.insertSendMsgInfoManage(vo);
	}

	@Override
	public int updateSendMsgInfoManage(SendMsgInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.updateSendMsgInfoManage(vo);
	}

	@Override
	public int insertSendMsgInfoManageState(SendMsgInfo vo)
			throws Exception {
		// TODO Auto-generated method stub		
		return  sendMsgInfoManagerMapper.insertSendMsgInfoManageState(vo);
	}

	@Override
	public int selectDIDScheduleCount(SendMsgInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		
		return sendMsgInfoManagerMapper.selectDIDScheduleCount(vo);
	}

	@Override
	public int selectMaxSeq() {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.selectMaxSeq();
	}

	@Override
	public int selectDIDOrderCount(SendMsgInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.selectDIDOrderCount(vo);
	}

	@Override
	public List<SendMsgInfoVO> selectDIDOrderLst(SendMsgInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.selectDIDOrderLst(searchVO);
	}

	@Override
	public int selectDIDMessageCount(SendMsgInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.selectDIDMessageCount(vo);
	}

	@Override
	public int insertSendMessageInsertManage(SendMsgInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.insertSendMessageInsertManage(vo);
	}

	@Override
	public int selectSendDidIDCheckCnt(String sendDidId) throws Exception {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.selectSendDidIDCheckCnt(sendDidId);
	}

	@Override
	public String selectSendDidIDMsgSeq(String sendDidId) throws Exception {
		// TODO Auto-generated method stub
		return sendMsgInfoManagerMapper.selectSendDidIDMsgSeq(sendDidId);
	}

	

}
