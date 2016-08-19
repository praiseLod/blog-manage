package com.huangtl.blogmgr.model.blog;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.huangtl.blogmgr.model.blog.dictionary.Gender;
import com.huangtl.blogmgr.model.blog.dictionary.UserStatus;
import com.huangtl.blogmgr.model.common.ObjectValue;

/**
 * 后台用户
 * @date 2016年7月9日
 * @author PraiseLord
 */
public class User extends ObjectValue {
	private static final long serialVersionUID = -7960114915317326699L;
	private String fId;				//
	private String fName;			//名称
	private String fAccount;		//登录账号
	private String fPassword;		//密码
	private UserStatus fStatus;		//状态
	private String fPhone;			//电话
	private String fEmail;			//邮箱
	private Gender fGender;			//性别
	private String fPinYin;			//拼音
	private String fDescr;			//备注
	private String fAvatar;			//头像
	private String fCreater;		//创建人
	private Date fCreateDate;		//创建日期
	private String fEditor;			//修改人
	private Date fEditDate;			//修改日期
	
	
	public User() {
		this(false);
	}
	/**
	 * true ,将自动创建id
	 */
	public User(boolean generateId) {
		super();
		if(generateId){
			this.fId = newId();
		}
	}

	@NotBlank(message="主键为空")
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	@NotBlank(message="名称为空")
	@Length(message="名称长度不大于10",max=10)
	@Pattern(regexp="[\\u4e00-\\u9fa5]*",message="名称必须是中文")
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	@Pattern(regexp="\\d{11}|\\s*",message="手机号码无效")
	public String getfPhone() {
		return fPhone;
	}
	public void setfPhone(String fPhone) {
		this.fPhone = fPhone;
	}
	@Email(message="邮箱地址无效")
	public String getfEmail() {
		return fEmail;
	}
	public void setfEmail(String fEmail) {
		this.fEmail = fEmail;
	}
	@NotNull(message="性别为空")
	public Gender getfGender() {
		return fGender;
	}
	public void setfGender(Gender fGender) {
		this.fGender = fGender;
	}
	public String getfAvatar() {
		return fAvatar;
	}
	public void setfAvatar(String fAvatar) {
		this.fAvatar = fAvatar;
	}
	@NotBlank(message="账号为空")
	@Length(message="账号长度为4-15",min=4,max=15)
	@Pattern(regexp="^[a-zA-Z][a-zA-Z0-9_]{3,16}$",message="字母开头,数字下划线组成")
	public String getfAccount() {
		return fAccount;
	}
	public void setfAccount(String fAccount) {
		this.fAccount = fAccount;
	}
	@NotBlank(message="密码为空")
	@Length(message="密码长度为4-12",min=4,max=12)
	@Pattern(regexp="[\\x21-\\x7E]*",message="密码由数字、字符、特殊字符组成")
	public String getfPassword() {
		return fPassword;
	}
	public void setfPassword(String fPassword) {
		this.fPassword = fPassword;
	}
	@NotNull(message="状态为空")
	public UserStatus getfStatus() {
		return fStatus;
	}
	public void setfStatus(UserStatus fStatus) {
		this.fStatus = fStatus;
	}
	@NotBlank(message="拼音为空")
	@Pattern(regexp="[a-zA-Z]*",message="拼音为英文无空格")
	public String getfPinYin() {
		return fPinYin;
	}
	public void setfPinYin(String fPinYin) {
		this.fPinYin = fPinYin;
	}
	@Length(max=150,message="备注长度小于150字")
	public String getfDescr() {
		return fDescr;
	}
	public void setfDescr(String fDescr) {
		this.fDescr = fDescr;
	}
	@NotBlank(message="创建人为空")
	public String getfCreater() {
		return fCreater;
	}
	public void setfCreater(String fCreater) {
		this.fCreater = fCreater;
	}
	@NotNull(message="创建日期为空")
	public Date getfCreateDate() {
		return fCreateDate;
	}
	public void setfCreateDate(Date fCreateDate) {
		this.fCreateDate = fCreateDate;
	}
	public String getfEditor() {
		return fEditor;
	}
	public void setfEditor(String fEditor) {
		this.fEditor = fEditor;
	}
	public Date getfEditDate() {
		return fEditDate;
	}
	public void setfEditDate(Date fEditDate) {
		this.fEditDate = fEditDate;
	}
	
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User user = new User(true);
		user.setfName("张三张三张三张三张三");
		user.setfAccount("aaaabbbbccccddd");
		user.setfPinYin("aaa");
		user.setfStatus(UserStatus.DISABLE);
		user.setfPassword(")@#%%^&#@)");
		user.setfGender(Gender.FEMALE);
		user.setfCreater("adsfa");
		user.setfEmail("");
		user.setfPhone("15960123876");
		user.setfCreateDate(new Date());
		
		
		System.out.println(user.checkValidity());
	}
}
