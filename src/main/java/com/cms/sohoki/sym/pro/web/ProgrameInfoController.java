package com.cms.sohoki.sym.pro.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.rte.fdl.property.EgovPropertyService;


@Controller
public class ProgrameInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProgrameInfoController.class);
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    
    @Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
    
    @Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
	
	
}
