package com.mali.huaweiqa.domain.quizzes;


import com.mali.huaweiqa.domain.questions.Question;

import java.util.ArrayList;
import java.util.Random;

/**
 * A quiz that will contain list of questions from the QuestionLibrary.
 * It will produce the questions in a random order.
 */

public class Quiz {
    // duration of the quiz
    private Integer duration;
    private ArrayList<Question> questions;

    public Quiz(Integer duration, ArrayList<Question> questions) {
        this.duration = duration;
        this.questions = questions;
    }

    /**
     * get a random question from the quiz.
     * @return a random question for the quiz
     */
    public Question nextQuestion(){
        Random rnd = new Random();
        return questions.remove(rnd.nextInt(questions.size()));
    }

    /**
     *
     * @return Ture if there are still any more questions in the quiz.
     */
    public boolean hasNextQuestion(){
        return questions.size() > 0;
    }
}
