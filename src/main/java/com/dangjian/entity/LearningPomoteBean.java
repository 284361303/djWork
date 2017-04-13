package com.dangjian.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 学习宣传的bean
 * Created by sg-pc on 2016/10/11.
 */
public class LearningPomoteBean implements Serializable{

    /**
     * list : [{"code":"STUDYGARD","iconPath":"","messCount":"3","name":"学习园地"},{"code":"FOCUSNEWS","iconPath":"","messCount":"3","name":"要闻聚焦"},{"code":"MEMBERSHOW","iconPath":"","messCount":"3","name":"党员风采"}]
     * msg : 查询成功。
     * showType : TAB
     * status : 1
     */

    private String msg;
    private String showType;
    private String status;
    /**
     * code : STUDYGARD
     * iconPath :
     * messCount : 3
     * name : 学习园地
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

    public static class ListBean implements Serializable{
        private String code;
        private String iconPath;
        private String messCount;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }

        public String getMessCount() {
            return messCount;
        }

        public void setMessCount(String messCount) {
            this.messCount = messCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
