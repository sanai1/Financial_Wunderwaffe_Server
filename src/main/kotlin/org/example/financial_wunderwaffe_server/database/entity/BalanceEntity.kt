//package org.example.financial_wunderwaffe_server.database.entity
//
//import jakarta.persistence.*
//import org.example.financial_wunderwaffe_server.model.request.BalanceView
//
//@Entity
//@Table(name = "Balance")
//data class BalanceEntity(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long = 0L,
//
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    val user: UserEntity,
//
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    val currency: CurrencyEntity,
//
//    @Column(nullable = false)
//    val amount: Long
//) {
//    fun toBalanceView(): BalanceView =
//        BalanceView(
//            id = id,
//            userUID = user.uid,
//            currency = currency.token,
//            amount = amount
//        )
//}