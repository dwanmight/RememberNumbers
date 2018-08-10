package com.junior.dwan.remembernumbers.ui.windows.finish.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.junior.dwan.remembernumbers.App;
import com.junior.dwan.remembernumbers.R;
import com.junior.dwan.remembernumbers.data.managers.PreferencesManager;
import com.junior.dwan.remembernumbers.ui.windows.BaseActivity;
import com.junior.dwan.remembernumbers.ui.windows.finish.di.DaggerFinishComponent;
import com.junior.dwan.remembernumbers.ui.windows.finish.di.FinishComponent;
import com.junior.dwan.remembernumbers.utils.ConstantsManager;
import com.junior.dwan.remembernumbers.utils.IntentUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Might on 26.11.2016.
 */

public class GameFinishActivity extends BaseActivity {
    @BindView(R.id.finish_start_new_game)
    Button mStartNewGameBtn;
    @BindView(R.id.finish_score_user)
    TextView tvScore;
    @BindView(R.id.finish_highScore)
    TextView tvHighScore;

    @Inject
    PreferencesManager mPreferencesManager;

    @Override
    public int getRootId() {
        return R.layout.activity_game_finish;
    }

    @Override
    protected boolean isNeedInject() {
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        FinishComponent component = DaggerFinishComponent.builder()
                .appComponentV2(App.getAppComponent())
                .build();
        component.inject(this);
    }

    @Override
    protected void initUI() {
        ButterKnife.bind(this);
    }

    @Override
    protected void setupUI(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();

        int score = intent.getIntExtra(ConstantsManager.EXTRA_SCORE, 0);
        tvScore.setText(getString(R.string.score_is, String.valueOf(score)));

        int highScore = mPreferencesManager.loadHighScore();
        tvHighScore.setText(getString(R.string.highscore_is, String.valueOf(highScore)));
        checkAndUpdateScore(score, highScore);
    }

    private void checkAndUpdateScore(int score, int highScore) {
        if (score > highScore) {
            highScore = score;
            mPreferencesManager.saveHighScore(highScore);
        }
    }

    @OnClick(R.id.finish_start_new_game)
    public void clickStartNewGame() {
        goToGame();
    }

    private void goToGame() {
        IntentUtils.startGame(this);
        finish();
    }
}
