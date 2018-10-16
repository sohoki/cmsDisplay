package egovframework.let.sym.sch.service;


import java.util.List;

public interface ScheduleInfoManageService {

	
    List<ScheduleInfoVO> selectScheduleInfoManageListByPagination( ScheduleInfoVO searchVO ) throws Exception;
	
	List<ScheduleInfo> selectScheduleInfoManageCombo()throws Exception;
	
	ScheduleInfoVO selectScheduleInfoManageDetail(String schCode) throws Exception;
	
	ScheduleInfoVO selectScheduleInfoManageDetailView(String schCode) throws Exception;
	
	String selectScheduleMaxInfo () throws Exception;
	
	List<ScheduleInfo> selectScheduleConSeqList(String contentCode)throws Exception;
	
	int  selectScheduleInfoManageListTotCnt_S( ScheduleInfoVO searchVO ) throws Exception;
	
	int  insertScheduleInfoManage(ScheduleInfo vo) throws Exception;
	
	int  updateScheduleInfoManage(ScheduleInfo vo) throws Exception;
	
	int  deleteScheduleInfoManage(String schCode) throws Exception;
}
