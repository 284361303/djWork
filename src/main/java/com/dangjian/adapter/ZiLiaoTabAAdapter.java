package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.LearningMterialsBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 学习资料中的党章党规Adapter
 */
public class ZiLiaoTabAAdapter extends RecyclerView.Adapter<ZiLiaoTabAAdapter.tabAVireHolder> {

    private Context mContext;
    private List<LearningMterialsBean.ListBean> mList;
    private LayoutInflater inflater;

    public ZiLiaoTabAAdapter(Context context, List<LearningMterialsBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }
    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public tabAVireHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_ziliaotab_a, parent, false);
        tabAVireHolder holder = new tabAVireHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final tabAVireHolder holder, final int position) {
        LearningMterialsBean.ListBean lists = mList.get(position);
        holder.itemZiLiaoTabATvTime.setText(lists.getCreateDate());
        holder.itemZiLiaoTabATvTitle.setText(lists.getTitle());
        String isRead = lists.getIsRead();
        if (isRead.equals("N")) {
            holder.itemZiLiaoTabATvNew.setVisibility(View.VISIBLE);
        } else {
            holder.itemZiLiaoTabATvNew.setVisibility(View.GONE);
        }

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClickListener(holder.itemView, position);
                }
            });
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

    public class tabAVireHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemZiLiaoTabA_tvNew)
        TextView itemZiLiaoTabATvNew;
        @Bind(R.id.itemZiLiaoTabA_tvTitle)
        TextView itemZiLiaoTabATvTitle;
        @Bind(R.id.itemZiLiaoTabA_tvTime)
        TextView itemZiLiaoTabATvTime;

        public tabAVireHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
