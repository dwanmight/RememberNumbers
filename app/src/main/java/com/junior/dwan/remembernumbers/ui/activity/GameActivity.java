package com.junior.dwan.remembernumbers.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.junior.dwan.remembernumbers.R;
import com.junior.dwan.remembernumbers.data.managers.DataManager;
import com.junior.dwan.remembernumbers.data.models.GameInfo;
import com.junior.dwan.remembernumbers.utils.ConstantsManager;
import com.junior.dwan.remembernumbers.utils.GameUtils;
import com.junior.dwan.remembernumbers.utils.IntentUtils;
import com.junior.dwan.remembernumbers.utils.RandomNumbers;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends BaseActivity {

    @BindView(R.id.table_layout)
    TableLayout mTableLayout;
    @BindView(R.id.tv_question)
    TextView mQuestionTv;
    @BindView(R.id.tv_answer)
    TextView mAnswerTv;
    @BindView(R.id.clear_btn)
    Button mClearBtn;
    @BindView(R.id.ok_btn)
    Button mOkBtn;
    @BindView(R.id.tv_score)
    TextView mScoreTv;
    @BindView(R.id.tv_life)
    TextView mLifeTv;
    @BindView(R.id.tap_to_start_tv)
    TextView mTapToStartTv;
    @BindView(R.id.answer_indicator_layout)
    FrameLayout mBgLayout;

    private RandomNumbers mRandomNumbers;
    private DataManager mDataManager;
    private GameInfo mGameInfo;
    private Typeface mFontTypeFace;

    private final Handler mHandler = new Handler() {
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
                    mBgLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    break;
            }
        }
    };

    private JumpingBeans mJumpingBeans;

    private ArrayList<Button> mListNumberButtons;

    private int minimumRandom;
    private int maximumRandom;

    @Override
    int getRootId() {
        return R.layout.activity_game;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRandomNumbers = new RandomNumbers();
        minimumRandom = 10;
        maximumRandom = 100;

        mDataManager = DataManager.get(this);
        mGameInfo = new GameInfo(mDataManager.getLife(), mDataManager.getScore());

        mFontTypeFace = Typeface.createFromAsset(getAssets(), "musseo.otf");
    }

    @Override
    protected void setupUI(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);

        mClearBtn.setTypeface(mFontTypeFace);
        mOkBtn.setTypeface(mFontTypeFace);
        mAnswerTv.setTypeface(mFontTypeFace);

        updateScoreText();
        updateLifeText();

        initNumberButtons();
        initStartGame();
        isEnterModeEnabled(false);
    }

    private void updateScoreText() {
        mScoreTv.setText(getString(R.string.game_current_score, String.valueOf(mGameInfo.getScoreCount())));
    }

    private void updateLifeText() {
        mScoreTv.setText(getString(R.string.game_current_life, String.valueOf(mGameInfo.getLifeCount())));
    }

    private void initNumberButtons() {
        int number = 1;
        Button btn;
        TableRow tableRow;

        mListNumberButtons = new ArrayList<>();

        for (int i = 0; i < mTableLayout.getChildCount(); i++) {
            tableRow = (TableRow) mTableLayout.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                btn = (Button) tableRow.getChildAt(j);
                setupNumberBtnAndAddToList(btn, String.valueOf(number));
                number++;
            }
        }

        //init button number = 0
        int lastIndex = mTableLayout.getChildCount() - 1;
        tableRow = (TableRow) mTableLayout.getChildAt(lastIndex);
        int indexRowLastEl = tableRow.getChildCount() - 1;

        btn = (Button) tableRow.getChildAt(indexRowLastEl);
        setupNumberBtnAndAddToList(btn, "0");
    }

    private void setupNumberBtnAndAddToList(Button btn, String number) {
        if (btn == null) return;
        btn.setText(number);
        btn.setTypeface(mFontTypeFace);
        btn.setOnClickListener(numberButtonListener);
        mListNumberButtons.add(btn);
    }


    @OnClick(R.id.tv_question)
    public void clickQuestion() {
        updateQuestion();
    }

    @OnClick(R.id.clear_btn)
    public void clickClearBtn() {
        String answer = mAnswerTv.getText().toString().trim();
        if (answer.length() <= 0) return;

        answer = answer.substring(0, answer.length() - 1);
        mAnswerTv.setText(answer);
    }

    @OnClick(R.id.ok_btn)
    public void clickOkBtn() {
        checkCorrectResult();
    }

    @OnClick(R.id.tap_to_start_tv)
    public void clickTapToStart() {
        mTapToStartTv.setVisibility(View.GONE);
        mJumpingBeans.stopJumping();
        updateQuestion();
    }

    private void checkCorrectResult() {
        if (mAnswerTv.getText().length() != 0) {
            String result = mAnswerTv.getText().toString();
            if (result.equals(mDataManager.getQuestion())) {
                mBgLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                if (mGameInfo.getCorrectAnswersCount() % 3 == 0) {
//                    mScoreCount = mScoreCount + 10;
                }
                mGameInfo.incrementScore();
                mGameInfo.incrementCorrectAnswers();
                updateScoreText();
                mHandler.sendEmptyMessage(ConstantsManager.STATUS_VISIBLE);

            } else {
                if (GameUtils.checkForGameOver(mGameInfo.getLifeCount())) {
                    mBgLayout.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    mGameInfo.setLifesCount(GameUtils.checkLife(true, mGameInfo.getLifeCount()));
                    updateLifeText();
                } else {
                    goToFinishActivity();
                }
                mHandler.sendEmptyMessage(ConstantsManager.STATUS_VISIBLE);
            }
            mHandler.postDelayed(() -> mHandler.sendEmptyMessage(ConstantsManager.STATUS_FON_DEFAULT), 300);
        } else
            showToast("Enter the number!");

    }

    private void updateQuestion() {
        if (mGameInfo.getCorrectAnswersCount() > 0 && mGameInfo.getCorrectAnswersCount() % 3 == 0) {
            minimumRandom = minimumRandom * 10;
            maximumRandom = maximumRandom * 10;
        }
        int intRandom = mRandomNumbers.getRandom3X(minimumRandom, maximumRandom);
        mDataManager.setQuestion(intRandom);
        mQuestionTv.setText(mDataManager.getQuestion());
        mAnswerTv.setText("");
        mHandler.postDelayed(() -> mHandler.sendEmptyMessage(ConstantsManager.STATUS_HIDE), 1500);
    }

    private void goToFinishActivity() {
        IntentUtils.startFinish(this, mGameInfo.getScoreCount());
        finish();
    }

    View.OnClickListener numberButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GameUtils.streamNumbersPanel(mAnswerTv, v);
        }
    };

    private void isEnterModeEnabled(boolean enabled) {
        if (enabled) {
            mQuestionTv.setVisibility(View.GONE);
            mAnswerTv.setVisibility(View.VISIBLE);
            mOkBtn.setEnabled(true);
            mClearBtn.setEnabled(true);
        } else {
            mQuestionTv.setVisibility(View.VISIBLE);
            mAnswerTv.setVisibility(View.GONE);
            mQuestionTv.setText("");
            mOkBtn.setEnabled(false);
            mClearBtn.setEnabled(false);
        }
    }

    private void initStartGame() {
        int length = mTapToStartTv.getText().length();
        mJumpingBeans = JumpingBeans.with(mTapToStartTv)
                .makeTextJump(0, length)
                .setIsWave(true)
                .setLoopDuration(1000)  // ms
                .build();
    }

}
