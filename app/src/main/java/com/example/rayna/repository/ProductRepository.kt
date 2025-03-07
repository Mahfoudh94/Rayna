package com.example.rayna.repository

import com.example.rayna.R
import com.example.rayna.model.Product

object ProductRepository {

    private val productList = listOf(
        Product(
            id = "1",
            name = "iPhone 15 Pro",
            cat ="Product",
            description = "The latest iPhone with A17 Pro chip, titanium design, and 48MP camera.",
            price = 999.0,
            rating = 4.6,
            pictureUrl = R.drawable.iphone15        ),
        Product(
            id = "2",
            name = "El Mordjene Chocolate",
            cat ="Product",
            description = "El Mordjène by Cébon is an Algerian roasted hazelnut cream spread..",
            price = 1199.0,
            rating = 3.5,
            pictureUrl = R.drawable.morgan     ),
        Product(
            id = "3",
            name = "MacBook Air M2",
            cat ="Product",
            description = "Ultra-thin and lightweight laptop with M2 chip and Retina display.",
            price = 1199.0,
            rating = 3.5,
            pictureUrl = R.drawable.macbook      ),
        Product(
            id = "4",
            name = "Apple Watch Ultra",
            cat ="Product",
            description = "Rugged and capable smartwatch for extreme adventures.",
            price = 799.0,
            rating = 3.6,
            pictureUrl = R.drawable.applewatch      ),
        Product(
            id = "5",
            name = "Samsung Galaxy Ultra 24",
            cat ="Product",
            description = "Active Noise Cancellation and Adaptive Transparency for immersive sound.",
            price = 249.0,
            rating = 3.5,
            pictureUrl = R.drawable.samsung   ),
        Product(
            id = "6",
            name = "Dahbi Resturant",
            cat = "Shop",
            description = "The ultimate iPad experience with M2 chip and Liquid Retina XDR display.",
            price = 799.0,
            rating = 3.5,
            pictureUrl = R.drawable.resturant
        ),
        Product(
            id = "7",
            name = "TechZone Electronics",
            cat = "Shop",
            description = "A leading electronics store offering the latest gadgets and accessories.",
            price = 10.0,
            rating = 4.7,
            pictureUrl = R.drawable.techzone
        ),
        Product(
            id = "8",
            name = "PlayStation 5 Repair Service",
            cat = "Service",
            description = "Professional repair service for PlayStation 5 consoles with original parts.",
            price = 49.0,
            rating = 4.8,
            pictureUrl = R.drawable.ps5_repair
        ),
        Product(
            id = "9",
            name = "Xbox Series X",
            cat = "Product",
            description = "Powerful gaming console with 12 teraflops of processing power.",
            price = 499.0,
            rating = 4.6,
            pictureUrl = R.drawable.xbox_x
        ),
        Product(
            id = "10",
            name = "Pixel Mobile Store",
            cat = "Shop",
            description = "A premium store specializing in Google Pixel smartphones and accessories.",
            price = 0.0,
            rating = 4.5,
            pictureUrl = R.drawable.phone_shop
        ),
        Product(
            id = "11",
            name = "Laptop Repair Center",
            cat = "Service",
            description = "Expert repair services for all major laptop brands with fast turnaround.",
            price = 69.0,
            rating = 4.7,
            pictureUrl = R.drawable.laptop_repair
        ),
        Product(
            id = "12",
            name = "Dell XPS 15",
            cat = "Product",
            description = "Powerful ultrabook with a stunning 4K display and Intel i9 processor.",
            price = 1899.0,
            rating = 4.6,
            pictureUrl = R.drawable.xps_15
        ),
        Product(
            id = "13",
            name = "Samsung Galaxy Tab S9",
            cat = "Product",
            description = "Premium Android tablet with AMOLED display and S Pen support.",
            price = 899.0,
            rating = 4.4,
            pictureUrl = R.drawable.tab_s9
        ),
        Product(
            id = "14",
            name = "Pro Photography Studio",
            cat = "Service",
            description = "A professional photography service for events, portraits, and commercial shoots.",
            price = 150.0,
            rating = 4.8,
            pictureUrl = R.drawable.studio
        ),
        Product(
            id = "15",
            name = "Bose SoundLink Revolve+",
            cat = "Product",
            description = "Portable 360-degree Bluetooth speaker with deep bass and great sound clarity.",
            price = 299.0,
            rating = 4.6,
            pictureUrl = R.drawable.bose_revolve
        )

    )

    fun getAllProducts(): List<Product> {
        return productList
    }
}
