package com.elisson.elissuhfoods.listItems

import com.elisson.elissuhfoods.Product.Product
import com.elisson.elissuhfoods.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FirstCourseFoods : Foods {
    private var _productList: MutableList<Product> = mutableListOf()

    override fun getProducts(): MutableList<Product> {
        val productList: MutableList<Product> = mutableListOf(
            Product(
                imgProduct = R.drawable.polenta_frita_com_queijo_ralado,
                name = "Polenta frita com queijo",
                price = 30.90f,
                time = 20,
                selected = false

            ),
            Product(
                imgProduct = R.drawable.salada_de_folha_verdes,
                name = "Salada de folhas verdes",
                price = 20.90f,
                time = 10,
                selected = false

            ),

            Product(
                imgProduct = R.drawable.bolinha_de_carne_seca,
                name = "Bolinha de carne seca",
                price = 35.90f,
                time = 20,
                selected = false

            ),

            Product(
                imgProduct = R.drawable.bruschetta,
                name = "Bruschetta",
                price = 29.90f,
                time = 25,
                selected = false

            ),

            Product(
                imgProduct = R.drawable.tabua_de_frios,
                name = "Tábua de frios e queijos",
                price = 39.90f,
                time = 30,
                selected = false

            ),

            )
        _productList = productList.toMutableList()
        return _productList
    }
}