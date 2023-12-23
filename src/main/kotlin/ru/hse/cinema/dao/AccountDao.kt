package ru.hse.cinema.dao

import ru.hse.cinema.entity.AccountEntity

interface AccountDao {
    fun registerUser(name: String, password: String) // user registration
    fun authenticateUser(inputPassword: String, user: AccountEntity): Boolean // authenticate user
    fun findAccountByLogin(name: String) : Boolean // search for account by login
    fun returnAccountByName(name: String) : AccountEntity // search account by name and return it
    fun saveAllAccounts() // save all accounts in json file
    fun fillingAccountsData() // data recovery from file
}