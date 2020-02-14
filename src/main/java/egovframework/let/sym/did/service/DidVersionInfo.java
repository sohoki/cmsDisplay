package egovframework.let.sym.did.service;

import java.io.Serializable;

public class DidVersionInfo implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String didId = "";
	private String didVersion = "";
	private String didUpdateCheck = "";
	private String didUpdateDate = "";
	private String didUpdateDownloadcheck = "";
	private String didUpdateDownloadDate = "";
	private String progCode = "";
	
	
	public String getDidId() {
		return didId;
	}
	public void setDidId(String didId) {
		this.didId = didId;
	}
	public String getDidVersion() {
		return didVersion;
	}
	public void setDidVersion(String didVersion) {
		this.didVersion = didVersion;
	}
	public String getDidUpdateCheck() {
		return didUpdateCheck;
	}
	public void setDidUpdateCheck(String didUpdateCheck) {
		this.didUpdateCheck = didUpdateCheck;
	}
	public String getDidUpdateDate() {
		return didUpdateDate;
	}
	public void setDidUpdateDate(String didUpdateDate) {
		this.didUpdateDate = didUpdateDate;
	}
	public String getDidUpdateDownloadcheck() {
		return didUpdateDownloadcheck;
	}
	public void setDidUpdateDownloadcheck(String didUpdateDownloadcheck) {
		this.didUpdateDownloadcheck = didUpdateDownloadcheck;
	}
	public String getDidUpdateDownloadDate() {
		return didUpdateDownloadDate;
	}
	public void setDidUpdateDownloadDate(String didUpdateDownloadDate) {
		this.didUpdateDownloadDate = didUpdateDownloadDate;
	}
	public String getProgCode() {
		return progCode;
	}
	public void setProgCode(String progCode) {
		this.progCode = progCode;
	}
	
	
	
	
}
