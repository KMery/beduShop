package org.kmery.bedushop

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second_main.*
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class DetailFragment : Fragment() {

    private lateinit var tvProduct: TextView
    private lateinit var tvDescription: TextView
    private lateinit var rbRate: RatingBar
    private lateinit var imgProduct: ImageView
    private lateinit var tvPrice: TextView

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        tvProduct = view.findViewById(R.id.tvProduct)
        tvDescription = view.findViewById(R.id.tvDescription)
        rbRate = view.findViewById(R.id.ratingBar)
        imgProduct = view.findViewById(R.id.imgProduct)
        tvPrice = view.findViewById(R.id.tvPrice)

        //Funcion que te escrolea SOLO EL DETALLE
        tvDescription.movementMethod = ScrollingMovementMethod()

        //Esconder bottomMenu
        //var bottomNavigationView = activity().view.find
        //bottomNavigationView.visibility = View.GONE

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product : Product = args.product
        tvProduct.text = product.title
        tvPrice.text = product.price.toString()
        tvDescription.text = product.description
        Picasso.get()
            .load(product.image)
            .placeholder(R.drawable.cabecera)
            .into(imgProduct)
        rbRate.rating = product.rating

        //bottom_navigation_view.visibility = View.GONE

        /*bottom_navigation_view.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_item -> {
                    view.findNavController().navigate(R.id.inicioFragment)
                    true
                }
                R.id.cart_item -> {
                    view.findNavController().navigate(R.id.action_inicioFragment_to_carritoFragment)
                    true
                }
                R.id.profile_item -> {
                    view.findNavController().navigate(R.id.action_inicioFragment_to_perfilFragment)
                    true
                }
                else -> false
            }
        }*/
    }

    /*fun showProduct(product: Product){
        //view?.visibility = View.VISIBLE
        //val product : Product = args.product

        tvProduct.text = product.title
        tvDescription.text = product.description
        tvPrice.text = "$ "+ product.price.toString()
        rbRate.rating = product.rating

        Picasso.get()
            .load(product.image)
            .placeholder(R.drawable.cabecera)
            .into(imgProduct);
    }*/

}