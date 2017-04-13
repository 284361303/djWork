package com.dangjian.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.adapter.PopTitleSelectAdapter;
import com.dangjian.entity.LearningPomoteBean;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sg  2016-10-4 20:53:22
 * 发布页面
 */
public class ReleaseActivity extends BaseActivity {

    @Bind(R.id.release_llBack)
    LinearLayout releaseLlBack;
    @Bind(R.id.release_tvTitleName)
    TextView releaseTvTitleName;
    @Bind(R.id.release_llTitleSelect)
    LinearLayout releaseLlTitleSelect;
    @Bind(R.id.release_etTitle)
    EditText releaseEtTitle;
    @Bind(R.id.release_etRole)
    EditText releaseEtRole;
    @Bind(R.id.release_llSelect)
    LinearLayout releaseLlSelect;
    @Bind(R.id.release_etContent)
    EditText releaseEtContent;
    @Bind(R.id.release_btnCancel)
    Button releaseBtnCancel;
    @Bind(R.id.release_btnSave)
    Button releaseBtnSave;
    private String rolesCode = "";
    private List<LearningPomoteBean.ListBean> listTitleData = new ArrayList<LearningPomoteBean.ListBean>();
    private static final String TAG = "ReleaseActivity";
    private String mCode = "", mTitleName = "";
    private PopupWindow popupWindow;
    private PopTitleSelectAdapter titleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);
        initView();
        getExtras();
    }

    private void initView() {
//        releaseTvTitleName.setText("通知公告");
        releaseEtRole.setKeyListener(null);
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            LearningPomoteBean bean = (LearningPomoteBean) extras.getSerializable("LearningPomoteBean");
            if (bean != null) {
                if (listTitleData != null && listTitleData.size() > 0) {
                    listTitleData.clear();
                }
                int size = bean.getList().size();
                if (size <= 1) {
                    releaseLlTitleSelect.setClickable(false);
                } else {
                    releaseLlTitleSelect.setClickable(true);
                }
                List<LearningPomoteBean.ListBean> list = bean.getList();
                listTitleData.addAll(list);
                String name = bean.getList().get(0).getName();
                mCode = bean.getList().get(0).getCode();
                mTitleName = name;
                releaseTvTitleName.setText(name);
                showTitlePop();
            }
        }
    }

    /**
     * 点击标题选择发布类型弹窗
     */
    private void showTitlePop() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View vv = inflater.inflate(R.layout.pop_title_select, null);
        popupWindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        RecyclerView titleRecyclerView = (RecyclerView) vv.findViewById(R.id.popTitleSelect_recyclerView);
        titleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        titleAdapter = new PopTitleSelectAdapter(mContext, listTitleData);
        initTitleListener();
        titleRecyclerView.setAdapter(titleAdapter);
        titleAdapter.notifyDataSetChanged();
    }

    /**
     * 弹窗中的item点击事件，也就是选择发布累类型item
     */
    private void initTitleListener() {
        titleAdapter.setOnItemClickListener(new PopTitleSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LearningPomoteBean.ListBean bean = listTitleData.get(position);
                String name = bean.getName();
                mCode = bean.getCode();
                mTitleName = name;
                releaseTvTitleName.setText(name);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    @OnClick({R.id.release_llBack, R.id.release_llTitleSelect, R.id.release_llSelect, R.id.release_btnCancel, R.id.release_btnSave})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.release_llBack:
                CheckBackNull();
                break;
            case R.id.release_llTitleSelect:        //标题发布类型
                try {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    } else {
                        popupWindow.showAsDropDown(releaseLlTitleSelect);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.release_llSelect:     //选择角色
                Intent i = new Intent(mContext, EmployeeRolesActivity.class);
                startActivityForResult(i, 105);
                break;
            case R.id.release_btnCancel:        //取消
                CheckBackNull();
                break;
            case R.id.release_btnSave:      //发布
                final String title = releaseEtTitle.getText().toString().trim();
                String role = releaseEtRole.getText().toString().trim();
                final String content = releaseEtContent.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    ToastUtil.showShort(mContext, "标题不能为空");
                    return;
                }
                if (TextUtils.isEmpty(role)) {
                    ToastUtil.showShort(mContext, "角色不能为空");
                    return;
                }
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.showShort(mContext, "内容不能为空");
                    return;
                }
                Log.i(TAG, "onClick/title: " + title + " ,mRoelType---" + role + " ,content---" + content);
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setMessage("您确认要发布该信息到 \"" + mTitleName + "\" 吗 ？");
                dialog.setNegativeButton("取消", null);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showProgressDialog(mContext);
                        SaveData(title, mCode, content, rolesCode);
                    }
                }).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 105) {
            String rolesName = data.getStringExtra("name");
            rolesCode = data.getStringExtra("code");
            releaseEtRole.setText(rolesName);
        }
    }

    /**
     * （13008）新增、编辑新闻
     */
    private void SaveData(String mTitle, String mType, final String mContent, String mRoelType) {
        Log.i(TAG, "SaveData/title: " + mTitle + " ,mRoelType---" + rolesCode + " ,content---" + mContent);
        Control.saveNews(mContext, "", mTitle, mType, mContent, mRoelType, "", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "发布: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status.equals("1")) {
                        ToastUtil.showShort(mContext, msg);
                        setResult(200);
                        mContext.finish();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        ToastUtil.showShort(mContext, msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Log.i(TAG, "onError: ");
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                Log.i(TAG, "onFinished: ");
                ToastUtil.closeProgressDialog();
            }
        });
    }

    /**
     * 返回上一个页面时候进行非空校验
     */
    private void CheckBackNull() {
        String title1 = releaseEtTitle.getText().toString().trim();
        String content1 = releaseEtContent.getText().toString().trim();
        if (TextUtils.isEmpty(title1) && TextUtils.isEmpty(content1)) {
            mContext.finish();
        } else {
            showFinishWindow();
        }
    }

    /**
     * 返回上一个页面时候，未发布时候就提示
     */
    private void showFinishWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage("确定退出发布吗？");
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mContext.finish();
            }
        }).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            CheckBackNull();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
