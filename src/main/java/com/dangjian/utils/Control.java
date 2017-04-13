package com.dangjian.utils;

import android.content.Context;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by shao_g on 2016/10/1.
 */
public class Control {

    private static final String TAG = "Control";

    /**
     * （11001）登录
     *
     * @param context
     * @param loginName 登录名字
     * @param password  登录密码
     */
    public static void Login(Context context, String loginName, String password, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.login);
            params.addParameter("loginName", loginName);
            params.addParameter("password", password);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （14002）获取部门信息
     *
     * @param context
     */
    public static void getDeptList(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getDeptList);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （14001）增加/修改人员
     *
     * @param context
     * @param loginName 登录名
     * @param password  密码
     * @param deptId    所属部门id
     * @param empId     员工id，新增员工该字段为空
     * @param roles     DW_ADMIN党委管理员，ZB_ADMIN支部管理员，XC_ADMIN宣传小组管理员，ZZ_ADMIN组织小组管理员,JJ_ADMIN纪检小组管理员,QN_AMIN青年小组管理员,GH_ADMIN工会管理员，可以兼职多个管理员，多个管理员以,号分割
     * @param result
     */
    public static void saveMember(Context context, String loginName, String password, String deptId,
                                  String empId, String roles, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.saveMember);
            params.addParameter("loginName", loginName);
            params.addParameter("password", password);
            params.addParameter("deptId", deptId);
            params.addParameter("empId", empId);
            params.addParameter("roles", roles);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （14004）获得人员列表
     *
     * @param context
     * @param deptId   部门id，0为所有部门
     * @param pageNo   分页页码
     * @param pageSize 每页记录数
     */
    public static void getEmployeeList(Context context, String deptId, String pageNo, String pageSize, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getEmployeeList);
            params.addParameter("deptId", deptId);
            params.addParameter("pageNo", pageNo);
            params.addParameter("pageSize", pageSize);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （12004）获取考试、练习列表
     *
     * @param context
     * @param exaType  考试类型competition为知识竞赛,studyDB 题库练习
     * @param pageNo   分页页码
     * @param pageSize 每页记录数
     */
    public static void getExaExcuteList(Context context, String exaType, String pageNo, String pageSize, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getExaExcuteList);
            params.addParameter("exaType", exaType);
            params.addParameter("pageNo", pageNo);
            params.addParameter("pageSize", pageSize);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （12001）开始考试（只用于知识竞赛和题库练习）
     *
     * @param context
     * @param excuteId
     */
    public static void startExamination(Context context, String excuteId, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.startExamination);
            params.addParameter("excuteId", excuteId);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （12005）获得考试结果
     *
     * @param context
     * @param excuteId 考试id
     * @param result
     */
    public static void getPaperResult(Context context, String excuteId, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getPaperResult);
            params.addParameter("excuteId", excuteId);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （12002）获取考题
     *
     * @param context
     * @param paperId  考试试卷id
     * @param sequence 题号
     */
    public static void getPaperQuestion(Context context, String paperId, String sequence, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getPaperQuestion);
            params.addParameter("paperId", paperId);
            params.addParameter("sequence", sequence);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （12003）提交试卷
     *
     * @param context
     * @param paperId  考试试卷id
     * @param sequence 最大序号，题库练习有用，其它考试无效
     * @param result
     */
    public static void submitPaper(Context context, String paperId, String sequence, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.submitPaper);
            params.addParameter("paperId", paperId);
            params.addParameter("sequence", sequence);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * (11004）修改密码
     *
     * @param context
     * @param oldPass 原密码
     * @param newPass 新密码
     */
    public static void changePass(Context context, String oldPass, String newPass, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.changePass);
            params.addParameter("oldPass", oldPass);
            params.addParameter("newPass", newPass);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （11002）获得我的个人信息数据
     *
     * @param context
     * @param result
     */
    public static void getMyInfo(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getMyInfo);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （14005）获得人员权限角色
     *
     * @param context
     * @param empId   人员id
     * @param result
     */
    public static void getEmployeeRoles(Context context, String empId, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getEmployeeRoles);
            params.addParameter("empId", empId);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （12006）提交考题答案
     *
     * @param context
     * @param paperItemId 考试考题id
     * @param answer
     * @param result
     */
    public static void submitQueAnswer(Context context, String paperItemId, String answer, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.submitQueAnswer);
            params.addParameter("paperItemId", paperItemId);
            params.addParameter("answer", answer);
            x.http().post(params, result);
//                String s = x.http().postSync(params, String.class);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （12006）提交考题答案(同步)
     *
     * @param context
     * @param paperItemId 考试考题id
     * @param answer
     * @param result
     */
    public static void submitQueAnswerSync(Context context, String paperItemId, String answer, String result) {
        try {
            if (NetWorkHelper.checkNetworkState(context) == true) {
                RequestParams params = new RequestParams(APIManager.submitQueAnswer);
                params.addParameter("paperItemId", paperItemId);
                params.addParameter("answer", answer);
                result = x.http().postSync(params, String.class);
                Log.i(TAG, "同步结果--: " + result);
            } else {
                ToastUtil.showShort(context, "请检查网络");
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * （12008）获取每周一考考试Id
     */
    public static void getWeeklyExcuteId(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getWeeklyExcuteId);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （12007）开始考试（只用每周一考）
     */
    public static void startWeeklyExa(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.startWeeklyExa);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （13002）获得新闻通知列表
     *
     * @param context
     * @param newsType PARTYCONSTITUTION:学习党章党规,PARTYSERIAL:学习系列讲话，NOTICE:通知
     * @param pageNo   第几页
     * @param pageSize 每页条数
     * @param result
     */
    public static void getNewsList(Context context, String newsType, String pageNo, String pageSize, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            if (context == null)
                context = MyApplication.getInstance();
            String wifiOrMobile = NetWorkHelper.isWifiOrMobile(context);
            Log.i(TAG, "获得新闻通知列表/wifiOrMobile: " + wifiOrMobile);
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getNewsList);
            params.addParameter("newsType", newsType);
            params.addParameter("pageNo", pageNo);
            params.addParameter("pageSize", pageSize);
            params.addParameter("netType", wifiOrMobile);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （14006）获得系统角色列表
     *
     * @param context
     * @param result
     */
    public static void getRolesList(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getRolesList);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * 退出登录
     */
    public static void logOut(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.logOut);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （13001）获得栏目的二级菜单
     *
     * @param context
     * @param typeCode 栏目编码：NOTICEMENU通知公告，STUDYMENU学习宣传，ORGMENU组织工作，INSPECTIONMENU纪检工作，YOUNGMENU青年工作，PARTMENU支部工作，UNIONMENU工会工作
     * @param result
     */
    public static void getSecondMenu(Context context, String typeCode, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getSecondMenu);
            params.addParameter("typeCode", typeCode);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （120049）获取考试排名
     *
     * @param context
     * @param pmType   排名类型PERSON个人,ORG支部
     * @param paperId  试卷id
     * @param excuteId 考试id
     * @param result
     */
    public static void getKSPMList(Context context, String pmType, String paperId, String excuteId, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getKSPMList);
            params.addParameter("pmType", pmType);
            params.addParameter("paperId", paperId);
            params.addParameter("excuteId", excuteId);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （13008）新增、编辑新闻
     *
     * @param context
     * @param id        新闻id，新增是id为空
     * @param type      类型
     * @param content   内容
     * @param roleType  角色
     * @param imageList 图片名称列表，多个图片以;分割
     * @param result
     */
    public static void saveNews(Context context, String id, String title, String type, String content, String roleType, String imageList, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.saveNews);
            params.addParameter("id", id);
            params.addParameter("title", title);
            params.addParameter("type", type);
            params.addParameter("content", content);
            params.addParameter("roleType", roleType);
            params.addParameter("imageList", imageList);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （13014）获得一级菜单新闻TOP3列表
     *
     * @param context
     * @param topMenu 一级菜单
     * @param result
     */
    public static void getTopNewsByTopMenu(Context context, String topMenu, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            String wifiOrMobile = NetWorkHelper.isWifiOrMobile(context);
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getTopNewsByTopMenu);
            params.addParameter("topMenu", topMenu);
            params.addParameter("netType", wifiOrMobile);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （16001）检查APP版本更新
     *
     * @param context
     * @param version 当前版本号
     * @param type    2—安卓，3—IOS
     * @param result
     */
    public static void checkAppUpdate(Context context, String version, String type, Callback.CommonCallback<String> result) {
        RequestParams params = new RequestParams(APIManager.checkAppUpdate);
        params.addParameter("version", version);
        params.addParameter("type", type);
        x.http().post(params, result);
    }

    /**
     * （13010）上传图片
     *
     * @param context
     * @param path    图片路径
     * @param result
     */
    public static void uploadFileApp(Context context, String path, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            try {
                ToastUtil.showProgressDialog(context);
                RequestParams params = new RequestParams(APIManager.uploadFileApp + ";jsessionid=" + Config.mCookie);
                params.setMultipart(true);
                params.addBodyParameter("file", new File(path));//设置上传的文件路径
                x.http().post(params, result);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （11005）获得我的收藏列表
     *
     * @param context
     * @param pageNo   分页页码
     * @param pageSize 每页记录数
     * @param result
     */
    public static void getMyFavorite(Context context, String pageNo, String pageSize, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getMyFavorite);
            params.addParameter("pageNo", pageNo);
            params.addParameter("pageSize", pageSize);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （15001）积分排名
     *
     * @param context
     * @param type    WEEK周，MONTH月，TOTAL总排名
     * @param result
     */
    public static void getRank(Context context, String type, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getRank);
            params.addParameter("type", type);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （11009）提交积分兑换申请
     */
    public static void submitPointApply(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.submitPointApply);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （13016）获得发帖列表
     *
     * @param context
     * @param isMyXD   0为所有人心得/发帖，1为我的心得/发帖
     * @param type     COMUNICATION:互动交流，STUDYNOTE:学习心得
     * @param pageNo   分页页码
     * @param pageSize 每页记录数
     * @param result
     */
    public static void getXDList(Context context, String isMyXD, String type, String pageNo, String pageSize, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getXDList);
            params.addParameter("isMyXD", isMyXD);
            params.addParameter("type", type);
            params.addParameter("pageNo", pageNo);
            params.addParameter("pageSize", pageSize);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * (13011）获取新闻通知内容（编辑新闻通知用）
     *
     * @param context
     * @param id      新闻或通知id
     * @param result
     */
    public static void getNewsContent(Context context, String id, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getNewsContent);
            params.addParameter("id", id);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （13009）删除新闻、评论
     *
     * @param context
     * @param id      新闻id
     * @param type    类型：NEWS为新闻点赞，COMMENTS回复
     * @param result
     */
    public static void deleteContent(Context context, String id, String type, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.deleteContent);
            params.addParameter("id", id);
            params.addParameter("type", type);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （11003）修改我的信息
     *
     * @param context
     * @param type    类型：SEX性别（1为男，0为女），BIRTHDAY生日(格式YYYY-MM-DD),JOINPARTMONTH入党年月,MZ民族，ICONPATH图像
     * @param value   修改值
     * @param result
     */
    public static void modifyMyInfo(Context context, String type, String value, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.modifyMyInfo);
            params.addParameter("type", type);
            params.addParameter("value", value);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （11007）获取民族列表
     *
     * @param context
     * @param result
     */
    public static void getMZList(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getMZList);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （13021）判断新闻是否可编辑和删除
     *
     * @param context
     * @param id      新闻id
     * @param result
     */
    public static void isNewsCanEditable(Context context, String id, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.isNewsCanEditable);
            params.addParameter("id", id);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （15005）获取积分兑换申请列表
     *
     * @param context
     * @param result
     */
    public static void getGiftApplyList(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getGiftApplyList);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （15004）积分兑换审批
     *
     * @param context
     * @param applyId 兑换id
     * @param approve Y审核通过，N拒绝
     * @param points  消费积分
     * @param result
     */
    public static void approveApply(Context context, String applyId, String approve, String points, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            if (NetWorkHelper.checkNetworkState(context) == true) {
                ToastUtil.showProgressDialog(context);
                RequestParams params = new RequestParams(APIManager.approveApply);
                params.addParameter("applyId", applyId);
                params.addParameter("approve", approve);
                params.addParameter("points", points);
                x.http().post(params, result);
            } else {
                ToastUtil.showShort(context, "请检查网络");
            }
        }
    }

    /**
     * （15002）积分日志
     *
     * @param context
     * @param pageNo   分页页码
     * @param pageSize 每页记录数
     * @param result
     */
    public static void getPointsLog(Context context, String pageNo, String pageSize, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getPointsLog);
            params.addParameter("pageNo", pageNo);
            params.addParameter("pageSize", pageSize);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （11003）删除人员
     *
     * @param context
     * @param empId   员工编号
     * @param result
     */
    public static void deleteMember(Context context, String empId, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.deleteMember);
            params.addParameter("empId", empId);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （13022）获得青年工作首页轮播图
     */
    public static void getQNTop5Pic(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            String wifiOrMobile = NetWorkHelper.isWifiOrMobile(context);
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.getQNTop5Pic);
            params.addParameter("netType", wifiOrMobile);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （13023）获得模块首页图片
     *
     * @param context
     * @param type    ORGMENU组织工作，UNIONMENU工会工作 ,TWOSTDONEDO两学一做
     * @param result
     */
    public static void getModulePic(Context context, String type, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getModulePic);
            params.addParameter("type", type);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （16003）提交意见反馈信息
     *
     * @param context
     * @param contactPhone 联系电话
     * @param content      反馈内容
     * @param result
     */
    public static void submitFeedback(Context context, String contactPhone, String content, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.submitFeedback);
            params.addParameter("contactPhone", contactPhone);
            params.addParameter("content", content);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （11010）获得生日祝语
     */
    public static void getBirthDayLan(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getBirthDayLan);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （11011）设置图片显示类型
     *
     * @param context
     * @param showPicType WIFIPIC 仅wifi下显示图片，ALLPIC 所有情况显示图片
     * @param result
     */
    public static void showPicType(Context context, String showPicType, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            ToastUtil.showProgressDialog(context);
            RequestParams params = new RequestParams(APIManager.showPicType);
            params.addParameter("showPicType", showPicType);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }

    /**
     * （16004）获取未读登录弹出页面消息id列表
     */
    public static void getNotReadMessageIds(Context context, Callback.CommonCallback<String> result) {
        if (NetWorkHelper.checkNetworkState(context) == true) {
            RequestParams params = new RequestParams(APIManager.getNotReadMessageIds);
            x.http().post(params, result);
        } else {
            ToastUtil.showShort(context, "请检查网络");
        }
    }
    /**
     *
     if (NetWorkHelper.checkNetworkState(context) == true) {

     } else {
     ToastUtil.showShort(context, "请检查网络");
     }
     */
    /**
     ToastUtil.showShort(mContext, msg);
     Intent i = new Intent(mContext, LoginActivity.class);
     startActivity(i);
     mContext.finish();
     */
}
