package com.on.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author On
 * @created on 2020/11/1 19:56
 */
@Component
public class CustomLimitFilter extends ZuulFilter {

	private static RateLimiter limiter = RateLimiter.create(10);

	@Override
	public String filterType () {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder () {
		return -10;
	}

	@Override
	public boolean shouldFilter () {
		return true;
	}

	@Override
	public Object run () throws ZuulException {

		RequestContext requestContext = RequestContext.getCurrentContext();
		if (limiter.tryAcquire()) {

		} else { // 限流
			requestContext.setSendZuulResponse(false);
			requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
		}

		return null;
	}

}
