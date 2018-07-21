/**
 * 
 */
package com.thesunboy.commons.config;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;
import  com.thesunboy.utils.mylog.core.MyLogger;

/**
 * 
 * @author hx940929
 * @CreateDate 2016-4-2 - 下午9:33:15
 * @ModifiedDate 2016-4-2 - 下午9:33:15
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public class HttpsTrustManager implements X509TrustManager
{

	private final MyLogger logger = CommonConstant.getLogger(
			HttpsTrustManager.class, LogImplEnum.ConsoleLogImpl);

	/**
	 * @param arg0
	 * @param arg1
	 * @throws CertificateException
	 * @Date 2016-4-2 - 下午9:33:42
	 */
	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException
	{

	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws CertificateException
	 * @Date 2016-4-2 - 下午9:33:42
	 */
	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException
	{

	}

	/**
	 * @return
	 * @Date 2016-4-2 - 下午9:33:42
	 */
	@Override
	public X509Certificate[] getAcceptedIssuers()
	{

		return null;
	}

}
