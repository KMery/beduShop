package org.kmery.bedushop

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_list_products.*
import java.io.IOException
import kotlin.reflect.javaType


class ListFragment : Fragment() {
    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }

    private lateinit var mAdapter : RecyclerAdapter
    private var listener : (Product) ->Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_products, container, false)
    }

    @ExperimentalStdlibApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerView()
    }

    //listener llamado desde SecondMainActivity
    fun setListener(l: (Product) -> Unit){
        listener = l
    }

    //configuramos lo necesario para desplegar el RecyclerView
    @ExperimentalStdlibApi
    private fun setUpRecyclerView(){
        recyclerProducts.setHasFixedSize(true)
        recyclerProducts.layoutManager = LinearLayoutManager(activity)
        mAdapter = RecyclerAdapter( requireActivity(),
        getProducts(requireContext()) as MutableList<Product>, listener)
        recyclerProducts.adapter = mAdapter
    }

    //Lee datos desde el file pasado
    private fun getJsonDataFromAsset(context: Context, fileName: String = "products.json"): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    //Obtiene los productos leidos como una lista de productos
    @ExperimentalStdlibApi
    fun getProducts(context: Context): List<Product> {
        val jsonString = getJsonDataFromAsset(context)
        val listProductType = kotlin.reflect.typeOf<List<Product>>().javaType //TypeToken<List<Product>>() {}.type
        return Gson().fromJson(jsonString, listProductType)
    }

}