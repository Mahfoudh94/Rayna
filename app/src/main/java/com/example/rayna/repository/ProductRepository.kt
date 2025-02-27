package com.example.rayna.repository

import com.example.rayna.model.Product

object ProductRepository {

    private val productList = listOf(
        Product(
            id = "1",
            name = "iPhone 15 Pro",
            description = "The latest iPhone with A17 Pro chip, titanium design, and 48MP camera.",
            price = 999.0,
            pictureUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1693009279096"
        ),
        Product(
            id = "2",
            name = "MacBook Air M2",
            description = "Ultra-thin and lightweight laptop with M2 chip and Retina display.",
            price = 1199.0,
            pictureUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/macbook-air-m2-blue-select-20220606?wid=904&hei=840&fmt=jpeg&qlt=90&.v=1653084303665"
        ),
        Product(
            id = "3",
            name = "Apple Watch Ultra",
            description = "Rugged and capable smartwatch for extreme adventures.",
            price = 799.0,
            pictureUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/MT3U3ref_VW_34FR+watch-49-titanium-ultra_VW_34FR_WF_CO?wid=750&hei=712&fmt=p-jpg&qlt=95&.v=1660713657930,1660927566964"
        ),
        Product(
            id = "4",
            name = "AirPods Pro (2nd Gen)",
            description = "Active Noise Cancellation and Adaptive Transparency for immersive sound.",
            price = 249.0,
            pictureUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/MQD83?wid=572&hei=572&fmt=jpeg&qlt=95&.v=1660803972361"
        ),
        Product(
            id = "5",
            name = "iPad Pro",
            description = "The ultimate iPad experience with M2 chip and Liquid Retina XDR display.",
            price = 799.0,
            pictureUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/ipad-pro-finish-select-202212-12-9inch-space-gray-wifi?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1670862799134"
        ),
        Product(
            id = "6",
            name = "Al Murjan Chocolate",
            description = "Al Murjan chocolate is currently trending worldwide.",
            price = 149.0,
            pictureUrl = "https://static.euronews.com/articles/stories/08/73/29/30/1200x675_cmsv2_e2f59f00-b4e6-544d-b2a4-52618c109b11-8732930.jpg"
        ),
        Product(
            id = "7",
            name = "Laptop Stand",
            description = "The laptop stand helps ventilate its fan.",
            price = 205.0,
            pictureUrl = "https://net-eshop.com/wp-content/uploads/2023/07/71yx00O20cL._AC_UF8941000_QL80_.jpg"
        ),

    )

    fun getAllProducts(): List<Product> {
        return productList
    }
}

