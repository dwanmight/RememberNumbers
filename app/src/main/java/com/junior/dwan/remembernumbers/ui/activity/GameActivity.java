package com.junior.dwan.remembernumbers.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.et_numberAnswer)
    EditText etAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        mRandomNumbers = new RandomNumbers();
        mQuestion = new Question();

        tvNumber.setOnClickListener(this);
        etAnswer.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {
        int intRandom = mRandomNumbers.getRandom3X(100, 1000);
        mQuestion.setTextQuestion(intRandom);
        tvNumber.setText(mQuestion.getTextQuestion());
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
}
