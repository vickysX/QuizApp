package com.example.quizapp.model

import androidx.annotation.StringRes

data class Question(
    @StringRes val questionText : Int,
    val options : List<Int>,
    @StringRes val rightAnswer : Int
)
