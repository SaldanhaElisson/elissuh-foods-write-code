package com.elisson.elissuhfoods.listItems

import com.elisson.elissuhfoods.Product.Product
import com.elisson.elissuhfoods.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainDishesFoods : Foods {
    private var _productList: MutableList<Product> = mutableListOf()

    override fun getProducts(): MutableList<Product> {
        val productList: MutableList<Product> = mutableListOf(
            Product(
                imgProduct = R.drawable.paillard_de_filet,
                name = "Paillard de filet",
                price = 59.90f,
                time = 30,
                selected = false
            ),

            Product(
                imgProduct = R.drawable.gold_arripa_mustard,
                name = "Gold arripa mustard",
                price = 60.90f,
                time = 45,
                selected = false
            ),

            Product(
                imgProduct = R.drawable.steak_tartare,
                name = "Steak tartare",
                price = 30.90f,
                time = 40,
                selected = false
            ),

            Product(
                imgProduct = R.drawable.risoto_de_cogumelos_com_ancho,
                name = "Risoto de cogumelos",
                price = 35.90f,
                time = 35,
                selected = false
            ),

            )
        _productList = productList.toMutableList()
        return _productList
    }
}