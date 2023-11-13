package chapter07;

import lombok.Data;

@Data
public class StudentVO {
	private int studno;
	private String name;
	private String id;
	private String telephone;
	private int grade;
	
	//sql에 VO로 파라미터를 주고받기위해 추가
	private String sgrade;//학년검색을위한필드
	private String searchType;
	private String searchWord;

}
