package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class ResultActivity extends AppCompatActivity {

    TextView txtScore;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtScore = findViewById(R.id.txtScore);
        btnRestart = findViewById(R.id.btnRestart);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);

        txtScore.setText("You scored " + score + " / " + total);

        btnRestart.setOnClickListener(v -> {
            startActivity(new Intent(ResultActivity.this, QuizActivity.class));
            finish();
        });
    }
}
