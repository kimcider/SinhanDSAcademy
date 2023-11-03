package board;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleVO> listArticles(){
		return boardDAO.selectAllArticles();
	}
	
	public void addArticle(ArticleVO article) {
		boardDAO.insertNewArticle(article);
	}
}