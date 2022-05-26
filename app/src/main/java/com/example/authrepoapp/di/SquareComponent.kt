package com.example.authrepoapp.di

import com.example.authrepoapp.model.network.SquareService
import com.example.authrepoapp.viewmodel.ListUserViewModel
import dagger.Component

/**
 * This interface is responsible for notifying the dagger about
 * which dependency is needs to be injected
 */

@Component(modules = [SquareAppModule::class])
interface SquareComponent {

    fun inject(service: SquareService)
    fun inject(viewModel: ListUserViewModel)
}