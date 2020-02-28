package com.cms.sohoki.sym.pro.service;

import java.io.Serializable;

public class ProgDeployInfo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String depSeq ="";
	private String progCode = "";
	private String didId = "";
	private String deplySenddate = "";
	private String deplyRecdate = "";
	private String deplyResult = "";
    private String mode = "";
    
    
    
	
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDepSeq() {
		return depSeq;
	}
	public void setDepSeq(String depSeq) {
		this.depSeq = depSeq;
	}
	public String getProgCode() {
		return progCode;
	}
	public void setProgCode(String progCode) {
		this.progCode = progCode;
	}
	public String getDidId() {
		return didId;
	}
	public void setDidId(String didId) {
		this.didId = didId;
	}
	public String getDeplySenddate() {
		return deplySenddate;
	}
	public void setDeplySenddate(String deplySenddate) {
		this.deplySenddate = deplySenddate;
	}
	public String getDeplyRecdate() {
		return deplyRecdate;
	}
	public void setDeplyRecdate(String deplyRecdate) {
		this.deplyRecdate = deplyRecdate;
	}
	public String getDeplyResult() {
		return deplyResult;
	}
	public void setDeplyResult(String deplyResult) {
		this.deplyResult = deplyResult;
	}
	
	
	
	
	
}
