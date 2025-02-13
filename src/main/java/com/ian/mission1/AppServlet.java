package com.ian.mission1;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static com.ian.mission1.AppRepository.*;


@WebServlet(urlPatterns = {"/load-wifi"})
public class AppServlet extends HttpServlet {
    public static final OkHttpClient CLIENT = new OkHttpClient();

    // Service
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        String url = "http://openapi.seoul.go.kr:8088/4258714f4b6a787832346863627a6a/json/TbPublicWifiInfo/1/5/";

        Request requestObj = new Request.Builder().url(url).build();

        if ("/load-wifi".equals(path)) {
            try (Response apiResponse = CLIENT.newCall(requestObj).execute()) {
                if (!apiResponse.isSuccessful()) throw new IOException("Unexpected code " + apiResponse);

                String responseBody = apiResponse.body().string();
                Gson gson = new Gson();
                WiFiResponse wifiResponse = gson.fromJson(responseBody, WiFiResponse.class);

                ArrayList<Row> rows = wifiResponse.getTbPublicWifiInfo().getRow();
                try (Connection conn = connect()) {
                    response.setContentType("text/html; charset=utf-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("데이터베이스 연결 성공");
                } catch (SQLException e) {
                    response.getWriter().write("SQLException 발생: " + e.getMessage());
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}