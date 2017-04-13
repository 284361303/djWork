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
 * 组织工作详情页
 * Created by sg-pc on 2016/12/1.
 */
public class ZZTitleDetailsAdapter extends RecyclerView.Adapter<ZZTitleDetailsAdapter.titleDetailsViewHolder> {

    private Context mContext;
    private List<LearningMterialsBean.ListBean> mList;
    private LayoutInflater inflater;

    public ZZTitleDetailsAdapter(Context mContext, List<LearningMterialsBean.ListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
    public titleDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_zzwork_content, parent, false);
        titleDetailsViewHolder holder = new titleDetailsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final titleDetailsViewHolder holder, final int position) {
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

    public class titleDetailsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemZZWorkContent_tvTitle)
        TextView itemZZWorkContentTvTitle;
        @Bind(R.id.itemZZWorkContent_tvWork)
        TextView itemZZWorkContentTvWork;
        @Bind(R.id.itemZZWorkContent_tvDate)
        TextView itemZZWorkContentTvDate;
        @Bind(R.id.itemZZWorkContent_tvStates)
        TextView itemZZWorkContentTvStates;

        public titleDetailsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
