package com.example.trivia_jan10

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TriviaViewModel : ViewModel() {

    private var _currentQuestion: MutableLiveData<Question> = MutableLiveData()
    val currentQuestion: LiveData<Question> = _currentQuestion

    private var _isGameOver: MutableLiveData<Boolean> = MutableLiveData(false)
    val isGameOver: LiveData<Boolean> = _isGameOver

    private var listOfQuestion = getQuestions()
    private var questionsAnswered = 0
    private var answeredCorrectly = 0

    init {
        _currentQuestion.value = listOfQuestion[0]
    }

    fun getPercentCorrect() = (answeredCorrectly / listOfQuestion.size) * 100

    fun onQuestionAnswered(answer: Answer) {
        questionsAnswered++
        if (answer.isCorrect) answeredCorrectly++
        if (questionsAnswered == listOfQuestion.size) {
            _isGameOver.value = true
        } else {
            _currentQuestion.value = listOfQuestion[questionsAnswered]
        }
    }

    fun resetGame() {
        questionsAnswered = 0
        answeredCorrectly = 0
        _currentQuestion.value = listOfQuestion[0]
        _isGameOver.value = false
    }
}