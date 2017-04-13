package com.dangjian.entity;

import java.util.List;

/**
 * 青年工作轮播图实体
 * Created by sg-pc on 2016/10/25.
 */
public class YouthWorkBannerBean {

    /**
     * list : [{"iconPath":"http://192.168.1.204:8080/static-server/static/upload/news/2016/10/27/1477548261864325.png","id":"7","isRead":"Y"},{"iconPath":"http://192.168.1.204:8080/static-server/static/upload/news/2016/10/27/1477548779343697.JPEG","id":"9","isRead":"Y"}]
     * msg : 查询成功。
     * status : 1
     */

    private String msg;
    private String status;
    /**
     * iconPath : http://192.168.1.204:8080/static-server/static/upload/news/2016/10/27/1477548261864325.png
     * id : 7
     * isRead : Y
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
        private String iconPath;
        private String id;
        private String isRead;

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }
    }
}
