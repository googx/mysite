/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.commons.utils.shell;

/**
 * <b>功能说明:</b><p>
 *一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p> 
 *一些说明写这里
 * </p></br><b>设计缺陷: </b><p> 一些说明写这里
</p>
 * @author hx940929
 * @CreateDate 2017-7-8 - 下午7:14:02 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public enum ChannelType
{
	Shell("shell"), Sftp("sftp"), X11("x11"), Exec("exec");

	private String chanName;

	ChannelType(String name)
	{
		this.chanName = name;
	}

	public String getTypeValue()
	{
		return this.chanName;
	}

}
