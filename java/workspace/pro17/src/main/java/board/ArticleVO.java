package board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@AllArgsConstructor@ToString
public class ArticleVO {
	/*
	 * 어차피 DB에는 죄다 대문자로 되어있기 때문에 자바만 대소문자를 구분해서 쓰면 햇갈린다.
	 * 따라서 앵간해서는 얘도 그냥 죄다 소문자로 만들어라. 
	 */
	private int articleno;
	private String title;
	private String content;
	private String imagefilename;
	private Date writedate;
	private String id;
	private int gno;	//그룹 번호
	private int ono;	//그룹 오더
	private int nested;	//들여쓰기 정도
	
	private int page;//페이지번호
	private String searchWord;//검색어
	private int rowPage;//페이지당 개수
	
	public ArticleVO(){
		page = 1;
		rowPage = 1;
	}
	
	//이렇게 하면 컨텐츠에 있는 개행문자를 br태그로 바꿔줄 수 있다. 
	public String getContentToHtml() {
		if(content != null) {
			return content.replace("\n", "<br>");
		}else return null;
	}
}