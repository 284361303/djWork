package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.entity.LearningMterialsBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 学习宣传中的内容适配器
 * Created by sg-pc on 2016/10/11.
 */
public class LearingContentAdapter extends RecyclerView.Adapter<LearingContentAdapter.ContentViewHolder> {

    private Context mContext;
    private List<LearningMterialsBean.ListBean> mList;
    private LayoutInflater inflater;

    public LearingContentAdapter(Context mContext, List<LearningMterialsBean.ListBean> list) {
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
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_learing_content, parent, false);
        ContentViewHolder holder = new ContentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ContentViewHolder holder, final int position) {
        LearningMterialsBean.ListBean lists = mList.get(position);
        String isRead = lists.getIsRead();
        String iconPath = lists.getIconPath();
        Glide.with(mContext).load(iconPath).error(R.drawable.all_default_image).crossFade().into(holder.learingContentIv);
        holder.learingContentTvTitle.setText(lists.getTitle());
        holder.learingContentTvZZ.setText(lists.getCreator());
        holder.learingContentTvDate.setText(lists.getCreateDate());
        if (isRead.equals("Y")) {
            holder.learingContentTvStates.setVisibility(View.GONE);
        } else {
            holder.learingContentTvStates.setVisibility(View.VISIBLE);
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

    public class ContentViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.learingContent_iv)
        ImageView learingContentIv;
        @Bind(R.id.learingContent_tvTitle)
        TextView learingContentTvTitle;
        @Bind(R.id.learingContent_tvZZ)
        TextView learingContentTvZZ;
        @Bind(R.id.learingContent_tvDate)
        TextView learingContentTvDate;
        @Bind(R.id.learingContent_tvStates)
        TextView learingContentTvStates;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
