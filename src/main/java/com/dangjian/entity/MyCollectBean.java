package com.dangjian.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 我的收藏bean
 * Created by sg-pc on 2016/10/19.
 */
public class MyCollectBean implements Serializable{

    /**
     * status : 1
     * list : [{"author":"宣传小组","iconPath":"http://123.57.220.230:8080/static-server/static/upload/news/2016/10/18/1476754370303708.jpg","title":"呵呵","newsId":"73","createDate":"2016-10-18 09:32:50"},{"author":"宣传小组","iconPath":"http://123.57.220.230:8080/static-server/static/upload/news/2016/10/18/1476754394757736.jpg","title":"呵呵1","newsId":"74","createDate":"2016-10-18 09:33:15"},{"author":"党委","iconPath":"http://123.57.220.230:8080/static-server/static/upload/news/2016/10/16/1476601516389617.png","title":"办公厅\"两学一做\"学习教育 学党章党规知识竞赛花絮","newsId":"45","createDate":"2016-10-16 15:36:26"},{"author":"党委","iconPath":"http://123.57.220.230:8080/static-server/static/upload/news/2016/10/12/1476268779272171.jpg","title":"学习习近平总书记在全国国有企业党的建设工作会议上的重要讲话","newsId":"1","createDate":"2016-10-12 00:00:00"},{"author":"两学一做","iconPath":"","title":"党的组织制度","newsId":"22","createDate":"2016-10-12 00:00:00"},{"author":"两学一做","iconPath":"","title":"《中国共产党纪律处分条例》第六条","newsId":"7","createDate":"2016-10-12 00:00:00"},{"author":"党委","iconPath":"","title":"深入推进两学一做学习教育","newsId":"17","createDate":"2016-10-12 20:26:35"}]
     * msg : 查询成功
     */

    private String status;
    private String msg;
    /**
     * author : 宣传小组
     * iconPath : http://123.57.220.230:8080/static-server/static/upload/news/2016/10/18/1476754370303708.jpg
     * title : 呵呵
     * newsId : 73
     * createDate : 2016-10-18 09:32:50
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

    public static class ListBean implements Serializable{
        private String author;
        private String iconPath;
        private String title;
        private String newsId;
        private String createDate;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNewsId() {
            return newsId;
        }

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
