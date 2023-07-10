package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configJwtAuthentication(){
    install(Authentication){
        jwt {
            verifier {
                JWT.require(Algorithm.HMAC256("secret"))
                    .withAudience("http://0.0.0.0:8080/")
                    .withIssuer("http://0.0.0.0:8080/register").build()
            }
            validate {
                if (it.payload.getClaim("name").asString()!=""){
                    JWTPrincipal(it.payload)
                }
                else{
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized,"Invalid Token")
            }
        }
    }
}