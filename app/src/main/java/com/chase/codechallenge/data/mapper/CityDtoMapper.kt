package com.chase.codechallenge.data.mapper

import com.chase.codechallenge.data.datasource.remote.api.entity.CityDto
import com.chase.codechallenge.data.datasource.remote.api.entity.CoordDto
import com.chase.codechallenge.domain.mapper.IEntityMapper
import com.chase.codechallenge.domain.model.City
import com.chase.codechallenge.domain.model.Coord
import javax.inject.Inject

class CityDtoMapper @Inject constructor() : IEntityMapper<CityDto, City> {
    override fun mapFromEntity(entity: CityDto): City {
        return City(
            entity.country,
            entity.timezone,
            entity.sunrise,
            entity.sunset,
            entity.cityName,
            Coord(
                entity.coordinate.latitude,
                entity.coordinate.longitude
            )
        )
    }

    override fun entityFromModel(model: City): CityDto {
        return CityDto(
            model.country,
            model.timezone,
            model.sunrise,
            model.sunset,
            model.cityName,
            CoordDto(
                model.coordinate.latitude,
                model.coordinate.longitude
            )
        )
    }

}