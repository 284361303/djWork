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
 * Created by sg-pc on 2016/12/1.
 */
public class ZZWorkTitleAdapter extends RecyclerView.Adapter<ZZWorkTitleAdapter.zzTitleViewHolder> {

    private Context mContext;
    private List<LearningPomoteBean.ListBean> mList;
    private LayoutInflater inflater;

    public ZZWorkTitleAdapter(Context context, List<LearningPomoteBean.ListBean> list) {
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
    public zzTitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_zzwork_title, parent, false);
        zzTitleViewHolder holder = new zzTitleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final zzTitleViewHolder holder, final int position) {
        LearningPomoteBean.ListBean lists = mList.get(position);
        String messCount = lists.getMessCount();        //未读消息数
        String iconPath = lists.getIconPath();
        String code = lists.getCode();
        if (iconPath != null && !iconPath.equals("")) {
            Glide.with(mContext).load(iconPath).error(R.drawable.all_branch_head).centerCrop().into(holder.itemzzWorkTitleIv);
        } else {
            if (code.equals("FZDY")) {
                Glide.with(mContext).load(lists.getIconPath()).error(R.drawable.organiza_developicon).centerCrop().into(holder.itemzzWorkTitleIv);
            } else if (code.equals("DFJN")) {
                Glide.with(mContext).load(lists.getIconPath()).error(R.drawable.organiza_painicon).centerCrop().into(holder.itemzzWorkTitleIv);
            } else if (code.equals("ORGJOB")) {
                Glide.with(mContext).load(lists.getIconPath()).error(R.drawable.organiza_knowledgeicon).centerCrop().into(holder.itemzzWorkTitleIv);
            }
        }
        if (messCount != null && !messCount.equals("")) {
            if (Integer.parseInt(messCount) > 0) {
                holder.itemzzWorkTitleBtnMessCount.setVisibility(View.VISIBLE);
                holder.itemzzWorkTitleBtnMessCount.setText(messCount);
            } else {
                holder.itemzzWorkTitleBtnMessCount.setVisibility(View.INVISIBLE);
                holder.itemzzWorkTitleBtnMessCount.setText("0");
            }
        } else {
            holder.itemzzWorkTitleBtnMessCount.setVisibility(View.INVISIBLE);
            holder.itemzzWorkTitleBtnMessCount.setText("0");
        }
        holder.itemzzWorkTitleTv.setText(lists.getName());


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

    public class zzTitleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemzzWorkTitle_iv)
        ImageView itemzzWorkTitleIv;
        @Bind(R.id.itemzzWorkTitle_tv)
        TextView itemzzWorkTitleTv;
        @Bind(R.id.itemzzWorkTitle_btnMessCount)
        Button itemzzWorkTitleBtnMessCount;

        public zzTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
