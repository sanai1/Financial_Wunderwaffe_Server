package org.example.financial_wunderwaffe_server.database.repository

import org.example.financial_wunderwaffe_server.database.entity.TypeAssetEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TypeAssetRepository: JpaRepository<TypeAssetEntity, Long>