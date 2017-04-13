package com.dangjian.entity;

import java.util.List;

/**
 * Created by sg-pc on 2016/10/10.
 * 学习资料实体类
 */
public class LearningMterialsBean {

    /**
     * list : [{"createDate":"2016-10-09","creator":"其它新闻","iconPath":"","isRead":"N","newsId":"185","title":"马克思主义哲学"},{"createDate":"2016-10-09","creator":"其它新闻","iconPath":"","isRead":"N","newsId":"186","title":"邓小平理论"}]
     * msg : 查询成功
     * showType : 2
     * status : 1
     */

    private String msg;
    private String showType;
    private String status;
    /**
     * createDate : 2016-10-09
     * creator : 其它新闻
     * iconPath :
     * isRead : N
     * newsId : 185
     * title : 马克思主义哲学
     */

    private List<ListBean> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
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
        private String createDate;
        private String creator;
        private String iconPath;
        private String isRead;
        private String newsId;
        private String title;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public String getNewsId() {
            return newsId;
        }

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
