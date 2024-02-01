package com.elisson.elissuhfoods.Product

import android.text.Editable

data class ProductPurchase(
    var purchaseValue: Float = 0f,
    val productsPurchase: MutableList<Product> = mutableListOf(),
    var purchaseOBS: String? = null
)
