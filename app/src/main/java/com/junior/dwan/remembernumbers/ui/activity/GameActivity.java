package com.junior.dwan.remembernumbers.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.junior.dwan.remembernumbers.R;
import com.junior.dwan.remembernumbers.data.managers.DataManager;
import com.junior.dwan.remembernumbers.utils.ConstantsManager;
import com.junior.dwan.remembernumbers.utils.GameUtils;
import com.junior.dwan.remembernumbers.utils.RandomNumbers;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.table_layout)
    TableLayout mTableLayout;
    @BindView(R.id.tv_question)
    TextView mTextQuestion;
    @BindView(R.id.tv_answer)
    TextView mTextAnswer;
    @BindView(R.id.btnClear)
    Button mBtnClear;
    @BindView(R.id.btnOk)
    Button mBtnok;
    @BindView(R.id.tv_score)
    TextView mTvScore;
    @BindView(R.id.tv_life)
    TextView mTvLife;
    @BindView(R.id.tap_to_start_textView)
    TextView tvTapToStart;
    @BindView(R.id.layout_tap_to_start)
    FrameLayout mStartLayout;
    @BindView(R.id.game_fon_layout)
    FrameLayout mFoneLayout;

    private RandomNumbers mRandomNumbers;
    private DataManager mDataManager;
    private Handler mHandler;
    private JumpingBeans mJumpingBeans;
    private ArrayList<Button> mListNumberButtons;
    private int mLife;
    private int mScore;
    private int minimumRandom;
    private int maximumRandom;
    private int mCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        mDataManager = DataManager.get(this);
        mScore = mDataManager.getScore();
        mLife = mDataManager.getLife();
        mRandomNumbers = new RandomNumbers();
        mCorrectAnswer=0;
        minimumRandom=10;
        maximumRandom=100;
        showScore(ConstantsManager.SHOW_SCORE);
        showScore(ConstantsManager.SHOW_LIFE);
        setTypeFacesAndListener();
        initNumberButtons();
        initStartGame();
        startHandler();
        isEnterModeEnabled(false);

    }

    private void startHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ConstantsManager.STATUS_HIDE:
                        isEnterModeEnabled(true);
                        break;
                    case ConstantsManager.STATUS_VISIBLE:
                        isEnterModeEnabled(false);
                        updateQuestion();
                        break;
                    case ConstantsManager.STATUS_FON_DEFAULT:
                        mFoneLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        break;
                }
            }
        };
    }

    private void setTypeFacesAndListener() {
        mBtnClear.setTypeface(Typeface.createFromAsset(getAssets(), "musseo.otf"));
        mBtnok.setTypeface(Typeface.createFromAsset(getAssets(), "musseo.otf"));
        mTextAnswer.setTypeface(Typeface.createFromAsset(getAssets(), "musseo.otf"));
        mTextQuestion.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);
        mBtnok.setOnClickListener(this);
        tvTapToStart.setOnClickListener(this);
    }

    private void initNumberButtons() {
        int number = 1;
        mListNumberButtons = new ArrayList<>();
        for (int i = 0; i < mTableLayout.getChildCount(); i++) {
            TableRow row = (TableRow) mTableLayout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                Button btn = (Button) row.getChildAt(j);
                btn.setText(number + "");
                btn.setTypeface(Typeface.createFromAsset(getAssets(), "musseo.otf"));
                mListNumberButtons.add(btn);
                number++;
                btn.setOnClickListener(numberButtonListener);
            }
        }
        //init button number = 0
        int lastIndex = mTableLayout.getChildCount() - 1;
        TableRow row = (TableRow) mTableLayout.getChildAt(lastIndex);
        int indexRowLastEl = row.getChildCount() - 1;
        Button btn = (Button) row.getChildAt(indexRowLastEl);
        btn.setText("0");
        btn.setTypeface(Typeface.createFromAsset(getAssets(), "musseo.otf"));
        mListNumberButtons.add(btn);
        btn.setOnClickListener(numberButtonListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_question:
                updateQuestion();
                break;
            case R.id.btnClear:
//                mTextAnswer.setText("");
                String answer=mTextAnswer.getText().toString();
                if(answer.length()>0){
                    answer=answer.substring(0,answer.length()-1);
                    mTextAnswer.setText(answer);
                } else return;
                break;
            case R.id.btnOk:
                checkCorrectResult();
                break;
            case R.id.tap_to_start_textView:
                mStartLayout.setVisibility(View.GONE);
                mJumpingBeans.stopJumping();
                updateQuestion();
        }
    }

    private void checkCorrectResult() {
        if (mTextAnswer.getText().length() != 0) {
            String result = mTextAnswer.getText().toString();
            if (result.equals(mDataManager.getQuestion())) {
                mFoneLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                if(mCorrectAnswer%3==0){
                    mScore=mScore+10;
                }
                mScore++;
                mCorrectAnswer++;
                showScore(ConstantsManager.SHOW_SCORE);
                mHandler.sendEmptyMessage(ConstantsManager.STATUS_VISIBLE);

            } else {
                if (GameUtils.checkForGameOver(mLife)) {
                    mFoneLayout.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    mLife = GameUtils.checkLife(true, mLife);
                    showScore(ConstantsManager.SHOW_LIFE);
                } else {
                    startFinishActivity();
                    finish();
                }
                mHandler.sendEmptyMessage(ConstantsManager.STATUS_VISIBLE);
            }
            Thread thread = new Thread(mChangeFonRunnable);
            thread.start();
        } else
            Toast.makeText(GameActivity.this, "Enter the number!", Toast.LENGTH_LONG).show();

    }

    private void updateQuestion() {
        if( mCorrectAnswer>0 &&mCorrectAnswer%3==0){
            minimumRandom=minimumRandom*10;
            maximumRandom=maximumRandom*10;
        }
        int intRandom = mRandomNumbers.getRandom3X(minimumRandom, maximumRandom);
        mDataManager.setQuestion(intRandom);
        mTextQuestion.setText(mDataManager.getQuestion());
        mTextAnswer.setText("");
        Thread t = new Thread(showRandomRunable);
        t.start();
    }

    private void startFinishActivity() {
        Intent startFinishIntent = new Intent(this, GameFinishActivity.class);
        startFinishIntent.putExtra(ConstantsManager.EXTRA_SCORE, mScore);
        startActivity(startFinishIntent);
    }

    View.OnClickListener numberButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GameUtils.streamNumbersPanel(mTextAnswer, v);
        }
    };

    Runnable showRandomRunable = new Runnable() {
        @Override
        public void run() {
            waitTime(1500);
            mHandler.sendEmptyMessage(ConstantsManager.STATUS_HIDE);
        }
    };

    Runnable mChangeFonRunnable = new Runnable() {
        @Override
        public void run() {
            waitTime(300);
            mHandler.sendEmptyMessage(ConstantsManager.STATUS_FON_DEFAULT);
        }
    };

    private void waitTime(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showScore(int value) {
        switch (value) {
            case ConstantsManager.SHOW_SCORE:
                mTvScore.setText(getString(R.string.game_current_score,String.valueOf(mScore)));
                break;
            case ConstantsManager.SHOW_LIFE:
                mTvLife.setText(getString(R.string.game_current_life,String.valueOf(mLife)));
        }
    }

    private void isEnterModeEnabled(boolean isEnabled) {
        if (isEnabled) {
            mTextQuestion.setVisibility(View.GONE);
            mTextAnswer.setVisibility(View.VISIBLE);
            mBtnok.setEnabled(true);
            mBtnClear.setEnabled(true);
//            mTableLayout.setEnabled(true);
//            if (mListNumberButtons.size() > 0) {
//                for (Button btnNumber : mListNumberButtons)
//                    btnNumber.setEnabled(true);
//            }
        } else {
            mTextQuestion.setVisibility(View.VISIBLE);
            mTextAnswer.setVisibility(View.GONE);
            mTextQuestion.setText("");
            mBtnok.setEnabled(false);
            mBtnClear.setEnabled(false);
//            mTableLayout.setEnabled(false);
//            if (mListNumberButtons.size() > 0) {
//                for (Button btnNumber : mListNumberButtons)
//                    btnNumber.setEnabled(false);
//            }
        }
    }

    private void initStartGame() {
        mJumpingBeans = JumpingBeans.with(tvTapToStart)
                .makeTextJump(0, tvTapToStart.getText().length())
                .setIsWave(true)
                .setLoopDuration(1000)  // ms
                .build();
    }

}
