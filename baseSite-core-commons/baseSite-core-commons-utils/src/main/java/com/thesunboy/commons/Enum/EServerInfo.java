/**
 * 
 */
package com.thesunboy.commons.Enum;

/**
 * <b>功能说明:</b><p>
 *一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p> 
 *一些说明写这里
 * </p></br><b>设计缺陷: </b><p> 一些说明写这里
</p>
 * @author hx940929
 * @CreateDate 2017-3-19 - 下午3:10:59 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class EServerInfo
{

	private static String OS = System.getProperty("os.name").toLowerCase();

	private static EServerInfo _instance = new EServerInfo();

	private ESystemType platform;

	private EServerInfo()
	{
	}

	public static boolean isLinux()
	{
		return OS.indexOf("linux") >= 0;
	}

	public static boolean isMacOS()
	{
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") < 0;
	}

	public static boolean isMacOSX()
	{
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
	}

	public static boolean isWindows()
	{
		return OS.indexOf("windows") >= 0;
	}

	public static boolean isOS2()
	{
		return OS.indexOf("os/2") >= 0;
	}

	public static boolean isSolaris()
	{
		return OS.indexOf("solaris") >= 0;
	}

	public static boolean isSunOS()
	{
		return OS.indexOf("sunos") >= 0;
	}

	public static boolean isMPEiX()
	{
		return OS.indexOf("mpe/ix") >= 0;
	}

	public static boolean isHPUX()
	{
		return OS.indexOf("hp-ux") >= 0;
	}

	public static boolean isAix()
	{
		return OS.indexOf("aix") >= 0;
	}

	public static boolean isOS390()
	{
		return OS.indexOf("os/390") >= 0;
	}

	public static boolean isFreeBSD()
	{
		return OS.indexOf("freebsd") >= 0;
	}

	public static boolean isIrix()
	{
		return OS.indexOf("irix") >= 0;
	}

	public static boolean isDigitalUnix()
	{
		return OS.indexOf("digital") >= 0 && OS.indexOf("unix") > 0;
	}

	public static boolean isNetWare()
	{
		return OS.indexOf("netware") >= 0;
	}

	public static boolean isOSF1()
	{
		return OS.indexOf("osf1") >= 0;
	}

	public static boolean isOpenVMS()
	{
		return OS.indexOf("openvms") >= 0;
	}

	/**
	 * 获取操作系统名字
	 * 
	 * @return 操作系统名
	 */
	public static ESystemType getOSname()
	{
		if(isAix())
		{
			_instance.platform = ESystemType.AIX;
		}
		else if(isDigitalUnix())
		{
			_instance.platform = ESystemType.Digital_Unix;
		}
		else if(isFreeBSD())
		{
			_instance.platform = ESystemType.FreeBSD;
		}
		else if(isHPUX())
		{
			_instance.platform = ESystemType.HP_UX;
		}
		else if(isIrix())
		{
			_instance.platform = ESystemType.Irix;
		}
		else if(isLinux())
		{
			_instance.platform = ESystemType.Linux;
		}
		else if(isMacOS())
		{
			_instance.platform = ESystemType.Mac_OS;
		}
		else if(isMacOSX())
		{
			_instance.platform = ESystemType.Mac_OS_X;
		}
		else if(isMPEiX())
		{
			_instance.platform = ESystemType.MPEiX;
		}
		else if(isNetWare())
		{
			_instance.platform = ESystemType.NetWare_411;
		}
		else if(isOpenVMS())
		{
			_instance.platform = ESystemType.OpenVMS;
		}
		else if(isOS2())
		{
			_instance.platform = ESystemType.OS2;
		}
		else if(isOS390())
		{
			_instance.platform = ESystemType.OS390;
		}
		else if(isOSF1())
		{
			_instance.platform = ESystemType.OSF1;
		}
		else if(isSolaris())
		{
			_instance.platform = ESystemType.Solaris;
		}
		else if(isSunOS())
		{
			_instance.platform = ESystemType.SunOS;
		}
		else if(isWindows())
		{
			_instance.platform = ESystemType.Windows;
		}
		else
		{
			_instance.platform = ESystemType.Others;
		}
		return _instance.platform;
	}
}
