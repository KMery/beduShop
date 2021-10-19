package org.kmery.bedushop

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.analytics.ecommerce.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second_main.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DetailFragment : Fragment() {

    private lateinit var tvProduct: TextView
    private lateinit var tvDescription: TextView
    private lateinit var rbRate: RatingBar
    private lateinit var rbRateText: TextView
    private lateinit var imgProduct: ImageView
    private lateinit var tvPrice: TextView

    private lateinit var addBoton: Button

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var navController: NavController

    companion object {
        val PRODUCT = "PRODUCT"
    }

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
        rbRateText = view.findViewById(R.id.ratingText)
        addBoton = view.findViewById(R.id.addCarrito)

        //scroll in detailView
        tvDescription.movementMethod = ScrollingMovementMethod()



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val product : Product = args.product
        val product : org.kmery.bedushop.Product = args.product
        tvProduct.text = product.title
        tvPrice.text = product.price.toString()
        tvDescription.text = product.description
        Picasso.get()
            .load(product.image)
            .placeholder(R.drawable.cabecera)
            .into(imgProduct)
        rbRate.rating = (0..5).random().toFloat()
        rbRateText.text = (99..999).random().toString()


        /*val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.detailFragment) as NavHostFragment? ?: return

        navController = navHostFragment.navController*/

        addBoton.setOnClickListener{
            Toast.makeText(requireContext(), "${product.title}agregado!", Toast.LENGTH_LONG).show()

            //findNavController().navigate(R.id.carritoFragment)



            val bundle = bundleOf("product" to product)
            //findNavController().navigate(R.id.action_detailFragment_to_carritoFragment, bundle)



            //bottom_navigation_view.selectedItemId = R.id.cart_item
            //findNavController().navigate(R.id.action_detailFragment_to_carritoFragment, bundle)

            var fragment:Fragment = CartFragment.newInstance()
            fragment.arguments = bundle
            val fragManager: FragmentManager? = this.getFragmentManager()
            val transaction = fragManager?.beginTransaction()
            //transaction.replace(R.id.fragmentList, fragment)
            transaction?.replace(R.id.second_activity, fragment)
            transaction?.addToBackStack(null)
            transaction?.commit()


        //findNavController().navigate(R.id.action_carritoFragment_to_inicioFragment)

            /*val intent= Intent(requireContext(), CartFragment::class.java).apply {
                putExtra("origen", "DETAIL")
            }
            startActivity(intent)*/

        }
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