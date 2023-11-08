package chapter04;

import org.springframework.stereotype.Repository;

//@Component
@Repository
public class BoardDAO {
	public void selectArticles() {
		System.out.println("게시판 목록 조회");
	}
}
