package com.ohgiraffers.section04.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/section01/select/category-query.xml"));
            String query = prop.getProperty("deleteCategory");

            Scanner sc = new Scanner(System.in);
            System.out.print("삭제할 카테고리 번호를 입력 : ");
            int categoryCode = sc.nextInt();

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, categoryCode);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
        if(result > 0) {
            System.out.println("해당 카테고리를 삭제 완료했습니다.");
        } else {
            System.out.println("해당 카테고리가 없어 삭제 불가합니다.");
        }
    }
}
