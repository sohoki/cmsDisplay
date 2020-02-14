package egovframework.let.sts.mhs.service;

import java.util.List;

public interface MhsMonitorInfoManageService {

	List<MhsMonitorInfoVO> selectMhsMonitorList (MhsMonitorInfoVO mhsMonitorInfoVO) throws Exception;
	
    int selectMhsMonitorListCnt(MhsMonitorInfoVO mhsMonitorInfoVO) throws Exception;
	
	MhsMonitorInfoVO selectMhsMonitorInfo(String mhsMonitorcd) throws Exception;
	
	List<MhsMonitorInfoVO> selectMhsMonitorNm(String mberId) throws Exception;
	
	int updateMhsMonitorInfo (MhsMonitorInfo vo) throws Exception;
	
	int updateMhsMonitorInfoIpMac (MhsMonitorInfo vo)throws Exception;
	
	int updateMoniterDidUpdateDayChange(String mhsMonitorcd)throws Exception;
	
	int updateMhsMonitorInfoStatus(String mhsMonitorcd)throws Exception;
	
	int deleteMhsMonitorInfo(String mhsMonitorcd) throws Exception;
}
