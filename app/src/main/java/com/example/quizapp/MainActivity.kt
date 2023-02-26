package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.data.Datasource
import com.example.quizapp.ui.theme.QuizAppTheme
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    QuizAppScreen()
                }
            }
        }
    }
}

enum class QuizScreen {
    Intro, FirstQuestion, SecondQuestion,
    ThirdQuestion, FourthQuestion, Outro
}

@Composable
fun QuizAppScreen() {
    var currentScreen by remember {
        mutableStateOf(QuizScreen.Intro)
    }
    //var currentAnswer by remember { mutableStateOf("") }
    var score by remember { mutableStateOf(0) }
    //var listIndex by remember { mutableStateOf(0) }
    val incrementScore = { score++ }
    when (currentScreen) {
        QuizScreen.Intro -> Column() {
            Text(
                text = stringResource(id = R.string.intro_text)
            )
            Button(onClick = {
                currentScreen = QuizScreen.FirstQuestion
            }) {
                Text(
                    text = stringResource(id = R.string.start_btn)
                )
            }
        }
        QuizScreen.FirstQuestion -> QAsScreen(
            listIndex = 0,
            onRightAnswer = incrementScore,
            onClick = {
                currentScreen = QuizScreen.SecondQuestion
            }
        )
        QuizScreen.SecondQuestion -> QAsScreen(
            listIndex = 1,
            onRightAnswer = incrementScore,
            onClick = {
                currentScreen = QuizScreen.ThirdQuestion
            }
        )
        QuizScreen.ThirdQuestion -> QAsScreen(
            listIndex = 2,
            onRightAnswer = incrementScore,
            onClick = {
                currentScreen = QuizScreen.FourthQuestion
            }
        )
        QuizScreen.FourthQuestion -> QAsScreen(
            listIndex = 3,
            onRightAnswer = incrementScore,
            onClick = {
                currentScreen = QuizScreen.Outro
            }
        )
        else -> OutroScreen(
            score = score,
            onRestart = {
                currentScreen = QuizScreen.Intro
            },
            onExit = { exitProcess(0) }
        )
    }
}

@Composable
fun QAsScreen(
    modifier: Modifier = Modifier,
    listIndex : Int,
    onClick: () -> Unit,
    onRightAnswer: () -> Int
) {
    Column() {
        QuestionCard(
            questionRes = Datasource.qas[listIndex].questionText
        )
        Options(
            answers = Datasource.qas[listIndex].options,
            rightAnswer = Datasource.qas[listIndex].rightAnswer,
            onRightAnswer = onRightAnswer,
            onClick = onClick
        )
    }
}

@Composable
fun QuestionCard(
    modifier: Modifier = Modifier,
    questionRes: Int
) {
    Card() {
        Text(
            text = stringResource(id = questionRes)
        )
    }
}

@Composable
fun Options(
    answers : List<Int>,
    @StringRes rightAnswer : Int,
    onClick : () -> Unit,
    onRightAnswer : () -> Int
) {
    LazyColumn() {
        items(answers) {
            OptionButton(
                answer = it,
                onClick = {
                    if (it == rightAnswer) {
                        onRightAnswer()
                    }
                    onClick()
                }
            )
        }
    }
}

@Composable
fun OptionButton(
    @StringRes answer : Int,
    onClick : () -> Unit
) {
    Button(onClick = onClick) {
        Text(
            text = stringResource(id = answer)
        )
    }
}

@Composable
fun OutroScreen(
    modifier: Modifier = Modifier,
    score : Int,
    onRestart : () -> Unit,
    onExit : () -> Unit
) {
    Column() {
        Text(
            text = stringResource(
                R.string.outro_text, score
            )
        )
        Row() {
            Button(onClick = onRestart) {
                Text(
                    text = stringResource(id = R.string.restart_btn)
                )
            }
            Button(onClick = onExit) {
                Text(
                    text = stringResource(id = R.string.exit_btn)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    QuizAppTheme {
        QuizAppScreen()
    }
}