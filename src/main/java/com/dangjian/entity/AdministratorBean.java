package com.dangjian.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员中的选择权限实体
 * Created by sg-pc on 2016/10/10.
 */
public class AdministratorBean implements Serializable{

    /**
     * list : [{"code":"DW_ADMIN","name":"党委管理员"},{"code":"ZB_ADMIN","name":"支部管理员"},{"code":"GH_ADMIN","name":"工会管理员"},{"code":"XC_ADMIN","name":"宣传管理员"},{"code":"ZZ_ADMIN","name":"组织小组管理员"},{"code":"JJ_ADMIN","name":"纪检小组管理员"},{"code":"QN_ADMIN","name":"青年小组管理员"}]
     * msg : 查询成功。
     * status : 1
     */

    private String msg;
    private String status;
    /**
     * code : DW_ADMIN
     * name : 党委管理员
     */

    private List<ListBean> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
