package com.example.quizapp.data

import com.example.quizapp.R
import com.example.quizapp.model.Question

object Datasource {
    val qas = listOf(
        Question(
            questionText = R.string.first_q,
            options = listOf(
                R.string.first_q_first_a,
                R.string.first_q_second_a,
                R.string.first_q_third_a
            ),
            rightAnswer = R.string.first_q_second_a
        ),
        Question(
            questionText = R.string.second_q,
            options = listOf(
                R.string.second_q_first_a,
                R.string.second_q_second_a,
                R.string.second_q_third_a
            ),
            rightAnswer = R.string.second_q_first_a
        ),
        Question(
            questionText = R.string.third_q,
            options = listOf(
                R.string.third_q_first_a,
                R.string.third_q_second_a,
                R.string.third_q_third_a
            ),
            rightAnswer = R.string.third_q_second_a
        ),
        Question(
            questionText = R.string.fourth_q,
            options = listOf(
                R.string.fourth_q_first_a,
                R.string.fourth_q_second_a,
                R.string.fourth_q_third_a
            ),
            rightAnswer = R.string.fourth_q_first_a
        ),
    )
}