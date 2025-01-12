package org.example.financial_wunderwaffe_server.model.repository

import org.example.financial_wunderwaffe_server.model.entity.ShareOfAssetEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ShareOfAssetRepository: JpaRepository<ShareOfAssetEntity, Long>