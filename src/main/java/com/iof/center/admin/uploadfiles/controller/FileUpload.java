package com.iof.center.admin.uploadfiles.controller;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	 MultipartFile file;
	    private int deptno;

	    public MultipartFile getFile() {
	        return file;
	    }

	    public void setFile(MultipartFile file) {
	        this.file = file;
	    }

	    public int getDeptno() {
	        return deptno;
	    }

	    public void setDeptno(int deptno) {
	        this.deptno = deptno;
	    }
}
