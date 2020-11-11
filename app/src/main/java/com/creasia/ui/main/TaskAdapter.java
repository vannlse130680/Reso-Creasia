package com.creasia.ui.main;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.creasia.BuildConfig;
import com.creasia.R;
import com.creasia.data.network.model.TaskResponse;
import com.creasia.ui.base.BaseViewHolder;
import com.creasia.ui.task.TaskActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskAdapter extends RecyclerView.Adapter<BaseViewHolder>  {

    //Các hằng số
    public static final int VIEW_TYPE_EMPTY = 0; //Ko có dữ liệu hoặc lỗi thì đưa về màn hình retry
    public static final int VIEW_TYPE_NORMAL = 1; //Hiển thị bình thường
    private static final int TYPE_LOAD = -1;

    private ArrayList<TaskResponse> mArrTaskResponse;
    private OnItemClickListener itemClickListener;

    private Callback mCallback;
    private OnLoadMoreListener loadMoreListener;
    private boolean isLoading = false, isMoreDataAvailable = true;
    private NumberFormat formatterWeight = new DecimalFormat("#,###,##0.00");
    private NumberFormat formatter = new DecimalFormat("#,###,###,###,###,###,##0");

    MainPresenter mPresenter;

    public TaskAdapter(ArrayList<TaskResponse> mDataList) {
        this.mArrTaskResponse = mDataList;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if (getItemViewType(position) != TYPE_LOAD) {
            holder.onBind(position);
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_view, parent, false));
            case TYPE_LOAD:
                return new LoadMoreHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false));
            case VIEW_TYPE_EMPTY:
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_not_data_view, parent, false));
            default:
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mArrTaskResponse != null && mArrTaskResponse.size() > 0) {
            if (mArrTaskResponse.get(position).isLoadMore()) {
                return TYPE_LOAD;
            } else {
                return VIEW_TYPE_NORMAL;
            }
        }else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mArrTaskResponse != null && mArrTaskResponse.size() > 0) {
            return mArrTaskResponse.size();
        } else {
            return 1;
        }
    }

    public void setItems(ArrayList<TaskResponse> mDataList) {
        this.mArrTaskResponse = mDataList;
        notifyDataSetChanged();
    }

    public void setMoreDataAvailable (boolean moreDataAvailable){
        isMoreDataAvailable = moreDataAvailable;
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public interface Callback {
        void onAEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.img_pick_1)
        ImageView imgViewPick1;

        @BindView(R.id.img_pick_2)
        ImageView imgViewPick2;
        @BindView(R.id.img_pick_3)
        ImageView imgViewPick3;
        @BindView(R.id.img_pick_4)
        ImageView imgViewPick4;

        @BindView(R.id.tv_customer_status)
        TextView tvCustomerStatus;

        @BindView(R.id.tv_customer_date)
        TextView tvCustomerDate;

        @BindView(R.id.tv_customer_name)
        TextView tvCustomerName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(final int position) {
            super.onBind(position);
            final TaskResponse task = mArrTaskResponse.get(position);

            if (task.getCaKpiCustomerImg1() != null && !task.getCaKpiCustomerImg1().isEmpty()) {
                Glide.with(itemView.getContext())
                        .load(BuildConfig.BASE_DOMAIN +task.getCaKpiCustomerImg1())
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.progress_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)
                        .into(imgViewPick1);
            }else{
                imgViewPick1.setImageResource(R.drawable.image_view);
            }
            if (task.getCaKpiCustomerImg2() != null && !task.getCaKpiCustomerImg2().isEmpty()) {
                Glide.with(itemView.getContext())
                        .load(BuildConfig.BASE_DOMAIN +task.getCaKpiCustomerImg2())
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.progress_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)
                        .into(imgViewPick2);
            }else{
                imgViewPick2.setImageResource(R.drawable.image_view);
            }
            if (task.getCaKpiCustomerImg2() != null && !task.getCaKpiCustomerImg2().isEmpty()) {
                Glide.with(itemView.getContext())
                        .load(BuildConfig.BASE_DOMAIN +task.getCaKpiCustomerImg2())
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.progress_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)
                        .into(imgViewPick3);
            }else{
                imgViewPick3.setImageResource(R.drawable.image_view);
            }
            if (task.getCaKpiCustomerImg2() != null && !task.getCaKpiCustomerImg2().isEmpty()) {
                Glide.with(itemView.getContext())
                        .load(BuildConfig.BASE_DOMAIN +task.getCaKpiCustomerImg2())
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.progress_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)
                        .into(imgViewPick4);
            }else{
                imgViewPick4.setImageResource(R.drawable.image_view);
            }

            if (task.getCaKpiCustomerId() != null && !task.getCaKpiCustomerId().isEmpty()) {
                tvCustomerName.setText(task.getCaKpiCustomerId() + (task.getCaKpiCustomerName()!=null ? "#"+task.getCaKpiCustomerName() :""));
            }

            if (task.getCaKpiCustomerDate()!=null) {
                tvCustomerDate.setText(task.getCaKpiCustomerDate());
            }


            if (task.getCaKpiCustomerStatus() != null) {
                if(task.getCaKpiCustomerStatus().trim().equals("0")) {
                    tvCustomerStatus.setText(R.string.task_doing);
                    tvCustomerStatus.setTextColor(Color.RED);
                }else {
                    tvCustomerStatus.setText(R.string.task_success);
                    tvCustomerStatus.setTextColor(Color.BLUE);
                }
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (task.getCaKpiCustomerStatus() != null) {
                        if (task.getCaKpiCustomerStatus().trim().equals("0")) {
                            itemView.getContext().startActivity(TaskActivity.getStartIntent(itemView.getContext(), task.getCaRouteplanId(), task.getCaKpiCustomerId(),true));
                        }
                    }
                }
            });
        }

        // Đưa về dữ liệu trắng
        @Override
        protected void clear() {
            imgViewPick1.setImageDrawable(null);
            imgViewPick2.setImageDrawable(null);
            imgViewPick3.setImageDrawable(null);
            tvCustomerStatus.setText("");
            tvCustomerDate.setText("");
            tvCustomerName.setText("");
        }
    }

    public static class LoadMoreHolder extends BaseViewHolder {
        public LoadMoreHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void clear() {

        }
    }

    // nếu dữ liệu là null hoặc xảy ra lỗi thì sẽ show layout empty
    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.btn_retry)
        Button retryButton;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @Override
        protected void clear() {

        }

        //Nút Retry
        @OnClick(R.id.btn_retry)
        void onRetryClick() {
            if (mCallback != null)
                mCallback.onAEmptyViewRetryClick();
        }


    }
    interface OnLoadMoreListener {
        void onLoadMore();
    }

    interface OnItemClickListener {
        void onItemClick(String cPass);
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }


}
