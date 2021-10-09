package org.kmery.bedushop

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.internal.service.Common
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.lang.Exception
import java.net.URL
import kotlin.random.Random

//Declaración con constructor
class RecyclerAdapter(
    private val context:Context,
    private val products: MutableList<Product>,
    private val clickListener: (Product) -> Unit): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

        //Aquí atamos el ViewHolder
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val product = products[position]
            holder.bind(product, context)
            holder.view.setOnClickListener{clickListener(product)}
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false))
        }

        override fun getItemCount(): Int {
            return products.size
        }

        //El ViewHolder ata los datos del RecyclerView a la Vista para desplegar la información
        //También se encarga de gestionar los eventos de la View, como los clickListeners
        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

            //obteniendo las referencias a las Views
            val productName = view.findViewById(R.id.tvProduct) as TextView
            val ratingB = view.findViewById<RatingBar>(R.id.ratingBar)
            val ratingT = view.findViewById(R.id.ratingText) as TextView
            val price = view.findViewById(R.id.tvPrice) as TextView
            val image = view.findViewById(R.id.imgProduct) as ImageView

            //"atando" los datos a las Views
            fun bind(product: Product, context: Context){
                productName.text = product.title
                ratingB.rating = (0..5).random().toFloat()
                ratingT.text = (99..999).random().toString()
                price.text = product.price.toString()
                Picasso.get()
                    .load(product.image)
                    .placeholder(R.drawable.cabecera)
                    .into(image)
            }
        }
}