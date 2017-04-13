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
 * Created by sg-pc on 2016/12/1.
 */
public class ZZWorkContentAdapter extends RecyclerView.Adapter<ZZWorkContentAdapter.zzContentViewHolder> {

    private Context mContext;
    private List<LearningMterialsBean.ListBean> mList;
    private LayoutInflater inflater;

    public ZZWorkContentAdapter(Context mContext, List<LearningMterialsBean.ListBean> list) {
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
    public zzContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_zzwork_content, parent, false);
        zzContentViewHolder holder = new zzContentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final zzContentViewHolder holder, final int position) {
        LearningMterialsBean.ListBean lists = mList.get(position);
        String isRead = lists.getIsRead();
        holder.itemZZWorkContentTvTitle.setText(lists.getTitle());
        holder.itemZZWorkContentTvWork.setText(lists.getCreator());
        holder.itemZZWorkContentTvDate.setText(lists.getCreateDate());
        if (isRead.equals("Y")) {
            holder.itemZZWorkContentTvStates.setVisibility(View.GONE);
        } else {
            holder.itemZZWorkContentTvStates.setVisibility(View.VISIBLE);
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
        if (mList != null && mList.size() > 0) {
            return mList.size();
        } else {
            return 0;
        }
    }

    public class zzContentViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemZZWorkContent_tvTitle)
        TextView itemZZWorkContentTvTitle;
        @Bind(R.id.itemZZWorkContent_tvWork)
        TextView itemZZWorkContentTvWork;
        @Bind(R.id.itemZZWorkContent_tvDate)
        TextView itemZZWorkContentTvDate;
        @Bind(R.id.itemZZWorkContent_tvStates)
        TextView itemZZWorkContentTvStates;

        public zzContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
