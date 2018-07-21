package com.thesunboy.commons.utils;

/**
 * 
 * @author hx940929
 * @CreateDate 2016-2-24 - 下午10:07:07
 * @ModifiedDate 2016-2-24 - 下午10:07:07
 * @Encoding UTF-8
 * @Version 1.0
 * @Site www.thesunboy.com
 * @QQ 836845967
 */
public class TextUtil
{

	/**
	 * Returns true if the parameter is null or of zero length
	 */
	public static boolean isEmpty(final CharSequence s)
	{
		if(s == null) { return true; }
		return s.length() == 0;
	}

	/**
	 * Returns true if the parameter is null or contains only whitespace
	 */
	public static boolean isBlank(final CharSequence s)
	{
		if(s == null) { return true; }
		for (int i = 0; i < s.length(); i++)
		{
			if(!Character.isWhitespace(s.charAt(i))) { return false; }
		}
		return true;
	}

	/**
	 * @since 4.4
	 */
	public static boolean containsBlanks(final CharSequence s)
	{
		if(s == null) { return false; }
		for (int i = 0; i < s.length(); i++)
		{
			if(Character.isWhitespace(s.charAt(i))) { return true; }
		}
		return false;
	}

}
