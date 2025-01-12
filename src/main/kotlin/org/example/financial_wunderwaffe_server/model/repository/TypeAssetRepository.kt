package org.example.financial_wunderwaffe_server.model.repository

import org.example.financial_wunderwaffe_server.model.entity.TypeAssetEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TypeAssetRepository: JpaRepository<TypeAssetEntity, Long>