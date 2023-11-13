package chapter08;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper //이거 반드시 써야한다!!!
public interface StudentMapper {
	
	List<StudentVO> search2(StudentVO vo);
	int count(StudentVO vo);
	
}
