package egovframework.let.sts.mhs.service;

import java.io.Serializable;

import egovframework.let.sts.mhs.service.MhsViewConnInfo;

public class MhsViewConnInfoVO extends MhsViewConnInfo implements Serializable  {

	
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
    
    private String mhsMonitornm;
	private String mhsClassnm;
	private String mhsTeachernm;
	private String mhsClassstartday;
	private String mhsClassendday;
	private String mhsClassdayofweek;
	private String mhsClassstarttime;
	private String mhsClassendtime;
	private String mhsClassstatus;
	private String mhsClassintro;
    private String searchDay;
    private String mhsBrandcd;
    private String mhsCentercd;
    private String mhsNowgubun;
    
    
    
	public String getMhsNowgubun() {
		return mhsNowgubun;
	}
	public void setMhsNowgubun(String mhsNowgubun) {
		this.mhsNowgubun = mhsNowgubun;
	}
	public String getMhsBrandcd() {
		return mhsBrandcd;
	}
	public void setMhsBrandcd(String mhsBrandcd) {
		this.mhsBrandcd = mhsBrandcd;
	}
	public String getMhsCentercd() {
		return mhsCentercd;
	}
	public void setMhsCentercd(String mhsCentercd) {
		this.mhsCentercd = mhsCentercd;
	}
	public String getMhsMonitornm() {
		return mhsMonitornm;
	}
	public void setMhsMonitornm(String mhsMonitornm) {
		this.mhsMonitornm = mhsMonitornm;
	}
	public String getMhsClassnm() {
		return mhsClassnm;
	}
	public void setMhsClassnm(String mhsClassnm) {
		this.mhsClassnm = mhsClassnm;
	}
	public String getMhsTeachernm() {
		return mhsTeachernm;
	}
	public void setMhsTeachernm(String mhsTeachernm) {
		this.mhsTeachernm = mhsTeachernm;
	}
	public String getMhsClassstartday() {
		return mhsClassstartday;
	}
	public void setMhsClassstartday(String mhsClassstartday) {
		this.mhsClassstartday = mhsClassstartday;
	}
	public String getMhsClassendday() {
		return mhsClassendday;
	}
	public void setMhsClassendday(String mhsClassendday) {
		this.mhsClassendday = mhsClassendday;
	}
	public String getMhsClassdayofweek() {
		return mhsClassdayofweek;
	}
	public void setMhsClassdayofweek(String mhsClassdayofweek) {
		this.mhsClassdayofweek = mhsClassdayofweek;
	}
	public String getMhsClassstarttime() {
		return mhsClassstarttime;
	}
	public void setMhsClassstarttime(String mhsClassstarttime) {
		this.mhsClassstarttime = mhsClassstarttime;
	}
	public String getMhsClassendtime() {
		return mhsClassendtime;
	}
	public void setMhsClassendtime(String mhsClassendtime) {
		this.mhsClassendtime = mhsClassendtime;
	}
	public String getMhsClassstatus() {
		return mhsClassstatus;
	}
	public void setMhsClassstatus(String mhsClassstatus) {
		this.mhsClassstatus = mhsClassstatus;
	}
	public String getMhsClassintro() {
		return mhsClassintro;
	}
	public void setMhsClassintro(String mhsClassintro) {
		this.mhsClassintro = mhsClassintro;
	}
	public String getSearchDay() {
		return searchDay;
	}
	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
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
