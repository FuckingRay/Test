package mybatis.test.userMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mybatis.test.bean.User;

public interface UserMapper {
	 
	User queryUserById(@Param("id")Long id);

	void insertUser(User user);
	
	User login(@Param("0")String userName,@Param("1")String password);
	 
	List<User> likeSelect(@Param("1")String userName);
	 
	void updateUser(User user);
	 
}
