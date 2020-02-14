package egovframework.let.sts.mhs.service;

import java.util.List;

public interface MhsCenterInfoManageService {

	List<MhsCenterInfoVO> selectMhsBrandList(MhsCenterInfoVO mhsCenterInfoVO) throws Exception;
	
	//상위 부서 콤보 박스
	List<MhsCenterInfo> selectMhsComboList(String mhsBrandcd) throws Exception;
	
	List<MhsCenterInfo> selectMhsComboListMeber(String  mhsBrandcd) throws Exception;
	
	MhsCenterInfo selectMhsCenterInfo (String mhsCentercd)throws Exception;
	
	List<MhsCenterInfoVO> selectMhsCenterList (MhsCenterInfoVO mhsCenterInfoVO) throws Exception;

	int updateMhsCenter (MhsCenterInfo vo) throws Exception;
	
	int deleteMhsCenter (String  mhsCentercd) throws Exception;
	
}
