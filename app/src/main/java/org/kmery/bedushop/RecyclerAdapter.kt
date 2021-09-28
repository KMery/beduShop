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
import coil.api.load
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
            val product = products.get(position)
            holder.bind(product, context)
            holder.view.setOnClickListener{clickListener(product)}
            //Picasso.get().load(product.image).into(holder.image)
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
            //val description = view.findViewById(R.id.tvDescription) as TextView
            val rating = view.findViewById<RatingBar>(R.id.ratingBar)
            val price = view.findViewById(R.id.tvPrice) as TextView
            val image = view.findViewById(R.id.imgProduct) as ImageView

            //Imagenes setadas por defecto
            val listImagesProducts: List<Int> = listOf<Int>(
                R.drawable.control,
                R.drawable.kobo,
                R.drawable.corei9,
                R.drawable.xm3,
                R.drawable.rem1,
                R.drawable.rem2,
                R.drawable.rem3,
                R.drawable.hooded1,
                R.drawable.hooded2,
                R.drawable.jacket1,
            )
            val randomIndex = Random.nextInt(listImagesProducts.size);
            val randomElement: Int = listImagesProducts[randomIndex]

            //"atando" los datos a las Views
            fun bind(product: Product, context: Context){
                productName.text = product.title
                //description.text = product.description
                rating.rating = (0..5).random().toFloat()
                price.text = product.price.toString()

                image.setImageResource(randomElement)

                /*Picasso.get()
                    .load(product.image)
                    .placeholder(R.drawable.cabecera)
                    .into(image)*/



                //image.load(product.image)
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
}