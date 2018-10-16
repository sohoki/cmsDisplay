package egovframework.let.sym.sch.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.let.sym.sch.service.ScheduleInfo;
import egovframework.let.sym.sch.service.ScheduleInfoVO;
import egovframework.let.sym.sch.service.ScheduleInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;





import javax.annotation.Resource;

import egovframework.com.mapper.SchedulenfoManagerMapper;



@Service("ScheduleInfoManageService")
public class ScheduleInfoManageServiceImpl extends EgovAbstractServiceImpl implements  ScheduleInfoManageService {

	@Resource(name="SchedulenfoManagerMapper")
	private SchedulenfoManagerMapper schedulenfoManagerMapper;
	
	@Override
	public List<ScheduleInfoVO> selectScheduleInfoManageListByPagination(
			ScheduleInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return schedulenfoManagerMapper.selectScheduleInfoManageListByPagination(searchVO);
	}

	@Override
	public List<ScheduleInfo> selectScheduleInfoManageCombo() throws Exception {
		// TODO Auto-generated method stub
		return schedulenfoManagerMapper.selectScheduleInfoManageCombo();
	}

	@Override
	public ScheduleInfoVO selectScheduleInfoManageDetail(String schCode)
			throws Exception {
		// TODO Auto-generated method stub
		return schedulenfoManagerMapper.selectScheduleInfoManageDetail(schCode);
	}

	@Override
	public ScheduleInfoVO selectScheduleInfoManageDetailView(String schCode)
			throws Exception {
		// TODO Auto-generated method stub
		return schedulenfoManagerMapper.selectScheduleInfoManageDetailView(schCode);
	}

	@Override
	public int selectScheduleInfoManageListTotCnt_S(ScheduleInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return schedulenfoManagerMapper.selectScheduleInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public int insertScheduleInfoManage(ScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return schedulenfoManagerMapper.insertScheduleInfoManage(vo);
	}

	@Override
	public int updateScheduleInfoManage(ScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return schedulenfoManagerMapper.updateScheduleInfoManage(vo);
	}

	@Override
	public int deleteScheduleInfoManage(String schCode) throws Exception {
		// TODO Auto-generated method stub
		return schedulenfoManagerMapper.deleteScheduleInfoManage(schCode);
	}

	@Override
	public String selectScheduleMaxInfo() throws Exception {
		// TODO Auto-generated method stub
		return schedulenfoManagerMapper.selectScheduleMaxInfo();
	}

	@Override
	public List<ScheduleInfo> selectScheduleConSeqList(String contentCode)
			throws Exception {
		// TODO Auto-generated method stub
		return schedulenfoManagerMapper.selectScheduleConSeqList(contentCode);
	}

}
