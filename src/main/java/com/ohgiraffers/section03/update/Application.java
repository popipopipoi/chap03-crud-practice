package com.ohgiraffers.section03.update;

import com.ohgiraffers.model.CategoryDTO;

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

        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 카테고리 코드 : ");
        int categoryCode = sc.nextInt();
        sc.nextLine();
        System.out.print("변경할 카테고리 이름 : ");
        String categoryName = sc.nextLine();
        System.out.print("변경할 상위 카테고리 코드 : ");
        int refCategoryCode = sc.nextInt();

        CategoryDTO changedCategory = new CategoryDTO();
        changedCategory.setCategoryCode(categoryCode);
        changedCategory.setCategoryName(categoryName);
        changedCategory.setRefCateCode(refCategoryCode);

        /* ------*/
        Connection con = getConnection();

        PreparedStatement ptmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/section01/select/category-query.xml"));
            String query = prop.getProperty("updateCategory");

            ptmt = con.prepareStatement(query);
            ptmt.setString(1, changedCategory.getCategoryName());
            ptmt.setInt(2, changedCategory.getRefCateCode());
            ptmt.setInt(3, changedCategory.getCategoryCode());

            result = ptmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(ptmt);
            close(con);
        }
        if(result > 0) {
            System.out.println("카테고리 변경이 완료 되었습니다.");
        } else {
            System.out.println("카테고리 변경에 실패하였습니다. ");
        }

    }
}
