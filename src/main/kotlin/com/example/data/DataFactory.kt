package com.example.data

import com.example.data.schemas.StudentTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DataFactory {
    fun init(){
        val db = Database.connect(hikari())
        transaction(db) {
            SchemaUtils.create(StudentTable)
        }

    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.jdbcUrl = "jdbc:postgresql://localhost:5432/myapp"
        config.driverClassName = "org.postgresql.Driver"
        config.username = "postgres"
        config.password = "root"
        config.maximumPoolSize = 3
        config.isAutoCommit =  false
        config.transactionIsolation= "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block : () -> T):T{
        return withContext(Dispatchers.IO){
            transaction {
                block()
            }
        }
    }
}