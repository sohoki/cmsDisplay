package com.cms.sohoki.sym.pro.service;

import java.io.Serializable;

public class ProgrameInfo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String progCode = "";
	private String progTitle = "";
	private String fileInfo = "";
    private String frstRegistPnttm;
    private String frstRegisterId;
    private String lastRegistPnttm;
    private String lastUpdusrId;
    private String mode = "";
    private String userId = "";
    private String progOstype= "";
    private String progVersion = "";
    
    
    
    
    
    
	public String getProgVersion() {
		return progVersion;
	}
	public void setProgVersion(String progVersion) {
		this.progVersion = progVersion;
	}
	public String getProgOstype() {
		return progOstype;
	}
	public void setProgOstype(String progOstype) {
		this.progOstype = progOstype;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProgCode() {
		return progCode;
	}
	public void setProgCode(String progCode) {
		this.progCode = progCode;
	}
	public String getProgTitle() {
		return progTitle;
	}
	public void setProgTitle(String progTitle) {
		this.progTitle = progTitle;
	}
	public String getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	public String getLastRegistPnttm() {
		return lastRegistPnttm;
	}
	public void setLastRegistPnttm(String lastRegistPnttm) {
		this.lastRegistPnttm = lastRegistPnttm;
	}
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
}
