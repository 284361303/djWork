package com.dangjian.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.AdministratorBean;
import com.dangjian.entity.EmployeeRolesBean;
import com.dangjian.utils.Config;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 选择权限adapter
 * Created by sg-pc on 2016/9/29.
 */
public class SelectAdministratorAdapter extends RecyclerView.Adapter<SelectAdministratorAdapter.selectAdViewHolder> {

    private Context mContext;
    private List<AdministratorBean.ListBean> mList;
    private LayoutInflater inflater;
    private static final String TAG = "SelectAdAdapter";
    public List<selectAdViewHolder> items = new ArrayList<selectAdViewHolder>();
    public List<EmployeeRolesBean.ListBean> mListSelect;
    private String mDeptId = "";

    public SelectAdministratorAdapter(Context context, List<AdministratorBean.ListBean> list, List<EmployeeRolesBean.ListBean> listSelect) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
        mListSelect = listSelect;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setmDeptId(String deptId) {
        this.mDeptId = deptId;
        Log.i(TAG, "setmDeptId/mDeptId: " + mDeptId);
    }

    @Override
    public selectAdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_quanxian, parent, false);
        selectAdViewHolder holder = new selectAdViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final selectAdViewHolder holder, final int position) {
        final AdministratorBean.ListBean lists = mList.get(position);
        String code = lists.getCode();
        holder.itemQuanXianTvName.setText(lists.getName());
        if (mList.size() == position + 1) {
            holder.itemQuanXianView.setVisibility(View.VISIBLE);
        } else {
            holder.itemQuanXianView.setVisibility(View.GONE);
        }
        items.add(holder);
        holder.itemQuanXianIvStates.setClickable(false);
        /*holder.itemQuanXianIvStates.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.itemQuanXianIvStates != null) {
                    holder.itemQuanXianIvStates.setChecked(b);
                }
            }
        });*/

        if (mListSelect != null && mListSelect.size() > 0) {
            for (int i = 0; i < mListSelect.size(); i++) {
                if (mListSelect.get(i).getCode().equals(lists.getCode())) {
                    holder.itemQuanXianIvStates.setChecked(true);
                }
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
        //区分
        if (Config.DW_ADMIN_ROLE.equals("DW_ADMIN")) {
            holder.itemQuanXianTvName.setTextColor(Color.parseColor("#999999"));
        } else {
            holder.itemQuanXianTvName.setTextColor(Color.parseColor("#e3e3e3"));
            if (Config.ZB_ADMIN_ROLE.equals("ZB_ADMIN") && Config.ZB_ADMIN_ROLE.equals(code)) {       //支部只能选择自己
                if (Config.mDeptId.equals(mDeptId)) {
                    holder.itemQuanXianTvName.setTextColor(Color.parseColor("#999999"));
                }
            }
            if (Config.GH_ADMIN_ROLE.equals("GH_ADMIN") && Config.GH_ADMIN_ROLE.equals(code)) {    //工会
                holder.itemQuanXianTvName.setTextColor(Color.parseColor("#999999"));
            }
            if (Config.XC_ADMIN_ROLE.equals("XC_ADMIN") && Config.XC_ADMIN_ROLE.equals(code)) {    //宣传
                holder.itemQuanXianTvName.setTextColor(Color.parseColor("#999999"));
            }
            if (Config.ZZ_ADMIN_ROLE.equals("ZZ_ADMIN") && Config.ZZ_ADMIN_ROLE.equals(code)) {    //组织小组
                holder.itemQuanXianTvName.setTextColor(Color.parseColor("#999999"));
            }
            if (Config.JJ_ADMIN_ROLE.equals("JJ_ADMIN") && Config.JJ_ADMIN_ROLE.equals(code)) {    //纪检小组
                holder.itemQuanXianTvName.setTextColor(Color.parseColor("#999999"));
            }
            if (Config.QN_AMIN_ROLE.equals("QN_ADMIN") && Config.QN_AMIN_ROLE.equals(code)) {    //青年小组
                holder.itemQuanXianTvName.setTextColor(Color.parseColor("#999999"));
            }
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

    public class selectAdViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemQuanXian_tvName)
        TextView itemQuanXianTvName;
        @Bind(R.id.itemQuanXian_view)
        View itemQuanXianView;
        @Bind(R.id.itemQuanXian_ivStates)
        public CheckBox itemQuanXianIvStates;

        public selectAdViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
