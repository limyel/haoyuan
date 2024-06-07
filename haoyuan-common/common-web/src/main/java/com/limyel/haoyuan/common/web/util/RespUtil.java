package com.limyel.haoyuan.common.web.util;

import com.limyel.haoyuan.common.core.util.JsonUtil;
import com.limyel.haoyuan.common.web.pojo.R;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RespUtil {

    public static void writeResp(HttpServletResponse response, String content) {
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeResp(HttpServletResponse response, R<?> resp) {
        String content = JsonUtil.toJson(resp);
        writeResp(response, content);
    }

}