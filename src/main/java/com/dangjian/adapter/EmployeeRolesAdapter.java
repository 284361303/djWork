package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.EmployeeRolesBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 获得人员权限角色adapter
 * Created by shao_g on 2016/10/11.
 */
public class EmployeeRolesAdapter extends RecyclerView.Adapter<EmployeeRolesAdapter.RolesViewHolder> {

    private Context mContext;
    private List<EmployeeRolesBean.ListBean> mList;
    private LayoutInflater inflater;
    private static final String TAG = "SelectAdAdapter";

    public EmployeeRolesAdapter(Context context, List<EmployeeRolesBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RolesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_selectadministrator, parent, false);
        RolesViewHolder holder = new RolesViewHolder(view);
        return holder;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(final RolesViewHolder holder, final int position) {
        EmployeeRolesBean.ListBean lists = mList.get(position);
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

    public class RolesViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_selectadministrator_tvName)
        TextView itemSelectadministratorTvName;
        @Bind(R.id.item_selectadministrator_view)
        View itemSelectadministratorView;

        public RolesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
