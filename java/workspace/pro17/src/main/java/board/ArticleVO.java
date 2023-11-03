package board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
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
}