package com.dangjian.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 组织管理实体
 * Created by shao_g on 2016/10/2.
 */
public class OrganizationManagerBean implements Serializable {

    /**
     * status : 1
     * msg : 查询成功
     * list : [{"iconPath":"","loginName":"null","sex":"","MZ":"","empId":"118","deptId":"15","deptName":"第一支部","nickName":"","joinPartMonth":"入党时间"},{"iconPath":"","loginName":"null","sex":"","MZ":"","empId":"119","deptId":"15","deptName":"第一支部","nickName":"","joinPartMonth":"入党时间"},{"iconPath":"","loginName":"null","sex":"","MZ":"","empId":"120","deptId":"15","deptName":"第一支部","nickName":"","joinPartMonth":"入党时间"},{"iconPath":"","loginName":"null","sex":"","MZ":"","empId":"121","deptId":"15","deptName":"第一支部","nickName":"","joinPartMonth":"入党时间"},{"iconPath":"","loginName":"null","sex":"","MZ":"","empId":"122","deptId":"14","deptName":"第二支部","nickName":"","joinPartMonth":"入党时间"},{"iconPath":"","loginName":"null","sex":"女","MZ":"回族","empId":"124","deptId":"14","deptName":"第二支部","nickName":"","joinPartMonth":"2009-11"},{"iconPath":"http://192.168.1.204:8080/static-server/static/upload/news/2016/10/26/1477449357045666.jpg","loginName":"null","sex":"","MZ":"","empId":"125","deptId":"14","deptName":"第二支部","nickName":"","joinPartMonth":"入党时间"},{"iconPath":"","loginName":"null","sex":"女","MZ":"","empId":"126","deptId":"14","deptName":"第二支部","nickName":"","joinPartMonth":"2015-12"},{"iconPath":"http://192.168.1.204:8080/static-server/static/upload/news/2016/10/25/1477376126860620.jpg","loginName":"null","sex":"女","MZ":"","empId":"127","deptId":"14","deptName":"第二支部","nickName":"","joinPartMonth":"2016-12"},{"iconPath":"","loginName":"null","sex":"","MZ":"","empId":"128","deptId":"15","deptName":"第一支部","nickName":"","joinPartMonth":"入党时间"}]
     */

    private String status;
    private String msg;
    /**
     * iconPath :
     * loginName : null
     * sex :
     * MZ :
     * empId : 118
     * deptId : 15
     * deptName : 第一支部
     * nickName :
     * joinPartMonth : 入党时间
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
        private String iconPath;
        private String loginName;
        private String sex;
        private String MZ;
        private String empId;
        private String deptId;
        private String deptName;
        private String nickName;
        private String joinPartMonth;

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMZ() {
            return MZ;
        }

        public void setMZ(String MZ) {
            this.MZ = MZ;
        }

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getJoinPartMonth() {
            return joinPartMonth;
        }

        public void setJoinPartMonth(String joinPartMonth) {
            this.joinPartMonth = joinPartMonth;
        }
    }
}
