package org.kmery.bedushop

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils.indexOf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.internal.service.Common
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import com.travijuu.numberpicker.library.NumberPicker
import java.io.InputStream
import java.lang.Exception
import java.net.URL
import kotlin.random.Random

//Declaración con constructor
class RecyclerAdapterCart(
    private val context:Context,
    private val products: MutableList<Product>
    ): RecyclerView.Adapter<RecyclerAdapterCart.ViewHolder>() {

        //Aquí atamos el ViewHolder
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //val product = products[position]
            val product = products.elementAt(position)
            holder.bind(product, context)
            //holder.view.setOnClickListener{clickListener(product)}
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ViewHolder(layoutInflater.inflate(R.layout.cart_product, parent, false))
        }

        override fun getItemCount(): Int {
            return products.size
        }

        //El ViewHolder ata los datos del RecyclerView a la Vista para desplegar la información
        //También se encarga de gestionar los eventos de la View, como los clickListeners
        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

            //obteniendo las referencias a las Views
            val productName = view.findViewById(R.id.tvProduct) as TextView
            val price = view.findViewById(R.id.tvPrice) as TextView
            val image = view.findViewById(R.id.imgProduct) as ImageView
            val numberPicker = view.findViewById(R.id.cartNumberPicker) as NumberPicker

            //"atando" los datos a las Views
            fun bind(product: Product, context: Context){
                productName.text = product.title
                price.text = product.price.toString()
                Picasso.get()
                    .load(product.image)
                    .placeholder(R.drawable.cabecera)
                    .into(image)
                numberPicker.max = 15
                numberPicker.min = 0
                numberPicker.unit = 1
                numberPicker.value = 1
            }
        }
}