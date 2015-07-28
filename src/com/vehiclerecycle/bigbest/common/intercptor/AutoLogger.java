package com.vehiclerecycle.bigbest.common.intercptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class AutoLogger implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Logger logger=Logger.getLogger(invocation.getClass());
		
		System.out.println(invocation.getArguments());
		return null;
	}

}
