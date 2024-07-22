package com.limyel.haoyuan.platform.core.util.http;

import com.limyel.haoyuan.platform.core.util.lang.json.JsonUtil;
import com.limyel.haoyuan.platform.core.util.result.ResultVO;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class HttpUtil {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(attrs, "获取 Servlet API 失败");
        return attrs.getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(attrs, "获取 Servlet API 失败");
        return attrs.getResponse();
    }

    public static String getReqeustIP() {
        HttpServletRequest request = getRequest();
        return request.getRemoteHost();
    }

    /**
     * 向 response 中写入 json 数据
     * @param response
     * @param vo
     * @throws IOException
     */
    public static void writeJson(HttpServletResponse response, ResultVO<?> vo) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtil.toJsonStr(vo));
        response.getWriter().flush();
        response.getWriter().close();
    }

}
