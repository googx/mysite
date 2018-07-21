package com.thesunboy.core.pojo.entity;

public class SysAccountInfoTable extends AbsSysInfoTable
{
	private String userName;
	private Integer ageInteger;
	private String password;

	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the ageInteger
	 */
	public Integer getAgeInteger()
	{
		return ageInteger;
	}

	/**
	 * @param ageInteger
	 *            the ageInteger to set
	 */
	public void setAgeInteger(Integer ageInteger)
	{
		this.ageInteger = ageInteger;
	}
}
