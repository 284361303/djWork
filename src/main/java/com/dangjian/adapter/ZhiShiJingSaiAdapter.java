package com.dangjian.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.TiKuLianXiBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 知识竞赛adapter
 * Created by sg-pc on 2016/9/30.
 */
public class ZhiShiJingSaiAdapter extends RecyclerView.Adapter<ZhiShiJingSaiAdapter.jingSaiViewHolder> {

    private Context mContext;
    private List<TiKuLianXiBean.ListBean> mList;
    private LayoutInflater inflater;

    public ZhiShiJingSaiAdapter(Context context, List<TiKuLianXiBean.ListBean> list) {
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
    public jingSaiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_jingsai, parent, false);
        jingSaiViewHolder holder = new jingSaiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final jingSaiViewHolder holder, final int position) {
        TiKuLianXiBean.ListBean lists = mList.get(position);
        holder.jingSaiTvTitle.setText(lists.getTitle());
        String status = lists.getStatus();      //ING 进行中,FINISHED已完成，NOTSTART没开始，DISABLED停用
        String isNew = lists.getIsNew();
        if (status.equals("进行中")) {
            holder.jingSaiTvStates.setText("进行中");
            holder.jingSaiTvTitle.setTextColor(Color.parseColor("#FF6446"));
            holder.jingSaiTvStates.setBackgroundResource(R.drawable.border_red);
            holder.jingSaiTvStates.setTextColor(Color.parseColor("#FF6446"));
            holder.jingSaiIv.setImageResource(R.drawable.circle_red);
        } else if (status.equals("未开始")) {
            holder.jingSaiTvStates.setText("未开始");
            holder.jingSaiTvTitle.setTextColor(Color.parseColor("#999999"));
            holder.jingSaiTvStates.setBackgroundResource(R.drawable.border_0f0);
            holder.jingSaiTvStates.setTextColor(Color.parseColor("#d4cecf"));
            holder.jingSaiIv.setImageResource(R.drawable.circle_gray);
        } else if (mList.get(position).getStatus().equals("已结束")) {
            holder.jingSaiTvStates.setText("已结束");
            holder.jingSaiTvTitle.setTextColor(Color.parseColor("#999999"));
            holder.jingSaiTvStates.setBackgroundResource(R.drawable.border_0f0);
            holder.jingSaiTvStates.setTextColor(Color.parseColor("#d4cecf"));
            holder.jingSaiIv.setImageResource(R.drawable.circle_gray);
        }
        holder.jingSaiTvTime.setText(lists.getStartDate());     //开始时间
        holder.jingSaiTvEndTime.setText(lists.getEndDate());    //结束时间
        if (isNew != null && !isNew.equals("") && isNew.equals("Y")) {
            holder.jingSaiTvIsNew.setVisibility(View.VISIBLE);
        } else {
            holder.jingSaiTvIsNew.setVisibility(View.INVISIBLE);
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

    public class jingSaiViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.jingSai_tvTitle)
        TextView jingSaiTvTitle;
        @Bind(R.id.jingSai_tvStates)
        TextView jingSaiTvStates;
        @Bind(R.id.jingSai_tvTime)
        TextView jingSaiTvTime;
        @Bind(R.id.jingSai_iv)
        ImageView jingSaiIv;
        @Bind(R.id.jingSai_tvIsNew)
        TextView jingSaiTvIsNew;
        @Bind(R.id.jingSai_tvEndTime)
        TextView jingSaiTvEndTime;

        public jingSaiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
