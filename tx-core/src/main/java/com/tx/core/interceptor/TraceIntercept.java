package com.tx.core.interceptor;

import com.tx.core.util.UuidUtils;
import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

/**
 * @author linzf
 * @since 2019/7/22
 * 类描述：trace的拦截器，实现生成链路拦截的实体的traceId
 */
public class TraceIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 生成一次请求的traceId
        httpServletRequest.setAttribute("traceId", UuidUtils.getUUID());
        reflectSetHeader(httpServletRequest,"traceId", UuidUtils.getUUID());
        return true;
    }

    private void reflectSetHeader(HttpServletRequest request, String key, String value){
        Class<? extends HttpServletRequest> requestClass = request.getClass();
        try {
            Field request1 = requestClass.getDeclaredField("request");
            request1.setAccessible(true);
            Object o = request1.get(request);
            Field coyoteRequest = o.getClass().getDeclaredField("coyoteRequest");
            coyoteRequest.setAccessible(true);
            Object o1 = coyoteRequest.get(o);
            Field headers = o1.getClass().getDeclaredField("headers");
            headers.setAccessible(true);
            MimeHeaders o2 = (MimeHeaders)headers.get(o1);
            o2.removeHeader(key);
            o2.addValue(key).setString(value);
        } catch (Exception e) {

        }
    }


}
