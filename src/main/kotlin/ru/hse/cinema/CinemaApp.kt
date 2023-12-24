package ru.hse.cinema

import ru.hse.cinema.dao.MovieDao
import ru.hse.cinema.dao.SessionDao
import ru.hse.cinema.dao.TicketDao
import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.SessionEntity
import ru.hse.cinema.entity.TicketEntity
import java.time.LocalDateTime

class CinemaApp(
    private val movieDao: MovieDao,
    private val ticketDao: TicketDao,
    private val sessionDao: SessionDao,
) {
    // TODO В дальнейшем надо реализовать просмотр возможности редактирования продолжительность фильма (чтобы не повлияло на сессии)
    private fun checkCorrectSessionTime(title: String, date: LocalDateTime): Boolean { // checking the correctness of the session time
        for (index in 0..<sessionDao.getAllSessions().size) {
            if ((sessionDao.getAllSessions()[index].timeStart.plusMinutes(movieDao.returnMovieByName(title).duration.toLong()) > date && (date > sessionDao.getAllSessions()[index].timeStart)) || ((date < sessionDao.getAllSessions()[index].timeStart) && (date.plusMinutes(
                    movieDao.returnMovieByName(title).duration.toLong()
                ) > sessionDao.getAllSessions()[index].timeStart))
            ) {
                return false
            }
        }
        return true
    }

    fun createSession(title: String, date: LocalDateTime) { // create new session
        if (movieDao.isMovie(title)) {
            if (checkCorrectSessionTime(title, date)) {
                sessionDao.addSession(SessionEntity(sessionDao.getID(), movieDao.returnMovieByName(title), date))
                println("The session has been created!")
            } else {
                println("Error! - the session cannot be created due to a time conflict with other sessions")
            }
        } else {
            println("Error! - the movie does not exist")
        }
    }

    fun createMovie(title: String, time: Int) { // create movie
        if (time <= 0){
            println("Error! - the duration of the movie cannot be negative")
        } else {
            if (!movieDao.isMovie(title)) {
                if (title.isEmpty()){
                    println("Error! - the title is empty")
                } else {
                    movieDao.addMovie(MovieEntity(title, time))
                    println("The movie has been created!")
                }
            } else {
                println("Error! - the movie with title already exists")
            }
        }
    }

    fun buyTicket(id: Int, num: Int) { // buy ticket
        if (sessionDao.isSession(id)) {
            if (ticketDao.ticketIsSold(sessionDao.returnSessionById(id).tickets[num - 1])) {
                println("The ticket is not available for sale!")
            } else {
                ticketDao.sellTicket(sessionDao.returnSessionById(id).tickets[num - 1])
                println("Congratulation! The ticket is available!")
            }
        } else {
            println("Error! - the session does not exist")
        }
    }

    fun returnTicket(id: Int, num: Int) { // return ticket if it was purchased and not activated
        if (sessionDao.isSession(id)) {
            if (ticketDao.ticketIsSold(sessionDao.returnSessionById(id).tickets[num - 1])) {
                if (sessionDao.returnSessionById(id).tickets[num - 1].activation) {
                    println("Error! - you cannot refund the ticket as it has been activated")
                } else {
                    ticketDao.returnTicket(sessionDao.returnSessionById(id).tickets[num - 1])
                    println("The ticket has been successfully refunded! Your money will be credited to your card within 7 business days.")
                }
            } else {
                println("Error! - the ticket was not purchased")
            }
        } else {
            println("Error! - the session does not exist")
        }
    }

    fun returnTicketsById(id: Int, access: Boolean) { // show tickets belonging to some session
        if (sessionDao.isSession(id)) {
            if (access) {
                sessionDao.returnSessionById(id).tickets
                val unsoldTickets = sessionDao.returnSessionById(id).tickets.filter { ticket ->
                    ticket !in ticketDao.returnSoldTicketsById(id)
                }
                if (unsoldTickets.isEmpty()) {
                    println("All tickets were purchased!")
                } else {
                    println("List of available tickets:")
                    prettyTicketsOutput(unsoldTickets, false)
                }
            } else {
                if (ticketDao.returnSoldTicketsById(id).isEmpty()) {
                    println("All tickets are available for purchase!")
                } else {
                    println("List of purchased tickets:")
                    prettyTicketsOutput(ticketDao.returnSoldTicketsById(id), true)
                }
            }
        } else {
            println("Error! - the session does not exist")
        }
    }

    fun activationTicket(id: Int, num: Int) { // activate ticket
        if (num > 32 || num <= 0) {
            println("Error! - the seat's number is not existing!")
        } else {
            if (sessionDao.isSession(id)) {
                if (sessionDao.returnSessionById(id).tickets[num - 1] in ticketDao.returnSoldTicketsById(id)) {
                    if (sessionDao.returnSessionById(id).tickets[num - 1].activation) {
                        println("Error! - attempting to activate the ticket again")
                    } else {
                        ticketDao.activationTicket(sessionDao.returnSessionById(id).tickets[num - 1])
                        println("The ticket has been successfully activated! Enjoy the show!")
                    }
                } else {
                    println("Error! - attempting to activate an unpurchased ticket! It seems someone is trying to hack you!")
                }
            } else {
                println("Error! - the session does not exist")
            }
        }
    }

    fun editMovieInformation(oldTitle: String, newTitle: String, newDuration: Int) { // edit information about movie
        var newTitle = newTitle
        var newDuration = newDuration
        if (movieDao.isMovie(oldTitle)) {
            if (movieDao.isMovie(newTitle)) {
                println("Error! - a movie with this title already exists")
            } else {
                if (newDuration < 0){
                    println("Error! - the duration of the movie cannot be negative")
                } else {
                    if (newTitle == "") {
                        newTitle = oldTitle
                    }
                    if (newDuration == 0) {
                        newDuration = movieDao.returnMovieByName(oldTitle).duration
                    }
                    movieDao.editMovieInformation(movieDao.returnMovieByName(oldTitle), newTitle, newDuration)
                    println("Movie information has been successfully updated!")
                }
            }
        } else {
            println("Error! - attempting to edit a non-exists movie")
        }
    }

    fun editSessionInformation(id: Int, newDate: LocalDateTime) { // edit information about session
        if (sessionDao.isSession(id)) {
            if (checkCorrectSessionTime(sessionDao.returnSessionById(id).movie.title, newDate)) {
                sessionDao.editSession(sessionDao.returnSessionById(id), newDate)
                println("Session information has been successfully updated!")
            } else {
                println("Error! - the session cannot be updated due to a time conflict with other sessions")
            }
        } else {
            println("Error! - the session does not exist")
        }
    }

    fun saveAllInformationToJson() { // save all information (tickets, sessions and movies) to Jsons
        movieDao.saveAllMovies()
        sessionDao.saveAllSessions()
        ticketDao.saveAllSoldTickets()
    }

    fun initialFillingOfFiles() { // get information from json files
        movieDao.fillingMoviesData()
        ticketDao.fillingSoldTicketsData()
        sessionDao.fillingSessionsData()
    }

    private fun prettyTicketsOutput(array: List<TicketEntity>, status: Boolean) { // output array tickets
        val condition = if (status) "purchased" else "available for purchase"
        for (index in array.indices) {
            val activation = if (array[index].activation) "activated" else "not activated"
            println("Ticket for the session(ID): ${array[index].sessionID}; seat number: ${array[index].seatNumber}; ticket status: $condition; activation status: $activation.")
        }
    }

    fun prettySessionsOutput() { // output sessions
        if (sessionDao.getAllSessions().isEmpty()){
            println("There are no sessions")
        } else{
            for (index in sessionDao.getAllSessions().indices) {
                println("Session ID: ${sessionDao.getAllSessions()[index].id}; movie: ${sessionDao.getAllSessions()[index].movie.title}; time start: ${sessionDao.getAllSessions()[index].timeStart}")
            }
        }
    }
}
