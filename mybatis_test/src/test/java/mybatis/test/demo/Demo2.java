package mybatis.test.demo;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mybatis.test.bean.User;
import mybatis.test.userMapper.UserMapper;

public class Demo2 {
	private UserMapper userMapper;
	private SqlSession sqlSession;
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 读取mybatis的全局配置文件
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		// 构建sqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 获取sqlSession会话
		//SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession=sqlSessionFactory.openSession(true);
		
		this.userMapper = sqlSession.getMapper(UserMapper.class);
		// 初始化userDao
	}

	@Test
	public void Insert() {
		User user = new User();
		user.setAge(23);
		user.setBirthday(new Date());
		user.setName("zz");
		user.setPassword("123456");
		user.setSex(1);
		user.setUserName("zz");
		this.userMapper.insertUser(user);
		
	}
	@Test
	public void testLogin(){
		System.out.println(this.userMapper.login("ww", "123456"));
	}
	
	@Test
	public void testLikeSelect(){
		List<User> list = this.userMapper.likeSelect("z");
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testUpdate(){
		User user = this.userMapper.queryUserById(18l);
		user.setName("ss");
		user.setUserName("ss");
		this.userMapper.updateUser(user);
	}
	
	@Test
	public void testCache2(){
		User user1 = this.userMapper.queryUserById(18l);
		System.out.println(user1);
		sqlSession.close();
		
		SqlSession sqlSession2 = this.sqlSessionFactory.openSession();
		this.userMapper = sqlSession2.getMapper(UserMapper.class);
		System.out.println("================update======================");
		User user =new User();
		user.setName("qq");
		user.setUserName("qq");
		this.userMapper.updateUser(user);
		
		// 注意：关闭sqlSession
		System.out.println("=================第二次查询======================");
		// 重新打开一个sqlSession会话
		// 通过sqlSession2重新实例化UserMapper
		User user2 = this.userMapper.queryUserById(18l);
		System.out.println(user2);
	}

	
	
	
	
	
	
	
	@After
	public void commit() {
		
		
	}
	
}
