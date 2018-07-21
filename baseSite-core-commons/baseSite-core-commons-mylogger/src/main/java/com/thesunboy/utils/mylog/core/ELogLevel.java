package com.thesunboy.utils.mylog.core;

public enum ELogLevel
{
	info_1(1), error_2(2), warn_3(3), debug_4(4);

	private final int num;

	ELogLevel(int num)
	{
		this.num = num;
	}

	public int level()
	{
		return this.num;
	}
}
