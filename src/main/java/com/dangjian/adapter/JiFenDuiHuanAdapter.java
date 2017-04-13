package com.dangjian.adapter;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.activity.JiFenDuiHuanActivity;
import com.dangjian.entity.JiFenDuiHuanBean;
import com.dangjian.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 积分兑换审核adapter
 * Created by sg-pc on 2016/9/29.
 */
public class JiFenDuiHuanAdapter extends RecyclerView.Adapter<JiFenDuiHuanAdapter.jiFenViewHolder> {

    private Context mContext;
    private List<JiFenDuiHuanBean.ListBean> mList;
    private LayoutInflater inflater;
    private JiFenDuiHuanActivity mActivity;

    public JiFenDuiHuanAdapter(Context context, List<JiFenDuiHuanBean.ListBean> list, JiFenDuiHuanActivity activity) {
        this.mContext = context;
        this.mList = list;
        this.mActivity = activity;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public jiFenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_jifen_duihuan, parent, false);
        jiFenViewHolder holder = new jiFenViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(jiFenViewHolder holder, int position) {
        try {
            final JiFenDuiHuanBean.ListBean lists = mList.get(position);
            String iconPath = lists.getIconPath();
            String status = lists.getStatus();  //1ING:申请中,2FINISHED:已兑换，3REJECT:拒接
            Glide.with(mContext).load(iconPath).error(R.drawable.all_smallhead).into(holder.itemJiFenUserPic);
            holder.itemJiFenTvName.setText(lists.getLoginName());
            holder.itemJiFenTvTime.setText(lists.getApplyDate());
            if (status != null && !status.equals("") && status.equals("1ING")) {
                holder.itemJiFenBtnTrue.setText("确认");
                holder.itemJiFenBtnTrue.setBackgroundResource(R.drawable.shi_btn_red);
                holder.itemJiFenBtnTrue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showPw(lists);
                    }
                });
            } else if (status.equals("2FINISHED")) {
                holder.itemJiFenBtnTrue.setText(lists.getStatusName());
                holder.itemJiFenBtnTrue.setBackgroundResource(R.drawable.shi_btn_ecf);
            } else if (status.equals("3REJECT")) {
                holder.itemJiFenBtnTrue.setText(lists.getStatusName());
                holder.itemJiFenBtnTrue.setBackgroundResource(R.drawable.shi_btn_ecf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null && !mList.equals("")) {
            return mList.size();
        } else {
            return 0;
        }
    }

    public class jiFenViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemJiFen_userPic)
        CircleImageView itemJiFenUserPic;
        @Bind(R.id.itemJiFen_tvName)
        TextView itemJiFenTvName;
        @Bind(R.id.itemJiFen_tvTime)
        TextView itemJiFenTvTime;
        @Bind(R.id.itemJiFen_btnTrue)
        Button itemJiFenBtnTrue;

        public jiFenViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void showPw(final JiFenDuiHuanBean.ListBean lists) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pw_jifen_duihuan, null);
        final PopupWindow popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(view);
        ImageView ivClose = (ImageView) view.findViewById(R.id.pwJiFenDuiHuan_ivClose);  //右上角关闭按钮
        TextView tvName = (TextView) view.findViewById(R.id.pwJiFenDuiHuan_ivName);      //名字
        TextView zuZhi = (TextView) view.findViewById(R.id.pwJiFenDuiHuan_ivZuZhi);      //所在组织机构
        TextView tvSum = (TextView) view.findViewById(R.id.pwJiFenDuiHuan_ivSum);        //剩余积分
        final EditText input = (EditText) view.findViewById(R.id.pwJiFenDuiHuan_etInput);      //输入框
        Button btnTrue = (Button) view.findViewById(R.id.pwJiFenDuiHuan_btnTrue);        //提交按钮
        Button btnCancel = (Button) view.findViewById(R.id.pwJiFenDuiHuan_btnCancel);        //拒绝按钮
        tvName.setText(lists.getLoginName());       //登录名称
        zuZhi.setText(lists.getDeptName());     //部门名称
        tvSum.setText(lists.getUsablePoints());     //可用积分
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        btnTrue.setOnClickListener(new View.OnClickListener() {     //兑换
            @Override
            public void onClick(View view) {
                String inputResult = input.getText().toString().trim();
                if (TextUtils.isEmpty(inputResult)) {
                    ToastUtil.showShort(mContext, "请输入积分数");
                    return;
                }
                mActivity.approveApply(lists.getApplyId(), "Y", inputResult);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {   //拒绝按钮
            @Override
            public void onClick(View view) {
                String inputResult = input.getText().toString().trim();
                if (TextUtils.isEmpty(inputResult)) {
                    ToastUtil.showShort(mContext, "请输入积分数");
                    return;
                }
                mActivity.approveApply(lists.getApplyId(), "N", inputResult);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }
}
