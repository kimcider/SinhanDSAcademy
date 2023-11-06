package board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//이런식으로 맴버와 관련된 처리들을 다 이 서블릿으로 불러올 수 있다.
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService;

	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
	}

	public void destroy() {
		boardService.boardDAO.close();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println("action: " + action);
		
		if ("/".equals(action) || "/listArticles.do".equals(action)) {
			List<ArticleVO> articlesList = boardService.listArticles();
			request.setAttribute("articlesList", articlesList);
			nextPage = "/WEB-INF/view/board/listArticles.jsp";
		} 
		
		// 글 추가를 담당하는 영역
		else if ("/articleForm.do".equals(action)) {
			nextPage = "/WEB-INF/view/board/articleForm.jsp";
		} else if ("/addArticle.do".equals(action)) {
			/* 게시글을 포맷팅해 MAP에 저장해 반환하고, 첨부파일을 저장 */
			Map<String, String> articleMap = upload(request, response);
			ArticleVO vo = new ArticleVO();
			
			//아직은 모른다. 이따가 articleForm.jsp를 만들 때 title이라고 줄것임
			//와우..
			vo.setTitle(articleMap.get("title"));
			vo.setContent(articleMap.get("content"));
			vo.setImagefilename(articleMap.get("imagefilename"));
			vo.setId("hong");
			
			boardService.addArticle(vo);
			nextPage = "redirect:listArticles.do";
		} 
		
		// 게시글 조회
		else if ("/viewArticle.do".equals(action)) {
			//parameter로 넘어오는 것들은 무조건 다 문자열이기 때문에 String으로 받는다. 
			String articleno = request.getParameter("articleno");
			ArticleVO vo = new ArticleVO();
			vo = boardService.viewArticle(Integer.valueOf(articleno));
			request.setAttribute("article", vo);
			nextPage = "/WEB-INF/view/board/viewArticle.jsp";
			System.out.println(vo);
		} 
		
		// 글 수정
		else if("/modArticleForm.do".equals(action)) {
			String articleno = request.getParameter("articleno");
			ArticleVO vo = new ArticleVO();
			vo = boardService.viewArticle(Integer.valueOf(articleno));
			request.setAttribute("article", vo);
			nextPage = "/WEB-INF/view/board/modArticleForm.jsp";
		}
		else if ("/modArticle.do".equals(action)) {
			Map<String, String> articleMap = upload(request, response);
			ArticleVO vo = new ArticleVO();
			
			//글 수정시 pk 필요
			//참고로 ~를 할때는 getParameter로 얻어올 수 없다.
			vo.setArticleno(Integer.valueOf(articleMap.get("articleno")));
			vo.setTitle(articleMap.get("title"));
			vo.setContent(articleMap.get("content"));
			vo.setImagefilename(articleMap.get("imagefilename"));
			vo.setId("hong");
			System.out.println(vo);
			
			boardService.modArticle(vo);
			nextPage = "redirect:listArticles.do";
		} 
		
		else if ("/removeArticle.do".equals(action)) {
			String articleno = request.getParameter("articleno");
			boardService.removeArticle(articleno);
			
			nextPage = "redirect:board/listArticles.do";
		}
		
		//추후 구현할 영역
		else if ("/.do".equals(action)) {

		}else {
			nextPage = "redirect:board/listArticles.do";
		}

		
		if (nextPage.startsWith("redirect:")) {
			// redirect가 앞에 붙어있으면 redirect를 수행
			response.sendRedirect(nextPage.replace("redirect:", ""));
		} else {
			// 포워딩될 경로를 변수로 설정하기!!
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}

	}

	/* 게시글을 포맷팅해 MAP에 저장해 반환하고, 첨부파일을 저장 */
	/* 얘는 어쨌든 DB와 상호작용하는애는 아니니까 여기다 둔건가? 그냥 서비스에 둬도 되지 않나? */
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		Map<String, String> articleMap = new HashMap<String, String>();

		String encoding = "utf-8";
		String realPath = request.getRealPath("upload/article_image");
		System.out.println("realPath: " + realPath);
		
		File currentDirPath = new File(realPath);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024); // 최대 파일 크기 설정 (1MB)
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println("여기가 실행이 되나?");
					//추가한 부분 이 역할이 뭔지 생각해보기
					//파라미터일 경우
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					//첨부파일일 경우
					System.out.println("매개변수이름: " + fileItem.getFieldName());
					System.out.println("파일이름: " + fileItem.getName());
					System.out.println("파일 크기: " + fileItem.getSize() + "bytes");
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
						
						//추가한 부분 이 역할이 뭔지 생각해보기
						articleMap.put(fileItem.getFieldName(), fileName);
						
						File uploadFile = new File(currentDirPath + "/" + fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return articleMap;
	}
}
