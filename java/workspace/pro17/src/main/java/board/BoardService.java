package board;

import java.io.File;
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
	
	public int removeArticles(String no, String realPath) {
		List<String> list = boardDAO.selectImagefilename(no);
		
		// 첨부 파일 삭제
		for(String image : list) {
			if(image != null && !"".equals(image)) {
				File f = new File(realPath + "/" + image);
				f.delete();
			}
		}

		// 글 삭제
		return boardDAO.deleteArticles(no);
	}
}