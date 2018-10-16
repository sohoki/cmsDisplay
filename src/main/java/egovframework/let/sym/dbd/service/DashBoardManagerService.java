package egovframework.let.sym.dbd.service;

import java.util.List;

public interface DashBoardManagerService {

	
	DashBoardInfo selectDidStatus() throws Exception;
	
	DashBoardInfo selectBrodStatus() throws Exception;
	
	int dashStateUpdateStep01() throws Exception;
	
	int dashStateUpdateStep02() throws Exception;
	
	
	List<DashBoardInfo>selectBrodStatusPage01(DashBoardInfo searchVo) throws Exception;
	
	List<DashBoardInfo>selectBrodStatusPage02(DashBoardInfo searchVo) throws Exception;
	
	int selectBrodStatusPage01Cnt() throws Exception;
	
	int selectBrodStatusPage02Cnt() throws Exception;
	
}
