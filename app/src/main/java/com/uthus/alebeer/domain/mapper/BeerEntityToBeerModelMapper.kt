package com.uthus.alebeer.domain.mapper

import com.uthus.alebeer.data.entity.BeerEntity
import com.uthus.alebeer.data.model.BeerModel

interface BeerEntityToBeerModelMapper {
    fun transform(from: BeerEntity) : BeerModel
}

class BeerEntityToBeerModelMapperImpl : BeerEntityToBeerModelMapper {
    override fun transform(from: BeerEntity): BeerModel {
        return BeerModel(
            id = from.id,
            name = from.name,
            price = from.price,
            saleOffTime = from.saleOffTime,
            image = from.image,
            note = from.note,
            rating = null,
            isSaved = from.isSaved
        )
    }

}