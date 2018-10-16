package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sym.sch.service.ScheduleInfo;
import egovframework.let.sym.sch.service.ScheduleInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("SchedulenfoManagerMapper")
public interface SchedulenfoManagerMapper {

	
	public List<ScheduleInfoVO> selectScheduleInfoManageListByPagination( ScheduleInfoVO searchVO );
	
	public List<ScheduleInfo> selectScheduleInfoManageCombo();
	
	public ScheduleInfoVO selectScheduleInfoManageDetail(String schCode);
	
	public ScheduleInfoVO selectScheduleInfoManageDetailView(String schCode);
	
	public String selectScheduleMaxInfo ();
	
	public List<ScheduleInfo> selectScheduleConSeqList(String contentCode);
	
	public int  selectScheduleInfoManageListTotCnt_S( ScheduleInfoVO searchVO );
	
	public int  insertScheduleInfoManage(ScheduleInfo vo);
	
	public int  updateScheduleInfoManage(ScheduleInfo vo);
	
	public int  deleteScheduleInfoManage(String schCode);
	
}
