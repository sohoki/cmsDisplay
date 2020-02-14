package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sts.mhs.service.MhsClassInfo;
import egovframework.let.sts.mhs.service.MhsClassInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MhsClassManageMapper")
public interface MhsClassManageMapper {
	
	public List<MhsClassInfoVO> selectMhsClassList(MhsClassInfoVO searchVO);
	
	public int selectMhsClassListCnt(MhsClassInfoVO searchVO);
	
	public List<MhsClassInfo> selectMhsMoniterClassList(MhsClassInfo vo);
	
	public MhsClassInfo selectMhsClassInfo(String mhsClasscd);
	
	public int insertMhsClassInfo(MhsClassInfo vo);
	
	public int updateMhsClassInfo(MhsClassInfo vo);
	
	public int deleteMhsClassInfo(String mhsClasscd);

}
