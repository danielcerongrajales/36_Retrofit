package com.example.a36_retrofit.domain.detailsDomain.model

import com.example.a36_retrofit.data.network.model.Crew

data class CrewItem(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?="null"
)
fun Crew.toDomain()=CrewItem(
     adult,
 credit_id,
 department,
 gender,
 id,
 job,
 known_for_department,
 name,
 original_name,
 popularity,
 profile_path

)