package ru.hse.cinema.dao

import ru.hse.cinema.entity.PlaceEntity

class InMemoryPlaceDao : PlaceDao{
    override fun freeUpSpace(place: PlaceEntity) {
        place.usage = true
    }

    override fun takeThePlace(place: PlaceEntity) {
        place.usage = false
    }
}