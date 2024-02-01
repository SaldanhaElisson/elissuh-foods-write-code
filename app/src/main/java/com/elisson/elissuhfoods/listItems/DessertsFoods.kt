package com.elisson.elissuhfoods.listItems

import com.elisson.elissuhfoods.Product.Product
import com.elisson.elissuhfoods.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DessertsFoods:Foods {
    private var _productList: MutableList<Product> = mutableListOf()

    override fun getProducts(): MutableList<Product> {
        _productList.clear()
        val productList: MutableList<Product> = mutableListOf(
            Product(
                imgProduct = R.drawable.tiramisu,
                name = "Tiramisù",
                price = 20.90f,
                time = 15,
                selected = false
            ),

            Product(
                imgProduct = R.drawable.crepe_suzette,
                name = "Crepe Suzette",
                price = 22.90f,
                time = 15,
                selected = false
            ),

            Product(
                imgProduct = R.drawable.petit_gateau_tradicional,
                name = "Petit Gâteau ",
                price = 27.90f,
                time = 10,
                selected = false
            ),

            Product(
                imgProduct = R.drawable.creme_brulle,
                name = "Crème Brulée",
                price = 17.90f,
                time = 7,
                selected = false
            ),

            )
        _productList = productList.toMutableList()
        return _productList
    }
}