package com.example.apapun.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.example.apapun.R
import com.example.apapun.data.dummy.DummyData
import com.example.apapun.data.model.Category
import com.example.apapun.data.model.Product
import com.example.apapun.ui.theme.ApapunTheme

import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarProductScreen(
    navController: NavController
) {

    var selectedCategoryId by rememberSaveable {
        mutableStateOf(
            DummyData.categories.firstOrNull()?.id
        )
    }

    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }

    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }

    var filteredProducts by remember {
        mutableStateOf(emptyList<Product>())
    }

    LaunchedEffect(
        key1 = selectedCategoryId,
        key2 = searchQuery
    ) {

        isLoading = true

        delay(1000)

        val filteredByCategory =
            if (selectedCategoryId != null) {

                DummyData.products.filter {
                    it.category_id == selectedCategoryId
                }

            } else {

                DummyData.products

            }

        filteredProducts =
            if (searchQuery.isBlank()) {

                filteredByCategory

            } else {

                filteredByCategory.filter {

                    it.name.contains(
                        other = searchQuery,
                        ignoreCase = true
                    )

                }

            }

        isLoading = false
    }

    StatelessDaftarProduct(

        categories = DummyData.categories,

        selectedCategoryId = selectedCategoryId,

        onCategorySelected = {
            selectedCategoryId = it
        },

        searchQuery = searchQuery,

        onSearchQueryChange = {
            searchQuery = it
        },

        isLoading = isLoading,

        products = filteredProducts,

        onProductClick = { product: Product ->

            navController.navigate(
                "detail/${product.id}"
            )

        },

        onContactUsClick = {

            navController.navigate(
                "hubungi_kami"
            )

        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatelessDaftarProduct(

    categories: List<Category>,

    selectedCategoryId: Int?,

    onCategorySelected: (Int) -> Unit,

    searchQuery: String,

    onSearchQueryChange: (String) -> Unit,

    isLoading: Boolean,

    products: List<Product>,

    onProductClick: (Product) -> Unit,

    onContactUsClick: () -> Unit

) {

    val context = LocalContext.current

    var expanded by remember {
        mutableStateOf(false)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Daftar Produk UMKM")
                },

                actions = {

                    // Tombol keranjang
                    IconButton(

                        onClick = {

                            android.widget.Toast
                                .makeText(
                                    context,
                                    "Keranjang diklik",
                                    android.widget.Toast.LENGTH_SHORT
                                )
                                .show()

                        }

                    ) {

                        Icon(

                            imageVector =
                                Icons.Default.ShoppingCart,

                            contentDescription =
                                "Keranjang"

                        )
                    }


                    // Tombol menu titik tiga
                    IconButton(

                        onClick = {
                            expanded = true
                        }

                    ) {

                        Icon(

                            imageVector =
                                Icons.Default.MoreVert,

                            contentDescription =
                                "Menu"

                        )
                    }


                    // Dropdown menu
                    DropdownMenu(

                        expanded = expanded,

                        onDismissRequest = {
                            expanded = false
                        }

                    ) {

                        DropdownMenuItem(

                            text = {
                                Text("Hubungi Kami")
                            },

                            onClick = {

                                expanded = false

                                onContactUsClick()

                            },

                            leadingIcon = {

                                Icon(

                                    imageVector =
                                        Icons.Default.Email,

                                    contentDescription =
                                        "Email"

                                )
                            }
                        )
                    }
                }
            )
        }

    ) { paddingValues ->


        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {


            // Search
            OutlinedTextField(

                value = searchQuery,

                onValueChange =
                    onSearchQueryChange,

                label = {
                    Text("Cari produk...")
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),

                singleLine = true

            )


            // Judul kategori
            Text(

                text = "Kategori Produk",

                style =
                    MaterialTheme.typography.titleLarge,

                modifier =
                    Modifier.padding(16.dp)

            )


            // Daftar kategori
            LazyRow(

                contentPadding =
                    PaddingValues(horizontal = 16.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(8.dp)

            ) {

                items(categories) { category ->

                    CategoryItem(

                        category = category,

                        isSelected =
                            category.id ==
                                    selectedCategoryId,

                        onClick = {

                            onCategorySelected(
                                category.id
                            )

                        }
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // Judul daftar produk
            Text(

                text = "Daftar Produk",

                style =
                    MaterialTheme.typography.titleLarge,

                modifier =
                    Modifier.padding(
                        horizontal = 16.dp
                    )

            )


            // Loading
            if (isLoading) {

                Box(

                    modifier =
                        Modifier.fillMaxSize(),

                    contentAlignment =
                        Alignment.Center

                ) {

                    Column(

                        horizontalAlignment =
                            Alignment.CenterHorizontally

                    ) {

                        CircularProgressIndicator()

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        Text("Mencari data...")
                    }
                }

            }

            // Tidak ada produk
            else if (products.isEmpty()) {

                Box(

                    modifier =
                        Modifier.fillMaxSize(),

                    contentAlignment =
                        Alignment.Center

                ) {

                    Text(
                        "Produk tidak ditemukan."
                    )
                }

            }

            // Ada produk
            else {

                LazyVerticalGrid(

                    columns =
                        GridCells.Fixed(2),

                    contentPadding =
                        PaddingValues(16.dp),

                    horizontalArrangement =
                        Arrangement.spacedBy(16.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(16.dp),

                    modifier =
                        Modifier.fillMaxSize()

                ) {

                    items(
                        items = products,
                        key = { product: Product ->
                            product.id
                        }
                    ) { product: Product ->

                        ProductItemCard(

                            product = product,

                            onClick = {

                                onProductClick(
                                    product
                                )

                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ProductItemCard(

    product: Product,

    onClick: () -> Unit

) {

    Card(

        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surface
            )

    ) {

        Column {


            val imageRes =
                if (product.img == "dummy_product") {

                    R.mipmap.dummy_product_foreground

                } else {

                    R.mipmap.dummy_product_foreground

                }


            Box(

                modifier =
                    Modifier.fillMaxWidth()

            ) {

                Image(

                    painter =
                        painterResource(
                            id = imageRes
                        ),

                    contentDescription =
                        product.name,

                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(
                            RoundedCornerShape(
                                topStart = 8.dp,
                                topEnd = 8.dp
                            )
                        )
                        .background(Color.White),

                    contentScale =
                        ContentScale.Fit

                )


                // Label kategori
                Box(

                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        )
                        .background(
                            MaterialTheme
                                .colorScheme
                                .secondary
                        )

                ) {

                    Text(

                        text =
                            product.category.name,

                        style =
                            MaterialTheme
                                .typography
                                .labelSmall,

                        color =
                            MaterialTheme
                                .colorScheme
                                .onSecondary,

                        modifier =
                            Modifier.padding(
                                horizontal = 8.dp,
                                vertical = 2.dp
                            )
                    )
                }
            }


            Column(

                modifier =
                    Modifier.padding(8.dp)

            ) {

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )


                Text(

                    text = product.name,

                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,

                    fontWeight =
                        FontWeight.Bold,

                    maxLines = 1,

                    overflow =
                        TextOverflow.Ellipsis

                )


                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )


                Text(

                    text =
                        "Rp ${product.price}",

                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium,

                    color =
                        MaterialTheme
                            .colorScheme
                            .primary

                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProduct() {

    ApapunTheme {

        ProductItemCard(

            product =
                DummyData.products[0],

            onClick = {}

        )
    }
}