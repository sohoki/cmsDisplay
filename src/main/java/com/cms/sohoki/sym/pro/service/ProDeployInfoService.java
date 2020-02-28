package com.cms.sohoki.sym.pro.service;

import java.util.List;

public interface ProDeployInfoService {

    List<ProgDeployInfoVO> selectProgramDeployListInfo(ProgDeployInfoVO searchVO) throws Exception;
 	
	ProgDeployInfoVO selectProgramDeployDetail(String depSeq) throws Exception;

	int insertProgramDeploy(ProgDeployInfoVO vo) throws Exception;
	
	int updateProgramDeploy(ProgDeployInfoVO vo) throws Exception;
	
	int deleteProgramDeploy(String depSeq) throws Exception;
	
}
