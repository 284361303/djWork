package com.dangjian.entity;

import java.util.List;

/**
 * 民族列表bean
 * Created by sg-pc on 2016/10/21.
 */
public class MZListBean {

    /**
     * status : 1
     * msg : 查询成功
     * list : [{"code":"HANZU","name":"汉族"},{"code":"HUIZU","name":"回族"},{"code":"MANZU","name":"满族"}]
     */

    private String status;
    private String msg;
    /**
     * code : HANZU
     * name : 汉族
     */

    private List<ListBean> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
