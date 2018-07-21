/**
 * 
 */
package com.thesunboy.commons.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.thesunboy.commons.config.MyLogFactory;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * 
 * @author hx940929
 * @CreateDate 2016-6-7 - 下午1:30:07
 * @ModifiedDate 2016-6-7 - 下午1:30:07
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public class Cmd
{
	private final MyLogger logger = MyLogFactory.getLogger(Cmd.class, LogImplEnum.ConsoleLogImpl);

	public static String command(String cmd) throws Exception
	{
		Process process = Runtime.getRuntime().exec(cmd);
		process.waitFor(); 
		InputStream in = process.getInputStream();
		StringBuilder result = new StringBuilder();
		byte[] data = new byte[256];
		while (in.read(data) != -1)
		{
			String encoding = System.getProperty("sun.jnu.encoding");
			result.append(new String(data, encoding));
		}
		return result.toString();
	}

	public static String command(String[] cmdarraya) throws Exception
	{
		Process process = Runtime.getRuntime().exec(cmdarraya);
		process.waitFor();
		InputStream in = process.getInputStream();
		StringBuilder result = new StringBuilder();
		byte[] data = new byte[256];
		while (in.read(data) != -1)
		{
			String encoding = System.getProperty("sun.jnu.encoding");
			result.append(new String(data, encoding));
		}
		return result.toString();
	}

	/**
	 * 获取mac地址
	 * 
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public static String getMacAddress(String ip) throws Exception
	{
		String result = null;
		// command("ping " + ip + " -n 2");
		// if(result.contains("TTL"))
		// {
		result = command("arp -a " + ip);
		// }
		String regExp = "([0-9A-Fa-f]{2})([-:][0-9A-Fa-f]{2}){5}";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(result);
		StringBuilder mac = new StringBuilder();
		while (matcher.find())
		{
			String temp = matcher.group();
			mac.append(temp);
		}
		return mac.toString();
	}

	public static void main(String[] args) throws Exception
	{
		// System.out.println(System.currentTimeMillis());
		// System.out.println(getMacAddress("10.193.1.1"));
		Set<String> coms = new HashSet<>();
		coms.add("ifconfig");
		coms.add("istart.sh nginx");
		coms.add("iptables -L");
		for (String string : coms)
		{
			try
			{
				System.out.println("main=>命令[" + string + "]" + command(string));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String tem;

		while ((tem = reader.readLine()) != null && tem.equals("exit") == false)
		{
			try
			{
				if(StringUtils.isNotBlank(tem) && tem.equals("old"))
				{

				}
				System.out.println("main=>命令[" + tem + "]" + command(tem));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		// System.out.println("main=>" + command("ifconfig"));
		// System.out.println(System.currentTimeMillis());
		// System.out.println(command("arp -a 192.168.1.103"));
		// System.out.println(System.getProperties());
	}

	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 测试是否能重复使用Process
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @throws Exception
	 * 
	 * @Author hx940929
	 * @Date 2017-7-8 - 下午10:38:03
	 */
	private void test2() throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String tem = null;

		Process process = Runtime.getRuntime().exec(tem);
		process.waitFor();

		InputStream in = process.getInputStream();
		OutputStream out = process.getOutputStream();
		OutputStreamWriter write = new OutputStreamWriter(out);
		while ((tem = reader.readLine()) != null && tem.equals("exit") == false)
		{
			try
			{
				write.write(tem + "\n");
				write.flush();
				StringBuilder result = new StringBuilder();
				byte[] data = new byte[256];
				while (in.read(data) != -1)
				{
					String encoding = System.getProperty("sun.jnu.encoding");
					result.append(new String(data, encoding));
				}
				System.out.println("main=>命令[" + tem + "]" + result.toString());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}
}
