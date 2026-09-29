package com.example.apapun.ui.screen

import android.widget.Toast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.example.apapun.R
import com.example.apapun.data.dummy.DummyData
import com.example.apapun.data.model.Product

import kotlinx.coroutines.delay


@Composable
fun DetailProductScreen(
    productId: Int,
    navController: NavController?
) {
    val context = androidx.compose.ui.platform.LocalContext.current

    var isLoading by remember {
        mutableStateOf(true)
    }

    var product by remember {
        mutableStateOf<Product?>(null)
    }

    var quantity by rememberSaveable {
        mutableStateOf(1)
    }

    LaunchedEffect(productId) {

        isLoading = true

        delay(1000)

        product = DummyData.products.find {
            it.id == productId
        }

        isLoading = false
    }

    StatelessDetailProduct(
        product = product,
        isLoading = isLoading,
        quantity = quantity,
        onQuantityChange = {
            quantity = it
        },
        onBackClick = {
            navController?.popBackStack()
        },
        onAddToCartClick = {
            Toast.makeText(
                context,
                "Dimasukkan: $quantity",
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}


@Composable
fun StatelessDetailProduct(
    product: Product?,
    isLoading: Boolean,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit
) {
    Scaffold { paddingValues ->

        if (isLoading) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        } else if (product != null) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {

                val imageRes =
                    if (product.img == "dummy_product") {
                        R.mipmap.dummy_product_foreground
                    } else {
                        R.mipmap.dummy_product_foreground
                    }

                Image(
                    painter = painterResource(
                        id = imageRes
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                )

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        product.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Rp ${product.price}",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(
                        "Deskripsi",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        product.description ?: ""
                    )

                    Text(
                        "Stok: ${product.stock}"
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text("Jumlah Beli")

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            FilledTonalButton(
                                onClick = {
                                    if (quantity > 1) {
                                        onQuantityChange(
                                            quantity - 1
                                        )
                                    }
                                },
                                enabled = quantity > 1
                            ) {
                                Text("-")
                            }

                            Text(
                                quantity.toString(),
                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                )
                            )

                            FilledTonalButton(
                                onClick = {
                                    if (quantity < product.stock) {
                                        onQuantityChange(
                                            quantity + 1
                                        )
                                    }
                                },
                                enabled = quantity < product.stock
                            ) {
                                Text("+")
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Button(
                        onClick = onAddToCartClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = product.stock > 0 && quantity > 0
                    ) {
                        Text("Tambah ke Keranjang")
                    }
                }
            }

        } else {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Produk tidak ditemukan")
            }
        }
    }
}