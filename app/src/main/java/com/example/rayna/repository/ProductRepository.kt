package com.example.rayna.repository

import com.example.rayna.R
import com.example.rayna.model.Product

object ProductRepository {

    private val productList = listOf(
        Product(
            id = "1",
            name = "iPhone 15 Pro",
            cat="Shop",
            description = "The latest iPhone with A17 Pro chip, titanium design, and 48MP camera.",
            price = 999.0,
            rating = 3.5,
            pictureUrl = R.drawable.pro        ),
        Product(
            id = "2",
            name = "MacBook Air M2",
            cat="Shop",
            description = "Ultra-thin and lightweight laptop with M2 chip and Retina display.",
            price = 1199.0,
            rating = 3.5,
            pictureUrl = R.drawable.pr      ),
        Product(
            id = "3",
            name = "Apple Watch Ultra",
            cat="Shop",
            description = "Rugged and capable smartwatch for extreme adventures.",
            price = 799.0,
            rating = 3.5,
            pictureUrl = R.drawable.pro       ),
        Product(
            id = "4",
            name = "AirPods Pro (2nd Gen)",
            cat="Shop",
            description = "Active Noise Cancellation and Adaptive Transparency for immersive sound.",
            price = 249.0,
            rating = 3.5,
            pictureUrl = R.drawable.p        ),
        Product(
            id = "5",
            name = "iPad Pro",
            cat="Shop",
            description = "The ultimate iPad experience with M2 chip and Liquid Retina XDR display.",
            price = 799.0,
            rating = 3.5,
            pictureUrl = R.drawable.s        )
    )

    fun getAllProducts(): List<Product> {
        return productList
    }
}
