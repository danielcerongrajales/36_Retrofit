package com.example.a36_retrofit.domain.detailsDomain.model

import com.example.a36_retrofit.data.network.model.Cast

data class CastItem(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
)
fun Cast.toDomain()=CastItem(
     adult,
 cast_id,
 character,
 credit_id,
 gender,
 id,
 known_for_department,
 name,
 order,
 original_name,
 popularity,
 profile_path

)