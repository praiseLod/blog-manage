package blogmgr.test.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huangtl.blogmgr.core.util.PinYinUtils;
import com.huangtl.blogmgr.model.blog.User;
import com.huangtl.blogmgr.model.common.Message;
import com.huangtl.blogmgr.model.type.Gender;
import com.huangtl.blogmgr.model.type.UserStatus;
import com.huangtl.blogmgr.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springContext.xml")
public class UserDaoTest {
	@Resource
	private UserService  userService;
	
	
	@Test
	public void userAddTest(){
		User user = new User(true);
		user.setfAccount("test1");
		user.setfCreateDate(new Date());
		user.setfGender(Gender.MALE);
		user.setfName("测试员1");
		user.setfPassword("123");
		user.setfStatus(UserStatus.ENABLE);
		user.setfCreater("root");
		user.setfPinYin(PinYinUtils.toPinYin("测试员1"));
		
		Message mesg = user.checkValidity();
		if(mesg.isSuccess()){
			System.out.println(userService.addUser(user));	
			return;
		}
		System.out.println(mesg);
	}
	
}