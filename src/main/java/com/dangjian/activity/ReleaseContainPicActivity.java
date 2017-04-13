package com.dangjian.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.adapter.PicAdapter;
import com.dangjian.adapter.PopTitleSelectAdapter;
import com.dangjian.entity.LearningPomoteBean;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.ImageTools;
import com.dangjian.utils.ImageUtil;
import com.dangjian.utils.ToastUtil;
import com.dangjian.widgets.PhotoPicker.PhotoPickerActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sg 2016年10月14日16:21:57
 * 发布页面，包含图片
 */
public class ReleaseContainPicActivity extends BaseActivity {

    @Bind(R.id.releasePic_llBack)
    LinearLayout releasePicLlBack;
    @Bind(R.id.releasePic_tvTitleName)
    TextView releasePicTvTitleName;
    @Bind(R.id.releasePic_llTitleSelect)
    LinearLayout releasePicLlTitleSelect;
    @Bind(R.id.releasePic_etTitle)
    EditText releasePicEtTitle;
    @Bind(R.id.releasePic_etContent)
    EditText releasePicEtContent;
    @Bind(R.id.releasePic_gridView)
    RecyclerView releasePicGridView;
    @Bind(R.id.releasePic_btnCancel)
    Button releasePicBtnCancel;
    @Bind(R.id.releasePic_btnSave)
    Button releasePicBtnSave;
    @Bind(R.id.releasePic_ivDown)
    ImageView releasePicIvDown;
    private GridLayoutManager gridLayoutManager;
    private PicAdapter adapter;
    private List<String> mResults = new ArrayList<String>();
    private List<LearningPomoteBean.ListBean> listTitledata = new ArrayList<LearningPomoteBean.ListBean>();
    private static final String TAG = "ReleaseCActivity";
    private int maxPic = 9, index = 0;
    private String mFilePath = "";
    private PopupWindow popupWindow;
    private PopTitleSelectAdapter titleAdapter;
    private String mCode = "", mTitleName = "";
    private String activity;
    private int photoIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_contain_pic);
        ButterKnife.bind(this);
        initView();
        getExtras();
        upDataPicView();
    }

    private void initView() {
        releasePicLlTitleSelect.setVisibility(View.GONE);
        gridLayoutManager = new GridLayoutManager(mContext, 5);
        releasePicGridView.setLayoutManager(gridLayoutManager);
    }

    private void getExtras() {
        try {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                activity = extras.getString("activity");
                if (activity.equals("XinDeWebActivity")) {      //学习心得
                    releasePicLlTitleSelect.setVisibility(View.VISIBLE);
                    releasePicEtTitle.setVisibility(View.GONE);
                    releasePicLlTitleSelect.setClickable(false);
                    releasePicIvDown.setVisibility(View.GONE);
                    mCode = "STUDYNOTE";
                    mTitleName = "学习心得";
                    releasePicTvTitleName.setText("学习心得");
                } else if (activity.equals("LearningPomoteActivity")) {       //学习宣传
                    LearningPomoteBean bean = (LearningPomoteBean) extras.getSerializable("LearningPomoteBean");
                    if (bean != null && bean.getList().size() > 0) {
                        if (listTitledata != null || listTitledata.size() > 0) {
                            listTitledata.clear();
                        }
                        int size = bean.getList().size();
                        if (size <= 1) {
                            releasePicLlTitleSelect.setClickable(false);
                        } else {
                            releasePicLlTitleSelect.setClickable(true);
                        }
                        List<LearningPomoteBean.ListBean> list = bean.getList();
                        listTitledata.addAll(list);
                        String name = bean.getList().get(0).getName();
                        mCode = bean.getList().get(0).getCode();
                        mTitleName = name;
                        releasePicTvTitleName.setText(name);
                    }
                    releasePicLlTitleSelect.setVisibility(View.VISIBLE);
                    showTitlePop();
                } else if (activity.equals("ZZWorkActivity")) {     //组织工作
                    ExtrasResult(extras);
                } else if (activity.equals("JiJianWorkActivity")) {     //纪检工作
                    ExtrasResult(extras);
                } else if (activity.equals("UnionWorkActivity")) {     //工会工作
                    mCode = extras.getString("mCode");
                    String titleName = extras.getString("titleName");
                    mTitleName = titleName;
                    releasePicTvTitleName.setText(titleName);
                    releasePicLlTitleSelect.setVisibility(View.VISIBLE);
                    releasePicLlTitleSelect.setClickable(false);
                    releasePicIvDown.setVisibility(View.GONE);
                } else if (activity.equals("YouthWorkActivity")) {      //青年工作
                    mCode = extras.getString("mCode");
                    String titleName = extras.getString("titleName");
                    mTitleName = titleName;
                    releasePicTvTitleName.setText(titleName);
                    releasePicLlTitleSelect.setVisibility(View.VISIBLE);
                    releasePicLlTitleSelect.setClickable(false);
                    releasePicIvDown.setVisibility(View.GONE);
                } else if (activity.equals("ZZWorkTitleDetailsActivity")) {     //组织工作的详情页面
                    mCode = extras.getString("mCode");
                    String titleName = extras.getString("titleName");
                    mTitleName = titleName;
                    releasePicTvTitleName.setText(titleName);
                    releasePicLlTitleSelect.setVisibility(View.VISIBLE);
                    releasePicLlTitleSelect.setClickable(false);
                    releasePicIvDown.setVisibility(View.GONE);
                } else if (activity.equals("ZhiBuFamilyActivity")) {    //支部天地
                    ExtrasResult(extras);
                } else if (activity.equals("XueXiZiLiaoActivity")) {    //学习资料
                    mCode = "PARTYCONSTITUTION";
                    mTitleName = "党章党规";
                    releasePicTvTitleName.setText("党章党规");
                    releasePicLlTitleSelect.setVisibility(View.VISIBLE);
                    showTitleSelect();
                } else if (activity.equals("ChatActivity")) {   //互动交流
                    mCode = "COMUNICATION";
                    mTitleName = "互动交流";
                    releasePicTvTitleName.setText("发帖");
                    releasePicLlTitleSelect.setVisibility(View.VISIBLE);
                    releasePicLlTitleSelect.setClickable(false);
                    releasePicIvDown.setVisibility(View.GONE);
                    showTitleSelect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收数据并显示
     */
    private void ExtrasResult(Bundle extras) {
        LearningPomoteBean bean = (LearningPomoteBean) extras.getSerializable("LearningPomoteBean");
        if (listTitledata != null && listTitledata.size() > 0) {
            listTitledata.clear();
        }
        int size = bean.getList().size();
        if (size <= 1) {
            releasePicLlTitleSelect.setClickable(false);
        } else {
            releasePicLlTitleSelect.setClickable(true);
        }
        List<LearningPomoteBean.ListBean> list = bean.getList();
        listTitledata.addAll(list);
        String name = bean.getList().get(0).getName();
        mCode = bean.getList().get(0).getCode();
        mTitleName = name;
        releasePicTvTitleName.setText(name);
        releasePicLlTitleSelect.setVisibility(View.VISIBLE);
        showTitlePop();
    }

    private void upDataPicView() {
        adapter = new PicAdapter(mContext, mResults);
        initListener();
        releasePicGridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new PicAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                int childCount = releasePicGridView.getChildCount();
                if (position == childCount - 1) {
                    showSelect();
                }
            }
        });
    }

    @OnClick({R.id.releasePic_llBack, R.id.releasePic_llTitleSelect, R.id.releasePic_btnCancel, R.id.releasePic_btnSave})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.releasePic_llBack:
                CheckBackNull();
                break;
            case R.id.releasePic_llTitleSelect:
                try {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    } else {
                        popupWindow.showAsDropDown(releasePicLlTitleSelect);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.releasePic_btnCancel:
                CheckBackNull();
                break;
            case R.id.releasePic_btnSave:       //发布按钮
                final String title = releasePicEtTitle.getText().toString().trim();
                final String content = releasePicEtContent.getText().toString().trim();
                if (!activity.equals("XinDeWebActivity")) {
                    if (TextUtils.isEmpty(title)) {
                        ToastUtil.showShort(mContext, "标题不能为空");
                        return;
                    }
                }
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.showShort(mContext, "内容不能为空");
                    return;
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setMessage("您确认要发布该信息到 \"" + mTitleName + "\" 吗 ？");
                dialog.setNegativeButton("取消", null);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showProgressDialog(mContext);
                        mFilePath = "";
                        index = 0;
                        int size = mResults.size();
                        if (size > 0) {
                            for (int j = 0; j < size; j++) {
                                String s = mResults.get(j);
                                UplodingPic(s, title, mCode, content);
                            }
                        } else {
                            SaveData(title, mCode, content, "");
                        }
                    }
                }).show();
                break;
        }
    }

    private void showSelect() {
        final android.app.AlertDialog userPictureDialog = new android.app.AlertDialog.Builder(mContext).create();
        userPictureDialog.setCanceledOnTouchOutside(true);
        userPictureDialog.show();
        Window window = userPictureDialog.getWindow();
        window.setContentView(R.layout.pop_select_photo);
        LinearLayout llPhoto = (LinearLayout) window.findViewById(R.id.popSelect_llPhoto);  //打开图库
        LinearLayout llCamera = (LinearLayout) window.findViewById(R.id.popSelect_llCamera);    //打开拍照
        llPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(mContext, PhotoPickerActivity.class);
                ii.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, false);
                ii.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, 1);
                ii.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, maxPic);
                startActivityForResult(ii, 108);
                userPictureDialog.cancel();
            }
        });

        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoIndex += 1;
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "dj.jpg" + photoIndex));
                iCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(iCamera, 110);
                userPictureDialog.cancel();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case 108:
                    if (data != null && !data.equals("")) {
                        ArrayList<String> lists = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
                        mResults.addAll(lists);
                        Log.i(TAG, "onActivityResult/listExtra: " + mResults);
                        adapter.notifyDataSetChanged();
                        //                    showResult(picList);
                        int size = lists.size();
                        if (size > 0) {
                            maxPic -= size;
                        }
                        Log.i(TAG, "onActivityResult/maxPic: " + maxPic);
                    }
                    break;
                case 110:
                    double SCALE = 5;// 照片缩小比例
                    Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/dj.jpg" + photoIndex);
                    if (bitmap.getHeight() > 500) {
                        SCALE = bitmap.getHeight() / 500.00;
                    } else {
                        SCALE = 1;
                    }
                    Bitmap newBitmap = ImageTools.zoomBitmap(bitmap, new Double(bitmap.getWidth() / SCALE).intValue(), new Double(bitmap.getHeight() / SCALE).intValue());
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                    String picName = "dangJian" + Config.randomTime();
                    ImageTools.saveMyBitmap(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), picName);
                    path += File.separator + picName + ".jpg";
                    mResults.add(path);
                    adapter.notifyDataSetChanged();
                    //                upDataPicView();
                    maxPic = maxPic - 1;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showResult(List<String> result) {
        mResults.addAll(result);
        adapter.notifyDataSetChanged();
    }

    /**
     * （13010）上传图片
     *
     * @param mPath    图片路径
     * @param mTitle   发布的标题
     * @param mContent 发布的内容
     * @param mType    类型
     */
    private void UplodingPic(String mPath, final String mTitle, final String mType, final String mContent) {
        final String newPath = ImageUtil.zipImage(mPath);
        Log.i(TAG, "UplodingPic/newPath: " + newPath);
        Control.uploadFileApp(mContext, newPath, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "onSuccess/上传: " + result);
                try {
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        index += 1;
                        //删除本地文件
                        File f = new File(newPath);
                        f.delete();
                        String filePath = jo.getString("filePath");
                        if (!mFilePath.equals("")) {
                            mFilePath += ";";
                        }
                        mFilePath += filePath;
                        Log.i(TAG, "onSuccess/mFilePath: " + mFilePath);
                        if (index == mResults.size()) {
                            index = 0;
                            SaveData(mTitle, mType, mContent, mFilePath);
                        }
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        ToastUtil.showShort(mContext, msg);
                    }
                    Log.i(TAG, "上传/index--: " + index + "  ,result-------" + result);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i(TAG, "onError: " + ex);
                ex.printStackTrace();
                ToastUtil.closeProgressDialog();
                ToastUtil.showShort(mContext, "服务器异常请重试");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                /*if (index == mResults.size()) {
                    ToastUtil.closeProgressDialog();
                }*/
            }
        });
    }

    /**
     * （13008）新增、编辑新闻
     */
    private void SaveData(String mTitle, String mType, final String mContent, String mImageList) {
        Control.saveNews(mContext, "", mTitle, mType, mContent, "", mImageList, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "发布: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status.equals("1")) {
                        if (mResults != null || mResults.size() > 0) {
                            mResults.clear();
                        }
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
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                ToastUtil.closeProgressDialog();
            }
        });
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
        titleAdapter = new PopTitleSelectAdapter(mContext, listTitledata);
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
                LearningPomoteBean.ListBean bean = listTitledata.get(position);
                String name = bean.getName();
                mCode = bean.getCode();
                mTitleName = name;
                releasePicTvTitleName.setText(name);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * 学习资料跳转过来的发布类型的头部筛选框显示
     */
    private void showTitleSelect() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View vv = inflater.inflate(R.layout.item_title_select, null);
        popupWindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        TextView tv = (TextView) vv.findViewById(R.id.titleSelect_tvSelect);        //支部排名
        TextView tvUser = (TextView) vv.findViewById(R.id.titleSelect_tvUser);      //个人排名
        tv.setText("党章党规");
        tvUser.setText("系列讲话");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCode = "PARTYCONSTITUTION";
                releasePicTvTitleName.setText("党章党规");
                mTitleName = "党章党规";
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        tvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCode = "PARTYSERIAL";
                releasePicTvTitleName.setText("系列讲话");
                mTitleName = "系列讲话";
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * 返回上一个页面时候进行非空校验
     */
    private void CheckBackNull() {
        String title1 = releasePicEtTitle.getText().toString().trim();
        String content1 = releasePicEtContent.getText().toString().trim();
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
