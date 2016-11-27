package com.junior.dwan.remembernumbers.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.junior.dwan.remembernumbers.R;
import com.junior.dwan.remembernumbers.data.managers.DataManager;
import com.junior.dwan.remembernumbers.utils.ContsantsManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Might on 26.11.2016.
 */

public class GameFinishActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.finish_start_new_game)
    Button mStartNewGameBtn;
    @BindView(R.id.finish_score_user)
    TextView tvScore;
    @BindView(R.id.finish_highScore)
    TextView tvHighScore;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finish);

        ButterKnife.bind(this);
        mStartNewGameBtn.setOnClickListener(this);
        showScore();
    }

    private void showScore() {
        Intent getScore = getIntent();
        int score = getScore.getIntExtra(ContsantsManager.EXTRA_SCORE, 0);
        int highScore = DataManager.get(this).getPreferencesManager().loadHighScore();
        if (score > highScore) {
            highScore = score;
            DataManager.get(this).getPreferencesManager().saveHighScore(highScore);
        }

        tvScore.setText(getString(R.string.score_is, String.valueOf(score)));
        tvHighScore.setText(getString(R.string.highscore_is, String.valueOf(highScore)));

    }

    @Override
    public void onClick(View v) {
        Intent newGameIntent = new Intent(this, GameActivity.class);
        startActivity(newGameIntent);
        finish();
    }
}
