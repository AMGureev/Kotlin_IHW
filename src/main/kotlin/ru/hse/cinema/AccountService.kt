package ru.hse.cinema

import ru.hse.cinema.dao.AccountDao

class AccountService(
    private val accountDao: AccountDao
) {
    fun registerUser(name: String, password: String) { // process register user
        if (accountDao.findAccountByLogin(name)) {
            println("An account with this username already exists!")
        } else {
            accountDao.registerUser(name, password)
            println("The account with this username $name successfully creation!")
        }
    }

    fun authenticateUser(name: String, inputPassword: String): Boolean { // authenticate user : return true or false
        return if (accountDao.findAccountByLogin(name)) {
            if (accountDao.authenticateUser(inputPassword, accountDao.returnAccountByName(name))) {
                println("Access granted!")
                true
            } else {
                println("Password error!")
                false
            }
        } else {
            println("Error! - an account with that username does not exist")
            false
        }
    }

    fun saveAllInformationAboutAccountsToJson() { // save all information about accounts to Json file
        accountDao.saveAllAccounts()
    }

    fun initialFillingOfAccountsFile() { // get information from json file
        accountDao.fillingAccountsData()
    }
}
