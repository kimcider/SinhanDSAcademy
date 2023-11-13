package chapter07;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentDAO dao;
	
	public List<StudentVO> all(){
		return dao.all();
	}
	
	public List<Map> search(Map map){
		return dao.search(map);
	}
	public List<StudentVO> search2(StudentVO vo){
		return dao.search2(vo);
	}
	public int count(StudentVO vo){
		return dao.count(vo);
	}
	public StudentVO view(Integer studno){
		return dao.view(studno);
	}
}
