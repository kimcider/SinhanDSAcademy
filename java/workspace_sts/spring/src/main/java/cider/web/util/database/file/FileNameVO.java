package cider.web.util.database.file;

import lombok.Getter;
import lombok.Setter;

/*
 * Uploader와 Downloader를 사용하기 위한 매개 클래스
 * 
 * 서버에 파일을 업로드할 경우 realPath가 반드시 필요하다.  
 * uploading_filename는 사용자가 서버에 파일을 올릴 때의 파일 명이다.
 * saved_filename는 서버에 저장된 파일명이다.
 * 
 */
@Getter@Setter
public class FileNameVO {
	/* 서버의 실제 경로 */
	private String realPath;
	
	/* 클라이언트가 파일을 업로드 할 때의 파일의 이름 */
	private String uploaded_filename;
	
	/* 클라이언트가 업로드 한 파일이 서버에 저장될 때의 이름 */
	private String saved_filename;
	
	/* 서버에 파일을 업로드할 경우 realPath가 반드시 필요하다 */
	public FileNameVO(String realPath) {
		this.realPath = realPath;
	}
	
	/* 서버에서 파일을 다운로드 할 경우 모든 필드가 필요하다 */
	public FileNameVO(String realPath, String uploaded_filename, String saved_filename) {
		this.realPath = realPath;
		this.uploaded_filename = uploaded_filename;
		this.saved_filename = saved_filename;
	}
	
}
