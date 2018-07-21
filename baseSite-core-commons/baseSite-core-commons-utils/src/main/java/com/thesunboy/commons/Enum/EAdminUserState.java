/**
 * 
 */
package com.thesunboy.commons.Enum;

/**
 * 
 * @author hx940929
 * @CreateDate 2016-5-9 - 下午3:21:24
 * @ModifiedDate 2016-5-9 - 下午3:21:24
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public enum EAdminUserState
{
	UnKnow("UnKnow", -1), Disable("Disable", 0), Normal("Normal", 1), LoginFailLock(
			"LoginFailLock", 5), AdminLock("AdminLock", 6);

	private String stateName;
	private int value;

	EAdminUserState(String stateName, int value)
	{
		this.stateName = stateName;
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}

	public String getStateName()
	{
		return this.stateName;
	}

}
