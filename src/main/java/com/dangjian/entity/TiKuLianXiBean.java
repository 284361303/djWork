package com.dangjian.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 题库练习实体
 * Created by shao_g on 2016/10/6.
 */
public class TiKuLianXiBean implements Serializable{

    /**
     * list : [{"endDate":"","excuteId":"10","isNew":"N","startDate":"","status":"进行中","title":"测试长题"},{"endDate":"","excuteId":"9","isNew":"N","startDate":"","status":"进行中","title":"学系列讲话"},{"endDate":"","excuteId":"7","isNew":"N","startDate":"","status":"进行中","title":"党章党规知识竞赛"},{"endDate":"","excuteId":"4","isNew":"N","startDate":"","status":"进行中","title":"判断题"},{"endDate":"","excuteId":"3","isNew":"Y","startDate":"","status":"进行中","title":"单选题"},{"endDate":"","excuteId":"2","isNew":"N","startDate":"","status":"进行中","title":"多选题"}]
     * msg : 查询成功
     * status : 1
     */

    private String msg;
    private String status;
    /**
     * endDate :
     * excuteId : 10
     * isNew : N
     * startDate :
     * status : 进行中
     * title : 测试长题
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

    public static class ListBean implements Serializable{
        private String endDate;
        private String excuteId;
        private String isNew;
        private String startDate;
        private String status;
        private String title;

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getExcuteId() {
            return excuteId;
        }

        public void setExcuteId(String excuteId) {
            this.excuteId = excuteId;
        }

        public String getIsNew() {
            return isNew;
        }

        public void setIsNew(String isNew) {
            this.isNew = isNew;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
