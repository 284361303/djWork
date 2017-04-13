package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.entity.ChatBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sg-pc on 2016/10/19.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.chatViewHolder> {

    private Context mContext;
    private List<ChatBean.ListBean> mList;
    private LayoutInflater inflater;

    public ChatAdapter(Context context, List<ChatBean.ListBean> list) {
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
    public chatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_chat, parent, false);
        chatViewHolder holder = new chatViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final chatViewHolder holder, final int position) {
        ChatBean.ListBean lists = mList.get(position);
        String iconPath = lists.getIconPath();
        String replyNum = lists.getReplyNum();
        String title = lists.getTitle();
        Glide.with(mContext).load(iconPath).error(R.drawable.all_smallhead).into(holder.chatUserPic);
        holder.chatTvName.setText(lists.getLoginName());    //用户名字
        holder.chatTvDate.setText(lists.getCreateDate());       //日期
        holder.chatTvSum.setText(replyNum);     //跟帖数
        holder.chatTvCollectionSum.setText(lists.getGoodNum()); //点赞数
        if (title != null && !title.equals("")) {
            holder.chatTvTitle.setVisibility(View.VISIBLE);
            holder.chatTvTitle.setText(title);   //标题
        } else {
            holder.chatTvTitle.setVisibility(View.GONE);
        }
        if (!replyNum.equals("") && replyNum.equals("0")) {
            holder.chatIvRight.setVisibility(View.GONE);
        } else {
            holder.chatIvRight.setVisibility(View.VISIBLE);
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

    public class chatViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.chat_userPic)
        CircleImageView chatUserPic;
        @Bind(R.id.chat_tvName)
        TextView chatTvName;
        @Bind(R.id.chat_tvTitle)
        TextView chatTvTitle;
        @Bind(R.id.chat_tvDate)
        TextView chatTvDate;
        @Bind(R.id.chat_tvSum)
        TextView chatTvSum;
        @Bind(R.id.chat_ivRight)
        ImageView chatIvRight;
        @Bind(R.id.chat_tvCollectionSum)
        TextView chatTvCollectionSum;

        public chatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
