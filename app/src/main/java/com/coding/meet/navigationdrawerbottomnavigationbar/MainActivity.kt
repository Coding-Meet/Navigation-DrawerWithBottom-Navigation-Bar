package com.coding.meet.navigationdrawerbottomnavigationbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.coding.meet.navigationdrawerbottomnavigationbar.nav_fragment.HomeFragment
import com.coding.meet.navigationdrawerbottomnavigationbar.nav_fragment.ProfileFragment
import com.coding.meet.navigationdrawerbottomnavigationbar.nav_fragment.SettingFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private val onBackPressedCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            onBackPressedMethod()
        }

    }

    private fun onBackPressedMethod() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            finish()
        }
    }

    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawerLayout)

        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        val header = navigationView.getHeaderView(0)
        val userNameTxt = header.findViewById<TextView>(R.id.userNameTxt)
        val emailTxt = header.findViewById<TextView>(R.id.emailTxt)
        val profileImg = header.findViewById<ImageView>(R.id.profileImg)


        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        /// Default Navigation bar Tab Selected
        replaceFragment(HomeFragment())
        navigationView.setCheckedItem(R.id.nav_home)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navFragment,fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                replaceFragment(HomeFragment())
            }
            R.id.nav_profile -> {
                replaceFragment(ProfileFragment())
                title = "Profile"
            }
            R.id.nav_setting -> {
                replaceFragment(SettingFragment())
                title = "Setting"
            }
            R.id.nav_share -> {
                Toast.makeText(this,"Share Clicked",Toast.LENGTH_LONG).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this,"Logout Clicked",Toast.LENGTH_LONG).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}