package egovframework.let.sym.did.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sym.did.service.ProgramInfoManageService;
import egovframework.let.sym.did.service.ProgramInfoVO;
import egovframework.let.sym.did.service.ProgramInfo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.com.mapper.ProgramInfoManageMapper;

@Service("ProgramInfoManageService")
public class ProgramInfoManageServiceImpl extends EgovAbstractServiceImpl implements ProgramInfoManageService {

	
	@Resource( name = "ProgramInfoManageMapper")
	private ProgramInfoManageMapper programMapper;

	@Override
	public List<ProgramInfoVO> selectProgramInfoManagerInfoManageListByPagination(
			ProgramInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return programMapper.selectProgramInfoManagerInfoManageListByPagination(searchVO);
	}

	@Override
	public ProgramInfoVO selectProgramDetailInfoManagerInfo(String progCode)
			throws Exception {
		// TODO Auto-generated method stub
		return programMapper.selectProgramDetailInfoManagerInfo(progCode);
	}

	

	@Override
	public int updateProgramInfo(ProgramInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		int ret = 0;
		if (vo.getMode().equals("Ins")){
			ret = programMapper.insertProgramInfo(vo);
		}else {
			ret = programMapper.updateProgramInfo(vo);
		}
		return ret;
	}

	@Override
	public int deleteProgramInfo(String progCode) throws Exception {
		// TODO Auto-generated method stub
		return programMapper.deleteProgramInfo(progCode);
	}
}
