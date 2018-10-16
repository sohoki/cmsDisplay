package egovframework.let.sym.ccm.cde.service.impl;

import java.util.List;






import egovframework.let.sym.ccm.cde.service.CmmnDetailCode;
import egovframework.let.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.com.mapper.EgovCmmnDetailCodeManageMapper;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



/**
 *
 * 공통상세코드에 대한 서비스 구현클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 * </pre>
 */
@Service("CmmnDetailCodeManageService")
public class EgovCcmCmmnDetailCodeManageServiceImpl extends EgovAbstractServiceImpl implements EgovCcmCmmnDetailCodeManageService {
	              
	@Resource(name="CmmnDetailCodeManageMapper")
    private EgovCmmnDetailCodeManageMapper CmmnDetailCodeManageMapper;
	/**
	 * 공통상세코드를 삭제한다.
	 */
	@Override
	public int deleteCmmnDetailCode(String code) throws Exception {
		 return CmmnDetailCodeManageMapper.deleteCmmnDetailCode(code);		
	}
	
	/**
	 * 공통상세코드를 등록한다.
	 */
	@Override
	public int insertCmmnDetailCode(CmmnDetailCode vo) throws Exception {    	
    	return CmmnDetailCodeManageMapper.insertCmmnDetailCode(vo);
	}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 */
	@Override
	public CmmnDetailCode selectCmmnDetailCodeDetail(CmmnDetailCode vo) throws Exception {
    	return CmmnDetailCodeManageMapper.selectCmmnDetailCodeDetail(vo.getCode());    	
	}

	/**
	 * 공통상세코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectCmmnDetailCodeList(String codeId) throws Exception {
        return CmmnDetailCodeManageMapper.selectCmmnDetailCodeListByPagination(codeId);
	}
	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectCmmnDetailCodeListTotCnt(String codeId) throws Exception {
        return CmmnDetailCodeManageMapper.selectCmmnDetailCodeListTotCnt(codeId);
	}
	
	@Override
	public List<CmmnDetailCode> selectCmmnDetailCombo(String code) throws Exception {
		// TODO Auto-generated method stub
		return CmmnDetailCodeManageMapper.selectCmmnDetailCombo(code);
	}
	

	/**
	 * 공통상세코드를 수정한다.
	 */
	@Override
	public int updateCmmnDetailCode(CmmnDetailCode vo) throws Exception {
		return CmmnDetailCodeManageMapper.updateCmmnDetailCode(vo);
	}

	@Override
	public int selectCmmnDetailCodeIdCheck(String code) throws Exception {
		// TODO Auto-generated method stub
		return CmmnDetailCodeManageMapper.selectCmmnDetailCodeIdCheck(code);
	}

	@Override
	public CmmnDetailCode selectCmmnDetail(String code) throws Exception {
		// TODO Auto-generated method stub
		return CmmnDetailCodeManageMapper.selectCmmnDetail(code);
	}
	

}
