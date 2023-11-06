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
	
	public ArticleVO viewArticle(int no) {
		return boardDAO.selectArticle(no);
	}
	public void modArticle(ArticleVO vo) {
		boardDAO.updateArticle(vo);
	}
	
	public void removeArticle(String no) {
		List<String> list = boardDAO.selectImagefilename(no);
		System.out.println(list);
		
		
	}
}