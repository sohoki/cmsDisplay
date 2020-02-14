package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sts.mhs.service.MhsCenterInfo;
import egovframework.let.sts.mhs.service.MhsCenterInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MhsCenterManageMapper")
public interface MhsCenterManageMapper {
	
	public List<MhsCenterInfoVO> selectMhsBrandList(MhsCenterInfoVO mhsCenterInfoVO);
	//상위 부서 콤보 박스
	public List<MhsCenterInfo> selectMhsComboList(String  mhsBrandcd);
	
	public List<MhsCenterInfo> selectMhsComboListMeber(String  mhsBrandcd);
	
	public MhsCenterInfo selectMhsCenterInfo (String mhsCentercd);
	
	public List<MhsCenterInfoVO> selectMhsCenterList(MhsCenterInfoVO mhsCenterInfoVO);

	public int insertMhsCenter (MhsCenterInfo vo);
	
	public int updateMhsCenter (MhsCenterInfo vo);
	
	public int deleteMhsCenter (String  mhsCentercd);
}
