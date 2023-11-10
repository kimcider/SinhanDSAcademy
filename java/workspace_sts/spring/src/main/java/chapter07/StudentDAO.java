package chapter07;

import java.util.List;

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
}
