package ru.hse.cinema

import ru.hse.cinema.dao.InMemoryMovieDao
import ru.hse.cinema.dao.InMemoryTicketDao
import ru.hse.cinema.dao.InMemorySessionDao
import java.lang.Exception
import java.text.SimpleDateFormat
import kotlin.system.exitProcess

fun displayWidget(){
    println("Вы находитесь на главной странице")
    val mainMenuList = arrayListOf("Купить билет", "Вернуть билет", "Активировать билет", "Показать информацию о сеансе", "Создать сеанс", "Создать фильм", "Редактировать фильм", "Редактировать сеанс", "Завершить работу")
    println("Выберите одно из действий")
    for(i in 0..<mainMenuList.size){
        println("#${i+1} ${mainMenuList[i]}")
    }
}
fun interactionsUser() : Int {
    while (true){
        try {
            return readln().toInt()
        } catch (ex: Exception) {
            println(ex.message)
            println("Что-то не так! Повторите попытку!")
        }
    }
}

fun processInteractionsWithUser(choice: Int, cinema: CinemaApp)
{
    try {
        when (choice) {
            1 -> {
                println("Вы хотите купить билет!")
                println("Выберите номер сеанса:")
                val sessionId = readln().toInt()
                println("Выберите место:")
                val seatNumber = readln().toInt()
                cinema.buyTicket(sessionId, seatNumber)
            }

            2 -> {
                println("Вы хотите вернуть билет!")
                println("Выберите номер сеанса:")
                val sessionId = readln().toInt()
                println("Выберите место:")
                val seatNumber = readln().toInt()
                cinema.returnTicket(sessionId, seatNumber)
            }

            3 -> {
                println("Вы хотите активировать билет!")
                println("Выберите номер сеанса:")
                val sessionId = readln().toInt()
                println("Выберите место:")
                val seatNumber = readln().toInt()
                cinema.activationTicket(sessionId, seatNumber)
            }

            4 -> {
                println("Вы хотите посмотреть информацию о сеансе!")
                println("Введите ID сеанса:")
                val sessionId = readln().toInt()
                println("Выберите, информацию о каких билетах вы хотите просмотреть:\n#1 Купленных\n#2 Доступных")
                val availability = readln().toInt()
                when (availability) {
                    1 -> cinema.returnTicketsById(sessionId, false)
                    2 -> cinema.returnTicketsById(sessionId, true)
                }
            }

            5 -> {
                println("Вы хотите создать сеанс!")
                println("Введите название фильма:")
                val title = readln()
                println("Введите начало сеанса в формате yyyy-MM-dd HH:mm:ss:")
                val inputDateTimeString = readln()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date = dateFormat.parse(inputDateTimeString)
                cinema.createSession(title, date)
            }

            6 -> {
                println("Вы хотите создать фильм!")
                println("Введите название фильма:")
                val title = readln()
                println("Введите продолжительность фильма (в минутах):")
                val duration = readln().toInt()
                cinema.createMovie(title, duration)
            }

            7 -> {
                println("Вы хотите отредактировать фильм!")
                println("Введите название фильма:")
                val oldTitle = readln()
                println("Введите новое название фильма(если название изменять не надо, введите пустоту):")
                val newTitle = readln()
                println("Введите новую продолжительность фильма(если изменять продолжительность не надо, введите 0):")
                val newDuration = readln().toInt()
                cinema.editMovieInformation(oldTitle, newTitle, newDuration)
            }

            8 -> {
                println("Вы хотите отредактировать сеанс!")
                println("Введите ID сеанса:")
                val id = readln().toInt()
                println("Введите новую дату начала сеанса:")
                val inputDateTimeString = readln()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date = dateFormat.parse(inputDateTimeString)
                cinema.editSessionInformation(id, date)
            }

            9 -> {
                println("Вы завершили работу программы. До свидания!")
                exitProcess(0)
            }
        }
    } catch (ex: Exception)
    {
        println(ex.message)
        println("Что-то не так! Повторите попытку!")
    }
}
fun main() {
    val movieDao = InMemoryMovieDao()
    val ticketDao = InMemoryTicketDao()
    val sessionDao = InMemorySessionDao()
    val cinemaApp = CinemaApp(movieDao, ticketDao, sessionDao)
    while(true){
        displayWidget()
        val option = interactionsUser()
        processInteractionsWithUser(option, cinemaApp)
    }
}