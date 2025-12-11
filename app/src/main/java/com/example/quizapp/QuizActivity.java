package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    TextView txtQuestion, txtProgress;
    Button btnA, btnB, btnC, btnD;

    String[] questions = {
            "Who is known as the Father of Computers?",
            "What is the capital of India?",
            "Which planet is known as the Red Planet?",
            "Which gas do plants absorb from the atmosphere?",
            "What is the largest ocean on Earth?",
            "Who wrote the Harry Potter series?",
            "How many continents are there?",
            "Which country invented paper?",
            "What is H2O commonly known as?",
            "Which is the fastest land animal?"
    };

    String[][] options = {
            {"Charles Babbage", "Alan Turing", "Isaac Newton", "Nikola Tesla"},
            {"New Delhi", "Mumbai", "Kolkata", "Chennai"},
            {"Mars", "Venus", "Jupiter", "Saturn"},
            {"Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"},
            {"Pacific Ocean", "Indian Ocean", "Atlantic Ocean", "Arctic Ocean"},
            {"J.K. Rowling", "William Shakespeare", "Mark Twain", "Agatha Christie"},
            {"5", "6", "7", "8"},
            {"India", "China", "Egypt", "Greece"},
            {"Salt", "Water", "Oxygen", "Hydrogen"},
            {"Cheetah", "Lion", "Tiger", "Leopard"}
    };

    int[] correctAnswers = {0, 0, 0, 1, 0, 0, 2, 1, 1, 0};

    int index = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtProgress = findViewById(R.id.txtProgress);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);

        loadQuestion();
    }

    private void loadQuestion() {
        txtQuestion.setText(questions[index]);
        txtProgress.setText("Question " + (index + 1) + " / " + questions.length);

        btnA.setText(options[index][0]);
        btnB.setText(options[index][1]);
        btnC.setText(options[index][2]);
        btnD.setText(options[index][3]);

        resetButtons();
        enableButtons();
    }

    public void optionClicked(View v) {
        disableButtons();
        Button clicked = (Button) v;
        int selectedIndex = Integer.parseInt(v.getTag().toString());

        if (selectedIndex == correctAnswers[index]) {
            clicked.setBackgroundColor(Color.parseColor("#00FF8A"));
            clicked.setTextColor(Color.BLACK);

            score++;
        } else {
            clicked.setBackgroundColor(Color.parseColor("#FF1744"));
            clicked.setTextColor(Color.WHITE);

        }

        new Handler().postDelayed(() -> {
            index++;
            if (index < questions.length) {
                loadQuestion();
            } else {
                Intent i = new Intent(QuizActivity.this, ResultActivity.class);
                i.putExtra("score", score);
                i.putExtra("total", questions.length);
                startActivity(i);
                finish();
            }
        }, 900);
    }

    private void resetButtons() {
        Button[] all = {btnA, btnB, btnC, btnD};
        for (Button b : all) {
            b.setBackgroundColor(Color.parseColor("#DDDDDD"));
            b.setTextColor(Color.BLACK);
        }
    }

    private void disableButtons() {
        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
        btnD.setEnabled(false);
    }

    private void enableButtons() {
        btnA.setEnabled(true);
        btnB.setEnabled(true);
        btnC.setEnabled(true);
        btnD.setEnabled(true);
    }
}
