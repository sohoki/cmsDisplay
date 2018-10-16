package egovframework.com.mapper;

import java.util.List;
import egovframework.let.sym.dbd.service.DashBoardInfo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("DashManagerMapper")
public interface DashBoardManagerMapper {

	
	public DashBoardInfo selectDidStatus();
	
	public DashBoardInfo selectBrodStatus();
	
	
	public List<DashBoardInfo>selectBrodStatusPage01(DashBoardInfo searchVo);
	
	public List<DashBoardInfo>selectBrodStatusPage02(DashBoardInfo searchVo);
	
	public int selectBrodStatusPage01Cnt();
	
	public int selectBrodStatusPage02Cnt();
	
	int dashStateUpdateStep01();
	
	int dashStateUpdateStep02();
	
	
}
