package de.home24.home24task.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.home24.home24task.R;
import de.home24.home24task.db.Articles;

public class ReviewScreenAdapter extends RecyclerView.Adapter<ReviewScreenAdapter.ReviewScreenHolder> {
    private List<Articles> articlesList;
    private Context mContext;
    private Boolean isViewWithList;

    public ReviewScreenAdapter(List<Articles> articlesList, Context mContext, Boolean isViewWithList) {
        this.articlesList = articlesList;
        this.mContext = mContext;
        this.isViewWithList = isViewWithList;
    }

    @NonNull
    @Override
    public ReviewScreenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(isViewWithList ? R.layout.holder_review_screen_item_list : R.layout.holder_review_screen_item_grid, parent, false);
        return new ReviewScreenHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewScreenHolder holder, int position) {
        Articles article = articlesList.get(position);

        if (isViewWithList) {
            holder.tvTitle.setText(article.getTitle());
        }

        Picasso.get().load(article.getImage()).into(holder.ivImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.clpbLoading.hide();
            }

            @Override
            public void onError(Exception e) {

            }
        });

        if (article.getLiked()) {
            holder.cbLiked.setChecked(true);
        } else {
            holder.cbLiked.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return (null != articlesList ? articlesList.size() : 0);
    }

    public class ReviewScreenHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        CheckBox cbLiked;
        ContentLoadingProgressBar clpbLoading;


        ReviewScreenHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);

            cbLiked = itemView.findViewById(R.id.cbLiked);

            clpbLoading = itemView.findViewById(R.id.clpbLoading);
        }
    }
}
