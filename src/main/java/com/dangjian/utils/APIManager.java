package com.dangjian.utils;

/**
 * Created by sg-pc on 2016/9/30.
 */
public class APIManager {
    /**
     * 域名
     * 10.14.1.213
     */
    public static final String domain = "10.14.1.213";
    /**
     * 域名下面节点
     */
    public static final String PATH = "djcms";
//    public static final String PATH = "";

    /**
     * 请求接口地址
     */
    public static final String APP_SERVER_URL = "http://" + APIManager.domain + ":8080/" + APIManager.PATH + "/welcome";
//    public static final String APP_SERVER_URL = "http://" + APIManager.domain + "/" + APIManager.PATH + "welcome";

    /**
     * 图片加载路径
     */
    public static final String ImagePath = "http://" + APIManager.domain + ":8080/" + "static-server/static/upload/";
//    public static final String ImagePath = "http://" + APIManager.domain + "/" + "static-server/static/upload/";

    /**
     * （11001）登录
     */
    public static final String login = APP_SERVER_URL
            + "/app/employeeCtrl/login.do";
    /**
     * （14002）获取部门信息
     */
    public static final String getDeptList = APP_SERVER_URL
            + "/app/managerCtrl/getDeptList.do";
    /**
     * （14001）增加/修改人员
     */
    public static final String saveMember = APP_SERVER_URL
            + "/app/managerCtrl/saveMember.do";
    /**
     * （14004）获得人员列表
     */
    public static final String getEmployeeList = APP_SERVER_URL
            + "/app/managerCtrl/getEmployeeList.do";
    /**
     * （12004）获取考试、练习列表
     */
    public static final String getExaExcuteList = APP_SERVER_URL
            + "/app/exaCtrl/getExaExcuteList.do";
    /**
     * （12001）开始考试（只用于知识竞赛和题库练习）
     */
    public static final String startExamination = APP_SERVER_URL
            + "/app/exaCtrl/startExamination.do";
    /**
     * （12005）获得考试结果
     */
    public static final String getPaperResult = APP_SERVER_URL
            + "/app/exaCtrl/getPaperResult.do";
    /**
     * （12002）获取考题
     */
    public static final String getPaperQuestion = APP_SERVER_URL
            + "/app/exaCtrl/getPaperQuestion.do";
    /**
     * （12003）提交试卷
     */
    public static final String submitPaper = APP_SERVER_URL
            + "/app/exaCtrl/submitPaper.do";
    /**
     * （11004）修改密码
     */
    public static final String changePass = APP_SERVER_URL
            + "/app/employeeCtrl/changePass.do";
    /**
     * （11002）获得我的个人信息数据
     */
    public static final String getMyInfo = APP_SERVER_URL
            + "/app/employeeCtrl/getMyInfo.do";
    /**
     * （12006）提交考题答案
     */
    public static final String submitQueAnswer = APP_SERVER_URL
            + "/app/exaCtrl/submitQueAnswer.do";
    /**
     * （12008）获取每周一考考试Id
     */
    public static final String getWeeklyExcuteId = APP_SERVER_URL
            + "/app/exaCtrl/getWeeklyExcuteId.do";
    /**
     * （12007）开始考试（只用每周一考）
     */
    public static final String startWeeklyExa = APP_SERVER_URL
            + "/app/exaCtrl/startWeeklyExa.do";
    /**
     * （13002）获得新闻通知列表
     */
    public static final String getNewsList = APP_SERVER_URL
            + "/app/newsCtrl/getNewsList.do";
    /**
     * （13003）显示新闻通知等
     */
    public static final String showNews = APP_SERVER_URL
            + "/app/newsCtrl/showNews.do";
    /**
     * （14006）获得系统角色列表
     */
    public static final String getRolesList = APP_SERVER_URL
            + "/app/managerCtrl/getRoleList.do";
    /**
     * 退出登录
     */
    public static final String logOut = APP_SERVER_URL
            + "/app/employeeCtrl/logOut.do";
    /**
     * （13001）获得栏目的二级菜单
     */
    public static final String getSecondMenu = APP_SERVER_URL
            + "/app/newsCtrl/getSecondMenu.do";
    /**
     * （120049）获取考试排名
     */
    public static final String getKSPMList = APP_SERVER_URL
            + "/app/exaCtrl/getKSPMList.do";
    /**
     * （14005）获得人员权限角色
     */
    public static final String getEmployeeRoles = APP_SERVER_URL
            + "/app/managerCtrl/getEmployeeRoles.do";
    /**
     * （13008）新增、编辑新闻
     */
    public static final String saveNews = APP_SERVER_URL
            + "/app/newsCtrl/saveNews.do";
    /**
     * （13014）获得一级菜单新闻TOP3列表
     */
    public static final String getTopNewsByTopMenu = APP_SERVER_URL
            + "/app/newsCtrl/getTopNewsByTopMenu.do";
    /**
     * （16001）检查APP版本更新
     */
    public static final String checkAppUpdate = APP_SERVER_URL
            + "/app/settingCtrl/checkAppUpdate.do";
    /**
     * （13017）进入心得列表页
     */
    public static final String toXDList = APP_SERVER_URL
            + "/app/newsCtrl/toXDList.do";
    /**
     * （13010）上传图片
     */
    public static final String uploadFileApp = APP_SERVER_URL
            + "/app/newsCtrl/uploadFileApp.do";
    /**
     * （11005）获得我的收藏列表
     */
    public static final String getMyFavorite = APP_SERVER_URL
            + "/app/employeeCtrl/getMyFavorite.do";
    /**
     * （15001）积分排名
     */
    public static final String getRank = APP_SERVER_URL
            + "/app/pointsCtrl/getRank.do";
    /**
     * （11009）提交积分兑换申请
     */
    public static final String submitPointApply = APP_SERVER_URL
            + "/app/employeeCtrl/submitPointApply.do";
    /**
     * （13016）获得发帖列表
     */
    public static final String getXDList = APP_SERVER_URL
            + "/app/newsCtrl/getXDList.do";
    /**
     * （13020）我的评论
     */
    public static final String myComments = APP_SERVER_URL
            + "/app/newsCtrl/myComments.do";
    /**
     * （13018）帖子详情页面
     */
    public static final String toTZDetail = APP_SERVER_URL
            + "/app/newsCtrl/toTZDetail.do";
    /**
     * (13011）获取新闻通知内容（编辑新闻通知用）
     */
    public static final String getNewsContent = APP_SERVER_URL
            + "/app/newsCtrl/getNewsContent.do";
    /**
     * （13009）删除新闻、评论
     */
    public static final String deleteContent = APP_SERVER_URL
            + "/app/newsCtrl/deleteContent.do";
    /**
     * （11003）修改我的信息
     */
    public static final String modifyMyInfo = APP_SERVER_URL
            + "/app/employeeCtrl/modifyMyInfo.do";
    /**
     * （11007）获取民族列表
     */
    public static final String getMZList = APP_SERVER_URL
            + "/app/employeeCtrl/getMZList.do";
    /**
     * （13021）判断新闻是否可编辑和删除
     */
    public static final String isNewsCanEditable = APP_SERVER_URL
            + "/app/newsCtrl/isNewsCanEditable.do";
    /**
     * （15005）获取积分兑换申请列表
     */
    public static final String getGiftApplyList = APP_SERVER_URL
            + "/app/pointsCtrl/getGiftApplyList.do";
    /**
     * （15004）积分兑换审批
     */
    public static final String approveApply = APP_SERVER_URL
            + "/app/pointsCtrl/approveApply.do";
    /**
     * （15002）积分日志
     */
    public static final String getPointsLog = APP_SERVER_URL
            + "/app/pointsCtrl/getPointsLog.do";
    /**
     * （11003）删除人员
     */
    public static final String deleteMember = APP_SERVER_URL
            + "/app/managerCtrl/deleteMember.do";
    /**
     * （13022）获得青年工作首页轮播图
     */
    public static final String getQNTop5Pic = APP_SERVER_URL
            + "/app/newsCtrl/getQNTop5Pic.do";
    /**
     * （13023）获得模块首页图片
     */
    public static final String getModulePic = APP_SERVER_URL
            + "/app/newsCtrl/getModulePic.do";
    /**
     * （16003）提交意见反馈信息
     */
    public static final String submitFeedback = APP_SERVER_URL
            + "/app/settingCtrl/submitFeedback.do";
    /**
     * （11010）获得生日祝语
     */
    public static final String getBirthDayLan = APP_SERVER_URL
            + "/app/employeeCtrl/getBirthDayLan.do";
    /**
     * （11011）设置图片显示类型
     */
    public static final String showPicType = APP_SERVER_URL
            + "/app/employeeCtrl/setShowPicType.do";
    /**
     * 关于我们下载二维码地址
     */
    public static final String downloadewm = ImagePath
            + "app/downloadewm.png";
    /**
     * （11013）下载APP操作手册
     */
    public static final String app_manual = ImagePath
            + "/app/app_manual.pdf";
    /**
     * （11014）下载管理员使用手册
     */
    public static final String manual = ImagePath
            + "/app/manual.pdf";
    /**
     * （16004）获取未读登录弹出页面消息id列表
     */
    public static final String getNotReadMessageIds = APP_SERVER_URL
            + "/app/settingCtrl/getNotReadMessageIds.do";
    /**
     * （16005）显示登录弹出消息页面
     */
    public static final String showMessage = APP_SERVER_URL
            + "/app/settingCtrl/showMessage.do";
}
