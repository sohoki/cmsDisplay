package egovframework.let.sym.did.service;

import java.util.List;

public interface ProgramInfoManageService {

    List<ProgramInfoVO> selectProgramInfoManagerInfoManageListByPagination(ProgramInfoVO searchVO)throws Exception;
	
	ProgramInfoVO selectProgramDetailInfoManagerInfo(String progCode)throws Exception;
	
	
	int updateProgramInfo(ProgramInfoVO searchVO)throws Exception;
	
	int deleteProgramInfo(String progCode)throws Exception;
}
