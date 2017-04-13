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
 * Created by sg-pc on 2016/10/24.
 */
public class OrganizationMTitleAdapter extends RecyclerView.Adapter<OrganizationMTitleAdapter.OrganizationMViewHolder> {

    private Context mContext;
    private List<ZhiBuListBean.ListBean> mList;
    private LayoutInflater inflater;

    public OrganizationMTitleAdapter(Context context, List<ZhiBuListBean.ListBean> list) {
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
    public OrganizationMViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_poptitle_select, parent, false);
        OrganizationMViewHolder holder = new OrganizationMViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final OrganizationMViewHolder holder, final int position) {
        ZhiBuListBean.ListBean lists = mList.get(position);
        holder.itemPopTitleSelectTv.setText(lists.getDeptName());

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

    public class OrganizationMViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemPopTitleSelect_tv)
        TextView itemPopTitleSelectTv;

        public OrganizationMViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
