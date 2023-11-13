package chapter07_myVersion;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class StudentDAO {
	@Autowired
	private SqlSessionTemplate sst;
	
	public List<Map> search(Map map){
		return sst.selectList("student.search", map);
	}
	
	public List<StudentVO> search2(StudentVO vo){
		return sst.selectList("student.search2", vo);
	}
	
	public StudentVO view(Integer studno){
		return sst.selectOne("student.view", studno);
	}
}
