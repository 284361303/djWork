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
 * 通知公告adapter
 * Created by sg-pc on 2016/10/10.
 */
public class informMessageAdapter extends RecyclerView.Adapter<informMessageAdapter.informViewHolder> {

    private Context mContext;
    private List<LearningMterialsBean.ListBean> mList;
    private LayoutInflater inflater;

    public informMessageAdapter(Context context, List<LearningMterialsBean.ListBean> list) {
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
    public informViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_informmessage, parent, false);
        informViewHolder holder = new informViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final informViewHolder holder, final int position) {
        LearningMterialsBean.ListBean lists = mList.get(position);
        holder.itemInformMessageTvTitle.setText(lists.getTitle());
        holder.itemInformMessageTvCreator.setText(lists.getCreator());
        holder.itemInformMessageTvDate.setText(lists.getCreateDate());
        String isRead = lists.getIsRead();
        if (isRead.equals("Y")) {
            holder.itemInformMessageTvStates.setVisibility(View.GONE);
        } else {
            holder.itemInformMessageTvStates.setVisibility(View.VISIBLE);
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

    public class informViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemInformMessage_tvTitle)
        TextView itemInformMessageTvTitle;
        @Bind(R.id.itemInformMessage_tvCreator)
        TextView itemInformMessageTvCreator;
        @Bind(R.id.itemInformMessage_tvDate)
        TextView itemInformMessageTvDate;
        @Bind(R.id.itemInformMessage_tvStates)
        TextView itemInformMessageTvStates;

        public informViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
