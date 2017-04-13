package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.entity.UserRankingBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 用户里面我的积分排名adapter
 * Created by sg-pc on 2016/10/19.
 */
public class UserRankingAdapter extends RecyclerView.Adapter<UserRankingAdapter.userRankingViewHolder> {

    private Context mContext;
    private List<UserRankingBean.ListBean> mList;
    private LayoutInflater inflater;

    public UserRankingAdapter(Context context, List<UserRankingBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public userRankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_user_ranking, parent, false);
        userRankingViewHolder holder = new userRankingViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(userRankingViewHolder holder, int position) {
        UserRankingBean.ListBean lists = mList.get(position);
        String imgPath = lists.getImgPath();
        Glide.with(mContext).load(imgPath).error(R.drawable.all_smallhead).into(holder.itemUserRankingUserPic);
        holder.itemUserRankingTvName.setText(lists.getLoginName());
        holder.itemUserRankingTvPosition.setText(lists.getRank());
        holder.itemUserRankingTvName.setText(lists.getLoginName());
        holder.itemUserRankingTvSum.setText(lists.getPoints());
    }

    @Override
    public int getItemCount() {
        if (mList != null && !mList.equals("")) {
            return mList.size();
        } else {
            return 0;
        }
    }

    public class userRankingViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemUserRanking_tvPosition)
        TextView itemUserRankingTvPosition;
        @Bind(R.id.itemUserRanking_userPic)
        CircleImageView itemUserRankingUserPic;
        @Bind(R.id.itemUserRanking_tvName)
        TextView itemUserRankingTvName;
        @Bind(R.id.itemUserRanking_tvSum)
        TextView itemUserRankingTvSum;

        public userRankingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
