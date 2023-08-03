package com.ohgiraffers.section01.select;

import com.ohgiraffers.model.CategoryDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        CategoryDTO row = null;
        List<CategoryDTO> cateList = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 행을 입력하세요 : ");
        int categoryCode = sc.nextInt();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/section01/select/category-query.xml"));

            String query = prop.getProperty("selectCategoryCode");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, categoryCode);

            rset = pstmt.executeQuery();

            cateList = new ArrayList<>();

            while (rset.next()) {
                row = new CategoryDTO();

                row.setCategoryCode(rset.getInt("CATEGORY_CODE"));
                row.setCategoryName(rset.getString("CATEGORY_NAME"));
                row.setRefCateCode(rset.getInt("REF_CATEGORY_CODE"));

                cateList.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        for(CategoryDTO categoryDTO : cateList) {
            System.out.println(categoryDTO);
        }
    }
}
