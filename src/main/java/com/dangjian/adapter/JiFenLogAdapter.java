package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.JiFenLogBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 积分明细adapter
 * Created by sg-pc on 2016/10/25.
 */
public class JiFenLogAdapter extends RecyclerView.Adapter<JiFenLogAdapter.logViewHolder> {

    private Context mContext;
    private List<JiFenLogBean.ListBean> mList;
    private LayoutInflater inflater;

    public JiFenLogAdapter(Context context, List<JiFenLogBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public logViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_jifen_log, parent, false);
        logViewHolder holder = new logViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(logViewHolder holder, int position) {
        JiFenLogBean.ListBean lists = mList.get(position);
        String typeName = lists.getTypeName();  //获得积分或消费积分
        holder.jiFenLogTvSubTypeName.setText(lists.getSubTypeName());
        holder.jiFenLogTvDate.setText(lists.getDate());
        holder.jiFenLogTvPoints.setText(lists.getPoints());
        holder.jiFenLogTvTypeName.setText(typeName);
        if (typeName.equals("扣除积分")) {
            holder.jiFenLogLlAll.setBackgroundResource(R.color.line_0f0);
        } else {
            holder.jiFenLogLlAll.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null && mList.size() > 0) {
            return mList.size();
        } else {
            return 0;
        }
    }

    public class logViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.jiFenLog_tvTypeName)
        TextView jiFenLogTvTypeName;
        @Bind(R.id.jiFenLog_tvPoints)
        TextView jiFenLogTvPoints;
        @Bind(R.id.jiFenLog_tvSubTypeName)
        TextView jiFenLogTvSubTypeName;
        @Bind(R.id.jiFenLog_tvDate)
        TextView jiFenLogTvDate;
        @Bind(R.id.jiFenLog_llAll)
        LinearLayout jiFenLogLlAll;

        public logViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
