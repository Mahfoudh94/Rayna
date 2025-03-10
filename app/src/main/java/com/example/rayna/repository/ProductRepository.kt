package com.example.rayna.repository

import com.example.rayna.model.Product

object ProductRepository {

    private val productList = listOf(

        Product(
            id = "1",
            name = "Milkshake Product",
            description = "Delicious milkshake with a variety of flavors and refreshing natural ingredients.",
            price = 1000.0,
            pictureUrl = "https://www.crazycheesy.com/wp-content/uploads/2023/01/Oreo-Kitkat-Milkshake-1.jpg"
        ),
        Product(
            id = "2",
            name = "Family Mood Restaurant Place",
            description = "A unique family-friendly restaurant offering delicious dishes in a warm and cozy atmosphere.",
            price = 0.0, // Since this is a place, not a physical product
            pictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4FuUXM4nMi5qKOKiR6iQhTHbu6sZcrn55ZA&s"
        ),
        Product(
            id = "3",
            name = "Al-Murjan Chocolate",
            description = "Premium chocolate with a rich flavor and smooth texture for true chocolate lovers.",
            price = 1800.0,
            pictureUrl = "https://s.france24.com/media/display/40de88c8-7506-11ef-928a-005056a90284/w:1280/p:1x1/5b5b710780b8c6bf024281905393c6300ed43672.jpg"
        ),
        Product(
            id = "4",
            name = "Pistachio Chocolate",
            description = "Delicious chocolate with pistachio pieces, a perfect blend of flavors and a crunchy texture.",
            price = 2000.0,
            pictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTv726z9ohf55lUAu4K6Z18S6NWQyqljsGSAg&s"
        ),
        Product(
            id = "5",
            name = "Olive Oil",
            description = "Pure Algerian olive oil, extracted from the finest local olives.",
            price = 1200.0,
            pictureUrl = "https://sarayoman.com/wp-content/uploads/2023/01/olive-gosne.png"
        )
    )
    fun getAllProducts(): List<Product> {
        return productList
    }
}
