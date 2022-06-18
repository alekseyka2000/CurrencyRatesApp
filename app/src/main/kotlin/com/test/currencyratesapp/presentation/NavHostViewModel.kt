package com.test.currencyratesapp.presentation

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.test.currencyratesapp.R

/**
 *  ViewModel for app navigation fragment
 *
 *  @author YarakhovichAA
 */
class NavHostViewModel : ViewModel() {

    /**
     * Function handle bottomNavigationView touch
     */
    fun wasNavigationViewClicked(
        navController: NavController,
        clickedItem: MenuItem,
        selectedItemId: Int
    ): Boolean {
        navigateFromFilterFragment(navController)
        return navigateNavigationBetweenPopularAndFavoriteFragment(navController, clickedItem, selectedItemId)
    }

    private fun navigateFromFilterFragment(navController: NavController) {
        if (navController.currentDestination?.id == R.id.currencyRatesFilterFragment) {
            navController.popBackStack()
        }
    }

    private fun navigateNavigationBetweenPopularAndFavoriteFragment(
        navController: NavController,
        item: MenuItem,
        selectedItemId: Int
    ): Boolean {
        return if (selectedItemId != item.itemId) {
            when (item.itemId) {
                R.id.nav_favorite -> {
                    navController.navigate(R.id.action_popularCurrencyRatesFragment_to_favoriteCurrencyRatesFragment)
                    true
                }
                R.id.nav_popular -> {
                    navController.navigate(R.id.action_favoriteCurrencyRatesFragment_to_popularCurrencyRatesFragment)
                    true
                }
                else -> false
            }
        } else false
    }
}