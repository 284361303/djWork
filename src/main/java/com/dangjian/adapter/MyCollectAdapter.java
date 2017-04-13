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
import com.dangjian.entity.MyCollectBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的收藏适配器
 * Created by sg-pc on 2016/10/19.
 */
public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.myCollectViewHolder> {

    private Context mContext;
    private List<MyCollectBean.ListBean> mList;
    private LayoutInflater inflater;

    public MyCollectAdapter(Context context, List<MyCollectBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public myCollectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_mycollect, parent, false);
        myCollectViewHolder viewHolder = new myCollectViewHolder(view);
        return viewHolder;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(final myCollectViewHolder holder, final int position) {
        MyCollectBean.ListBean lists = mList.get(position);
        String iconPath = lists.getIconPath();
        Glide.with(mContext).load(iconPath).error(R.drawable.all_default_image).into(holder.mycollectIv);
        holder.mycollectTvTitle.setText(lists.getTitle());
        holder.mycollectTvZZ.setText(lists.getAuthor());
        holder.mycollectTvDate.setText(lists.getCreateDate());

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

    public class myCollectViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.mycollect_iv)
        ImageView mycollectIv;
        @Bind(R.id.mycollect_tvTitle)
        TextView mycollectTvTitle;
        @Bind(R.id.mycollect_tvZZ)
        TextView mycollectTvZZ;
        @Bind(R.id.mycollect_tvDate)
        TextView mycollectTvDate;

        public myCollectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
