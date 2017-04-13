package com.dangjian.entity;

import java.util.List;

/**
 * 我的排名bean
 * Created by sg-pc on 2016/10/11.
 */
public class RankingsBean {

    /**
     * list : [{"imgPath":"http://123.57.220.230:8080/static-server/static/upload/","name":"HGQ","rank":"1","score":"0","times":"16"}]
     * msg : 获取成功。
     * status : 1
     */

    private String msg;
    private String status;
    /**
     * imgPath : http://123.57.220.230:8080/static-server/static/upload/
     * name : HGQ
     * rank : 1
     * score : 0
     * times : 16
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
        private String imgPath;
        private String name;
        private String rank;
        private String score;
        private String times;

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }
    }
}
