package com.dangjian.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.entity.UserBean;
import com.dangjian.utils.APIManager;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.ImageTools;
import com.dangjian.utils.ImageUtil;
import com.dangjian.utils.ToastUtil;
import com.dangjian.widgets.PhotoPicker.PhotoPickerActivity;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 用户信息详情页面
 */
public class UserDetailActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.userDetail_llRechangePic)
    LinearLayout userDetailLlRechangePic;
    @Bind(R.id.userDetail_tvUserName)
    TextView userDetailTvUserName;
    @Bind(R.id.userDetail_tvZhiBu)
    TextView userDetailTvZhiBu;
    @Bind(R.id.userDetail_tvSex)
    TextView userDetailTvSex;
    @Bind(R.id.userDetail_llSex)
    LinearLayout userDetailLlSex;
    @Bind(R.id.userDetail_tvBirth)
    TextView userDetailTvBirth;
    @Bind(R.id.userDetail_llBirth)
    LinearLayout userDetailLlBirth;
    @Bind(R.id.userDetail_tvNation)
    TextView userDetailTvNation;
    @Bind(R.id.userDetail_llNation)
    LinearLayout userDetailLlNation;
    @Bind(R.id.userDetail_tvInTime)
    TextView userDetailTvInTime;
    @Bind(R.id.userDetail_llInTime)
    LinearLayout userDetailLlInTime;
    private static final String TAG = "UserDetailActivity";
    @Bind(R.id.userDetail_ivPic)
    CircleImageView userDetailIvPic;
    @Bind(R.id.userDetail_tvNickName)
    TextView userDetailTvNickName;
    @Bind(R.id.userDetail_llNickName)
    LinearLayout userDetailLlNickName;
    private String mMZCode = "", intentType = "";
    private String nickName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_user_detail);
            ButterKnife.bind(this);
            initView();
