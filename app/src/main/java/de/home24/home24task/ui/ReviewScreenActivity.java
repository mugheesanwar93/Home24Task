package de.home24.home24task.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.home24.home24task.R;
import de.home24.home24task.adapter.ReviewScreenAdapter;
import de.home24.home24task.db.Articles;
import de.home24.home24task.utils.ApplicationController;

public class ReviewScreenActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rvReviews;
    ImageView ivGrid, ivList, ivBack;
    TextView tvHeader;

    ReviewScreenAdapter reviewScreenAdapter;
    List<Articles> articlesList = new ArrayList<>();
    Boolean isViewWithList = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_screen);
        init();
    }

    private void init() {
        setupHeader();
        rvReviews = findViewById(R.id.rvReviews);

        articlesList = ApplicationController.getDatabaseObj().eventDAO().getAllArticles();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvReviews.setLayoutManager(llm);

        reviewScreenAdapter = new ReviewScreenAdapter(articlesList, getApplicationContext(), isViewWithList);
        rvReviews.setAdapter(reviewScreenAdapter);
    }

    private void setupHeader() {
        tvHeader = findViewById(R.id.tvHeader);
        tvHeader.setText(getString(R.string.review_screen));

        ivGrid = findViewById(R.id.ivGrid);
        ivList = findViewById(R.id.ivList);
        ivBack = findViewById(R.id.ivBack);

        ivGrid.setOnClickListener(this);
        ivList.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivGrid:
                isViewWithList = false;
                changeView();
                break;
            case R.id.ivList:
                isViewWithList = true;
                changeView();
                break;
            case R.id.ivBack:
                finish();
        }
    }

    public void changeView() {
        rvReviews.setLayoutManager(isViewWithList ? new LinearLayoutManager(this) : new GridLayoutManager(this, 2));
        reviewScreenAdapter = new ReviewScreenAdapter(articlesList, getApplicationContext(), isViewWithList);
        rvReviews.setAdapter(reviewScreenAdapter);
    }
}
