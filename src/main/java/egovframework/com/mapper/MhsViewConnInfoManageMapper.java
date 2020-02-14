package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sts.mhs.service.MhsViewConnInfoVO;
import egovframework.let.sts.mhs.service.MhsViewConnInfo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MhsViewConnInfoManageMapper")
public interface MhsViewConnInfoManageMapper {

	public List<MhsViewConnInfoVO> selectViewMoniterClassInfo(MhsViewConnInfoVO searchVO);
	
	public List<MhsViewConnInfoVO> selectViewMoniterClassUninPageInfo(MhsViewConnInfoVO searchVO);
	
	public int selectViewMoniterClassUpdateInfoChange(String mhsMonitorcd);
	
	public int insertMoniterClassInfo(MhsViewConnInfo vo);
	
	public int updateMoniterClassInfo(MhsViewConnInfo vo);
	
	public int updateMoniterDidUpdateChange(String mhsMonitorcd);
	
	public int updateMoniterClassChangeInfo(String mhsClasscd);
	
	public int updateMoniterDidUpdateDayChange(String mhsMonitorcd);
	
	
	public int deleteMoniterClassInfo(String mhsConnSeq);
	
	
}
