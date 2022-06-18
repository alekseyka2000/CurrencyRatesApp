package com.test.currencyratesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Fragment for navigation in the app
 *
 *  @author YarakhovichAA
 */

@FragmentScoped
class NavHostFragment : Fragment() {

    private val navigationViewModel: NavHostViewModel by viewModels()
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nav_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationView = view.findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            setCheckableBottomNavigationView()
            navigationViewModel.navigationViewWasClicked(
                navController,
                item,
                bottomNavigationView.selectedItemId
            )
        }
    }

    override fun onStart() {
        super.onStart()
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
    }

    /**
     * Function allows you to inactivate bottomNavigationView items
     */
    fun setNotCheckableBottomNavigationView() {
        bottomNavigationView.menu.forEach { it.isCheckable = false }
    }

    private fun setCheckableBottomNavigationView() {
        bottomNavigationView.menu.forEach { it.isCheckable = true }
    }
}