package com.uthus.alebeer.domain.mapper

import com.uthus.alebeer.data.entity.BeerEntity
import com.uthus.alebeer.data.model.BeerModel

interface BeerModelToBeerEntityMapper {
    fun transform(from: BeerModel) : BeerEntity
}

class BeerModelToBeerEntityMapperImpl : BeerModelToBeerEntityMapper {
    override fun transform(from: BeerModel): BeerEntity {
        return BeerEntity(
            id = from.id,
            name = from.name,
            price = from.price,
            saleOffTime = from.saleOffTime,
            image = from.image,
            note = from.note ?: "",
        )
    }

}