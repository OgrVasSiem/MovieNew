package com.ogrvassiem.myfilms.aplication.rest

data class TopicsResponse(
    val topics: List<Topic>
) {
    data class Topic(
        val nameCategory: String,
        val color: String,
        val image: String,
        val freeTopic: Boolean,
        val storeID: String,
        val quantity: Int,
        val content: List<Map<String, List<Film>>>
    )

    data class Film(
        val filmName: String,
        val filmDescription: String,
        val rating: Double,
        val poster: String,
        val genres: String,
        val years: String
    )
}