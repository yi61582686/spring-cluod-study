package com.on.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul过滤器，有四种。见filterType()方法注释。
 * Filter是Zuul的核心组件，Zuul的大部分功能都是通过Filter实现的。
 */
@Component
public class LogFilter extends ZuulFilter {
    
    /**
     * 声明过滤器的类型。Zuul中定义了四种过滤器：
     * 1、PRE：在请求被路由之前调用。可以用来实现身份验证、在集群中选择请求的微服务、记录调试信息等；
     * 2、ROTING：将请求路由到微服务。用于构建发送给微服务的请求，并使用Apache HttpClient或Netflix Ribbon请求微服务；
     * 3、POST：在路由到微服务后执行。可以用来为微服务返回的响应添加标准和Http Header、收集统计信息和指标、将响应从微服务发送给客户端；
     * 4、ERROR：在其他阶段发生错误时执行该中过滤器。
     *
     * Zuul中各种类型的过滤器都默认提供了。
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
    
    // 排序，Zuul中同种类型的过滤器的执行优先级。数字小的优先执行。
    @Override
    public int filterOrder() {
        // PRE_DECORATION_FILTER_ORDER：Zuul提供的PreDecorationFilter过滤器的优先级。PreDecorationFilter用来路由请求。
        // 当我们的优先级比它高时，减一。低时加1。
        // 例如，当过滤IP地址时，减1；当要获取到路由后的地址时，加1.
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1; // 记录日志
    }
    
    // 是否启用过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String routedURI = context.get(FilterConstants.REQUEST_URI_KEY).toString();// 路由后的地址
        System.out.println("客户端：" + request.getRemoteAddr() + "访问的" +
                request.getRequestURI() + "资源，路由后的地址是：" + routedURI);
        return null;
    }
    
}
