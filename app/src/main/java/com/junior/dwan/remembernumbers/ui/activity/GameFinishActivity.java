package com.junior.dwan.remembernumbers.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.junior.dwan.remembernumbers.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Might on 26.11.2016.
 */

public class GameFinishActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.finish_start_new_game)Button mStartNewGameBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finish);
        ButterKnife.bind(this);
        mStartNewGameBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent newGameIntent =new Intent(this, GameActivity.class);
        startActivity(newGameIntent);
        finish();
    }
}
