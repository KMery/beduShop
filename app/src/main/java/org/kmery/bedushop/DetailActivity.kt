package org.kmery.bedushop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        val PRODUCT = "PRODUCT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val product = intent.getParcelableExtra<Product>(PRODUCT)
        val detailFragment = supportFragmentManager.findFragmentById(R.id.detailView) as? DetailFragment
        if (product != null) {
            detailFragment?.showProduct(product)
        }
    }
}