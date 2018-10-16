package egovframework.let.sym.ccm.zip.service;


import java.util.List;

public interface RdnmadZipManageService {

	List<RdnmadZipVO> selectRdnmadZipManageListByPagination(RdnmadZipVO searchVO )throws Exception;
	
	int selectRdnmadZipManageListTotCnt_S(RdnmadZipVO searchVO )throws Exception;
	
	
}
