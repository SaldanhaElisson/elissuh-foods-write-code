package com.elisson.elissuhfoods.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.elisson.elissuhfoods.Product.Product
import com.elisson.elissuhfoods.Product.ProductPurchase
import com.elisson.elissuhfoods.QuantifyListerner
import com.elisson.elissuhfoods.databinding.ActivityProductItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class ProductAdaptor(
    private val context: Context,
    private val productList: MutableList<Product>,
    val quantifyListerner: QuantifyListerner,
    val productsPurchCase: ProductPurchase
) : RecyclerView.Adapter<ProductAdaptor.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val listItem =
            ActivityProductItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductViewHolder(listItem)
    }

    override fun getItemCount() = productList.size


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.imgFood.setBackgroundResource(productList[position].imgProduct)
        holder.txtNameProduct.text = productList[position].name
        holder.txtPriceProduct.text = "R$${"%.2f".format(productList[position].price)}"
        holder.txtTimeProduct.text = "${productList[position].time} min"


        if (productList[position].selected) {
            holder.itemCheckBox.isChecked = true
        }

        holder.itemCheckBox.setOnClickListener {
            if (holder.itemCheckBox.isChecked()) {
                productList[position].selected = true
                productsPurchCase.purchaseValue += productList[position].price

                productsPurchCase.productsPurchase.add(productList[position])

            } else {
                productsPurchCase.purchaseValue -= productList[position].price
                productsPurchCase.productsPurchase.remove(productList[position])
            }
            quantifyListerner.onQuuantify(productsPurchCase.purchaseValue)
        }
    }

    inner class ProductViewHolder(binding: ActivityProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgFood = binding.imgFood
        val txtNameProduct = binding.txtNameProduct
        val txtPriceProduct = binding.txtPrice
        val txtTimeProduct = binding.txtTimeProduct
        val itemCheckBox = binding.itemCheckBox
    }

}