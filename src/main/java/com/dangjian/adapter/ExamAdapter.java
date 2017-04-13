package com.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.activity.ExamActivity;
import com.dangjian.entity.ExamBean;
import com.dangjian.utils.Config;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sg-pc on 2016/10/8.
 * 试题适配器
 */
public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private Context mContext;
    private List<ExamBean.SelectItemsBean> mList;
    private LayoutInflater inflater;
    private ExamActivity mActivity;
    public List<ExamViewHolder> holders = new ArrayList<ExamViewHolder>();
    public boolean flag = false;
    private String answer = "";
    private String staticFlag = "LOCK";
    private static final String TAG = "ExamAdapter";

    public ExamAdapter(Context context, List<ExamBean.SelectItemsBean> list, ExamActivity activity) {
        this.mContext = context;
        this.mList = list;
        this.mActivity = activity;
        inflater = LayoutInflater.from(mContext);
    }

    public void cleanAnsItem() {
        if (holders != null && holders.size() > 0) {
            holders.clear();
        }
        answer = "";
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_exam, parent, false);
        ExamViewHolder holder = new ExamViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ExamViewHolder holder, final int position) {
        final ExamBean.SelectItemsBean lists = mList.get(position);
        holder.itemExamTvContent.setText(lists.getContent());
        holder.itemExamTvNumber.setText(lists.getNum());
        flag = false;
        holders.add(holder);

        if (Config.type.equals("JUDGE")) {      //判断题
            holder.itemExamTvNumber.setVisibility(View.GONE);
            holder.itemExamTv.setVisibility(View.GONE);
        } else {
            holder.itemExamTvNumber.setVisibility(View.VISIBLE);
            holder.itemExamTv.setVisibility(View.VISIBLE);
        }
        //多选题就切换点击图标
        if (Config.type.equals("MULTI")) {
            holder.itemExamIv.setBackgroundResource(R.drawable.custom_checkbox2);
        } else {
            holder.itemExamIv.setBackgroundResource(R.drawable.custom_checkbox);
        }
        holder.itemExamIv.setClickable(false);  //禁止点击checkBox

        if (Config.userAnswer == null) {
            Config.userAnswer = "";
        }
        if (Config.userAnswer.indexOf(lists.getNum()) >= 0) {
            holder.itemExamIv.setChecked(true);
        } else {
            holder.itemExamIv.setChecked(false);
        }
        flag = true;

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

    public class ExamViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemExam_tvNumber)
        TextView itemExamTvNumber;
        @Bind(R.id.itemExam_tvContent)
        TextView itemExamTvContent;
        @Bind(R.id.itemExam_iv)
        public CheckBox itemExamIv;
        @Bind(R.id.itemExam_tv)
        TextView itemExamTv;

        public ExamViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
