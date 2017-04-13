package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.LearningMterialsBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sg-pc on 2016/10/12.
 * 组织工作adapter
 */
public class ZZWorkAdapter extends RecyclerView.Adapter<ZZWorkAdapter.ZZWorkViewHolder> {

    private Context mContext;
    private List<LearningMterialsBean.ListBean> mList;
    private LayoutInflater inflater;
    private View mHeaderView;

    public ZZWorkAdapter(Context mContext, List<LearningMterialsBean.ListBean> list) {
        this.mContext = mContext;
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
    public ZZWorkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_zzwork, parent, false);
        ZZWorkViewHolder holder = new ZZWorkViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ZZWorkViewHolder holder, final int position) {
        if (mList != null && mList.size() > 0) {
            LearningMterialsBean.ListBean lists = mList.get(position);
            String isRead = lists.getIsRead();
            holder.itemZZWorkTvTitle.setText(lists.getTitle());
            if (isRead.equals("Y")) {
                holder.itemZZWorkTvStates.setVisibility(View.GONE);
            } else {
                holder.itemZZWorkTvStates.setVisibility(View.VISIBLE);
            }
        }

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClickListener(holder.itemView, position - 1);
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

    public class ZZWorkViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemZZWork_iv)
        ImageView itemZZWorkIv;
        @Bind(R.id.itemZZWork_tvTitle)
        TextView itemZZWorkTvTitle;
        @Bind(R.id.itemZZWork_tvStates)
        TextView itemZZWorkTvStates;

        public ZZWorkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
