package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.ResponseTemplate
import org.example.financial_wunderwaffe_server.model.Status
import org.example.financial_wunderwaffe_server.model.repository.CalculationShareOfAssetRepository
import org.example.financial_wunderwaffe_server.model.repository.ShareOfAssetRepository
import org.example.financial_wunderwaffe_server.model.repository.UserAnswerRepository
import org.example.financial_wunderwaffe_server.model.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.view.CalculationShareOfAssetView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CalculationShareOfAssetService(
    private val calculationShareOfAssetRepository: CalculationShareOfAssetRepository,
    private val shareOfAssetRepository: ShareOfAssetRepository,
    private val userRepository: UserRepository,
    private val userAnswerRepository: UserAnswerRepository,
    private val pythonService: PythonService
) {

    fun getCalculationShareOfAssetsByUserUID(userUID: UUID): ResponseTemplate<CalculationShareOfAssetView> {
        val user = userRepository.findByIdOrNull(userUID) // получение пользователя по которому будет все выполняться
        return if (user != null) {
            val questionnaire = userAnswerRepository.findAllByUser(user) // получение анкеты пользователя
            if (questionnaire.isNotEmpty()) {
                val listCalculationShareOfAssetByUser =
                    calculationShareOfAssetRepository.getAllByUser(user) // получаем список всех записей с информацией о записях долей активов
                if (listCalculationShareOfAssetByUser.isNotEmpty()) {
                    val lastCalculationShareOfAsset =
                        listCalculationShareOfAssetByUser.maxBy { it.date } // получаем последнюю запись по дате
                    val listShareOfAssetView =
                        shareOfAssetRepository.getAllByCalculationShareOfAsset(lastCalculationShareOfAsset) // получаем все записи по конкретной информационной записи (основная ее цель - хранение даты)
                    if (listShareOfAssetView.isNotEmpty()) {
                        ResponseTemplate(
                            status = Status(
                                code = 200,
                                message = "OK"
                            ),
                            data = CalculationShareOfAssetView(
                                id = lastCalculationShareOfAsset.id,
                                userUID = lastCalculationShareOfAsset.user.uid,
                                date = lastCalculationShareOfAsset.date,
                                listShareOfAsset = listShareOfAssetView.map { it.toShareOfAssetView() },
                            )
                        )
                    } else ResponseTemplate( // не получилось найти записи по распределению долей активов - ПЛОХАЯ ОШИБКА
                        status = Status(
                            code = 500,
                            message = "Shares of assets Not Found"
                        ),
                        data = null
                    )
                } else pythonService.getNewCalculationShareOfAsset(questionnaire, user) // получен новый расчет долей активов
            } else ResponseTemplate( // не получилось найти заполненную анкету (нужно ее заполнить)
                status = Status(
                    code = 422,
                    message = "Questionnaire Not Found",
                ),
                data = null
            )
        } else ResponseTemplate( // пользователя не получилось найти
            status = Status(
                code = 400,
                message = "User Not Found",
            ),
            data = null
        )
    }

}