package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.ZhiBuListBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sg-pc on 2016/9/29.
 *
 */
public class SelectZhiBuAdapter extends RecyclerView.Adapter<SelectZhiBuAdapter.selectZhiBViewHolder> {

    private Context mContext;
    private List<ZhiBuListBean.ListBean> mList;
    private LayoutInflater inflater;
    private static final String TAG = "SelectAdAdapter";

    public SelectZhiBuAdapter(Context context, List<ZhiBuListBean.ListBean> list) {
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
    public selectZhiBViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_selectadministrator, parent, false);
        selectZhiBViewHolder holder = new selectZhiBViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final selectZhiBViewHolder holder, final int position) {
        ZhiBuListBean.ListBean lists = mList.get(position);
        String deptName = lists.getDeptName();
        holder.itemSelectadministratorTvName.setText(deptName);
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

    public class selectZhiBViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_selectadministrator_tvName)
        TextView itemSelectadministratorTvName;
        @Bind(R.id.item_selectadministrator_view)
        View itemSelectadministratorView;

        public selectZhiBViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
