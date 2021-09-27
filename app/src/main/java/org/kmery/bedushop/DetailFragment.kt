package org.kmery.bedushop

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class DetailFragment : Fragment() {

    private lateinit var tvProduct: TextView
    private lateinit var tvDescription: TextView
    private lateinit var rbRate: RatingBar
    private lateinit var imgProduct: ImageView
    private lateinit var tvPrice: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        tvProduct = view.findViewById(R.id.tvProduct)
        tvDescription = view.findViewById(R.id.tvDescription)
        rbRate = view.findViewById(R.id.rbRate)
        imgProduct = view.findViewById(R.id.imgProduct)
        tvPrice = view.findViewById(R.id.tvPrice)

        return view
    }

    fun showProduct(product: Product){
        view?.visibility = View.VISIBLE

        tvProduct.text = product.title
        tvDescription.text = product.description
        tvPrice.text = product.price.toString()
        rbRate.rating = product.rating
        //imgProduct.setImageResource(product.idImage)
        //imgProduct.setImageDrawable(LoadImageFromWebOperations(product.image, product.name))

        Picasso.get()
            .load(product.image)
            .placeholder(R.drawable.cabecera)
            //.error(R.drawable.user_placeholder_error)
            .into(imgProduct);
    }

    /*fun LoadImageFromWebOperations(url: String?, name: String?): Drawable? {
        return try {
            val `is`: InputStream = URL(url).getContent() as InputStream
            Drawable.createFromStream(`is`, name)
        } catch (e: Exception) {
            null
        }
    }*/

}