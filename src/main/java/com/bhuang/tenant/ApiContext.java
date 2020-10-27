package com.bhuang.tenant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;


/**
@author: bhuang
@date: 2020-10-14 10:19:56
		*/
@Component
public class ApiContext {

	private static final String TENANT_ID = "tenant_id";
	private static final Map<String, Object> mContext = new ConcurrentHashMap<String, Object>();


	public void setCurrentProviderId(Long tenantId) {
		mContext.put(TENANT_ID, tenantId);
	}

	public Long getCurrentProviderId() {
		return (Long) mContext.get(TENANT_ID);
	}
}
