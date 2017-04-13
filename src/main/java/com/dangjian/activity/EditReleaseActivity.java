package com.dangjian.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.adapter.EditReleasePicAdapter;
import com.dangjian.entity.EditReleaseBean;
import com.dangjian.utils.APIManager;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.ImageTools;
import com.dangjian.utils.ImageUtil;
import com.dangjian.utils.NetWorkHelper;
import com.dangjian.utils.ToastUtil;
import com.dangjian.widgets.PhotoPicker.PhotoPickerActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改发布的内容
 */
public class EditReleaseActivity extends BaseActivity {

    @Bind(R.id.editRelease_llBack)
    LinearLayout editReleaseLlBack;
    @Bind(R.id.editRelease_tvTitleName)
    TextView editReleaseTvTitleName;
    @Bind(R.id.editRelease_llTitleSelect)
    LinearLayout editReleaseLlTitleSelect;
    @Bind(R.id.editRelease_etTitle)
    EditText editReleaseEtTitle;
    @Bind(R.id.editRelease_etContent)
    EditText editReleaseEtContent;
    @Bind(R.id.editRelease_gridView)
    RecyclerView editReleaseGridView;
    @Bind(R.id.editRelease_btnDetele)
    Button editReleaseBtnDetele;
    @Bind(R.id.editRelease_btnSave)
    Button editReleaseBtnSave;
    @Bind(R.id.editRelease_llGridView)
    LinearLayout editReleaseLlGridView;
    private GridLayoutManager gridLayoutManager;
    private String newsId = "";
    private List<String> newLists = new ArrayList<String>();
    private List<String> fileSize = new ArrayList<String>();    //上传服务器图片的张数统计
    private List<String> imgListSize = new ArrayList<String>();
    private static final String TAG = "EditReleaseActivity";
    private int maxPic = 9, index = 0;
    private EditReleasePicAdapter adapter;
    private String mTypes = "";
    private int photoIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_release);
        ButterKnife.bind(this);
        initView();
        getExtras();
        getData();
    }

    private void initView() {
        gridLayoutManager = new GridLayoutManager(mContext, 5);
        editReleaseGridView.setLayoutManager(gridLayoutManager);
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String titleName = extras.getString("titleName");
            newsId = extras.getString("newsId");
            editReleaseTvTitleName.setText(titleName);
            String activity = extras.getString("activity");
            if (activity != null && activity.equals("InformMessageActivity")) {
                editReleaseLlGridView.setVisibility(View.INVISIBLE);
            } else {
                editReleaseLlGridView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void upDataPicView() {
        adapter = new EditReleasePicAdapter(mContext, newLists);
        initListener();
        editReleaseGridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new EditReleasePicAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                int childCount = editReleaseGridView.getChildCount();
                if (position == childCount - 1) {
                    showSelect();
                } else {
                    int size = newLists.size();
                    if (size > 0) {
                        maxPic = maxPic + 1;
                    }
                    newLists.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
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
                        ArrayList<String> listExtra = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
                        Log.i(TAG, "onActivityResult-----------: " + listExtra.size());
                        newLists.addAll(listExtra);
                        Log.i(TAG, "onActivityResult/newLists: " + newLists);
                        upDataPicView();
                        int size = listExtra.size();
                        if (size > 0) {
                            maxPic -= size;
                        }
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
                    newLists.add(path);
                    upDataPicView();
                    maxPic = maxPic - 1;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.editRelease_llBack, R.id.editRelease_llTitleSelect, R.id.editRelease_btnDetele, R.id.editRelease_btnSave})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editRelease_llBack:
                CheckBackNull();
                break;
            case R.id.editRelease_llTitleSelect:    //标题选择
                break;
            case R.id.editRelease_btnDetele:    //删除按钮
                showDeteleWindow();
                break;
            case R.id.editRelease_btnSave:      //发布按钮
                if (imgListSize != null && imgListSize.size() > 0) {
                    imgListSize.clear();
                }
                final String title = editReleaseEtTitle.getText().toString().trim();
                final String content = editReleaseEtContent.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    ToastUtil.showShort(mContext, "标题不能为空");
                    return;
                }
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.showShort(mContext, "内容不能为空");
                    return;
                }
                if (fileSize != null && fileSize.size() > 0) {
                    fileSize.clear();
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setMessage("您确认要提交该信息？");
                dialog.setNegativeButton("取消", null);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int m) {
                        String filePath = "";
                        ToastUtil.showProgressDialog(mContext);
                        if (newLists != null && newLists.size() > 0) {
                            for (int i = 0; i < newLists.size(); i++) {
                                String s = newLists.get(i);
                                if (s.toLowerCase().startsWith("http:")) {
                                    s = s.substring(APIManager.ImagePath.length());
                                    imgListSize.add(s);
                                } else {
                                    UplodingPicSync(s, title, content);
                                }
                            }
                            if (imgListSize.size() == newLists.size()) {
                                filePath = "";
                                for (int i = 0; i < imgListSize.size(); i++) {
                                    if (i > 0) {
                                        filePath = filePath + ";";
                                    }
                                    filePath = filePath + imgListSize.get(i);
                                }
                                imgListSize.clear();
                                SaveData(title, mTypes, content, filePath);
                            }
                        } else {
                            SaveData(title, mTypes, content, "");
                        }
                    }
                }).show();
                break;
        }
    }

    /**
     * (13011）编辑新闻通知
     */
    private void getData() {
        if (newLists != null && newLists.size() > 0) {
            newLists.clear();
        }
        Control.getNewsContent(mContext, newsId, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "编辑新闻通知: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        EditReleaseBean editReleaseBean = gson.fromJson(result, EditReleaseBean.class);
                        editReleaseEtTitle.setText(editReleaseBean.getTitle());
                        editReleaseEtContent.setText(editReleaseBean.getContent());
                        mTypes = editReleaseBean.getType();
                        List<EditReleaseBean.ImageListBean> imageList = editReleaseBean.getImageList();
                        for (int i = 0; i < imageList.size(); i++) {
                            String imagePath = imageList.get(i).getImagePath();
                            if (!imagePath.equals("")) {
                                newLists.add(APIManager.ImagePath + imagePath);
                            }
                        }
                        int size = newLists.size();
                        if (size > 0) {
                            maxPic -= size;
                        }
                        upDataPicView();
                        adapter.notifyDataSetChanged();
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
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                ToastUtil.closeProgressDialog();
            }
        });
    }

    /**
     * 返回上一个页面时候进行非空校验
     */
    private void CheckBackNull() {
        String title1 = editReleaseEtTitle.getText().toString().trim();
        String content1 = editReleaseEtContent.getText().toString().trim();
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

    /**
     * （13010）上传图片同步
     *
     * @param mPath 图片路径
     */
    private void UplodingPicSync(final String mPath, final String mTitle, final String mContent) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    if (NetWorkHelper.checkNetworkState(mContext) == true) {
                        final String newPath = ImageUtil.zipImage(mPath);
                        RequestParams params = new RequestParams(APIManager.uploadFileApp + ";jsessionid=" + Config.mCookie);
                        params.setMultipart(true);
                        params.addBodyParameter("file", new File(newPath));//设置上传的文件路径
                        String result = x.http().postSync(params, String.class);
                        JSONObject jo = new JSONObject(result);
                        String status = jo.getString("status");
                        String msg = jo.getString("msg");
                        if (status != null && !status.equals("") && status.equals("1")) {
                            //删除本地文件
                            File f = new File(newPath);
                            f.delete();
                            String filePath = jo.getString("filePath");
                            imgListSize.add(filePath);
                            if (imgListSize.size() == newLists.size()) {
                                filePath = "";
                                for (int i = 0; i < imgListSize.size(); i++) {
                                    if (i > 0) {
                                        filePath = filePath + ";";
                                    }
                                    filePath = filePath + imgListSize.get(i);
                                }
                                imgListSize.clear();
                                SaveData(mTitle, mTypes, mContent, filePath);
                            }
                        } else if (status.equals("9")) {
                            ToastUtil.showShort(mContext, msg);
                            Intent i = new Intent(mContext, LoginActivity.class);
                            startActivity(i);
                            mContext.finish();
                        } else {
                            ToastUtil.showShort(mContext, msg);
                        }
                    } else {
                        ToastUtil.showShort(mContext, "请检查网络");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * （13008）新增、编辑新闻
     */
    private void SaveData(String mTitle, String mType, final String mContent, String mImageList) {
        Control.saveNews(mContext, newsId, mTitle, mType, mContent, "", mImageList, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "新增、编辑新闻: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status.equals("1")) {
                        if (newLists != null || newLists.size() > 0) {
                            newLists.clear();
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
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.closeProgressDialog();
                ToastUtil.showShort(mContext, "服务器异常请重试");
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
     * （13009）删除新闻、评论
     */
    private void deteleRelease() {
        Log.i(TAG, "删除新闻/id---: " + newsId);
        Control.deleteContent(mContext, newsId, "NEWS", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "删除发布: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        mContext.finish();
                        ToastUtil.showShort(mContext, msg);
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
     * 返回上一个页面时候，未发布时候就提示
     */
    private void showDeteleWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage("确定删除？");
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deteleRelease();
                setResult(201);
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
