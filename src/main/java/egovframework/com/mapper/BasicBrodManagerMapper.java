package egovframework.com.mapper;

import java.util.List;
import egovframework.let.sts.brd.service.BasciBrodInfo;
import egovframework.let.sts.brd.service.BasciBrodInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BasicBrodManagerMapper")
public interface BasicBrodManagerMapper {

	public List<BasciBrodInfoVO> selectBasciBrodLst(BasciBrodInfoVO searchVO) throws Exception;
	
	public BasciBrodInfo selectBasciBrod(String basicCode) throws Exception;
	
	public List<BasciBrodInfo> selectBasicBrodCombo()throws Exception;
	
	public int selectBasciBrodPageCnt(BasciBrodInfoVO searchVO) throws Exception;
	
	public String selectMaxBrodCode() throws Exception;
	
	public String selectBasciCode();
	
	public int insertBasciBrod(BasciBrodInfo vo)throws Exception;
	
	public int insertBasciBrodCopy(BasciBrodInfo vo)throws Exception;
	
	public int updateBasciBrod(BasciBrodInfo vo)throws Exception;
	
	public int updateBasciBrodCnt(String basicCode)throws Exception;
	
	public int updateBasciBrodCntPlus(String basicCode)throws Exception;
	
	public int updateBasciBrodCntMins(String basicCode)throws Exception;
	
	public int deleteBasciBrod (String basicCode)throws Exception;
	
	
}
