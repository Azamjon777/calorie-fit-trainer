package goope.rimu.caloriefittrainer.model

import java.io.Serializable

data class TrainingModel(
    val imageUrl: String,
    val title: String,
    val desc: String,
    val lessons: Int,
    val urlReference: String,
    val kcal: String,
    val timeDuration: String
) : Serializable
