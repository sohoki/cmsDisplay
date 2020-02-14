package egovframework.let.sts.met.web;



import java.util.Iterator;

import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.let.cmm.use.service.GroupManagerService;
import egovframework.let.cmm.use.service.GroupVo;
import egovframework.let.sym.did.service.DidInfo;
import egovframework.let.sym.did.service.DidInfoVO;
import egovframework.let.sym.did.service.DidInfoManageService;
import egovframework.let.sym.grp.service.GroupDidInfo;
import egovframework.let.sym.grp.service.GroupDidInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoVO;
import egovframework.let.sym.sch.service.ScheduleInfoManageService;
import egovframework.let.sts.cnt.service.ContentFileInfo;
import egovframework.let.sts.met.service.MeetViewInfo;
import egovframework.let.sts.snd.service.SendMsgInfo;
import egovframework.let.sts.snd.service.SendMsgInfoManageService;
import egovframework.let.sts.xml.service.XmlInfoManageService;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sym.mnu.service.MenuInfoVO;
import egovframework.let.sym.rnt.service.RoleInfoManageService;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.SystemOutLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import java.io.*;
import java.net.*;

@Controller
public class MeetRoomInfoManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MeetRoomInfoManageController.class);


	@Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	

	@RequestMapping ("/backoffice/sub/roomManage/meetRoomList.do")
	public String selectDidInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
														, @ModelAttribute("searchVO") DidInfoVO searchVO
														, HttpServletRequest request
														, BindingResult result
														, ModelMap model) throws Exception {

			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
			if (user != null ){
				searchVO.setAuthor_Code(user.getAuthorCode());
				searchVO.setGroupCode(user.getGroupId());
				searchVO.setParentGroupId(user.getParentGroupId());
			}else {
				System.out.println("로그인 기록 없음");		    	
				return "/backoffice/login";
			}			  			    
			 
			return "/backoffice/sub/roomManage/meetRoomList";
	}
	
	
	public String meetroomViewCreate(String mViewNo, boolean localType){
		/**
		 * 회의실 화면 표출 HTML 생성
		 * mViewNo : 화면을 생성 할 구분번호
		 * localType : 화면 생성의 용도 (미리보기 : true / 배포용 : false)
		 * */
		
		String createHTML = "";
		String localTypeAddDot = "";
		StringBuilder htmlSource = new StringBuilder();
		MeetViewInfo meetViewInfo = new MeetViewInfo();
		
		
		try {
			
			// 01. 바로 아랫줄에서 넘어온 mViewNo use -> select MeetViewInfo data
			
			
			if(meetViewInfo.getmViewNo() != null && !meetViewInfo.getmViewNo().equals("0")){
				// 화면구성 기본 데이터에 문제가 없음
				if(meetViewInfo.getmViewType() != null && !meetViewInfo.getmViewType().equals("")){
					// 화면정보에 문제가 없음
					
					if(localType){
						localTypeAddDot = ".";
					} else {
						localTypeAddDot = "";
					}
					
					
					htmlSource.append("<!DOCTYPE HTML>\r\n");
					htmlSource.append("<html>\r\n");
					htmlSource.append("<head>\r\n");
					htmlSource.append("    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>\r\n");
					htmlSource.append("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\r\n");
					htmlSource.append("    <title>" + meetViewInfo.getmViewNm() + "</title>\r\n");
					htmlSource.append("    <link rel='stylesheet' href='"+localTypeAddDot+"/css/paragraph.css'>\r\n");
					htmlSource.append("    <link rel='stylesheet' href='"+localTypeAddDot+"/css/reset.css'>\r\n");
					htmlSource.append("    <script type='text/javascript' src='"+localTypeAddDot+"/jquery-2.2.4.min.js'></script>\r\n");
					htmlSource.append("    <link rel='stylesheet' href='"+localTypeAddDot+"/css/swiper.css'>\r\n");
					htmlSource.append("</head>\r\n");
					htmlSource.append("<body>\r\n");
					htmlSource.append("    <div class='wrap'>\r\n");
					htmlSource.append("        <div class='header'>\r\n");
					htmlSource.append("            <div class='contents'>\r\n");
					if(meetViewInfo.getmViewType().equals("ROOM_TYPE1")){
					htmlSource.append("                <div class='float_left'>\r\n");
					htmlSource.append("                    <span></span>\r\n");
					htmlSource.append("                    <span></span>\r\n");
					htmlSource.append("                </div>\r\n");
					htmlSource.append("                <div class='float_right'>\r\n");
					htmlSource.append("                    <ul class='date'>\r\n");
					htmlSource.append("                        <li class='fix_view_monthday'></li>\r\n");
					htmlSource.append("                        <li class='fix_view_dayOfWeek'></li>\r\n");
					htmlSource.append("                    </ul>\r\n");
					htmlSource.append("                    <p class='fix_view_dateTime'></p>\r\n");
					htmlSource.append("                </div>\r\n");
					htmlSource.append("                <div class='clear'></div>\r\n");
					htmlSource.append("            </div>\r\n");
					htmlSource.append("            <div class='main_slider'>\r\n");
					htmlSource.append("                <div class='swiper-container'>\r\n");
					htmlSource.append("                    <div class='swiper-wrapper'>\r\n");
					htmlSource.append("                        <div class='swiper-slide slide01'>\r\n");
					htmlSource.append("                            <div class='contents'>\r\n");
					htmlSource.append("                                <div class='margin-top90'>\r\n");
					htmlSource.append("                                    <div class='meeting_tit'>\r\n");
					htmlSource.append("                                        <p class='meeting_noti'></p>\r\n");
					htmlSource.append("                                        <h3 class='meeting_txt'></h3>\r\n");
					htmlSource.append("                                    </div>\r\n");
					htmlSource.append("                                    <div class='float_right'>\r\n");
					htmlSource.append("                                        <span class='onMeeting'></span>\r\n");
					htmlSource.append("                                    </div>\r\n");
					htmlSource.append("                                    <div class='clear'></div>\r\n");
					htmlSource.append("                                </div>\r\n");
					htmlSource.append("                                <div class='user_nam text-right'>\r\n");
					htmlSource.append("                                        <p class='meeting_noti'></p>\r\n");
					htmlSource.append("                                        <p class='meeting_part'></p>\r\n");
					htmlSource.append("                                        <p class='meeting_user'></p>\r\n");
					htmlSource.append("                                </div>\r\n");
					htmlSource.append("                                <div class='next_reser'>\r\n");
					htmlSource.append("                                    <ul>\r\n");
					htmlSource.append("                                        <li>다음예약</li>\r\n");
					htmlSource.append("                                        <li></li>\r\n");
					htmlSource.append("                                    </ul>\r\n");
					htmlSource.append("                                    <div class='clear'></div>\r\n");
					htmlSource.append("                                </div>\r\n");
					htmlSource.append("                            </div>\r\n");
					htmlSource.append("                        </div>\r\n");
					htmlSource.append("                        <div class='swiper-slide slide02'>\r\n");
					htmlSource.append("                            <div class='contents'>\r\n");
					htmlSource.append("                                <div class='margin-top90'>\r\n");
					htmlSource.append("                                    <div class='meeting_tit'>\r\n");
					htmlSource.append("                                        <p class='meeting_noti'></p>\r\n");
					htmlSource.append("                                        <h3 class='meeting_txt'></h3>\r\n");
					htmlSource.append("                                    </div>\r\n");
					htmlSource.append("                                    <div class='float_right'>\r\n");
					htmlSource.append("                                        <span class='offMeeting'></span>\r\n");
					htmlSource.append("                                    </div>\r\n");
					htmlSource.append("                                </div>\r\n");
					htmlSource.append("                                <div class='user_nam text-right'>\r\n");
					htmlSource.append("                                    <p class='meeting_noti'></p>\r\n");
					htmlSource.append("                                    <p class='meeting_part'></p>\r\n");
					htmlSource.append("                                    <p class='meeting_user'></p>\r\n");
					htmlSource.append("                                </div>\r\n");
					htmlSource.append("                            </div>\r\n");
					htmlSource.append("                        </div>\r\n");
					htmlSource.append("                        <div class='swiper-slide slide03'>\r\n");
					htmlSource.append("                            <div class='contents'>\r\n");
					htmlSource.append("                                <h2 class='slider_tit'>회의실 예약 현황</h2>\r\n");
					htmlSource.append("                                <table>\r\n");
					htmlSource.append("                                    <thead>\r\n");
					htmlSource.append("                                        <tr>\r\n");
					htmlSource.append("                                            <th>시간</th>\r\n");
					htmlSource.append("                                            <th>상태</th>\r\n");
					htmlSource.append("                                            <th>회의주제</th>\r\n");
					htmlSource.append("                                            <th>부서</th>\r\n");
					htmlSource.append("                                            <th>사용자</th>\r\n");
					htmlSource.append("                                        </tr>\r\n");
					htmlSource.append("                                    </thead>\r\n");
					htmlSource.append("                                    <tbody>\r\n");
					htmlSource.append("                                        <!-- 여기에 앞으로 남은 회의 내용 반복 표출 -->\r\n");
					htmlSource.append("                                        <!-- 현재 시간에 해당 되는 경우 tr에  class='metting_active' 추가 -->\r\n");
					htmlSource.append("                                    </tbody>\r\n");
					htmlSource.append("                                </table>\r\n");
					htmlSource.append("                            </div>\r\n");
					htmlSource.append("                        </div>\r\n");
					htmlSource.append("                    </div>\r\n");
					htmlSource.append("                    <div class='swiper-pagination'></div>\r\n");
					htmlSource.append("                </div>\r\n");
					htmlSource.append("            </div>\r\n");
					htmlSource.append("            <div class='footer'>\r\n");
					htmlSource.append("                <div class='footer'>\r\n");
					htmlSource.append("                    <h1><img src='/"+localTypeAddDot+"/img/meet_logo.png' alt='이마트로고'></h1>\r\n");
					htmlSource.append("                </div>\r\n");
					htmlSource.append("            </div>\r\n");
					} else {
						htmlSource.append("                <div class='two_box'>\r\n");
						htmlSource.append("                    <span class='room_name room_place_left'></span>\r\n");
						htmlSource.append("                    <span class='floor room_place_left'></span>\r\n");
						htmlSource.append("                </div>\r\n");
						htmlSource.append("                <div class='two_box'>\r\n");
						htmlSource.append("                    <div class='padding-left45'>\r\n");
						htmlSource.append("                        <span class='room_name room_place_right'></span>\r\n");
						htmlSource.append("                        <span class='floor room_place_right'></span>\r\n");
						htmlSource.append("                    </div>\r\n");
						htmlSource.append("                </div>\r\n");
						htmlSource.append("                <div class='clear'></div>\r\n");
						htmlSource.append("            </div>\r\n");
						htmlSource.append("            <div>\r\n");
						
						htmlSource.append("            <div class='main_slider two_container'>\r\n");
						htmlSource.append("            </div>\r\n");
						
						
						htmlSource.append("            </div>\r\n");
					}
					
					
					
					
					
					htmlSource.append("            <div>\r\n");
					htmlSource.append("            </div>\r\n");
					
					
					
					htmlSource.append("        </div>\r\n");
					htmlSource.append("    </div>\r\n");
					htmlSource.append("\r\n");
					htmlSource.append("\r\n");
					if(meetViewInfo.getmViewType().equals("ROOM_TYPE1")){
						// Single Meetroom View
						if(meetViewInfo.getmViewRoom1() != null && !meetViewInfo.getmViewRoom1().equals("")){
							// 회의실1 정보에 문제가 없음
							htmlSource.append("\r\n");
							htmlSource.append("\r\n");
							
							
						} else {
							// 회의실1 정보에 문제가 있음
							return "";
						}
					} else {
						// Double Meetroom View
						if(meetViewInfo.getmViewRoom1() != null 
								&& !meetViewInfo.getmViewRoom1().equals("")
								&& meetViewInfo.getmViewRoom2() != null 
								&& !meetViewInfo.getmViewRoom2().equals("")){
							// 회의실1, 2 정보에 문제가 없음
							htmlSource.append("\r\n");
							htmlSource.append("\r\n");
						} else {
							// 회의실1, 2 정보에 문제가 있음
							return "";
						}
					}
					htmlSource.append("</body>\r\n");
					htmlSource.append("</html>\r\n");
				} else {
					// 화면정보에 문제가 있음
					return "";
				}
			} else {
				// 화면구성 기본 데이터에 문제가 있음
				return "";
			}
			
		} catch (Exception e){
			
		}
		
		return createHTML;
	}
	
	
}
