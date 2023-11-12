package chapter07_oracleAtHome;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import lombok.Getter;

@Repository
@Getter
public class MyDataFactory {
	private DataSource dataFactory;
	
	public MyDataFactory() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			
		}
	}
}
