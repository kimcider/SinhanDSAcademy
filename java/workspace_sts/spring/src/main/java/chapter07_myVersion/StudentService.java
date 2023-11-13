package chapter07_myVersion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
	@Autowired
	private StudentDAO dao;
	
	
	public List<Map> search(Map map){
		return dao.search(map);
	}
	public List<StudentVO> search2(StudentVO vo){
		return dao.search2(vo);
	}
	
	public StudentVO view(Integer studno){
		return dao.view(studno);
	}
}
