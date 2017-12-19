package com.iof.center.admin.uploadfiles.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class AdminUploadfilesVo {
	private int frm_f_idx;
	private int frm_idx;
	private String frm_f_filepath;
	private String frm_f_filename;
	private String frm_f_filename_org;
	private String frm_f_size;
	private String frm_f_type;
	private int frm_f_no;
	private String frm_f_createtime;
	private String frm_f_updatetime;
	private int pageBegin;
	private Integer frm_f_random;
	 
	
	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public String getFrm_f_updatetime() {
		return frm_f_updatetime;
	}

	public Integer getRandom() {
		return frm_f_random;
	}

	public void setRandom(Integer random) {
		this.frm_f_random = random;
	}

	public void setFrm_f_updatetime(String frm_f_updatetime) {
		this.frm_f_updatetime = frm_f_updatetime;
	}

	public AdminUploadfilesVo() {
		super();
	}

	public AdminUploadfilesVo(int frm_f_idx, int frm_idx, String frm_f_filepath, String frm_f_filename,
			String frm_f_filename_org, String frm_f_size, String frm_f_type, int frm_f_no, String frm_f_createtime, String frm_f_editor) {
		this.frm_f_idx = frm_f_idx;
		this.frm_idx = frm_idx;
		this.frm_f_filepath = frm_f_filepath;
		this.frm_f_filename = frm_f_filename;
		this.frm_f_filename_org = frm_f_filename_org;
		this.frm_f_size = frm_f_size;
		this.frm_f_type = frm_f_type;
		this.frm_f_no = frm_f_no;
		this.frm_f_createtime = frm_f_createtime;
		
	}

	public int getFrm_f_idx() {
		return frm_f_idx;
	}

	public void setFrm_f_idx(int frm_f_idx) {
		this.frm_f_idx = frm_f_idx;
	}

	public int getFrm_idx() {
		return frm_idx;
	}

	public void setFrm_idx(int frm_idx) {
		this.frm_idx = frm_idx;
	}

	public String getFrm_f_filepath() {
		return frm_f_filepath;
	}

	public void setFrm_f_filepath(String frm_f_filepath) {
		this.frm_f_filepath = frm_f_filepath;
	}

	public String getFrm_f_filename() {
		return frm_f_filename;
	}

	public void setFrm_f_filename(String frm_f_filename) {
		this.frm_f_filename = frm_f_filename;
	}

	public String getFrm_f_filename_org() {
		return frm_f_filename_org;
	}

	public void setFrm_f_filename_org(String frm_f_filename_org) {
		this.frm_f_filename_org = frm_f_filename_org;
	}

	public String getFrm_f_size() {
		return frm_f_size;
	}

	public void setFrm_f_size(String frm_f_size) {
		this.frm_f_size = frm_f_size;
	}

	public String getFrm_f_type() {
		return frm_f_type;
	}

	public void setFrm_f_type(String frm_f_type) {
		this.frm_f_type = frm_f_type;
	}

	public int getFrm_f_no() {
		return frm_f_no;
	}

	public void setFrm_f_no(int frm_f_no) {
		this.frm_f_no = frm_f_no;
	}

	public String getFrm_f_createtime() {
		return frm_f_createtime;
	}

	public void setFrm_f_createtime(String frm_f_createtime) {
		this.frm_f_createtime = frm_f_createtime;
	}


	public String uploadFile(String originalName, byte[] fileData, String fseperate) throws Exception{
	        // uuid 생성(Universal Unique IDentifier, 범용 고유 식별자)
	        UUID uuid = UUID.randomUUID();
	        
	        // 랜덤생성+파일이름 저장
	        String savedName = uuid.toString()+"."+fseperate;
	        //File target = new File(uploadPath, savedName);
	        // 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
	        // FileCopyUtils.copy(바이트배열, 파일객체)
	        //FileCopyUtils.copy(fileData, target);
	        return savedName;
	    }
	 
	 public static void badFileExtIsReturnException(File file) {
	        String fileName = file.getName();
	        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,
	                fileName.length());
	        final String[] BAD_EXTENSION = { "jsp", "php", "asp", "html", "perl" };
	 
	        try {
	            int len = BAD_EXTENSION.length;
	            for (int i = 0; i < len; i++) {
	                if (ext.equalsIgnoreCase(BAD_EXTENSION[i])) {
	                    // 불량 확장자가 존재할떄 IOExepction 발생
	                	 System.out.println("불량존자확인");
	                    throw new IOException("BAD EXTENSION FILE UPLOAD");
	                }
	            }
	        } catch (IOException e) {
	           
	        	e.printStackTrace();
	        }
	    }
	 
	    /**
	     * 파일의 확장자를 체크하여 필터링된 확장자를 포함한 파일인 경우에 true를 리턴한다.
	     * @param file
	     * */
	    public static boolean badFileExtIsReturnBoolean(File file) {
	        String fileName = file.getName();
	        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,
	                fileName.length());
	        final String[] BAD_EXTENSION = { "jsp", "php", "asp", "html", "perl" };
	 
	        int len = BAD_EXTENSION.length;
	        for (int i = 0; i < len; i++) {
	            if (ext.equalsIgnoreCase(BAD_EXTENSION[i])) {
	                return true; // 불량 확장자가 존재할때..
	            }
	        }
	        return false;
	    }
}
