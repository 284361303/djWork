package com.dangjian.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sg-pc on 2016/10/8.
 * 试题实体类
 */
public class ExamBean implements Serializable{

    /**
     * userAnswer :
     * title : 1921年7月下旬至8月初,中国共产党第一次全国代表大会先后在（ ）召开。
     * paperItemId : 188
     * isRight : 2
     * rightAnswer :
     * selectItems : [{"answer":"","content":"中国共产党的成立","num":"A"},{"answer":"","content":"五四运动","num":"B"},{"answer":"","content":"二七大罢工","num":"C"}]
     */

    private String userAnswer;
    private String title;
    private String paperItemId;
    private String isRight;
    private String rightAnswer;
    private String type;
    /**
     * answer :
     * content : 中国共产党的成立
     * num : A
     */

    private List<SelectItemsBean> selectItems;

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaperItemId() {
        return paperItemId;
    }

    public void setPaperItemId(String paperItemId) {
        this.paperItemId = paperItemId;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<SelectItemsBean> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItemsBean> selectItems) {
        this.selectItems = selectItems;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class SelectItemsBean implements Serializable{
        private String answer;
        private String content;
        private String num;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
