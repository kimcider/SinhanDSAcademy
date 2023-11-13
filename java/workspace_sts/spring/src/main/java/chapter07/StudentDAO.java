package chapter07;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {
	@Autowired
	private SqlSessionTemplate sst;
	
	// 모두를 조회할 메소드
	public List<StudentVO> all(){
		//select에 select와 selectList가있다.
		//select는 한건 selectList는 여러건
		//그리고 아까 말했더 ㄴnamespace.id를써줘야한다.
		
		//DAO는 그냥 xml의 sql만 호출해주면된다.
		//진짜 할일이 없어짐 이제 ㅎㅎ
		return sst.selectList("student.all");
	}
	
	
	//맵을 파라미터로 전달해주는것
	public List<Map> search(Map map){
		return sst.selectList("student.search", map);
	}
	
	//목록을 가져오는 메소드
	public List<StudentVO> search2(StudentVO vo){
		return sst.selectList("student.search2", vo);
	}
	//가져온 목록이 몇개인지를 카운트해주는 메소드
	public int count(StudentVO vo){
		return sst.selectOne("student.count", vo);
	}
	
	public StudentVO view(Integer studno){
		return sst.selectOne("student.view", studno);
	}
}
