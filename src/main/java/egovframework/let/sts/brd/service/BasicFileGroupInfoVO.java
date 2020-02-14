package egovframework.let.sts.brd.service;

import java.io.Serializable;

import egovframework.let.sts.brd.service.BasicFileGroupInfo;
public class BasicFileGroupInfoVO extends BasicFileGroupInfo implements Serializable {
	
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
    private int rnum = 0;
    
    private String filePls = "";
    private String fileMin = "";
    
    private String timeCnt = "";
    private String inutCnt = "";
    
    private String cp_copyGroupSeq = "";
    
    
    
    
    
    
    
	public String getCp_copyGroupSeq() {
		return cp_copyGroupSeq;
	}
	public void setCp_copyGroupSeq(String cp_copyGroupSeq) {
		this.cp_copyGroupSeq = cp_copyGroupSeq;
	}
	public String getTimeCnt() {
		return timeCnt;
	}
	public void setTimeCnt(String timeCnt) {
		this.timeCnt = timeCnt;
	}
	public String getInutCnt() {
		return inutCnt;
	}
	public void setInutCnt(String inutCnt) {
		this.inutCnt = inutCnt;
	}
	public String getFilePls() {
		return filePls;
	}
	public void setFilePls(String filePls) {
		this.filePls = filePls;
	}
	public String getFileMin() {
		return fileMin;
	}
	public void setFileMin(String fileMin) {
		this.fileMin = fileMin;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
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