//            getExtras();
            getMyInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        titleOneTextTvTitleName.setText("个人信息");
        userDetailTvUserName.setText(Config.mLoginName);
        userDetailTvZhiBu.setText(Config.mDeptCode);
        Glide.with(mContext).load(APIManager.ImagePath + Config.mIconPath).error(R.drawable.all_smallhead).into(userDetailIvPic);
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            UserBean user = (UserBean) extras.getSerializable("userBean");
            userDetailTvSex.setText(user.getSex());     //性别
            userDetailTvBirth.setText(user.getBirthDay());    //出生时间
            userDetailTvNation.setText(user.getMZ());    //民族
            userDetailTvInTime.setText(user.getJoinPartMonth());        //入党时间
        }
    }

    @OnClick({R.id.titleOneText_llBack, R.id.userDetail_llRechangePic, R.id.userDetail_llSex, R.id.userDetail_llBirth, R.id.userDetail_llNation, R.id.userDetail_llInTime, R.id.userDetail_llNickName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.userDetail_llRechangePic:     //去选择头像上传
                showSelect();
                break;
            case R.id.userDetail_llSex:     //性别
                showSexSelect();
                break;
            case R.id.userDetail_llBirth:   //出生日期
                intentType = "Birth";
                Intent i = new Intent(mContext, SelectDatePickerActivity.class);
                i.putExtra("activity", intentType);
                startActivityForResult(i, 202);
                break;
            case R.id.userDetail_llNation:  //民族
                Intent iNation = new Intent(mContext, SelectMZActivity.class);
                startActivityForResult(iNation, 200);
                break;
            case R.id.userDetail_llInTime:  //入党时间
                intentType = "InTime";
                Intent iIn = new Intent(mContext, SelectDatePickerActivity.class);
                iIn.putExtra("activity", intentType);
                startActivityForResult(iIn, 202);
                break;
            case R.id.userDetail_llNickName:    //修改昵称
                showChangeNickName(nickName);
                break;
        }
    }

    private void showSexSelect() {
        final AlertDialog userPictureDialog = new AlertDialog.Builder(mContext).create();
        userPictureDialog.setCanceledOnTouchOutside(true);
        userPictureDialog.show();
        Window window = userPictureDialog.getWindow();
        window.setContentView(R.layout.pop_select_sex);
        LinearLayout llMan = (LinearLayout) window.findViewById(R.id.popSelectSexSex_llMan);  //男
        LinearLayout llGirl = (LinearLayout) window.findViewById(R.id.popSelectSex_llGirl);    //女
        llMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyMyInfo("SEX", "1");
                userPictureDialog.cancel();
            }
        });
        llGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyMyInfo("SEX", "0");
                userPictureDialog.cancel();
            }
        });
    }

    private void showSelect() {
        final AlertDialog userPictureDialog = new AlertDialog.Builder(mContext).create();
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
                ii.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, 0);
                ii.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, 1);
                startActivityForResult(ii, 108);
                userPictureDialog.cancel();
            }
        });
        //  打开拍照
        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "dj.jpg"));
                iCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(iCamera, 109);
                iCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                userPictureDialog.cancel();
            }
        });
    }

    /**
     * 修改昵称弹窗
     *
     * @param mNickName 当前的昵称，没有就空
     */
    private void showChangeNickName(final String mNickName) {
        final AlertDialog userPictureDialog = new AlertDialog.Builder(mContext).create();
        userPictureDialog.setCanceledOnTouchOutside(true);
        userPictureDialog.show();
        Window window = userPictureDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);    //不加上这一行输入法就不会弹出来
        window.setContentView(R.layout.pop_change_nickname);
        final EditText et = (EditText) window.findViewById(R.id.popChangeNickName_et);
        Button btn = (Button) window.findViewById(R.id.popChangeNickName_btn);
        if (mNickName != null && !mNickName.equals("")) {
            et.setText(mNickName);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickName = et.getText().toString().trim();
                if (TextUtils.isEmpty(nickName)) {
                    ToastUtil.showShort(mContext, "昵称不能为空");
                    return;
                }
                modifyMyInfo("NICKNAME", nickName);
                userDetailTvNickName.setText(nickName);
                Config.mNickName = nickName;
                userPictureDialog.cancel();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (resultCode) {
                case 108:   //图库返回
                    if (data != null && !data.equals("")) {
                        List<String> listExtra = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
                        if (listExtra != null && listExtra.size() > 0) {
                            String path = listExtra.get(0);
                            uploadPic(path, "ICONPATH");
                        }
                    }
                    break;
                case 200:   //接收选择的民族回调进行显示
                    if (data != null && !data.equals("")) {
                        mMZCode = data.getStringExtra("code");
                        String name = data.getStringExtra("name");
                        userDetailTvNation.setText(name);
                        Log.i(TAG, "onActivityResult/code: " + mMZCode);
                        Log.i(TAG, "onActivityResult/name: " + name);
                        modifyMyInfo("MZ", mMZCode);
                    }
                    break;
                case 202:   //选择的出生年月回调
                    if (data != null && !data.equals("")) {
                        String mMonth = data.getStringExtra("mMonth");
                        String mDay = data.getStringExtra("mDay");
                        Log.i(TAG, "接收的日期为: " + intentType + " ,---" + mMonth + "-" + mDay);
                        if (!TextUtils.isEmpty(mMonth) && !TextUtils.isEmpty(mDay)) {
                            if (intentType.equals("InTime")) {  //入党时间
                                userDetailTvInTime.setText(mDay + "-" + mMonth);
                                modifyMyInfo("JOINPARTMONTH", mDay + "-" + mMonth);
                            } else if (intentType.equals("Birth")) {
                                userDetailTvBirth.setText(mMonth + "-" + mDay);
                                modifyMyInfo("BIRTHDAY", mMonth + "-" + mDay);
                            }
                        }
                    }
                    break;
            }
            if (requestCode == 109) {   //拍照完回调并进行上传
                double SCALE = 5;// 照片缩小比例
                Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/dj.jpg");
                if (bitmap.getHeight() > 500) {
                    SCALE = bitmap.getHeight() / 500.00;
                } else {
                    SCALE = 1;
                }
                Bitmap newBitmap = ImageTools.zoomBitmap(bitmap, new Double(bitmap.getWidth() / SCALE).intValue(), new Double(bitmap.getHeight() / SCALE).intValue());
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                String picName = "dangJian";
                ImageTools.saveMyBitmap(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), picName);
                path += File.separator + picName + ".jpg";
                uploadPic(path, "ICONPATH");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * （13010）上传图片
     *
     * @param path 图片路径
     */
    private void uploadPic(String path, final String type) {
        final String newPath = ImageUtil.zipImage(path);
        Control.uploadFileApp(mContext, newPath, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "上传图片: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        String filePath = jo.getString("filePath");
                        File f = new File(newPath);
                        f.delete();
                        modifyMyInfo(type, filePath);
                        Config.mIconPath = filePath;
                        Glide.with(mContext).load(APIManager.ImagePath + filePath).error(R.drawable.all_smallhead).into(userDetailIvPic);
                        Log.i(TAG, "上传图片/onSuccess-- : " + APIManager.ImagePath);
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
     * （11003）修改我的信息
     *
     * @param mType  //类型    类型：SEX性别（1为男，0为女），BIRTHDAY生日(格式YYYY-MM-DD),JOINPARTMONTH入党年月,MZ民族，ICONPATH图像
     * @param mValue //修改的值
     */
    private void modifyMyInfo(final String mType, final String mValue) {
        Control.modifyMyInfo(mContext, mType, mValue, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "修改: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        if (mType.equals("SEX")) {
                            if (mValue.equals("1")) {
                                userDetailTvSex.setText("男");
                            } else {
                                userDetailTvSex.setText("女");
                            }
                        }
                        ToastUtil.showShort(mContext, msg);
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
     * （11002）获得我的个人信息数据
     */
    private void getMyInfo() {
        Control.getMyInfo(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "个人信息: " + result);
                    JSONObject jo = new JSONObject(result);
                    userDetailTvSex.setText(jo.getString("sex"));     //性别
                    userDetailTvBirth.setText(jo.getString("birthDay"));    //出生时间
                    userDetailTvNation.setText(jo.getString("MZ"));    //民族
                    userDetailTvInTime.setText(jo.getString("joinPartMonth"));        //入党时间
                    nickName = jo.getString("nickName");
                    if (nickName != null && !nickName.equals("")) {
                        userDetailTvNickName.setText(nickName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
