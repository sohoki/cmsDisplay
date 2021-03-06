package egovframework.let.sts.brd.service;

import java.io.Serializable;
import egovframework.let.sts.brd.service.BasicBrodScheduleInfo;

public class BasicBrodScheduleInfoVO extends BasicBrodScheduleInfo implements  Serializable {

	
	private static final long serialVersionUID = 1L;
	/** 검색조건 */
    private String searchCondition = "";    
    /** 검색Keyword */
    private String searchKeyword = "";    
    /** 검색사용여부 */
    private String searchUseYn = "";    
    /** 현재페이지 */
    private int pageIndex = 1;    
    /** 페이지갯수 */
    private int pageUnit = 10;    
    /** 페이지사이즈 */
    private int pageSize = 10;    
    private int firstIndex = 1;
    private int lastIndex = 1;    
    private int recordCountPerPage = 10;
    
    private String centerId;
    private String centerNm;
    private String basicGroupNm;
    private String centerGubun;
    private String preCreateCheck;
    
    
    private String didId;
    private String didMac;
    
    
    
    
    
    
    
    
    
    
    
	public String getDidId() {
		return didId;
	}
	public void setDidId(String didId) {
		this.didId = didId;
	}
	public String getDidMac() {
		return didMac;
	}
	public void setDidMac(String didMac) {
		this.didMac = didMac;
	}
	public String getPreCreateCheck() {
		return preCreateCheck;
	}
	public void setPreCreateCheck(String preCreateCheck) {
		this.preCreateCheck = preCreateCheck;
	}
	public String getCenterGubun() {
		return centerGubun;
	}
	public void setCenterGubun(String centerGubun) {
		this.centerGubun = centerGubun;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getCenterNm() {
		return centerNm;
	}
	public void setCenterNm(String centerNm) {
		this.centerNm = centerNm;
	}
	public String getBasicGroupNm() {
		return basicGroupNm;
	}
	public void setBasicGroupNm(String basicGroupNm) {
		this.basicGroupNm = basicGroupNm;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getSearchUseYn() {
		return searchUseYn;
	}
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
    
    
    
    
    
	
}
