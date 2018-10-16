package egovframework.let.sts.xml.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sts.xml.service.XmlInfo;
import egovframework.let.sts.xml.service.XmlInfoVO;
import egovframework.let.sts.xml.service.XmlInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.com.mapper.XmlInfoManagerMapper;

@Service("XmlInfoManageService")
public class XmlInfoManageServiceImpl extends EgovAbstractServiceImpl implements XmlInfoManageService {

	
	@Resource(name= "XmlInfoManagerMapper")
	private XmlInfoManagerMapper xmlInfoManagerMapper;
	
	@Override
	public List<XmlInfoVO> selectXmlInfoManageListByPagination(
			XmlInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectXmlInfoManageListByPagination(searchVO);
	}

	@Override
	public XmlInfoVO selectXmlrInfoManageDetail(String xmlSeq) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectXmlrInfoManageDetail(xmlSeq);
	}

	@Override
	public int selectXmlInfoManageListTotCnt_S(XmlInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectXmlInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public int insertXmlInfoManage(XmlInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.insertXmlInfoManage(vo);
	}

	@Override
	public int updateXmlInfoManage(XmlInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.updateXmlInfoManage(vo);
	}

	@Override
	public int deleteXmlInfoManage(String xmlSeq) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.deleteXmlInfoManage(xmlSeq);
	}

	@Override
	public int selectXmlProcessCount(String xmlProcessName) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectXmlProcessCount(xmlProcessName);
	}

	@Override
	public XmlInfoVO selectXmlrInfoManageNameDetail(String xmlProcessName)
			throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectXmlrInfoManageNameDetail(xmlProcessName);
	}

	@Override
	public String selectDIDProcessNm(String code) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectDIDProcessNm(code);
	}

	@Override
	public List<XmlInfo> selectXmlProcessCombo() throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectXmlProcessCombo();
	}
	

}
