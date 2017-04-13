package com.dangjian.adapter;

import android.content.Context;
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
 * 发布页面头部筛选发布类型使用适配器
 * Created by sg-pc on 2016/10/17.
 */
public class PopTitleSelectAdapter extends RecyclerView.Adapter<PopTitleSelectAdapter.TitleSelectViewHolder> {

    private Context mContext;
    private List<LearningPomoteBean.ListBean> mList;
    private LayoutInflater inflater;

    public PopTitleSelectAdapter(Context context, List<LearningPomoteBean.ListBean> list) {
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
    public TitleSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_poptitle_select, parent, false);
        TitleSelectViewHolder holder = new TitleSelectViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TitleSelectViewHolder holder, final int position) {
        LearningPomoteBean.ListBean lists = mList.get(position);
        holder.itemPopTitleSelectTv.setText(lists.getName());

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

    public class TitleSelectViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemPopTitleSelect_tv)
        TextView itemPopTitleSelectTv;

        public TitleSelectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
