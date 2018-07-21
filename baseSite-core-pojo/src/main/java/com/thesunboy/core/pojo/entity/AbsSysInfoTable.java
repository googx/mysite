/**
 * 
 */
package com.thesunboy.core.pojo.entity;

import java.util.Date;

/**
 * <b>功能说明:</b>
 * <p>
 * 
 * </p>
 * </br> <b>设计思想、目的:</b>
 * <p>
 * 
 * </p>
 * </br><b>设计缺陷: </b>
 * <p>
 * </p>
 * 
 * @author hx940929
 * @CreateDate 2017-4-18 - 下午3:19:06
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public abstract class AbsSysInfoTable extends AbsPojo
{

	private static final long serialVersionUID = 2398957717528108058L;
	/**
	 * 创建人
	 */
	private String createUser;// 后期 改成user接口
	/**
	 * 上次修改人
	 */
	private String modifierUser;

	/**
	 * 数据创建时间,必须
	 */
	private Date createTime;
	/**
	 * 数据上次修改时间,
	 */
	private Date modifierTime;

	/**
	 * @return the createUser
	 */
	public String getCreateUser()
	{
		return createUser;
	}

	/**
	 * @return the modifierUser
	 */
	public String getModifierUser()
	{
		return modifierUser;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime()
	{
		return createTime;
	}

	/**
	 * @return the modifierTime
	 */
	public Date getModifierTime()
	{
		return modifierTime;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}

	/**
	 * @param modifierUser
	 *            the modifierUser to set
	 */
	public void setModifierUser(String modifierUser)
	{
		this.modifierUser = modifierUser;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	/**
	 * @param modifierTime
	 *            the modifierTime to set
	 */
	public void setModifierTime(Date modifierTime)
	{
		this.modifierTime = modifierTime;
	}

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b> 注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2017-4-18 - 下午3:44:03
	 */
	@Override
	public String toString()
	{
		return "AbsSysInfoTable [" + (createUser != null ? "createUser=" + createUser + ", " : "")
				+ (modifierUser != null ? "modifierUser=" + modifierUser + ", " : "")
				+ (createTime != null ? "createTime=" + createTime + ", " : "")
				+ (modifierTime != null ? "modifierTime=" + modifierTime : "") + "]";
	}

}
