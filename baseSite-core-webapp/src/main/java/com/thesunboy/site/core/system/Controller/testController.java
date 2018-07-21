/**
 *
 */
package com.thesunboy.site.core.system.Controller;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <b>功能说明:</b><p>
 * 一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p>
 * 一些说明写这里
 * </p></br><b>设计缺陷: </b><p> 一些说明写这里
 * </p>
 *
 * @author hx940929
 * @CreateDate 2017-5-11 - 下午9:00:11
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
@Controller @RequestMapping("/") public class testController
{
	private final MyLogger logger = MyLogFactory.getLogger(testController.class, LogImplEnum.ConsoleLogImpl);

}
