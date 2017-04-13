package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.entity.RankingsBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sg-pc on 2016/10/11.
 */
public class RankingsAdapter extends RecyclerView.Adapter<RankingsAdapter.RankingsViewHolder> {

    private Context mContext;
    private List<RankingsBean.ListBean> mList;
    private LayoutInflater inflater;
    private String mPmType;
    private static final String TAG = "RankingsAdapter";

    public RankingsAdapter(Context mContext, List<RankingsBean.ListBean> list) {
        this.mContext = mContext;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    public void setPmType(String pmType) {
        this.mPmType = pmType;
        notifyDataSetChanged();
    }

    @Override
    public RankingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rankings, parent, false);
        RankingsViewHolder holder = new RankingsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RankingsViewHolder holder, int position) {
        try {
            RankingsBean.ListBean lists = mList.get(position);
            holder.itemRankingsTvPosition.setText(lists.getRank());
            Log.i(TAG, "onBindViewHolder/mPmType: " + mPmType);
            if (mPmType.equals("PERSON") || mPmType.equals("PERSONINORG")) {     //个人或者在支部内的排名
                String imgPath = lists.getImgPath();
                Glide.with(mContext).load(imgPath).error(R.drawable.all_smallhead).crossFade().into(holder.itemRankingsIv);
                holder.itemRankingsLlSingle.setVisibility(View.GONE);
                holder.itemRankingsLlMore.setVisibility(View.VISIBLE);
                holder.itemRankingsIvName.setText(lists.getName());
                holder.itemRankingsTvFenSum.setText(lists.getScore());
                String times = lists.getTimes();
                if (times != null && !times.equals("")) {
                    long lTimes = Long.parseLong(times) * 1000;
                    String date = timeFormat(lTimes);
                    holder.itemRankingsTvFen.setText(date);
                }
            } else {        //支部        itemRankings_llSingle
                Glide.with(mContext).load(R.drawable.all_branch_head).into(holder.itemRankingsIv);
                holder.itemRankingsLlMore.setVisibility(View.GONE);
                holder.itemRankingsLlSingle.setVisibility(View.VISIBLE);
                holder.itemRankingsLlSingleTvName.setText(lists.getName()); //支部编码显示
                holder.itemRankingsLlSingleTvSum.setText(lists.getScore());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
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

    public class RankingsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemRankings_tvPosition)
        TextView itemRankingsTvPosition;
        @Bind(R.id.itemRankingsLlSingle_tvName)
        TextView itemRankingsLlSingleTvName;
        @Bind(R.id.itemRankingsLlSingle_tvSum)
        TextView itemRankingsLlSingleTvSum;
        @Bind(R.id.itemRankings_llSingle)
        LinearLayout itemRankingsLlSingle;
        @Bind(R.id.itemRankings_ivName)
        TextView itemRankingsIvName;
        @Bind(R.id.itemRankings_tvFenSum)
        TextView itemRankingsTvFenSum;
        @Bind(R.id.itemRankings_tvFen)
        TextView itemRankingsTvFen;
        @Bind(R.id.itemRankings_llMore)
        LinearLayout itemRankingsLlMore;
        @Bind(R.id.itemRankings_iv)
        CircleImageView itemRankingsIv;

        public RankingsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String timeFormat(long times) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;
        long day = times / dd;
        long hour = (times - day * dd) / hh;
        long minute = (times - day * dd - hour * hh) / mi;
        long second = (times - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = times - day * dd - hour * hh - minute * mi - second * ss;
        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        return strMinute + " ' " + strSecond + " ''";
    }
}
