package com.dangjian.entity;

import java.util.List;

/**
 * 所有支部列表实体
 * Created by shao_g on 2016/10/2.
 */
public class ZhiBuListBean {

    /**
     * list : [{"deptCode":"FIRSTONEPART","deptId":"10","deptName":"第一党支部"},{"deptCode":"SECONDPART","deptId":"11","deptName":"第二党支部"}]
     * msg : 查询成功
     * status : 1
     */

    private String msg;
    private String status;
    /**
     * deptCode : FIRSTONEPART
     * deptId : 10
     * deptName : 第一党支部
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
        private String deptCode;
        private String deptId;
        private String deptName;

        public String getDeptCode() {
            return deptCode;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }
    }
}
