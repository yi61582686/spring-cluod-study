package com.on.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 容错的回调方法。Zuul默认集成了Ribbon和Hystrix。
 */
@Component
public class MyFallbackProvider implements FallbackProvider {
    
    /**
     * 需要进行容错的服务名，在配置文件中配置的serviceId。
     * 当全部需要容错时，返回 * 或 null 即可。
     * @return serviceId
     */
    @Override
    public String getRoute() {
//        return "user-service";
//        return null;
        return "*";
    }
    
    /**
     * 发生错误时，返回的响应对象
     * @param route  serviceId
     * @param cause  异常类型
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
        // 判断异常类型
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // 封装响应对象
    private ClientHttpResponse response(final HttpStatus status) {
        // 返回一个匿名内部类
        return new ClientHttpResponse() {
            // HttpStatus：INTERNAL_SERVER_ERROR(500, "Internal Server Error")
            // 响应状态码：INTERNAL_SERVER_ERROR(500, "Internal Server Error")
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }
            
            // 响应码对应的值：500
            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }
            
            //响应码对应的原因短语："Internal Server Error"
            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }
            
            // 响应信息输入完后调用，关闭流资源。在下面的getBody()将响应信息输入完后用来关闭资源。
            @Override
            public void close() {
            }
            
            // 输入响应信息，执行完后调用上面的close()方法。
            // 输入就是将信息加载到响应对象中。对计算机而言是输入。
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("系统繁忙，请稍后再试".getBytes());
            }
            
            // 设置响应头信息
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
