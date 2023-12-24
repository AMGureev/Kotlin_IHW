package ru.hse.cinema.dao

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.mindrot.jbcrypt.BCrypt
import ru.hse.cinema.entity.AccountEntity
import ru.hse.cinema.entity.TicketEntity
import java.io.File
import kotlin.io.path.Path

class InMemoryAccountDao : AccountDao {
    private val directoryPath = "accounts"
    private val fileName = "accounts.json"
    private var accounts = mutableListOf<AccountEntity>()
    override fun registerUser(name: String, password: String) {
        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
        accounts.add(AccountEntity(hashedPassword, name))
    }

    override fun authenticateUser(inputPassword: String, user: AccountEntity): Boolean {
        return BCrypt.checkpw(inputPassword, user.password)
    }

    override fun findAccountByLogin(name: String): Boolean {
        return accounts.any { it.name == name }
    }

    override fun returnAccountByName(name: String): AccountEntity {
        return accounts.find { it.name == name }!!
    }

    override fun saveAllAccounts() {
        File(directoryPath).mkdirs()
        val file = Path(directoryPath, fileName).toFile()
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerKotlinModule()
        mapper.writeValue(file, accounts)
    }

    override fun fillingAccountsData() {
        File(directoryPath).mkdirs()
        val file = File(directoryPath, fileName)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("[]")
        }
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerKotlinModule()
        accounts = mapper.readValue<MutableList<AccountEntity>>(file.readText())
    }

}
