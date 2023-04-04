package com.example.a36_retrofit.domain.detailsDomain.model

import com.example.a36_retrofit.data.network.model.Credits

data class CreditsItem(
    val cast: List<CastItem>,
    val crew: List<CrewItem>,
    val id: Int
)
fun Credits.toDomain()=CreditsItem(
     cast=cast.map { it.toDomain()},
 crew=crew.map { it.toDomain()},
 id=id

)