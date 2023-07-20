package com.coding.meet.navigationdrawerbottomnavigationbar.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.coding.meet.navigationdrawerbottomnavigationbar.R
import com.coding.meet.navigationdrawerbottomnavigationbar.bottom_fragment.CartFragment
import com.coding.meet.navigationdrawerbottomnavigationbar.bottom_fragment.CategoryFragment
import com.coding.meet.navigationdrawerbottomnavigationbar.bottom_fragment.HistoryFragment
import com.coding.meet.navigationdrawerbottomnavigationbar.bottom_fragment.NotificationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_category -> {
                    replaceFragment(CategoryFragment())
                    activity?.title = "Category"
                }
                R.id.bottom_history -> {
                    replaceFragment(HistoryFragment())
                    activity?.title = "History"
                }
                R.id.bottom_notification -> {
                    replaceFragment(NotificationFragment())
                    activity?.title = "Notification"
                }
                R.id.bottom_cart -> {
                    replaceFragment(CartFragment())
                    activity?.title = "Cart"
                }
            }
            true
        }

        replaceFragment(CategoryFragment())
        activity?.title = "Category"
        bottomNavigationView.selectedItemId = R.id.bottom_category

        val addFab = view.findViewById<FloatingActionButton>(R.id.addFabBtn)
        addFab.setOnClickListener {
            Toast.makeText(context,"Add Clicked",Toast.LENGTH_LONG).show()
        }
        return view
    }
    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.bottomFragment,fragment)
            .commit()
    }

}