package org.kmery.bedushop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_second_main.*

class DetailActivity : AppCompatActivity() {

    companion object {
        val PRODUCT = "PRODUCT"
    }

    private lateinit var addBoton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /*val product = intent?.getParcelableExtra<Product>(PRODUCT)
        val detailFragment = supportFragmentManager.findFragmentById(R.id.fragmentDetail) as? DetailFragment
        if (product != null) {
            detailFragment?.showProduct(product)
        }*/

        //val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        //bottomNavigationView.visibility = View.GONE

        addBoton = findViewById(R.id.addCarrito)
        addBoton.setOnClickListener{
            Toast.makeText(this, "$it.title agregado!", Toast.LENGTH_LONG).show()
            /*val intent= Intent(this, SecondMainActivity::class.java).apply {
                putExtra("origen", "DETAIL")
            }
            startActivity(intent)*/

        }
    }

}