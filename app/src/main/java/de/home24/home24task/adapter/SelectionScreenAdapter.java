package de.home24.home24task.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.home24.home24task.R;
import de.home24.home24task.pojo.Article;

public class SelectionScreenAdapter extends PagerAdapter {
    private ArrayList<Article> articlesList;
    private Context mContext;
    private LayoutInflater inflater;

    public SelectionScreenAdapter(Context context, ArrayList<Article> articlesList) {
        this.articlesList = articlesList;
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return (null != articlesList ? articlesList.size() : 0);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.pager_selection, container, false);

        assert imageLayout != null;
        final ImageView imageView = imageLayout
                .findViewById(R.id.ivSelection);

        final ContentLoadingProgressBar clpbLoading = imageLayout.findViewById(R.id.clpbLoading);


        Picasso.get().load(articlesList.get(position).getMedia().get(0).getUri())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        clpbLoading.hide();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        container.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
