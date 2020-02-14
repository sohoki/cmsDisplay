package egovframework.let.sts.brd.service;

import java.io.Serializable;

public class BasicFileGroupPlayInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;	

	private String centerId = "";
	private String basicCode = "";
	private String playDay = "";
	private String atchFileId = "";
	private String playCnt = "";
	private String mode = "";
	
	
	
	public String getPlayCnt() {
		return playCnt;
	}
	public void setPlayCnt(String playCnt) {
		this.playCnt = playCnt;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getBasicCode() {
		return basicCode;
	}
	public void setBasicCode(String basicCode) {
		this.basicCode = basicCode;
	}
	public String getPlayDay() {
		return playDay;
	}
	public void setPlayDay(String playDay) {
		this.playDay = playDay;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	
	
	
}
