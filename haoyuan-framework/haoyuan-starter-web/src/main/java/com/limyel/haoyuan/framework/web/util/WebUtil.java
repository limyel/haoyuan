package com.limyel.haoyuan.framework.web.util;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {

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

}
