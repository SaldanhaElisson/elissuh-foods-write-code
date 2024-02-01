package com.elisson.elissuhfoods

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elisson.elissuhfoods.Product.Product
import com.elisson.elissuhfoods.Product.ProductPurchase
import com.elisson.elissuhfoods.adapter.ProductAdaptor
import com.elisson.elissuhfoods.databinding.ActivityMainBinding
import com.elisson.elissuhfoods.listItems.DessertsFoods
import com.elisson.elissuhfoods.listItems.DrinksFoods
import com.elisson.elissuhfoods.listItems.FirstCourseFoods
import com.elisson.elissuhfoods.listItems.Foods
import com.elisson.elissuhfoods.listItems.MainDishesFoods

class MainActivity : AppCompatActivity(), View.OnClickListener, QuantifyListerner {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdaptor: ProductAdaptor
    private val firstCourseFoods = FirstCourseFoods()
    private val mainDishesFoods = MainDishesFoods()
    private val dessetsFoods = DessertsFoods()
    private val drinksFoods = DrinksFoods()
    private val productsList: MutableList<Product> = mutableListOf()
    private val productsPurchCase = ProductPurchase()
    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        recyclerView = findViewById(R.id.recyclerViewFoods)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.setHasFixedSize(true)

        binding.btFirstCourse.setOnClickListener(this)
        binding.btMainDishes.setOnClickListener(this)
        binding.btDessert.setOnClickListener(this)
        binding.btDrink.setOnClickListener(this)
        binding.btDetailsRequest.setOnClickListener(this)
        binding.btFinish.setOnClickListener(this)

        val flagLayoutDetailsRequest =
            intent.extras?.getBoolean("flagLayoutDetailsRequest") ?: false


        if (flagLayoutDetailsRequest) {
            getDatasOnLayoutDetailsRequest()
        }

        changeStateBntFirstCourse()
        addViewFoodProducts(firstCourseFoods)
    }

    override fun onClick(viewButton: View) {
        if (viewButton.id == R.id.btFirstCourse) {
            changeStateBntFirstCourse()
            addViewFoodProducts(firstCourseFoods)
        }
        if (viewButton.id == R.id.btMainDishes) {
            changeStateBntMainDishe()
            addViewFoodProducts(mainDishesFoods)
        }
        if (viewButton.id == R.id.btDessert) {
            changeStateBntDesserts()
            addViewFoodProducts(dessetsFoods)
        }
        if (viewButton.id == R.id.btDrink) {
            changeStateBntDrinks()
            addViewFoodProducts(drinksFoods)
        }

        if (viewButton.id == R.id.btDetailsRequest) {
            changeLayoutDetailsRequest(DetailsRequests::class.java)
        }

        if (viewButton.id == R.id.btFinish) {
            if (productsPurchCase.purchaseValue == 0f) {
                Toast.makeText(this, "Pedido não selecionado, por favor selecione algum pedido", Toast.LENGTH_LONG).show()
                return
            }
            changeLayout(ActivityThanks::class.java)
        }
    }

    private fun addViewFoodProducts(foods: Foods) {
        checkItemsSelected(foods)
        productAdaptor = ProductAdaptor(this, productsList, this, productsPurchCase)
        recyclerView!!.adapter = productAdaptor
    }

    private fun changeStateBntFirstCourse() {
        binding.btFirstCourse.setBackgroundResource(R.drawable.bg_button_enabled)
        binding.btFirstCourse.setTextColor(Color.WHITE)

        binding.btMainDishes.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btMainDishes.setTextColor(Color.DKGRAY)

        binding.btDrink.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btDrink.setTextColor(Color.DKGRAY)

        binding.btDessert.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btDessert.setTextColor(Color.DKGRAY)

        binding.sectionName.setText(R.string.FirstCourse)
    }

    private fun changeStateBntMainDishe() {
        binding.btFirstCourse.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btFirstCourse.setTextColor(Color.DKGRAY)

        binding.btMainDishes.setBackgroundResource(R.drawable.bg_button_enabled)
        binding.btMainDishes.setTextColor(Color.WHITE)

        binding.btDrink.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btDrink.setTextColor(Color.DKGRAY)

        binding.btDessert.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btDessert.setTextColor(Color.DKGRAY)

        binding.sectionName.setText(R.string.MainDishe)
    }

    private fun changeStateBntDesserts() {
        binding.btFirstCourse.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btFirstCourse.setTextColor(Color.DKGRAY)

        binding.btMainDishes.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btMainDishes.setTextColor(Color.DKGRAY)

        binding.btDrink.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btDrink.setTextColor(Color.DKGRAY)

        binding.btDessert.setBackgroundResource(R.drawable.bg_button_enabled)
        binding.btDessert.setTextColor(Color.WHITE)


        binding.sectionName.setText(R.string.Dessert)
    }

    private fun changeStateBntDrinks() {
        binding.btFirstCourse.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btFirstCourse.setTextColor(Color.DKGRAY)

        binding.btMainDishes.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btMainDishes.setTextColor(Color.DKGRAY)

        binding.btDrink.setBackgroundResource(R.drawable.bg_button_enabled)
        binding.btDrink.setTextColor(Color.WHITE)

        binding.btDessert.setBackgroundResource(R.drawable.bg_button_disable)
        binding.btDessert.setTextColor(Color.DKGRAY)

        binding.sectionName.setText(R.string.Drink)
    }

    override fun onQuuantify(priceTotal: Float) {
        binding.btFinish.text = "Realizar pedido | R$${"%.2f".format(priceTotal)}"
    }

    fun checkItemsSelected(products: Foods) {
        productsList.clear()

        for (product in products.getProducts()) {
            for (productSelected in productsPurchCase.productsPurchase) {
                if (product.name == productSelected.name && productSelected.selected) {
                    product.selected = true
                }
            }
            productsList.add(product)
        }
    }

    fun <T> changeLayout(Activity: Class<T>) {
        val intent = Intent(this, Activity)
        startActivity(intent)
        finish()
    }

    private fun saveProductSelectedToDetailsRequest(nameProductSelected: ArrayList<String>) {
        productsPurchCase.productsPurchase.forEach {
            nameProductSelected.add(it.name)
        }
    }

    private fun <T> changeLayoutDetailsRequest(Activity: Class<T>) {
        val nameProductSelected = arrayListOf<String>()
        saveProductSelectedToDetailsRequest(nameProductSelected)
        val intent = Intent(this, Activity)
        intent.putExtra("nameProductSelected", nameProductSelected)
        intent.putExtra("txtEditTextObs", productsPurchCase.purchaseOBS)
        startActivity(intent)
        finish()
    }

    private fun getProduct(
        foods: Foods,
        nameProductSelected: ArrayList<String>?
    ) {
        for (product in foods.getProducts()) {
            val findedProduct =
                nameProductSelected?.find { element -> product.name.equals(element) }
            if (findedProduct != null) {
                product.selected = true
                productsPurchCase.purchaseValue += product.price
                productsPurchCase.productsPurchase.add(product)
            }
        }
        onQuuantify(productsPurchCase.purchaseValue)
    }

    private fun getDatasOnLayoutDetailsRequest() {
        val txtEditTextObs = intent.extras?.getString("txtEditTextObs")
        productsPurchCase.purchaseOBS = txtEditTextObs

        val nameProductSelected = intent.extras?.getStringArrayList("nameProductSelected")
        getProduct(firstCourseFoods, nameProductSelected)
        getProduct(mainDishesFoods, nameProductSelected)
        getProduct(dessetsFoods, nameProductSelected)
        getProduct(drinksFoods, nameProductSelected)
    }

}