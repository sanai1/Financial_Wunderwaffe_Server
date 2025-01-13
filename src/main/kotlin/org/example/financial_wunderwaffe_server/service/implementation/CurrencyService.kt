package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.entity.CurrencyEntity
import org.example.financial_wunderwaffe_server.database.repository.CurrencyRepository
import org.example.financial_wunderwaffe_server.model.request.CurrencyView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CurrencyService (
    private val currencyRepository: CurrencyRepository
){

    fun getCurrencies(): List<CurrencyView> =
        currencyRepository.findAll().map { it.toCurrencyView() }

    fun createCurrencyList(currencyViewList: List<CurrencyView>): List<CurrencyView> =
        currencyViewList.map { createCurrency(it.toCurrencyEntity()).toCurrencyView() }

    private fun createCurrency(currencyEntity: CurrencyEntity): CurrencyEntity =
        currencyRepository.save(currencyEntity)

    fun updateCurrency(currencyView: CurrencyView): CurrencyView? {
        val currency = currencyRepository.findByIdOrNull(currencyView.id)
        return if (currency != null)
            currencyRepository.save(currencyView.toCurrencyEntity(currencyView.id)).toCurrencyView()
        else null
    }

}