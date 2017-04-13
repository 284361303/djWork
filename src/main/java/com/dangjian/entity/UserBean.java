package com.dangjian.entity;

import java.io.Serializable;

/**
 * Created by sg-pc on 2016/10/9.
 * 用户信息实体
 */
public class UserBean implements Serializable{

    /**
     * sexCode : 0
     * rank : 0
     * sex : 女
     * MZCode : MANZU
     * MZ : 满族
     * monthRank : 0
     * weekRank : 0
     * birthDay :
     * usablePoint : 3000
     * joinPartMonth :
     * totalPoints : 0
     */

    private String sexCode;
    private String rank;
    private String sex;
    private String MZCode;
    private String MZ;
    private String monthRank;
    private String weekRank;
    private String birthDay;
    private String usablePoint;
    private String joinPartMonth;
    private String totalPoints;

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMZCode() {
        return MZCode;
    }

    public void setMZCode(String MZCode) {
        this.MZCode = MZCode;
    }

    public String getMZ() {
        return MZ;
    }

    public void setMZ(String MZ) {
        this.MZ = MZ;
    }

    public String getMonthRank() {
        return monthRank;
    }

    public void setMonthRank(String monthRank) {
        this.monthRank = monthRank;
    }

    public String getWeekRank() {
        return weekRank;
    }

    public void setWeekRank(String weekRank) {
        this.weekRank = weekRank;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getUsablePoint() {
        return usablePoint;
    }

    public void setUsablePoint(String usablePoint) {
        this.usablePoint = usablePoint;
    }

    public String getJoinPartMonth() {
        return joinPartMonth;
    }

    public void setJoinPartMonth(String joinPartMonth) {
        this.joinPartMonth = joinPartMonth;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }
}
