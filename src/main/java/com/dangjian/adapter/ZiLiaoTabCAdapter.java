package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.TiKuLianXiBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 题库练习adapter
 * Created by shao_g on 2016/10/6.
 */
public class ZiLiaoTabCAdapter extends RecyclerView.Adapter<ZiLiaoTabCAdapter.tabCViewHolder> {

    private Context mContext;
    private List<TiKuLianXiBean.ListBean> mList;
    private LayoutInflater inflater;

    public ZiLiaoTabCAdapter(Context mContext, List<TiKuLianXiBean.ListBean> list) {
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
    public tabCViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_ziliaotab_c, parent, false);
        tabCViewHolder holder = new tabCViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final tabCViewHolder holder, final int position) {
        TiKuLianXiBean.ListBean lists = mList.get(position);
        String isNew = lists.getIsNew();
        if (isNew != null && !isNew.equals("") && isNew.equals("Y")) {
            holder.itemZiLiaoTabCTvStates.setVisibility(View.VISIBLE);
        } else {
            holder.itemZiLiaoTabCTvStates.setVisibility(View.INVISIBLE);
        }
        holder.itemZiLiaoTabCTvTitle.setText(lists.getTitle());


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

    public class tabCViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemZiLiaoTabC_tvTitle)
        TextView itemZiLiaoTabCTvTitle;
        @Bind(R.id.itemZiLiaoTabC_tvStates)
        TextView itemZiLiaoTabCTvStates;

        public tabCViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
