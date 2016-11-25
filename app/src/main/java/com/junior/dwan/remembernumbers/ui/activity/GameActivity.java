package com.junior.dwan.remembernumbers.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.junior.dwan.remembernumbers.R;
import com.junior.dwan.remembernumbers.data.managers.DataManager;
import com.junior.dwan.remembernumbers.utils.ContsantsManager;
import com.junior.dwan.remembernumbers.utils.UserQuestionUtils;
import com.junior.dwan.remembernumbers.utils.RandomNumbers;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    RandomNumbers mRandomNumbers;
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

    DataManager mDataManager;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        mDataManager=DataManager.get(this);
        mBtnClear.setTypeface(Typeface.createFromAsset(getAssets(), "musseo.otf"));
        mBtnok.setTypeface(Typeface.createFromAsset(getAssets(), "musseo.otf"));
        mTextAnswer.setTypeface(Typeface.createFromAsset(getAssets(), "musseo.otf"));
        mRandomNumbers = new RandomNumbers();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ContsantsManager.STATUS_HIDE:
                        mTextQuestion.setVisibility(View.GONE);
                        mTextAnswer.setVisibility(View.VISIBLE);
                        break;
                    case ContsantsManager.STATUS_VISIBLE:
                        break;
                }
            }
        };
        mHandler.sendEmptyMessage(ContsantsManager.STATUS_VISIBLE);

        mTextQuestion.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);
        mBtnok.setOnClickListener(this);
        initButtons();
    }

    private void initButtons() {
        int number = 0;
        for (int i = 0; i < mTableLayout.getChildCount(); i++) {
            TableRow row = (TableRow) mTableLayout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                Button btn = (Button) row.getChildAt(j);
                btn.setText(number + "");
                btn.setTypeface(Typeface.createFromAsset(getAssets(), "musseo.otf"));
                number++;
                btn.setOnClickListener(numberButtonListener);

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_question:
                int intRandom = mRandomNumbers.getRandom3X(100, 1000);
                mDataManager.setQuestion(intRandom);
                Log.i("TAGTAG", mDataManager.getQuestion() + " random");
                mTextQuestion.setText(mDataManager.getQuestion());
                mTextAnswer.setText("");
                Thread t = new Thread(mRunnable);
                t.start();

                break;
            case R.id.btnClear:
                mTextAnswer.setText("");
                break;
            case R.id.btnOk:
                String result = mTextAnswer.getText().toString();
                Log.i("TAGTAG", mDataManager.getQuestion() + " result");
                if (result.equals(mDataManager.getQuestion())) {
                    Toast.makeText(getBaseContext(), "Correct", Toast.LENGTH_SHORT).show();
                    Log.i("TAGTAG", mDataManager.getQuestion() + " true");
                } else {
                    Toast.makeText(getBaseContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                    Log.i("TAGTAG", mDataManager.getQuestion() + " false");
                }

        }


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String correctAnswer = mDataManager.getQuestion();
        if (s.toString().equals(correctAnswer))
            Toast.makeText(this, "correct toast", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener numberButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            waitTime();
            mHandler.sendEmptyMessage(ContsantsManager.STATUS_HIDE);

        }
    };

    private void waitTime() {

        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
