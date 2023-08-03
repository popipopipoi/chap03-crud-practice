package com.ohgiraffers.model;

public class CategoryDTO {

    private int categoryCode;
    private String categoryName;
    private int refCateCode;

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryCode, String categoryName, int refCateCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCateCode = refCateCode;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getRefCateCode() {
        return refCateCode;
    }

    public void setRefCateCode(int refCateCode) {
        this.refCateCode = refCateCode;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCateCode=" + refCateCode +
                '}';
    }
}

