package com.dangjian.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.LearningPomoteBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 学习宣传的标题分类适配器
 * Created by sg-pc on 2016/10/11.
 */
public class LearingTitleAdapter extends RecyclerView.Adapter<LearingTitleAdapter.TitleViewHolder> {

    private Context mContext;
    private List<LearningPomoteBean.ListBean> mList;
    private LayoutInflater inflater;
    private int mFlag = 0;

    public LearingTitleAdapter(Context mContext, List<LearningPomoteBean.ListBean> list) {
        this.mContext = mContext;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    public void listenerFlag(int flag) {
        this.mFlag = flag;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_learing_title, parent, false);
        TitleViewHolder holder = new TitleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TitleViewHolder holder, final int position) {

        holder.itemLearingTitleView.setVisibility(View.INVISIBLE);
        holder.itemLearingTitleTv.setTextColor(Color.parseColor("#999999"));

        if (position == mFlag) {
            holder.itemLearingTitleView.setVisibility(View.VISIBLE);
            holder.itemLearingTitleTv.setTextColor(Color.parseColor("#333333"));
        }

        LearningPomoteBean.ListBean lists = mList.get(position);
        holder.itemLearingTitleTv.setText(lists.getName());

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

    public class TitleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemLearingTitle_tv)
        public TextView itemLearingTitleTv;
        @Bind(R.id.itemLearingTitle_view)
        public View itemLearingTitleView;

        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
