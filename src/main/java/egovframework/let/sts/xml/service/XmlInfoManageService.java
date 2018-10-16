package egovframework.let.sts.xml.service;

import java.util.List;

public interface XmlInfoManageService {

	 List<XmlInfoVO> selectXmlInfoManageListByPagination(  XmlInfoVO searchVO) throws Exception ;
	
	 XmlInfoVO selectXmlrInfoManageDetail(String xmlSeq) throws Exception;
	 
	 XmlInfoVO selectXmlrInfoManageNameDetail(String xmlProcessName)throws Exception;
	 
	 List<XmlInfo> selectXmlProcessCombo()throws Exception;
	
	 int selectXmlInfoManageListTotCnt_S(  XmlInfoVO searchVO) throws Exception;
	 
	 
	
	 int insertXmlInfoManage(  XmlInfo vo) throws Exception;
	
	 int updateXmlInfoManage(  XmlInfo vo) throws Exception;
	
	 int deleteXmlInfoManage(String xmlSeq) throws Exception;
	 
	 int selectXmlProcessCount(String xmlProcessName) throws Exception;
	 
	 
	 String selectDIDProcessNm (String code) throws Exception;
}
