package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sts.xml.service.XmlInfo;
import egovframework.let.sts.xml.service.XmlInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("XmlInfoManagerMapper")
public interface XmlInfoManagerMapper {

	
	public List<XmlInfoVO> selectXmlInfoManageListByPagination(  XmlInfoVO searchVO);
	
	public XmlInfoVO selectXmlrInfoManageDetail(String xmlSeq);
	
	public XmlInfoVO selectXmlrInfoManageNameDetail(String xmlProcessName);
	
	public List<XmlInfo> selectXmlProcessCombo();
	
	public int selectXmlInfoManageListTotCnt_S(  XmlInfoVO searchVO);
	
	public int insertXmlInfoManage(  XmlInfo vo);
	
	public int updateXmlInfoManage(  XmlInfo vo);
	
	public int deleteXmlInfoManage(String xmlSeq);
	
	public int selectXmlProcessCount(String xmlProcessName);
	
	
	public String selectDIDProcessNm(String code);
	
}
