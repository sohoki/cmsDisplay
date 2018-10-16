package egovframework.let.sym.ccm.ccc.service.impl;

import java.util.List;

import egovframework.let.sym.ccm.ccc.service.CmmnClCode;
import egovframework.let.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.com.mapper.EgovCmmnClCodeManageMapper;

import egovframework.let.sym.ccm.ccc.service.EgovCcmCmmnClCodeManageService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 *
 * 공통분류코드에 대한 서비스 구현클래스를 정의한다
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
@Service("CmmnClCodeManageService")
public class EgovCcmCmmnClCodeManageServiceImpl extends EgovAbstractServiceImpl implements EgovCcmCmmnClCodeManageService {

    //@Resource(name="CmmnClCodeManageDAO")
	//private CmmnClCodeManageDAO cmmnClCodeManageDAO;
	@Resource(name="CmmnClCodeManageMapper")
	EgovCmmnClCodeManageMapper cmmnClCodeManageMapper;

	/**
	 * 공통분류코드를 삭제한다.
	 */
	@Override
	public int deleteCmmnClCode(CmmnClCode cmmnClCode) throws Exception {
		return cmmnClCodeManageMapper.deleteCmmnClCode(cmmnClCode.getClCode());				
	}

	/**
	 * 공통분류코드를 등록한다.
	 */
	@Override
	public int insertCmmnClCode(CmmnClCode cmmnClCode) throws Exception {
		return cmmnClCodeManageMapper.insertCmmnClCode(cmmnClCode);    	
	}

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 */
	@Override
	public CmmnClCode selectCmmnClCodeDetail(CmmnClCode cmmnClCode) throws Exception {    	
    	return cmmnClCodeManageMapper.selectCmmnClCodeDetail(cmmnClCode.getClCode());
	}

	/**
	 * 공통분류코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectCmmnClCodeList(CmmnClCodeVO searchVO) throws Exception {
		return cmmnClCodeManageMapper.selectCmmnClCodeListByPagination(searchVO);        
	}

	/**
	 * 공통분류코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectCmmnClCodeListTotCnt(CmmnClCodeVO searchVO) throws Exception {
		return cmmnClCodeManageMapper.selectCmmnClCodeListTotCnt(searchVO);
	}

	/**
	 * 공통분류코드를 수정한다.
	 */
	@Override
	public int updateCmmnClCode(CmmnClCode cmmnClCode) throws Exception {
		return cmmnClCodeManageMapper.updateCmmnClCode(cmmnClCode) ;		
	}

}
