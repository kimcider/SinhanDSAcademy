package chapter08;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentMapper mapper;
	
	public List<StudentVO> search2(StudentVO vo){
		return mapper.search2(vo);
	}
	
	public int count(StudentVO vo) {
		return mapper.count(vo);
	}
}
