package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.repository.TypeAssetRepository
import org.example.financial_wunderwaffe_server.model.request.TypeAssetView
import org.example.financial_wunderwaffe_server.service.TypeAssetService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TypeAssetsServiceImplementation (
    private val typeAssetRepository: TypeAssetRepository
): TypeAssetService {

    override fun findAll(): List<TypeAssetView> = typeAssetRepository.findAll().map { it.toTypeAssetView() }

    override fun create(typeAssetView: TypeAssetView): Long =
        typeAssetRepository.save(typeAssetView.toTypeAssetEntity()).id

    override fun update(typeAssetView: TypeAssetView): Boolean {
        val typeAsset = typeAssetRepository.findByIdOrNull(typeAssetView.id)
        return if (typeAsset != null) {
            typeAssetRepository.save(typeAssetView.toTypeAssetEntity())
            true
        } else false
    }

}