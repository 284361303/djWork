package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.MZListBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sg-pc on 2016/10/21.
 * 民族列表适配器
 */
public class SelectMZAdapter extends RecyclerView.Adapter<SelectMZAdapter.mzViewHolder> {

    private Context mContext;
    private List<MZListBean.ListBean> mList;
    private LayoutInflater inflater;
    private static final String TAG = "SelectMZAdapter";

    public SelectMZAdapter(Context mContext, List<MZListBean.ListBean> list) {
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
    public mzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_selectadministrator, parent, false);
        mzViewHolder holder = new mzViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final mzViewHolder holder, final int position) {
        MZListBean.ListBean lists = mList.get(position);
        holder.itemSelectadministratorTvName.setText(lists.getName());
        if (mList.size() == position + 1) {
            holder.itemSelectadministratorView.setVisibility(View.VISIBLE);
        } else {
            holder.itemSelectadministratorView.setVisibility(View.GONE);
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

    public class mzViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_selectadministrator_tvName)
        TextView itemSelectadministratorTvName;
        @Bind(R.id.item_selectadministrator_view)
        View itemSelectadministratorView;

        public mzViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
