package com.example.domain.usecases

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.model.CreateStudentParams

class CreateJwtToken{
    operator fun invoke(params:CreateStudentParams):String = JWT.create()
        .withAudience("http://0.0.0.0:8080/")
        .withIssuer("http://0.0.0.0:8080/register")
        .withClaim("name",params.name)
        .sign(Algorithm.HMAC256("secret"))
}