package egovframework.let.sym.did.service;

import java.io.Serializable;

import egovframework.let.sym.did.service.DidInfo;

public class DidInfoVO extends DidInfo implements Serializable {

	
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
    
    
    private String searchdidSwver = "";
    private String searchschCode = "";
    private String requestSystemType = "";
    
    
    private String author_Code;
    private String role_Code;
    
    private String schCnt;
    private String schCode;
    private String schNm;
    private String schStartday;
    private String schEndday;
    private String conSeq;
    private String conNm;
    
    
    
    
    
       
	public String getRequestSystemType() {
		return requestSystemType;
	}
	public void setRequestSystemType(String requestSystemType) {
		this.requestSystemType = requestSystemType;
	}
	public String getSchCnt() {
		return schCnt;
	}
	public void setSchCnt(String schCnt) {
		this.schCnt = schCnt;
	}
	public String getSchCode() {
		return schCode;
	}
	public void setSchCode(String schCode) {
		this.schCode = schCode;
	}
	public String getSchNm() {
		return schNm;
	}
	public void setSchNm(String schNm) {
		this.schNm = schNm;
	}
	public String getSchStartday() {
		return schStartday;
	}
	public void setSchStartday(String schStartday) {
		this.schStartday = schStartday;
	}
	public String getSchEndday() {
		return schEndday;
	}
	public void setSchEndday(String schEndday) {
		this.schEndday = schEndday;
	}
	public String getConSeq() {
		return conSeq;
	}
	public void setConSeq(String conSeq) {
		this.conSeq = conSeq;
	}
	public String getConNm() {
		return conNm;
	}
	public void setConNm(String conNm) {
		this.conNm = conNm;
	}
	public String getAuthor_Code() {
		return author_Code;
	}
	public void setAuthor_Code(String author_Code) {
		this.author_Code = author_Code;
	}
	public String getRole_Code() {
		return role_Code;
	}
	public void setRole_Code(String role_Code) {
		this.role_Code = role_Code;
	}
	public String getSearchdidSwver() {
		return searchdidSwver;
	}
	public void setSearchdidSwver(String searchdidSwver) {
		this.searchdidSwver = searchdidSwver;
	}
	public String getSearchschCode() {
		return searchschCode;
	}
	public void setSearchschCode(String searchschCode) {
		this.searchschCode = searchschCode;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
	
}
