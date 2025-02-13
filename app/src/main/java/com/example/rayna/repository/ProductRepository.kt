package com.example.rayna.repository

import com.example.rayna.R
import com.example.rayna.model.Product

object ProductRepository {
//حولت كل صورة من نوع int الي string//
    private val productList = listOf(
        Product(
            id = "1",
            name = "tridda",
            cat="Shop",
            description = "The latest iPhone with A17 Pro chip, titanium design, and 48MP camera.",
            price = 999.0,
            rating = 3.5,
            pictureUrl = R.drawable.img.toString()
        ),
        Product(
            id = "2",
            name = "Mourdjan",
            cat="Shop",
            description = "Ultra-thin and lightweight laptop with M2 chip and Retina display.",
            price = 1199.0,
            rating = 3.5,
            pictureUrl = R.drawable.img_1.toString()
        ),
        Product(
            id = "3",
            name = "danone",
            cat="Shop",
            description = "Rugged and capable smartwatch for extreme adventures.",
            price = 799.0,
            rating = 3.5,
            pictureUrl = R.drawable.img_2.toString()
        ),
        Product(
            id = "4",
            name = "Aroma",
            cat="Shop",
            description = "Active Noise Cancellation and Adaptive Transparency for immersive sound.",
            price = 249.0,
            rating = 3.5,
            pictureUrl = R.drawable.img_3.toString()
        ),
        Product(
            id = "5",
            name = "tango",
            cat="Shop",
            description = "The ultimate iPad experience with M2 chip and Liquid Retina XDR display.",
            price = 799.0,
            rating = 3.5,
            pictureUrl = R.drawable.img_4.toString()
        )
    )

    fun getAllProducts(): List<Product> {
        return productList
    }
}

