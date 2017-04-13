package com.dangjian.entity;

import java.util.List;

/**
 * 积分明细bean
 * Created by sg-pc on 2016/10/25.
 */
public class JiFenLogBean {

    /**
     * status : 1
     * msg : 查询成功
     * list : [{"date":"2016-10-25 09:24:28","typeName":"获得积分","subTypeName":"每次登录","points":"2"},{"date":"2016-10-25 09:14:15","typeName":"获得积分","subTypeName":"每次登录","points":"2"},{"date":"2016-10-24 17:45:53","typeName":"获得积分","subTypeName":"每周一考每题积分","points":"10"},{"date":"2016-10-24 17:44:48","typeName":"获得积分","subTypeName":"每周一考每题积分","points":"10"},{"date":"2016-10-24 17:43:53","typeName":"获得积分","subTypeName":"每周一考每题积分","points":"10"},{"date":"2016-10-24 17:40:48","typeName":"获得积分","subTypeName":"每周一考每题积分","points":"10"},{"date":"2016-10-24 17:40:33","typeName":"获得积分","subTypeName":"每周一考每题积分","points":"10"},{"date":"2016-10-24 17:39:06","typeName":"获得积分","subTypeName":"每周一考每题积分","points":"10"},{"date":"2016-10-24 17:38:28","typeName":"获得积分","subTypeName":"每周一考每题积分","points":"10"},{"date":"2016-10-24 15:22:10","typeName":"获得积分","subTypeName":"每周一考每题积分","points":"10"}]
     */

    private String status;
    private String msg;
    /**
     * date : 2016-10-25 09:24:28
     * typeName : 获得积分
     * subTypeName : 每次登录
     * points : 2
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
        private String date;
        private String typeName;
        private String subTypeName;
        private String points;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getSubTypeName() {
            return subTypeName;
        }

        public void setSubTypeName(String subTypeName) {
            this.subTypeName = subTypeName;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }
}
