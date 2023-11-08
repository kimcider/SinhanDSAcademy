package chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Component
@Service
public class BoardServiceImpl implements BoardService {
	
	//서비스도 dao를 주입받아야한다. 
	@Autowired
	private BoardDAO dao;
	
	@Override
	public void selectArticles() {
		dao.selectArticles();
	}

}
