package org.example.project.data.di

import org.example.project.data.network.HttpClientFactory
import org.example.project.data.remote.ProductRemoteDataSource
import org.example.project.data.remote.ProductRemoteDataSourceImpl
import org.example.project.data.repository.ProductRepositoryImpl
import org.example.project.domain.repository.ProductRepository
import org.example.project.domain.usecase.GetProductUseCase
import org.example.project.domain.usecase.SearchProductsUseCase
import org.example.project.presentation.viewmodel.ProductViewModel
import org.koin.dsl.module

val appModule =
    module {

        single { HttpClientFactory.create() }

        single<ProductRemoteDataSource> {
            ProductRemoteDataSourceImpl(get())
        }

        single<ProductRepository> {
            ProductRepositoryImpl(get())
        }

        factory {
            GetProductUseCase(get())
        }

        factory {
            SearchProductsUseCase(get())
        }

        factory {
            ProductViewModel(
                get(),
                get(),
            )
        }
    }
