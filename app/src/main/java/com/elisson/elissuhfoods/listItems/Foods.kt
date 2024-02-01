package com.elisson.elissuhfoods.listItems

import com.elisson.elissuhfoods.Product.Product

interface Foods {
    fun getProducts(): MutableList<Product>
}