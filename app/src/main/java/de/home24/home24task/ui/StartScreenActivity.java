package de.home24.home24task.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import de.home24.home24task.R;

public class StartScreenActivity extends AppCompatActivity implements View.OnClickListener {
    Button bStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start_screen);
        init();
    }

    private void init() {
        bStart = findViewById(R.id.bStart);
        bStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bStart:
                startActivity(new Intent(this, SelectionScreenActivity.class));
                break;
        }
    }
}
