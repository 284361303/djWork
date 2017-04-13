package com.dangjian.entity;

import java.util.List;

/**
 * Created by sg-pc on 2016/10/20.
 */
public class EditReleaseBean {

    /**
     * content : 测试
     * title : 测试
     * imageList : [{"imagePath":""},{"imagePath":""},{"imagePath":""},{"imagePath":""},{"imagePath":""},{"imagePath":""},{"imagePath":""},{"imagePath":""},{"imagePath":""}]
     * status : 1
     * roleType : DW_ADMIN
     * type : NOTICE
     * msg : 查询成功。
     */

    private String content;
    private String title;
    private String status;
    private String roleType;
    private String type;
    private String msg;
    /**
     * imagePath :
     */

    private List<ImageListBean> imageList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ImageListBean> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageListBean> imageList) {
        this.imageList = imageList;
    }

    public static class ImageListBean {
        private String imagePath;

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }
}
