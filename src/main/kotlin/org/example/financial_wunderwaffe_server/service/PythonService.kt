package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.ResponseTemplate
import org.example.financial_wunderwaffe_server.model.Status
import org.example.financial_wunderwaffe_server.model.entity.CalculationShareOfAssetEntity
import org.example.financial_wunderwaffe_server.model.entity.ShareOfAssetEntity
import org.example.financial_wunderwaffe_server.model.entity.UserAnswerEntity
import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import org.example.financial_wunderwaffe_server.model.repository.CalculationShareOfAssetRepository
import org.example.financial_wunderwaffe_server.model.repository.ShareOfAssetRepository
import org.example.financial_wunderwaffe_server.model.repository.TypeAssetRepository
import org.example.financial_wunderwaffe_server.model.view.CalculationShareOfAssetView
import org.example.financial_wunderwaffe_server.service.retrofit.python.PythonSingletonAPI
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
                if (typeAssetEntity == null)
                    ResponseTemplate(
                        status = Status(
                            code = 500,
                            message = "TypeAsset Not Found",
                        ),
                        data = null
                    )
                listShareOfAssetEntity.add(
                    ShareOfAssetEntity(
                        calculationShareOfAsset = calculationShareOfAssetEntity,
                        typeAsset = typeAssetEntity!!,
                        share = it.share
                    )
                )
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