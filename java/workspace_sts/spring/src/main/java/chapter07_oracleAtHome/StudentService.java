package chapter07_oracleAtHome;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentDAO dao;
	
	public List<StudentVO> all(){
		return dao.all();
	}
}
