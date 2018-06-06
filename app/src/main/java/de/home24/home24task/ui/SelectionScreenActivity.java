package de.home24.home24task.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import de.home24.home24task.CustomViewPager;
import de.home24.home24task.R;
import de.home24.home24task.adapter.SelectionScreenAdapter;
import de.home24.home24task.db.Articles;
import de.home24.home24task.networks.NetworkManager;
import de.home24.home24task.networks.RequestResponseListener;
import de.home24.home24task.pojo.Article;
import de.home24.home24task.utils.AppUtil;
import de.home24.home24task.utils.ApplicationController;
import de.home24.home24task.utils.Constants;

public class SelectionScreenActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivDislike, ivLike, ivBack;
    TextView tvCounter, tvHeader;
    Button bReview;
    ArrayList<Article> articleList;

    SelectionScreenAdapter selectionScreenAdapter;
    CustomViewPager vpArticles;
    private ProgressDialog progressDialog;

    int likeCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);
        init();
    }

    private void init() {
        setupHeader();
        vpArticles = findViewById(R.id.vpArticles);

        ivDislike = findViewById(R.id.ivDislike);
        ivLike = findViewById(R.id.ivLike);
        bReview = findViewById(R.id.bReview);

        tvCounter = findViewById(R.id.tvCounter);

        ivDislike.setOnClickListener(this);
        ivLike.setOnClickListener(this);
        bReview.setOnClickListener(this);

        callService();
    }

    private void setupHeader() {
        tvHeader = findViewById(R.id.tvHeader);
        tvHeader.setText(getString(R.string.selection_screen));

        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(this);
    }

    private void callService() {
        showProgress();
        NetworkManager.getInstance().get(new RequestResponseListener<Object>() {
            @Override
            public void onResult(Integer response, Object object) {
                dismissProgress();
                switch (response) {
                    case Constants.WebApi.Response.NO_INTERNET:
                        dismissProgress();
                        AppUtil.showToast(getApplicationContext(), getString(R.string.no_internet));
                        break;
                    case Constants.WebApi.Response.SUCCESS:
                        Gson gson = new Gson();

                        articleList = gson.fromJson((String) object, new TypeToken<ArrayList<Article>>() {
                        }.getType());

                        selectionScreenAdapter = new SelectionScreenAdapter(getApplicationContext(), articleList);
                        vpArticles.setAdapter(selectionScreenAdapter);
                        break;
                }
            }
        });
    }

    protected void showProgress() {
        dismissProgress();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    protected void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onBackPressed() {
        ivBack.performClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDislike:
                addData(false);
                break;
            case R.id.ivLike:
                addData(true);
                break;

            case R.id.bReview:
                startActivity(new Intent(this, ReviewScreenActivity.class));
                break;

            case R.id.ivBack:
                startOverDialog();
                break;
        }
    }

    public void startOverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectionScreenActivity.this, R.style.MyDialogTheme);
        builder.setTitle(getString(R.string.start_over_confirmation));
        builder.setMessage(getString(R.string.press_ok_to_logout));
        builder.setCancelable(false);

        String positiveText = this.getString(R.string.yes);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApplicationController.getDatabaseObj().eventDAO().delete();
                        finish();
                    }
                });

        String negativeText = getString(R.string.no);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void addData(boolean selection) {
        int count = vpArticles.getCurrentItem();

        if (count < articleList.size() - 1) {
            vpArticles.setCurrentItem(getItem(+1), true);
            Articles articles = new Articles(
                    articleList.get(count).getSku(),
                    articleList.get(count).getTitle(),
                    articleList.get(count).getMedia().get(0).getUri(),
                    selection);

            ApplicationController.getDatabaseObj().eventDAO().insertAll(articles);
            if (selection) {
                tvCounter.setText(++likeCounter + " / " + articleList.size());
            }
        } else {
            AppUtil.showToast(getApplicationContext(), getString(R.string.articles_finished));
            ivLike.setEnabled(false);
            ivDislike.setEnabled(false);
            bReview.setEnabled(true);
        }
    }

    private int getItem(int i) {
        return vpArticles.getCurrentItem() + i;
    }
}
