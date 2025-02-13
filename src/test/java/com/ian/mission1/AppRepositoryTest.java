package com.ian.mission1;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AppRepositoryTest {

    AppRepository ar = new AppRepository();

    @Test
    void connect() throws SQLException {
        try {
            Connection conn = ar.connect();
            assertNotNull(conn, "데이터베이스 연결이 null이 아닙니다.");
            System.out.println("SQLite 연결 성공");
        } catch (SQLException e) {
            fail("SQLite 연결 실패: " + e.getMessage());
        }
    }
}