package ru.hse.cinema

import ru.hse.cinema.dao.MovieDao
import ru.hse.cinema.dao.SessionDao
import ru.hse.cinema.dao.TicketDao
import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.SessionEntity
import java.util.*

class CinemaApp(
    private val movieDao: MovieDao,
    private val ticketDao: TicketDao,
    private val sessionDao: SessionDao,
) {
    fun createSession(title: String, date: Date) {
        if (movieDao.isMovie(title)) {
            sessionDao.addSession(SessionEntity(sessionDao.getID(), movieDao.returnMovieByName(title), date))
            println("Сессия успешно создана!")
        } else {
            println("Такого фильма нет!")
        }
    }

    fun createMovie(title: String, time: Int) {
        movieDao.addMovie(MovieEntity(title, time))
        println("Фильм успешно создан!")
    }

    fun buyTicket(id: Int, num: Int) {
        if (sessionDao.isSession(id)) {
            if (ticketDao.ticketIsSold(sessionDao.returnSessionById(id).tickets[num - 1])) {
                println("Билет уже был куплен!")
            } else {
                ticketDao.sellTicket(sessionDao.returnSessionById(id).tickets[num - 1])
                println("Билет успешно куплен!")
            }
        } else {
            println("Сессия не найдена! - продажа билета недоступна")
        }
    }

    fun returnTicket(id: Int, num: Int) {
        if (sessionDao.isSession(id)) {
            if (ticketDao.ticketIsSold(sessionDao.returnSessionById(id).tickets[num - 1])) {
                if (sessionDao.returnSessionById(id).tickets[num - 1].activation) {
                    println("Билет нельзя вернуть! - он уже был активирован")
                } else {
                    ticketDao.returnTicket(sessionDao.returnSessionById(id).tickets[num - 1])
                    println("Билет успешно возвращен! Ваши деньги поступят на карту в течение 7 рабочих дней!")
                }
            } else {
                println("Не удалось вернуть билет - он не был куплен!")
            }
        } else {
            println("Сессия не найдена! - возврат билета не произведен")
        }
    }

    fun returnTicketsById(id: Int, access: Boolean) {
        if (sessionDao.isSession(id)) {
            if (access) {
                sessionDao.returnSessionById(id).tickets
                val unsoldTickets = sessionDao.returnSessionById(id).tickets.filter { ticket ->
                    ticket !in ticketDao.returnSoldTicketsById(id)
                }
                println(unsoldTickets.joinToString())
            } else {
                println(ticketDao.returnSoldTicketsById(id).joinToString())
            }
        } else {
            println("Сессия не найдена! - списка билетов нет")
        }
    }

    fun activationTicket(id: Int, num: Int) {
        if (sessionDao.isSession(id)) {
            if (sessionDao.returnSessionById(id).tickets[num - 1] in ticketDao.returnSoldTicketsById(id)) {
                if (sessionDao.returnSessionById(id).tickets[num - 1].activation) {
                    println("Ошибка! - попытка повторной активации билета")
                } else {
                    ticketDao.activationTicket(sessionDao.returnSessionById(id).tickets[num - 1])
                    println("Билет успешно активирован! Приятного просмотра!")
                }
            } else {
                println("Билет не был куплен! - вас пытаются взломать")
            }
        } else {
            println("Сессия не найдена! - вас пытаются взломать")
        }
    }

    fun editMovieInformation(oldTitle: String, newTitle: String, newDuration: Int) {
        var newTitle = newTitle
        var newDuration = newDuration
        if (movieDao.isMovie(oldTitle)) {
            if (newTitle == ""){
                newTitle = oldTitle
            }
            if (newDuration == 0){
                newDuration = movieDao.returnMovieByName(oldTitle).duration
            }
            movieDao.editMovieInformation(movieDao.returnMovieByName(oldTitle), newTitle, newDuration)
            println("Информация о фильме успешно изменена!")
        } else {
            println("Ошибка! - попытка редактирования несуществующего фильма")
        }
    }

    fun editSessionInformation(id: Int, newDate: Date) {
        if (sessionDao.isSession(id)) {
            sessionDao.editSession(sessionDao.returnSessionById(id), newDate)
            println("Информация о сессии успешно изменена!")
        } else {
            println("Сессия не найдена! - сессия не может быть отредактирована")
        }
    }
}