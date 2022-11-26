package com.jjang051.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//시작 전 mybatis 라이브러리 필요
public class MyBatisConnectionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	// static 바로 띄워 쓸수 있는 것
	static {//static로 한번 막아서 사용하여야 에러가 발생하지 않는다
		try {
			String resource = "com/jjang051/mybatis/config.xml"; // 우리가 만들어둔 것을 소스로 쓰겠다
			InputStream inputStream = Resources.getResourceAsStream(resource);//io를 통해 읽어오기 
			//ibatis를 읽어와야한다(import시)
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);// 읽어온 것으로 선언
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static SqlSession getSqlSession() { //public로 접근할수 있게 하나 만들어 두기
		return sqlSessionFactory.openSession(); //괄호 안에 true를 쓰면 auto commit이 된다 디폴트 값은 false
	}
}




