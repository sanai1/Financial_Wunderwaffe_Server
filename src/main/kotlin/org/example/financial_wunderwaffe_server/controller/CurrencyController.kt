package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.view.CurrencyView
import org.example.financial_wunderwaffe_server.service.CurrencyService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class CurrencyController (
    private val currencyService: CurrencyService
){

    @GetMapping("/currency")
    fun getCurrencies(): List<CurrencyView> =
        currencyService.getCurrencies()

    @PostMapping("/admin/currency")
    fun addCurrency(@RequestBody currencyViewList: List<CurrencyView>): List<CurrencyView> =
        currencyService.createCurrencyList(currencyViewList)

    @PutMapping("/admin/currency")
    fun updateCurrencyByID(@RequestBody currencyView: CurrencyView): CurrencyView? =
        currencyService.updateCurrency(currencyView)

}