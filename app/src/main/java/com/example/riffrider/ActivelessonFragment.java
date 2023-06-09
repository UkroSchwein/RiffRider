package com.example.riffrider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ActivelessonFragment extends Fragment {

    private TextView textLesson;
    private TextView textQuestion;
    private RadioGroup optionsRadioGroup;
    private ProgressBar progressBar;

    private int currentSlide = 0;

    private int lastSlide = 3;
    private Button submitBtn;

    private List<String> questions;
    String[][] answers;

    private int progress = 0;

    public ActivelessonFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activlesson_layout, container, false);

        questions = new ArrayList<String>();
        questions.add("\"Аккорд - это совместное звучание двух или более нот, которые играются одновременно. Аккорды используются в музыке для создания гармонии и образования основы для мелодии. Они состоят из трех или более нот, которые играются на гитаре одновременно с помощью пальцев на разных ладах.");
        questions.add("Вопрос 1: Что представляет собой аккорд в музыке?");
        questions.add("Аккорды на гитаре обычно обозначаются специальными символами или нотацией. Наиболее распространенные обозначения аккордов включают буквенные обозначения (например, C, G, D) и цифровые обозначения (например, Cmaj, G7, Dm). Эти обозначения указывают на основную ноту аккорда и его характеристики, такие как тип и расширение.");
        questions.add("Вопрос 2: Как обычно обозначаются аккорды на гитаре?");
        answers = new String[4][3];
        answers[1][0] = "Аккорд - это комбинация двух или более нот, которые играются одновременно.";
        answers[1][1] = "Аккорд - это нота, которую играют одновременно на разных струнах гитары.?";
        answers[1][2] = "Аккорд - это мелодия, которую играют на гитаре.";
        answers[3][0] = "Аккорды на гитаре обозначаются буквенными обозначениями, например, C, G, D.";
        answers[3][1] = "Аккорды на гитаре обозначаются римскими цифрами, например, I, IV, V.";
        answers[3][2] = "Аккорды на гитаре обозначаются строчными и прописными буквами, например, cm, Em, Am.";
        textQuestion = view.findViewById(R.id.textQuestion);
        optionsRadioGroup = view.findViewById(R.id.optionsRadioGroup);
        progressBar = view.findViewById(R.id.progressBar);
        submitBtn = view.findViewById(R.id.submitBtn);
        textQuestion.setText("Аккорд - это совместное звучание двух или более нот, которые играются одновременно. Аккорды используются в музыке для создания гармонии и образования основы для мелодии. Они состоят из трех или более нот, которые играются на гитаре одновременно с помощью пальцев на разных ладах");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSlide % 2 != 0)
                    checkAnswer();
                else {
                    currentSlide++;
                    progress += 25;
                    progressBar.setProgress(progress);
                }
                updateQuestionsAndAnswers();
                checkTestCompletion(progress);
            }
        });

        return view;
    }

    private void checkTestCompletion(int score) {
        if (score == 100) {
            requireActivity().onBackPressed();
            Toast.makeText(getContext(), "Вы успешно прошли первый урок", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkAnswer() {
        int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedOptionId != -1) {
            RadioButton selectedOption = optionsRadioGroup.findViewById(selectedOptionId);
            String selectedAnswer = selectedOption.getText().toString();
            Boolean isCorrect= isAnswerCorrect(selectedAnswer);
            if (isCorrect) {
                progress +=25;
                progressBar.setProgress(progress);
                currentSlide++;
            }
            String message = isCorrect ? "Правильно!" : "Неправильно!";
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Пожалуйста, выберите ответ", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAnswerCorrect(String answer) {
        return (answer.equals(answers[1][0])) || (answer.equals(answers[3][0]));
    }

    private void updateQuestionsAndAnswers() {
        if (currentSlide <= lastSlide) {
            textQuestion.setText(questions.get(currentSlide));
            if (currentSlide % 2 != 0) {
                RadioButton option1 = (RadioButton) optionsRadioGroup.getChildAt(0);
                RadioButton option2 = (RadioButton) optionsRadioGroup.getChildAt(1);
                RadioButton option3 = (RadioButton) optionsRadioGroup.getChildAt(2);
                option1.setText(answers[currentSlide][0]);
                option2.setText(answers[currentSlide][1]);
                option3.setText(answers[currentSlide][2]);
                optionsRadioGroup.setVisibility(View.VISIBLE);
            }
            else optionsRadioGroup.setVisibility(View.GONE);

        }
    }
}

