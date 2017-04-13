package com.dangjian.entity;

import java.util.List;

/**
 * Created by sg-pc on 2016/10/23.
 */
public class JiFenDuiHuanBean {

    /**
     * status : 1
     * msg : 查询成功
     * list : [{"loginName":"shaoguang","applyDate":"2016-10-23 16:32:51","iconPath":"http://123.57.220.230:8080/static-server/static/upload/news/2016/10/23/1477194841075555.jpg","applyId":"7","statusName":"申请中","usablePoints":"34","deptName":"第一党支部","status":"1ING"},{"loginName":"HGQ","applyDate":"2016-10-20 08:46:12","iconPath":"http://123.57.220.230:8080/static-server/static/upload/news/2016/10/21/1477032671742530.jpg","applyId":"6","statusName":"申请中","usablePoints":"3082","deptName":"第二党支部","status":"1ING"},{"loginName":"HGQ","applyDate":"2016-10-07 00:00:00","iconPath":"http://123.57.220.230:8080/static-server/static/upload/news/2016/10/21/1477032671742530.jpg","applyId":"1","statusName":"已兑换","usablePoints":"3082","deptName":"第二党支部","status":"2FINISHED"}]
     */

    private String status;
    private String msg;
    /**
     * loginName : shaoguang
     * applyDate : 2016-10-23 16:32:51
     * iconPath : http://123.57.220.230:8080/static-server/static/upload/news/2016/10/23/1477194841075555.jpg
     * applyId : 7
     * statusName : 申请中
     * usablePoints : 34
     * deptName : 第一党支部
     * status : 1ING
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
        private String loginName;
        private String applyDate;
        private String iconPath;
        private String applyId;
        private String statusName;
        private String usablePoints;
        private String deptName;
        private String status;

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }

        public String getApplyId() {
            return applyId;
        }

        public void setApplyId(String applyId) {
            this.applyId = applyId;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getUsablePoints() {
            return usablePoints;
        }

        public void setUsablePoints(String usablePoints) {
            this.usablePoints = usablePoints;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
