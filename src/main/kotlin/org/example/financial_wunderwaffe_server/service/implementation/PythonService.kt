package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.model.response.ResponseTemplate
import org.example.financial_wunderwaffe_server.model.response.Status
import org.example.financial_wunderwaffe_server.database.entity.CalculationShareOfAssetEntity
import org.example.financial_wunderwaffe_server.database.entity.ShareOfAssetEntity
import org.example.financial_wunderwaffe_server.database.entity.UserAnswerEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import org.example.financial_wunderwaffe_server.database.repository.CalculationShareOfAssetRepository
import org.example.financial_wunderwaffe_server.database.repository.ShareOfAssetRepository
import org.example.financial_wunderwaffe_server.database.repository.TypeAssetRepository
import org.example.financial_wunderwaffe_server.model.request.CalculationShareOfAssetView
import org.example.financial_wunderwaffe_server.service.implementation.retrofit.python.PythonSingletonAPI
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.jvm.optionals.getOrNull

@Service
class PythonService (
    private val calculationShareOfAssetRepository: CalculationShareOfAssetRepository,
    private val shareOfAssetRepository: ShareOfAssetRepository,
    private val typeAssetRepository: TypeAssetRepository
){

    fun getNewCalculationShareOfAsset(questionnaire: List<UserAnswerEntity>, user: UserEntity): ResponseTemplate<CalculationShareOfAssetView> {
        val response = PythonSingletonAPI.getAPI().getPercentage(questionnaire.map { it.toUserAnswerView() }).execute()
        return if (response.isSuccessful && response.body() != null && response.code() == 200) {
            val listPercentage = response.body()!! // TODO: при необходимости сделать проверку на пустой список
            val listShareOfAssetEntity = mutableListOf<ShareOfAssetEntity>()
            val calculationShareOfAssetEntity = CalculationShareOfAssetEntity(
                user = user,
                date = LocalDate.now()
            )
            listPercentage.forEach {
                val typeAssetEntity = typeAssetRepository.findById(it.typeAssetID).getOrNull()
                if (typeAssetEntity == null) {
                    ResponseTemplate(
                        status = Status(
                            code = 500,
                            message = "TypeAsset Not Found",
                        ),
                        data = null
                    )
                } else {
                    listShareOfAssetEntity.add(
                        ShareOfAssetEntity(
                            calculationShareOfAsset = calculationShareOfAssetEntity,
                            typeAsset = typeAssetEntity,
                            share = it.share
                        )
                    )
                }
            }
            calculationShareOfAssetRepository.save(calculationShareOfAssetEntity)
            listShareOfAssetEntity.forEach {
                shareOfAssetRepository.save(it)
            }

            ResponseTemplate(
                status = Status(
                    code = 200,
                    message = "OK"
                ),
                data = calculationShareOfAssetEntity.toCalculationShareOfAssetView(
                    listShareOfAssetEntity.map { it.toShareOfAssetView() }
                )
            )
        } else ResponseTemplate(
            status = Status(
                code = 500,
                message = "Invalid Python Server. Code:${response.code()}",
            ),
            data = null
        )
    }

}