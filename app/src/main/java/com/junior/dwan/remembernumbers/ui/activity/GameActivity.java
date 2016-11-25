package com.junior.dwan.remembernumbers.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.junior.dwan.remembernumbers.R;
import com.junior.dwan.remembernumbers.utils.Question;
import com.junior.dwan.remembernumbers.utils.RandomNumbers;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    RandomNumbers mRandomNumbers;
    Question mQuestion;
    @BindView(R.id.table_layout)
    TableLayout mTableLayout;
    @BindView(R.id.tv_question) TextView mTextQuestion;
    @BindView(R.id.tv_answer) TextView mTextAnswer;
    @BindView(R.id.btnClear)Button mBtnClear;
    @BindView(R.id.btnOk)Button mBtnok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        mBtnClear.setTypeface(Typeface.createFromAsset(getAssets(),"op.ttf"));
        mBtnok.setTypeface(Typeface.createFromAsset(getAssets(),"op.ttf"));
        mTextAnswer.setTypeface(Typeface.createFromAsset(getAssets(),"op.ttf"));
        mRandomNumbers = new RandomNumbers();
        mQuestion = new Question();

        mTextQuestion.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);
        mBtnok.setOnClickListener(this);
        initButtons();
    }

    private void initButtons() {
        int number =0;
        for(int i=0;i<mTableLayout.getChildCount();i++){
            TableRow row=(TableRow)mTableLayout.getChildAt(i);
            for(int j=0;j<row.getChildCount();j++){
                Button btn=(Button)row.getChildAt(j);
                btn.setText(number+"");
                btn.setTypeface(Typeface.createFromAsset(getAssets(),"op.ttf"));
                number++;
                btn.setOnClickListener(numberButtonListener);

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_question:
                int intRandom = mRandomNumbers.getRandom3X(100, 1000);
                mQuestion.setTextQuestion(intRandom);
                mTextQuestion.setText(mQuestion.getTextQuestion());
                mTextAnswer.setText("");
                mTextQuestion.setVisibility(View.GONE);
                mTextAnswer.setVisibility(View.VISIBLE);
                break;
            case R.id.btnClear:
                mTextAnswer.setText("");
                break;
            case R.id.btnOk:
                String result=mTextAnswer.getText().toString();
                if(result.equals(mQuestion.getTextQuestion())){
                    Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this,"Incorrect",Toast.LENGTH_SHORT).show();

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
        String correctAnswer=mQuestion.getTextQuestion();
        if(s.toString().equals(correctAnswer))
            Toast.makeText(this,"correct toast",Toast.LENGTH_SHORT).show();
        else Toast.makeText(this,"incorrect",Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener numberButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textView = (TextView) v;
            String working = mTextAnswer.getText().toString();
            String text = textView.getText().toString();
            if (working.equals("0")) {
                mTextAnswer.setText(text);
            } else
                mTextAnswer.setText(working + text);
        }
    };

    private void waitTime() throws InterruptedException {

            Thread.sleep(1000);

    }
}
