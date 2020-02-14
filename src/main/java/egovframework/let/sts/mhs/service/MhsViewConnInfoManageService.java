package egovframework.let.sts.mhs.service;

import java.util.List;

public interface MhsViewConnInfoManageService {
	
    List<MhsViewConnInfoVO> selectViewMoniterClassInfo(MhsViewConnInfoVO searchVO) throws Exception;
    
    List<MhsViewConnInfoVO> selectViewMoniterClassUninPageInfo(MhsViewConnInfoVO searchVO)throws Exception;
    
    int selectViewMoniterClassUpdateInfoChange(String mhsMonitorcd) throws Exception;
	
	int insertMoniterClassInfo(MhsViewConnInfo vo) throws Exception;
	
	int updateMoniterClassInfo(MhsViewConnInfo vo) throws Exception;
	
    int updateMoniterDidUpdateChange(String mhsMonitorcd) throws Exception;
	
	int updateMoniterClassChangeInfo(String mhsClasscd) throws Exception;
	
	int deleteMoniterClassInfo(String mhsConnSeq) throws Exception;
}
