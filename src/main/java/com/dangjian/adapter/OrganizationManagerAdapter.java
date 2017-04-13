package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.entity.OrganizationManagerBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 组织管理adapter
 * Created by sg-pc on 2016/9/29.
 */
public class OrganizationManagerAdapter extends RecyclerView.Adapter<OrganizationManagerAdapter.zuZhiViewHolder> {

    private Context mContext;
    private List<OrganizationManagerBean.ListBean> mList;
    private LayoutInflater inflater;

    public OrganizationManagerAdapter(Context context, List<OrganizationManagerBean.ListBean> list) {
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
    public zuZhiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_organizationmanager, parent, false);
        zuZhiViewHolder holder = new zuZhiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final zuZhiViewHolder holder, final int position) {
        OrganizationManagerBean.ListBean lists = mList.get(position);
        String iconPath = lists.getIconPath();
        String joinPartMonth = lists.getJoinPartMonth();
        String loginName = lists.getLoginName();
        String nickName = lists.getNickName();  //昵称
        String mz = lists.getMZ();
        String sex = lists.getSex();
        if (nickName != null && !nickName.equals("")) {
            holder.itemOrganizationTvName.setText(loginName + "/" + nickName);
        } else {
            holder.itemOrganizationTvName.setText(loginName);
        }
        holder.itemOrganizationTvMinZu.setText(mz);
        holder.itemOrganizationTvSex.setText(sex);
        holder.itemOrganizationTvTime.setText(joinPartMonth);
        Glide.with(mContext).load(iconPath).error(R.drawable.all_smallhead).into(holder.itemOrganizationUserPic);

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

    public class zuZhiViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemOrganization_userPic)
        CircleImageView itemOrganizationUserPic;
        @Bind(R.id.itemOrganization_tvName)
        TextView itemOrganizationTvName;
        @Bind(R.id.itemOrganization_tvSex)
        TextView itemOrganizationTvSex;
        @Bind(R.id.itemOrganization_tvMinZu)
        TextView itemOrganizationTvMinZu;
        @Bind(R.id.itemOrganization_tvTime)
        TextView itemOrganizationTvTime;

        public zuZhiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
