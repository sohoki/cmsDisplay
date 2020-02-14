package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sts.mhs.service.MhsMonitorInfo;
import egovframework.let.sts.mhs.service.MhsMonitorInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MhsMonitorManageMapper")
public interface MhsMonitorManageMapper {
	
	public List<MhsMonitorInfoVO> selectMhsMonitorList(MhsMonitorInfoVO mhsMonitorInfoVO);
   
	public int selectMhsMonitorListCnt(MhsMonitorInfoVO mhsMonitorInfoVO);
	
	public MhsMonitorInfoVO selectMhsMonitorInfo(String mhsMonitorcd);
	
	public List<MhsMonitorInfoVO> selectMhsMonitorNm(String mberId);
	
	public int insertMhsMonitorInfo (MhsMonitorInfo vo);
	
	public int updateMhsMonitorInfo (MhsMonitorInfo vo);
	
	public int updateMhsMonitorInfoIpMac (MhsMonitorInfo vo);
	
	public int updateMhsMonitorInfoStatus(String mhsMonitorcd);
	
	public int deleteMhsMonitorInfo(String mhsMonitorcd);
}
