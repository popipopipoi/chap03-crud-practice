package com.ohgiraffers.section01.select;

import com.ohgiraffers.model.CategoryDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {

    public static void main(String[] args) {
        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        CategoryDTO row = null;

        List<CategoryDTO> categoryList = null;

        String query = "SELECT * FROM TBL_CATEGORY";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            categoryList = new ArrayList<>();

            while (rset.next()) {
                row = new CategoryDTO();

                row.setCategoryCode(rset.getInt("CATEGORY_CODE"));
                row.setCategoryName(rset.getString("CATEGORY_NAME"));
                row.setRefCateCode(rset.getInt("REF_CATEGORY_CODE"));

                categoryList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }

        for(CategoryDTO categoryDTO : categoryList) {
            System.out.println(categoryDTO);
        }

        System.out.println("row : " + row);
    }
}
