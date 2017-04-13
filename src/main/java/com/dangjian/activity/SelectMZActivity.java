package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.adapter.SelectMZAdapter;
import com.dangjian.entity.MZListBean;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择民族
 */
public class SelectMZActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.selectMZ_recyclerView)
    RecyclerView selectMZRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<MZListBean.ListBean> listData = new ArrayList<MZListBean.ListBean>();
    private static final String TAG = "SelectMZActivity";
    private SelectMZAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mz);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getData();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("选择民族");
        linearLayoutManager = new LinearLayoutManager(this);
        selectMZRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void upDataView() {
        adapter = new SelectMZAdapter(mContext, listData);
        initListener();
        selectMZRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new SelectMZAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                try {
                    MZListBean.ListBean listBean = listData.get(position);
                    String code = listBean.getCode();
                    String name = listBean.getName();
                    Intent i = new Intent();
                    i.putExtra("code", code);
                    i.putExtra("name", name);
                    mContext.setResult(200, i);
                    mContext.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick(R.id.titleOneText_llBack)
    public void onClick() {
        mContext.finish();
    }

    /**
     * （11007）获取民族列表
     */
    private void getData() {
        Control.getMZList(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "民族列表: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (!status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        MZListBean mzListBean = gson.fromJson(result, MZListBean.class);
                        List<MZListBean.ListBean> list = mzListBean.getList();
                        if (list != null && list.size() > 0) {
                            listData.addAll(list);
                        }
                        adapter.notifyDataSetChanged();
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
}
