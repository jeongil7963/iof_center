package com.iof.center.admin.uploadfiles.vo;

import java.io.File;

public class FileDelete { //파일삭제
	public static void boardFileDelete(String path, String name) {
			int idx = path.indexOf("/"+name);
			String mail1 = path.substring(0, idx);
			System.out.println("mail1 = "+mail1);
			File file = new File(mail1);
	        if( file.exists() ){ //파일존재여부확인
	            if(file.isDirectory()){ //파일이 디렉토리인지 확인
	                File[] files = file.listFiles();
	                for( int i=0; i<files.length; i++){
	                    if( files[i].delete() ){
	                        System.out.println(files[i].getName()+" 삭제성공");
	                    }else{
	                        System.out.println(files[i].getName()+" 삭제실패");
	                    }
	                }
	            }
	            if(file.delete()){
	                System.out.println("파일삭제 성공");
	            }else{
	                System.out.println("파일삭제 실패");
	            }
	        }else{
	            System.out.println("파일이 존재하지 않습니다.");
	        }
        }
	
	public static void OnlyFileDelete(String RootPath, String onpath1) {
		String path = RootPath + onpath1;
		System.out.println("delete file path :" + path );
		File file = new File(path);
		if(file.exists()==true) {
			file.delete();
		}
    }
    }