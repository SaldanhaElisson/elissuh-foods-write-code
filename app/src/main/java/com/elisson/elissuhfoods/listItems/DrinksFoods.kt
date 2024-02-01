package com.elisson.elissuhfoods.listItems

import com.elisson.elissuhfoods.Product.Product
import com.elisson.elissuhfoods.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DrinksFoods : Foods {
    private var _productList: MutableList<Product> = mutableListOf()

    override fun getProducts(): MutableList<Product> {
        _productList.clear()
        val productList: MutableList<Product> = mutableListOf(
            Product(
                imgProduct = R.drawable.cosmopolitan,
                name = "Cosmopolitan",
                price = 21.90f,
                time = 10,
                selected = false
            ),

            Product(
                imgProduct = R.drawable.negroni,
                name = "Negroni",
                price = 15.90f,
                time = 5,
                selected = false
            ),

            Product(
                imgProduct = R.drawable.martini,
                name = "Martini",
                price = 20.90f,
                time = 7,
                selected = false
            ),

            Product(
                imgProduct = R.drawable.wisky_sour,
                name = "Whisky SOur",
                price = 17.90f,
                time = 7,
                selected = false
            ),

            )
        _productList = productList.toMutableList()
        return _productList
    }
}