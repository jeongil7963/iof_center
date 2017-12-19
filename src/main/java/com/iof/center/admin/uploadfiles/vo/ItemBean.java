package com.iof.center.admin.uploadfiles.vo;

import org.springframework.web.multipart.MultipartFile;

public class ItemBean {
 
    MultipartFile file;
     
 
    public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }
}


