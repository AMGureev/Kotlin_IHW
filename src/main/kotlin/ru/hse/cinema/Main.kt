package ru.hse.cinema

import ru.hse.cinema.dao.InMemoryAccountDao
import ru.hse.cinema.dao.InMemoryMovieDao
import ru.hse.cinema.dao.InMemoryTicketDao
import ru.hse.cinema.dao.InMemorySessionDao
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

fun authorizeUser(service: AccountService) { // the process of user login to the application
    println("Welcome to the app!")
    while (true) {
        println("Choose one of the two actions:\n1. Sign in\n2. Register\n3. Exit")
        print(">>")
        try {
            val choice = readln().toInt()
            when (choice) {
                1 -> { // sign in
                    println("You want to sign in!")
                    println("Input login:")
                    print(">>")
                    val login = readln()
                    println("Input password:")
                    print(">>")
                    val password = readln()
                    if (service.authenticateUser(login, password)) {
                        service.saveAllInformationAboutAccountsToJson()
                        break
                    }
                }

                2 -> { // register
                    println("You want to register!")
                    println("Come up with a username:")
                    print(">>")
                    val login = readln()
                    println("Come up with a password:")
                    print(">>")
                    val password = readln()
                    service.registerUser(login, password)
                }

                3 -> { // exit
                    println("Goodbye!")
                    service.saveAllInformationAboutAccountsToJson()
                    exitProcess(0)
                }
            }
        } catch (ex: Exception) {
            println(ex.message)
            println("Something went wrong! Please try again!")
        }
    }
}

fun displayWidget() { // shows the available options
    println("You are on the main page")
    val mainMenuList = arrayListOf(
        "Buy ticket",
        "Return ticket",
        "Activate ticket",
        "Display session information",
        "Create session",
        "Create movie",
        "Edit to movie",
        "Edit to session",
        "Display all sessions",
        "Save and exit the program"
    )
    println("Choose one action:")
    for (i in 0..<mainMenuList.size) {
        println("#${i + 1} ${mainMenuList[i]}")
    }
    print(">>")
}

fun interactionsUser(): Int { // get an integer and processing it
    while (true) {
        try {
            return readln().toInt()
        } catch (ex: Exception) {
            println(ex.message)
            println("Something went wrong! Please try again!")
        }
    }
}

fun processInteractionsWithUser(choice: Int, cinema: CinemaApp) { // determining the user's choice and following instructions
    try {
        when (choice) {
            1 -> { // buy a ticket
                println("You want to buy a ticket!")
                println("Enter the session ID:")
                print(">>")
                val sessionId = readln().toInt()
                println("Enter seat's number:")
                print(">>")
                val seatNumber = readln().toInt()
                cinema.buyTicket(sessionId, seatNumber)
            }

            2 -> { // return a ticket
                println("You want to return a ticket!")
                println("Enter the session ID:")
                print(">>")
                val sessionId = readln().toInt()
                println("Enter seat's number:")
                print(">>")
                val seatNumber = readln().toInt()
                cinema.returnTicket(sessionId, seatNumber)
            }

            3 -> { // activate a ticket
                println("You want to activate a ticket!")
                println("Enter the session ID:")
                print(">>")
                val sessionId = readln().toInt()
                println("Enter seat's number:")
                print(">>")
                val seatNumber = readln().toInt()
                cinema.activationTicket(sessionId, seatNumber)
            }

            4 -> { // see information about the session
                println("You want to see information about the session!")
                println("Enter the session ID:")
                print(">>")
                val sessionId = readln().toInt()
                println("Choose which ticket information you want to see:\n#1 Purchased\n#2 Available")
                print(">>")
                val availability = readln().toInt()
                when (availability) {
                    1 -> cinema.returnTicketsById(sessionId, false)
                    2 -> cinema.returnTicketsById(sessionId, true)
                }
            }

            5 -> { // create a session
                println("You want to create a session!")
                println("Enter the movie title:")
                print(">>")
                val title = readln()
                println("Enter the session start time (format- dd/MM/yy:HH:mm:ss):")
                print(">>")
                val inputDateTimeString = readln()
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yy:HH:mm:ss")
                val date = LocalDateTime.parse(inputDateTimeString, formatter)
                cinema.createSession(title, date)
            }

            6 -> { // create a movie
                println("You want to create a movie!")
                println("Enter the movie title:")
                print(">>")
                val title = readln()
                println("Enter the movie duration (in minutes):")
                print(">>")
                val duration = readln().toInt()
                cinema.createMovie(title, duration)
            }

            7 -> { // edit a movie
                println("You want to edit a movie!")
                println("Enter the movie title:")
                print(">>")
                val oldTitle = readln()
                println("Enter the new movie title (if no change is needed, leave it blank):")
                print(">>")
                val newTitle = readln()
                println("Enter the new movie's duration (if no change is needed, leave it '0'):")
                print(">>")
                val newDuration = readln().toInt()
                cinema.editMovieInformation(oldTitle, newTitle, newDuration)
            }

            8 -> { // edit a session
                println("You want to edit a session!")
                println("Enter the session ID")
                print(">>")
                val id = readln().toInt()
                println("Enter tne new session's start data (format - dd/MM/yy:HH:mm:ss):")
                print(">>")
                val inputDateTimeString = readln()
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yy:HH:mm:ss")
                val date = LocalDateTime.parse(inputDateTimeString, formatter)
                cinema.editSessionInformation(id, date)
            }

            9 -> { // see all available sessions
                println("You want to see all available sessions!")
                cinema.prettySessionsOutput()
            }

            10 -> { // save and exit
                println("You have completed the program!\nGoodbye!")
                cinema.saveAllInformationToJson()
                exitProcess(0)
            }
        }
    } catch (ex: Exception) {
        println(ex.message)
        println("Something went wrong! Please try again!")
    }
}

fun main() {
    val movieDao = InMemoryMovieDao()
    val ticketDao = InMemoryTicketDao()
    val sessionDao = InMemorySessionDao()
    val accountDao = InMemoryAccountDao()
    val accountService = AccountService(accountDao)
    val cinemaApp = CinemaApp(movieDao, ticketDao, sessionDao)
    accountService.initialFillingOfAccountsFile()
    authorizeUser(accountService)
    cinemaApp.initialFillingOfFiles()
    while (true) {
        displayWidget()
        val option = interactionsUser()
        processInteractionsWithUser(option, cinemaApp)
    }
}
