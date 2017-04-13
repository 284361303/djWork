package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.widgets.PhotoPicker.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sg-pc on 2016/10/14.
 * 发布页面中的图片适配器
 */
public class PicAdapter extends RecyclerView.Adapter<PicAdapter.picViewHolder> {

    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public PicAdapter(Context context, List<String> list) {
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
    public picViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pic, null, false);
        picViewHolder holder = new picViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final picViewHolder holder, final int position) {
        try {
            if (position < mList.size()) {
                ImageLoader.getInstance().display(mList.get(position), holder.itemPicIv, 60, 60);
            } else {
                Glide.with(mContext).load(R.drawable.all_camera).centerCrop().into(holder.itemPicIv);
            }

            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClickListener(holder.itemView, position);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    public class picViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemPic_iv)
        ImageView itemPicIv;

        public picViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
