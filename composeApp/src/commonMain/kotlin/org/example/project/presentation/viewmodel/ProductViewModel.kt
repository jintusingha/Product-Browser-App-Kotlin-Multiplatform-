package org.example.project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.model.Product
import org.example.project.domain.usecase.GetProductUseCase
import org.example.project.domain.usecase.SearchProductsUseCase
import org.example.project.presentation.state.ProductUiState

class ProductViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    fun loadProducts() {
        viewModelScope.launch {
            try {
                val products = getProductUseCase()
                _uiState.value =
                    ProductUiState.Success(
                        products = products,
                        allProducts = products,
                        selectedCategory = null,
                    )
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            try {
                val products = searchProductsUseCase(query)
                _uiState.value =
                    ProductUiState.Success(
                        products = products,
                        allProducts = products,
                        selectedCategory = null,
                    )
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun filterByCategory(category: String?) {
        val current = _uiState.value as? ProductUiState.Success ?: return
        val filtered =
            if (category == null) {
                current.allProducts
            } else {
                current.allProducts.filter { it.category == category }
            }
        _uiState.value =
            current.copy(
                products = filtered,
                selectedCategory = category,
            )
    }
    fun getProductById(id: Int): Product? {
        val current = _uiState.value as? ProductUiState.Success ?: return null
        return current.allProducts.find { it.id == id }
    }
}
