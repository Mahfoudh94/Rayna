package com.example.rayna.data.repository

import com.example.rayna.data.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseProductRepository @Inject constructor() : ProductRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val productCollection = firestore.collection("products")

    override fun getProducts(): Flow<List<Product>> = callbackFlow {
        val listener = productCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val products = snapshot?.documents?.mapNotNull { doc ->
                doc.toObject(Product::class.java)?.copy(id = doc.id)
            } ?: emptyList()

            trySend(products)
        }

        awaitClose { listener.remove() }
    }

    override suspend fun addProduct(product: Product) {
        productCollection.add(product)
    }
}
//last task of app mobile__