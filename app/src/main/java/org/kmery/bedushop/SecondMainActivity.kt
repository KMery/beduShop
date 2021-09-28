package org.kmery.bedushop

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import kotlinx.android.synthetic.main.activity_second_main.*
import kotlinx.android.synthetic.main.fragment_register.*
import okhttp3.internal.Internal.instance
import java.security.AccessController.getContext

//Actividad post logeo
class SecondMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_main)

        //Setea hacia que fragmento va según opción elegida
        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            var fragment: Fragment
            when (menuItem.itemId) {
                R.id.home_item -> {
                    fragment = ListFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.cart_item -> {
                    fragment = CartFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.profile_item -> {
                    fragment = ProfileFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        bottom_navigation_view.selectedItemId = R.id.home_item

        //Aquí debiera mandar al detail del producto elegido
        val listFragment = supportFragmentManager.findFragmentById(R.id.fragmentList) as ListFragment

        listFragment.setListener{
            Toast.makeText(this, "Buena elección!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.PRODUCT, it)
            }
            startActivity(intent)
        }
    }

    //Se levanta topNavBar
    override fun onCreateOptionsMenu(menu: Menu?):Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //TopNav --> se setea de acuerdo a item presionado su acción
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.option_1 -> {
                Toast.makeText(this, "Búsqueda deshabilitada", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.option_2 -> {
                val urlIntent = Intent(Intent.ACTION_VIEW)
                urlIntent.data = Uri.parse("https://www.bedu.org")
                startActivity(urlIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //BottomNav --> permite mostrar el fragmento correspondiente al item elegido
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        //transaction.replace(R.id.fragmentList, fragment)
        transaction.replace(R.id.second_activity, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
