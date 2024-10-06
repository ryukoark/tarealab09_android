package com.example.lab09tarea

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("users") val users: List<UserModel>
)

data class UserModel(
    @SerializedName("id") val id: Int,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("age") val age: Int,
    @SerializedName("gender") val gender: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("username") val username: String,
    @SerializedName("birthDate") val birthDate: String,
    @SerializedName("image") val image: String,
    @SerializedName("bloodGroup") val bloodGroup: String,
    @SerializedName("height") val height: Double,
    @SerializedName("weight") val weight: Double,
    @SerializedName("eyeColor") val eyeColor: String,
    @SerializedName("hair") val hair: HairModel
)

data class HairModel(
    @SerializedName("color") val color: String,
    @SerializedName("type") val type: String
)