package com.dangjian.entity;

import java.util.List;

/**
 * 用户里面的积分排名实体
 * Created by sg-pc on 2016/10/19.
 */
public class UserRankingBean {

    /**
     * myPoints : 0
     * status : 1
     * myRank : 0
     * list : [{"rank":"0","imgPath":"","points":"0","loginName":"gho"},{"rank":"0","imgPath":"","points":"0","loginName":"sgg"},{"rank":"0","imgPath":"","points":"0","loginName":"3333"},{"rank":"0","imgPath":"","points":"0","loginName":"32"},{"rank":"0","imgPath":"","points":"0","loginName":"3232"},{"rank":"0","imgPath":"","points":"0","loginName":"HGX3_77_del"},{"rank":"0","imgPath":"","points":"0","loginName":"HGX2"},{"rank":"0","imgPath":"","points":"0","loginName":"HGX1"},{"rank":"0","imgPath":"","points":"0","loginName":"HGX"},{"rank":"0","imgPath":"","points":"0","loginName":"HGQ"}]
     * msg : 查询成功。
     */

    private String myPoints;
    private String status;
    private String myRank;
    private String msg;
    /**
     * rank : 0
     * imgPath :
     * points : 0
     * loginName : gho
     */

    private List<ListBean> list;

    public String getMyPoints() {
        return myPoints;
    }

    public void setMyPoints(String myPoints) {
        this.myPoints = myPoints;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMyRank() {
        return myRank;
    }

    public void setMyRank(String myRank) {
        this.myRank = myRank;
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
        private String rank;
        private String imgPath;
        private String points;
        private String loginName;

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }
    }
}
