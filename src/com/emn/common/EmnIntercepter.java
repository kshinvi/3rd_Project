package com.emn.common;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class EmnIntercepter extends AbstractInterceptor{

	private static final long serialVersionUID = 6167222277768493258L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().getSession();
		Object member = session.get("member");
		String result = "";
		if( member == null)
			result = Action.LOGIN;
		else
			result = invocation.invoke();
		return result;
	}

}
