package com.creasia.ui.main.route;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.creasia.R;
import com.creasia.data.network.model.BlogResponse;
import com.creasia.ui.base.BaseViewHolder;
import com.creasia.ui.task.TaskActivity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RouteAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<BlogResponse.Blog> mBlogResponseList;

    public RouteAdapter(List<BlogResponse.Blog> blogResponseList) {
        mBlogResponseList = blogResponseList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_view, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBlogResponseList != null && mBlogResponseList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mBlogResponseList != null && mBlogResponseList.size() > 0) {
            return mBlogResponseList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<BlogResponse.Blog> blogList) {
        mBlogResponseList.addAll(blogList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onBlogEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {

//        @BindView(R.id.cover_image_view)
//        ImageView coverImageView;
//
//        @BindView(R.id.title_text_view)
//        TextView titleTextView;
//
//        @BindView(R.id.author_text_view)
//        TextView authorTextView;
//
//        @BindView(R.id.date_text_view)
//        TextView dateTextView;
//
//        @BindView(R.id.content_text_view)
//        TextView contentTextView;
//
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
//
        protected void clear() {
//            coverImageView.setImageDrawable(null);
//            titleTextView.setText("");
//            contentTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

//            final BlogResponse.Blog blog = mBlogResponseList.get(position);
//
//            if (blog.getCoverImgUrl() != null) {
//                Glide.with(itemView.getContext())
//                        .load(blog.getCoverImgUrl())
//                        .asBitmap()
//                        .centerCrop()
//                        .into(coverImageView);
//            }
//
//            if (blog.getTitle() != null) {
//                titleTextView.setText(blog.getTitle());
//            }
//
//            if (blog.getAuthor() != null) {
//                authorTextView.setText(blog.getAuthor());
//            }
//
//            if (blog.getDate() != null) {
//                dateTextView.setText(blog.getDate());
//            }
//
//            if (blog.getDescription() != null) {
//                contentTextView.setText(blog.getDescription());
//            }
//
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = TaskActivity.getStartIntent(itemView.getContext());
//                    itemView.getContext().startActivity(intent);
                }
            });
       }
    }

    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.btn_retry)
        Button retryButton;

        @BindView(R.id.tv_message)
        TextView messageTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @OnClick(R.id.btn_retry)
        void onRetryClick() {
            if (mCallback != null)
                mCallback.onBlogEmptyViewRetryClick();
        }
    }
}
