package ru.hse.cinema.dao

import ru.hse.cinema.entity.PlaceEntity
interface PlaceDao {
    fun freeUpSpace(place: PlaceEntity)
    fun takeThePlace(place: PlaceEntity)
}