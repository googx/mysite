/**
 * 
 */
package com.thesunboy.commons.Enum;

/**
 * 游览器类型,根据请求头解析请求的客户端类型
 * 
 * @author hx940929
 * @CreateDate 2016-3-12 - 下午5:13:29
 * @ModifiedDate 2016-3-12 - 下午5:13:29
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public enum EBrowerType
{
	Unknown(null, null), IE6("IE", "6"), IE7("IE", "7"), IE8("IE", "8"), IE9(
			"IE", "9"), IE10("IE", "10"), IE11("IE", "11"), IE12("IE", "12");

	private String browerName, version;

	EBrowerType(String browerName, String version)
	{
		this.browerName = browerName;
		this.version = version;
	}

	@Override
	public String toString()
	{
		StringBuilder bnBuilder = new StringBuilder();
		bnBuilder.append("Brower:").append(browerName).append(";Version:")
				.append(version);
		return bnBuilder.toString();
	}
}
