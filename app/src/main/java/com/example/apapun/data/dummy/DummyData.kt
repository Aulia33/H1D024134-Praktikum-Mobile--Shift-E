package com.example.apapun.data.dummy
import com.example.apapun.data.model.Category
import com.example.apapun.data.model.Product
object DummyData {
    val categories = listOf(
        Category(id = 1, name = "Makanan", description = "Aneka Makanan Lokal", products_count = 5),
        Category(id = 2, name = "Minuman", description = "Minuman Segar", products_count = 5),
        Category(id = 3, name = "Kerajinan", description = "Kerajinan Tangan", products_count = 5)
    )
    val products = listOf(
        Product(id = 1, category_id = 1, category = categories[0], name = "Keripik Singkong", description = "Keripik gurih", price = 15000.0, stock = 50, img = "dummy_product"),
        Product(id = 2, category_id = 1, category = categories[0], name = "Bakso", description = "Bakso Bulat Makanan", price = 20000.0, stock = 30, img = "dummy_product"),
        Product(id = 3, category_id = 1, category = categories[0], name = "Sate Pisang", description = "Pisang Bakar", price = 25000.0, stock = 20, img = "dummy_product"),
        Product(id = 4, category_id = 1, category = categories[0], name = "Getuk Goreng", description = "Getuk khas", price = 30000.0, stock = 40, img = "dummy_product"),
        Product(id = 5, category_id = 1, category = categories[0], name = "Nasi", description = "Nasi rica sehat", price = 20000.0, stock = 60, img = "dummy_product"),
        Product(id = 6, category_id = 2, category = categories[1], name = "Es Dawet", description = "Minuman segar", price = 10000.0, stock = 100, img = "dummy_product"),
        Product(id = 7, category_id = 2, category = categories[1], name = "Kopi Robusta", description = "Kopi khas", price = 15000.0, stock = 20, img = "dummy_product"),
        Product(id = 8, category_id = 2, category = categories[1], name = "Wedang Jahe", description = "Jahe hangat", price = 12000.0, stock = 50, img = "dummy_product"),
        Product(id = 9, category_id = 2, category = categories[1], name = "Teh Poci", description = "Teh khas", price = 15000.0, stock = 40, img = "dummy_product"),
        Product(id = 10, category_id = 2, category = categories[1], name = "Sirup Stroberi", description = "Sirup rasa", price = 35000.0, stock = 15, img = "dummy_product"),
        Product(id = 11, category_id = 3, category = categories[2], name = "Batik Purbalingga", description = "Motif batik", price = 150000.0, stock = 10, img = "dummy_product"),
        Product(id = 12, category_id = 3, category = categories[2], name = "Sandal Bambu", description = "Sandal unik", price = 45000.0, stock = 25, img = "dummy_product"),
        Product(id = 13, category_id = 3, category = categories[2], name = "Sapu Glagah", description = "Sapu lantai", price = 25000.0, stock = 100, img = "dummy_product"),
        Product(id = 14, category_id = 3, category = categories[2], name = "Keranjang Akar", description = "Keranjang anyam", price = 50000.0, stock = 15, img = "dummy_product"),
        Product(id = 15, category_id = 3, category = categories[2], name = "Tas Bambu", description = "Tas unik", price = 75000.0, stock = 30, img = "dummy_product")
    )
}