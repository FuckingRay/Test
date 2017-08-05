package mybatis.test.demo;

import mybatis.test.bean.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Demo1 {

	public static void main(String[] args) throws Exception {
		SqlSession sqlSession=null;
		
		try {
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build
					(Resources.getResourceAsStream("mybatis-config.xml"));
			sqlSession = sqlSessionFactory.openSession();
			User user = sqlSession.selectOne("UserMapper.queryUserById", 2l);
			System.out.println(user);
			System.out.println("11111");
			System.out.println("2222");
		} finally {
			// 关闭连接
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
}
