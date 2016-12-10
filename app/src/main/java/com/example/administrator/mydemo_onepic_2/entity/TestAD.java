package com.example.administrator.mydemo_onepic_2.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class TestAD {


    /**
     * errorCode : 1
     * productList : [{"id":2,"name":"实时解盘"},{"id":1,"name":"涨股专家"}]
     */

    private int errorCode;
    private List<ProductListBean> productList;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        /**
         * id : 2
         * name : 实时解盘
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
