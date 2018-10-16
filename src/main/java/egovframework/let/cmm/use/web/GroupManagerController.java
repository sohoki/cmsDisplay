package egovframework.let.cmm.use.web;



import java.util.List;

import egovframework.com.cmm.LoginVO;
import egovframework.let.cmm.use.service.Group;
import egovframework.let.cmm.use.service.GroupVo;
import egovframework.let.cmm.use.service.GroupManagerService;


import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.jcodec.common.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.apache.commons.lang3.StringUtils;


@Controller
public class GroupManagerController {

	@Resource(name = "GroupManagerService")
    private GroupManagerService groupManagerService;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	@RequestMapping( value = "/backoffice/sub/basicManage/selectGroupLst.do")
	public String selectUserGroupManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO,
			                                                                           @ModelAttribute("searchVO") GroupVo searchVO,
			                                                                           ModelMap model
			                                                                           )throws Exception {
								
        model.addAttribute("resultList", groupManagerService.selectUserGroupManageListByPagination(searchVO));
        int totalCnt = groupManagerService.selectGroupManageListTotCnt_S(searchVO);
        model.addAttribute("totalCnt", totalCnt);		
		return "/backoffice/sub/basicManage/group_list";
	}
	
	
	
	@SuppressWarnings("finally")	
	@RequestMapping(value="/backoffice/sub/basicManage/deleteGroup.do")
	public String deleteGroupManage(@ModelAttribute("loginVO") LoginVO loginVO, 
			                                             Group vo,
			                                             ModelMap model
			                                             )throws Exception{
		model.addAttribute("detail",vo );
		if (StringUtils.equals(vo.getGroupId(), Globals.REGITER_SYSTEM)){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
			return "forward:/backoffice/sub/basicManage/selectGroupLst.do";			        
		}
				
		try{
			
			
		      int ret = 	groupManagerService.deleteGroupManage(vo.getGroupId().toString().trim());
		      
		      if (ret > 0 ) {		    	  
		    	  model.addAttribute("status", Globals.STATUS_SUCCESS);
		    	  model.addAttribute("message", egovMessageSource.getMessage("success.common.delete") );
		    	  
		      }else {		    	  
		    	    model.addAttribute("status", Globals.STATUS_FAIL);
					model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		      }
		}catch (Exception e){
			Logger.debug("deleteGroupManage error:" + e.toString());
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));			
		}		
		finally{
			return "forward:/backoffice/sub/basicManage/selectGroupLst.do";				
		}		
	}		
	
	@SuppressWarnings("finally")	
	@RequestMapping(value = "/backoffice/sub/basicManage/updateGroup.do")
	public String insertGroupManage(@ModelAttribute("loginVO") LoginVO loginVO,	
			                                             Group vo,
			                                             ModelMap model
			                                             )throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";		
		try{
			int ret  = 0;
			if (vo.getMode().equals("Ins")){
				ret = groupManagerService.insertGroupManage(vo);
				meesage = "sucess.common.insert";				
			}else {
				 ret = groupManagerService.updateGroupManage(vo);
				 meesage = "sucess.common.update";				 
			}	
			
			
			if (ret >0){
				model.addAttribute("status", Globals.STATUS_SUCCESS);
				model.addAttribute("message", egovMessageSource.getMessage(meesage));				
			}else {
				throw new Exception();
			}
			
		}catch (Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
		}finally {
			return "/backoffice/sub/basicManage/groupDetail";
		}			
	}
	@RequestMapping (value="/backoffice/sub/basicManage/groupDetail.do")
	public String getGroupManage(@ModelAttribute("loginVO") LoginVO loginVO,	
			                                             Group vo,
			                                             ModelMap model
			                                             )throws Exception{
		model.addAttribute("selectGroup", groupManagerService.selectGroupManageCombo());
		model.addAttribute("regist", vo);
		if (!vo.getMode().equals("Ins")){			
		model.addAttribute("regist",  groupManagerService.selectGroupManageDetail(vo.getGroupId())  );		
		}				
		return "/backoffice/sub/basicManage/groupDetail";
	}
	
	
}
