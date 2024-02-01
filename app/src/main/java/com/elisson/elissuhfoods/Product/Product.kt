package com.elisson.elissuhfoods.Product

data class Product(
    val imgProduct: Int,
    val name: String,
    val price: Float,
    val time: Int,
    var selected: Boolean
)
