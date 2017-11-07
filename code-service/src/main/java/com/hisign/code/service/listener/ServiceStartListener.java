package com.hisign.code.service.listener;

import com.hisign.code.api.business.TranslationDictService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 项目启动初始化类
 * @author yinxiaoyong
 * @mailto yinxiaoyong@hisign.com.cn
 * 2016年11月26日
 */
@Service
public class ServiceStartListener implements ApplicationListener<ContextRefreshedEvent>{

	@Resource
	private TranslationDictService translationDictService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event){
		translationDictService.initTranslationMap();
	}

}
