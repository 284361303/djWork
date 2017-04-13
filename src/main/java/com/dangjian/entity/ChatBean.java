package com.dangjian.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sg-pc on 2016/10/19.
 */
public class ChatBean implements Serializable{

    /**
     * list : [{"content":"","createDate":"2016-10-18 23:14:55","goodNum":"0","iconPath":"","image1":"","image10":"","image2":"","image3":"","image4":"","image5":"","image6":"","image7":"","image8":"","image9":"","isDeleted":"Y","isGood":"N","loginName":"HGQ","replyNum":"0","title":"","xdId":"96"},{"content":"","createDate":"2016-10-18 21:50:16","goodNum":"1","iconPath":"","image1":"","image10":"","image2":"","image3":"","image4":"","image5":"","image6":"","image7":"","image8":"","image9":"","isDeleted":"Y","isGood":"Y","loginName":"HGQ","replyNum":"0","title":"","xdId":"95"},{"content":"","createDate":"2016-10-18 18:50:57","goodNum":"0","iconPath":"","image1":"","image10":"","image2":"","image3":"","image4":"","image5":"","image6":"","image7":"","image8":"","image9":"","isDeleted":"Y","isGood":"N","loginName":"HGQ","replyNum":"0","title":"hvv","xdId":"94"}]
     * msg : 查询成功。
     * status : 1
     */

    private String msg;
    private String status;
    /**
     * content :
     * createDate : 2016-10-18 23:14:55
     * goodNum : 0
     * iconPath :
     * image1 :
     * image10 :
     * image2 :
     * image3 :
     * image4 :
     * image5 :
     * image6 :
     * image7 :
     * image8 :
     * image9 :
     * isDeleted : Y
     * isGood : N
     * loginName : HGQ
     * replyNum : 0
     * title :
     * xdId : 96
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
        private String content;
        private String createDate;
        private String goodNum;
        private String iconPath;
        private String image1;
        private String image10;
        private String image2;
        private String image3;
        private String image4;
        private String image5;
        private String image6;
        private String image7;
        private String image8;
        private String image9;
        private String isDeleted;
        private String isGood;
        private String loginName;
        private String replyNum;
        private String title;
        private String xdId;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getGoodNum() {
            return goodNum;
        }

        public void setGoodNum(String goodNum) {
            this.goodNum = goodNum;
        }

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public String getImage10() {
            return image10;
        }

        public void setImage10(String image10) {
            this.image10 = image10;
        }

        public String getImage2() {
            return image2;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }

        public String getImage3() {
            return image3;
        }

        public void setImage3(String image3) {
            this.image3 = image3;
        }

        public String getImage4() {
            return image4;
        }

        public void setImage4(String image4) {
            this.image4 = image4;
        }

        public String getImage5() {
            return image5;
        }

        public void setImage5(String image5) {
            this.image5 = image5;
        }

        public String getImage6() {
            return image6;
        }

        public void setImage6(String image6) {
            this.image6 = image6;
        }

        public String getImage7() {
            return image7;
        }

        public void setImage7(String image7) {
            this.image7 = image7;
        }

        public String getImage8() {
            return image8;
        }

        public void setImage8(String image8) {
            this.image8 = image8;
        }

        public String getImage9() {
            return image9;
        }

        public void setImage9(String image9) {
            this.image9 = image9;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getIsGood() {
            return isGood;
        }

        public void setIsGood(String isGood) {
            this.isGood = isGood;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(String replyNum) {
            this.replyNum = replyNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getXdId() {
            return xdId;
        }

        public void setXdId(String xdId) {
            this.xdId = xdId;
        }
    }
}
