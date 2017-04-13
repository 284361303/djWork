package com.dangjian.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 获取人员角色权限
 * Created by sg-pc on 2016/10/12.
 */
public class EmployeeRolesBean implements Serializable {

    /**
     * status : 1
     * list : [{"name":"党委管理员","code":"DW_ADMIN"},{"name":"支部管理员","code":"ZB_ADMIN"}]
     * msg : 查询成功。
     */

    private String status;
    private String msg;
    /**
     * name : 党委管理员
     * code : DW_ADMIN
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

    public static class ListBean implements Serializable {
        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
