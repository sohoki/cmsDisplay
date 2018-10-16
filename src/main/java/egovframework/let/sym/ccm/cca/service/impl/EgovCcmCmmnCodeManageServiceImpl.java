package egovframework.let.sym.ccm.cca.service.impl;

import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.EgovCmmnCodeManageMapper;
import egovframework.let.sym.ccm.cca.service.CmmnCode;
import egovframework.let.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.let.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;



@Service("CmmnCodeManageService")
public class EgovCcmCmmnCodeManageServiceImpl extends EgovAbstractServiceImpl implements EgovCcmCmmnCodeManageService {

    /*
	@Resource(name="CmmnCodeManageDAO")
    private CmmnCodeManageDAO cmmnCodeManageDAO;
   */
	
	@Resource(name="CmmnCodeManageMapper")
	EgovCmmnCodeManageMapper cmmnCodeManageMapper;
	
	/**
	 * 공통코드를 삭제한다.
	 */
	
	@Override
	public int deleteCmmnCode(String codeId) throws Exception {
		return cmmnCodeManageMapper.deleteCmmnCode(codeId);
	}

	/**
	 * 공통코드를 등록한다.
	 */
	@Override
	public int insertCmmnCode(CmmnCode cmmnCode) throws Exception {
		return cmmnCodeManageMapper.insertCmmnCode(cmmnCode);    	
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 */
	@Override
	public CmmnCode selectCmmnCodeDetail(String codeId) throws Exception {
		return cmmnCodeManageMapper.selectCmmnCodeDetail(codeId);		    	
	}

	/**
	 * 공통코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectCmmnCodeList(CmmnCodeVO searchVO) throws Exception {
		return cmmnCodeManageMapper.selectCmmnCodeListByPagination(searchVO);        
	}

	/**
	 * 공통코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectCmmnCodeListTotCnt(CmmnCodeVO searchVO) throws Exception {
        return cmmnCodeManageMapper.selectCmmnCodeListTotCnt(searchVO);
	}

	/**
	 * 공통코드를 수정한다.
	 */
	@Override
	public int  updateCmmnCode(CmmnCode cmmnCode) throws Exception {
		return cmmnCodeManageMapper.updateCmmnCode(cmmnCode);		
	}

	@Override
	public int selectIDCheck(String codeId) throws Exception {
		// TODO Auto-generated method stub
		return cmmnCodeManageMapper.selectIDCheck(codeId);
	}

}
