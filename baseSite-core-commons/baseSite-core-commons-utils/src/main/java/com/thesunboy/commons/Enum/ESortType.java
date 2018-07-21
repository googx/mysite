/**
 * 
 */
package com.thesunboy.commons.Enum;

/**
 * 表查询的时候的排序规则</br></br><b>注意FieldOrder该字段,并不是所有的表都有</b>
 * 
 * @author hx940929
 * @CreateDate 2016-4-5 - 下午7:22:46
 * @ModifiedDate 2016-4-5 - 下午7:22:46
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public enum ESortType
{
	/**
	 * 添加时间升序
	 */
	AddTime,
	/**
	 * 更新实现升序
	 */
	UpdateTime,
	/**
	 * 表专属排序的字段 升序 (推荐)
	 */
	FieldOrder,
	/**
	 * 表专属排序的字段 降序
	 */
	FieldOrder_DESC,
	/**
	 * 添加时间降序
	 */
	AddTime_DESC,
	/**
	 * 更新时间 降序
	 */
	UpdateTime_DESC,
	/**
	 * 不使用排序,具体的实现类,可以根据具体的需要使用默认的良好排序方式
	 */
	None
}
