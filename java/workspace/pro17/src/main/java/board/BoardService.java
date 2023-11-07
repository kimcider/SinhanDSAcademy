package board;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	//페이징 없을 때의 리스트호출
	public List<ArticleVO> listArticles(){
		return boardDAO.selectAllArticles();
	}
	//페이징 있을떄의 리스트 호출
	public Map listArticles(ArticleVO param){
		//총개수
		int count = boardDAO.selectCounter(param);
		System.out.println("counter: " + count);
		
		//총 페이지 수
		int totalPage = count / param.getRowPage(); 
		if(count % param.getRowPage() > 0) totalPage++;
		List<ArticleVO> list = boardDAO.selectPagingArticles(param);
		
		Map map = new HashMap();
		map.put("count", count);
		map.put("totalPage", totalPage);
		map.put("articlesList", list);
		
		
		// 하단에 한번에 5개의 페이지만 노출하고싶을경우
		int num = 5;
		int endPage = ((int)Math.ceil(param.getPage()/(double)num) * num);
		int startPage = endPage - (num - 1);
		
		if(endPage > totalPage) endPage = totalPage;
		
		//이전 페이지가 true라는얘기는 startPage가 1이 아니라는 이야기이다. 
		boolean prev = startPage > 1;
		//엔드페이지가 토탈페이지보다 작으면 ㅇㅇ. 
		boolean next = endPage < totalPage;
		
		map.put("endPage", endPage);
		map.put("startPage", startPage);
		map.put("prev", prev);
		map.put("next", next);
		
		return map;
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
	
	public void replyArticle(ArticleVO vo) {
//		여기서의 vo에서 gno, ono, nested는 부모의 gno, ono, nested 정보가 담겨있을것
		//이제 ONO를 업데이트해주면됨!ㅎㅎ
		boardDAO.updateOno(vo);
		vo.setOno(vo.getOno() + 1);
		vo.setNested(vo.getNested() + 1);
		boardDAO.insertReplyArticle(vo);
	}
}