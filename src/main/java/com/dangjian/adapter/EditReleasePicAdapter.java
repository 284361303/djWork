package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dangjian.R;

import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 编辑发布的内容adapter
 * Created by sg-pc on 2016/10/20.
 */
public class EditReleasePicAdapter extends RecyclerView.Adapter<EditReleasePicAdapter.editReleaseViewHolder> {

    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;
    private static final String TAG = "EditReleaseActivity";

    public EditReleasePicAdapter(Context context, List<String> list) {
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
    public editReleaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_edit_release, null, false);
        editReleaseViewHolder holder = new editReleaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final editReleaseViewHolder holder, final int position) {
        try {
            if (position < mList.size()) {
                String s = mList.get(position);
                x.image().bind(holder.itemEditPicIv, s);
                holder.itemEditPicIvDetele.setVisibility(View.VISIBLE);
            } else {
                Glide.with(mContext).load(R.drawable.add_goods).centerCrop().into(holder.itemEditPicIv);
                holder.itemEditPicIvDetele.setVisibility(View.GONE);
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

    public class editReleaseViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemEditPic_iv)
        ImageView itemEditPicIv;
        @Bind(R.id.itemEditPic_ivDetele)
        ImageView itemEditPicIvDetele;

        public editReleaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
