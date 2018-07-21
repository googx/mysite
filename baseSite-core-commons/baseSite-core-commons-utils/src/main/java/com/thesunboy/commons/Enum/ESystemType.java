/**
 * 
 */
package com.thesunboy.commons.Enum;

/**
 * 设备类型
 * 
 * @author hx940929
 * @CreateDate 2016-3-12 - 下午5:10:45
 * @ModifiedDate 2016-3-12 - 下午5:10:45
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public enum ESystemType
{
	Unknow("Unknow"),
	Any("any"), Linux("Linux"), Mac_OS("Mac OS"), Mac_OS_X("Mac OS X"), Windows("Windows"), OS2("OS/2"), Solaris("Solaris"), SunOS("SunOS"), MPEiX("MPE/iX"), HP_UX("HP-UX"), AIX("AIX"), OS390(
			"OS/390"), FreeBSD("FreeBSD"), Irix("Irix"), Digital_Unix("Digital Unix"), NetWare_411("NetWare"), OSF1("OSF1"), OpenVMS("OpenVMS"), Others("Others");

	private ESystemType(String desc)
	{
		this.description = desc;
	}

	public String toString()
	{
		return description;
	}

	private String description;

}
