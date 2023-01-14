package com.example.wingstofly.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Result__1 : Serializable {
    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("difficulty")
    @Expose
    var difficulty: String? = null

    @SerializedName("question")
    @Expose
    var question: String? = null

    @SerializedName("correct_answer")
    @Expose
    var correctAnswer: String? = null

    @SerializedName("incorrect_answers")
    @Expose
    var incorrectAnswers: List<String>? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param difficulty
     * @param incorrectAnswers
     * @param question
     * @param category
     * @param type
     * @param correctAnswer
     */
    constructor(
        category: String?,
        type: String?,
        difficulty: String?,
        question: String?,
        correctAnswer: String?,
        incorrectAnswers: List<String>?
    ) : super() {
        this.category = category
        this.type = type
        this.difficulty = difficulty
        this.question = question
        this.correctAnswer = correctAnswer
        this.incorrectAnswers = incorrectAnswers
    }
}