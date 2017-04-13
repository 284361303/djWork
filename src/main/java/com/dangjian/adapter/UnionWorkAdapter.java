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
 * 工会适配器
 * Created by sg-pc on 2016/10/14.
 */
public class UnionWorkAdapter extends RecyclerView.Adapter<UnionWorkAdapter.UnionViewHolder> {

    private Context mContext;
    private List<LearningPomoteBean.ListBean> mList;
    private LayoutInflater inflater;
    private String mShowType;

    public UnionWorkAdapter(Context context, List<LearningPomoteBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public UnionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_unionwork, parent, false);
        UnionViewHolder holder = new UnionViewHolder(view);
        return holder;
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
    public void onBindViewHolder(final UnionViewHolder holder, final int position) {
        LearningPomoteBean.ListBean lists = mList.get(position);
        if (mShowType.equals("ICON")) {
            Glide.with(mContext).load(lists.getIconPath()).error(R.drawable.all_branch_head).into(holder.itemUnionWorkIv);
        }
        holder.itemUnionWorkTv.setText(lists.getName());
        String messCount = lists.getMessCount();        //未读消息数
        if (messCount != null && !messCount.equals("")) {
            if (Integer.parseInt(messCount) > 0) {
                holder.itemUnionWorkBtnMessCount.setVisibility(View.VISIBLE);
                holder.itemUnionWorkBtnMessCount.setText(messCount);
            } else {
                holder.itemUnionWorkBtnMessCount.setVisibility(View.INVISIBLE);
                holder.itemUnionWorkBtnMessCount.setText("0");
            }
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

    public class UnionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemUnionWork_iv)
        ImageView itemUnionWorkIv;
        @Bind(R.id.itemUnionWork_tv)
        TextView itemUnionWorkTv;
        @Bind(R.id.itemUnionWork_btnMessCount)
        Button itemUnionWorkBtnMessCount;

        public UnionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
