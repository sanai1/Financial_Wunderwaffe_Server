package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.repository.TypeAssetRepository
import org.example.financial_wunderwaffe_server.model.view.TypeAssetView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TypeAssetsService(
    private val typeAssetRepository: TypeAssetRepository
) {

    fun findAll(): List<TypeAssetView> = typeAssetRepository.findAll().map { it.toTypeAssetView() }

    fun createTypeAsset(typeAssetView: TypeAssetView): Long =
        typeAssetRepository.save(typeAssetView.toTypeAssetEntity()).id

    fun updateTypeAssetByID(typeAssetView: TypeAssetView): Boolean {
        val typeAsset = typeAssetRepository.findByIdOrNull(typeAssetView.id)
        return if (typeAsset != null) {
            typeAssetRepository.save(typeAssetView.toTypeAssetEntity())
            true
        } else false
    }

}