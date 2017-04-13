package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.entity.LearningPomoteBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sg-pc on 2016/10/13.
 */
public class YouThWorkTitleAdapter extends RecyclerView.Adapter<YouThWorkTitleAdapter.YouthTitleViewHolder> {

    private Context mContext;
    private List<LearningPomoteBean.ListBean> mList;
    private LayoutInflater inflater;
    private String mShowType = "";

    public YouThWorkTitleAdapter(Context context, List<LearningPomoteBean.ListBean> list) {
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

    public void setShowType(String showType) {
        this.mShowType = showType;
        notifyDataSetChanged();
    }

    @Override
    public YouthTitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_youthwork_title, parent, false);
        YouthTitleViewHolder holder = new YouthTitleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final YouthTitleViewHolder holder, final int position) {
        LearningPomoteBean.ListBean lists = mList.get(position);
        String messCount = lists.getMessCount();        //未读消息数
        if (mShowType.equals("ICON")) {
            Glide.with(mContext).load(lists.getIconPath()).error(R.drawable.all_branch_head).into(holder.itemYouthWorkTitleIv);
        }
        if (messCount != null && !messCount.equals("")) {
            if (Integer.parseInt(messCount) > 0) {
                holder.itemYouthWorkTitleBtnMessCount.setVisibility(View.VISIBLE);
                holder.itemYouthWorkTitleBtnMessCount.setText(messCount);
            } else {
                holder.itemYouthWorkTitleBtnMessCount.setVisibility(View.INVISIBLE);
                holder.itemYouthWorkTitleBtnMessCount.setText("0");
            }
        } else {
            holder.itemYouthWorkTitleBtnMessCount.setVisibility(View.INVISIBLE);
            holder.itemYouthWorkTitleBtnMessCount.setText("0");
        }
        holder.itemYouthWorkTitleTv.setText(lists.getName());

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

    public class YouthTitleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemYouthWorkTitle_iv)
        ImageView itemYouthWorkTitleIv;
        @Bind(R.id.itemYouthWorkTitle_tv)
        TextView itemYouthWorkTitleTv;
        @Bind(R.id.itemYouthWorkTitle_btnMessCount)
        Button itemYouthWorkTitleBtnMessCount;

        public YouthTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
