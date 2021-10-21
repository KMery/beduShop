package org.kmery.bedushop

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.transition.Scene
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import androidx.transition.TransitionInflater
import android.transition.TransitionInflater
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import com.google.gson.JsonParser
import java.lang.reflect.Type
import java.util.*
import kotlin.concurrent.timerTask


class ListFragment : Fragment() {
    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }

    private lateinit var mAdapter : RecyclerAdapter
    private lateinit var listener : (Product) -> Unit
    private lateinit var recyclerProducts : RecyclerView

    /*private lateinit var sceneOne: Scene
    private lateinit var sceneTwo: Scene
    private lateinit var currentScene: Scene*/

    private val url = "https://fakestoreapi.com/products"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Transition
        /*val transitionXml = TransitionInflater.from(requireContext()).inflateTransition(R.transition.go_detail).apply {
            excludeTarget(activity?.window?.decorView?.findViewById<View>(R.id.action_bar_container), true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        activity?.window?.exitTransition = transitionXml*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        Thread{
            getProductOk()
        }.start()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_products, container, false)
    }

    //Animaciones
    val options = navOptions {
        anim {
            enter = R.anim.slide_in_down
            exit = R.anim.slide_out_down
            /*popEnter = R.anim.slide_in_down
            popExit = R.anim.slide_out_down*/
        }
    }

    private fun setUpRecyclerView() {
        recyclerProducts.setHasFixedSize(true)
        recyclerProducts.layoutManager = LinearLayoutManager(activity)

        //setting the adapter
        //mAdapter = RecyclerAdapter(requireActivity(), getProducts(), listener)
        //mAdapter = RecyclerAdapter(requireActivity(), mutableListOf(), listener)
        //assigning the adapter to RecyclerView
        //recyclerProducts.adapter = mAdapter


    }

    fun getProductOk() {
        //instanciando al cliente
        val okHttpClient = OkHttpClient()

        //El objeto Request contiene todos los parámetros de la petición (headers, url, body etc)
        val request = Request.Builder()
            .url(url)
            .build()

        val clientBuilder = okHttpClient.newBuilder()
        clientBuilder.build()
            .newCall(request)
            .enqueue(object : Callback {
                //el callback a ejecutar cuando hubo un error
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("Error", e.toString())
                }

                //el callback a ejectutar cuando obtuvimos una respuesta
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()?.string()
                    try {

                        val jsonParser = JsonParser()
                        val gsonProduct = jsonParser.parse(body.toString()) as JsonArray
                        println(gsonProduct)

                        val listProductType = object : TypeToken<MutableList<Product>>() {}.type
                        val products: MutableList<Product> = Gson().fromJson(body, listProductType)


                        recyclerProducts = requireView().findViewById(R.id.recyclerProducts)

                        activity!!.runOnUiThread {
                            //Setting the listener that will be executed any time a product is clicked
                            //then navigates to Detail Fragment passing the product information by safeArgs
                            listener = {
                                val direction = ListFragmentDirections.actionInicioFragmentToDetailFragment(it)
                                findNavController().navigate(direction, options)
                            }

                            recyclerProducts.setHasFixedSize(true)
                            recyclerProducts.layoutManager = LinearLayoutManager(activity)
                            //setting the adapter
                            //mAdapter = RecyclerAdapter(requireActivity(), json as MutableList<Product>, listener)
                            mAdapter = RecyclerAdapter(requireActivity(), products, listener)
                            //assigning the adapter to RecyclerView
                            recyclerProducts.adapter = mAdapter
                        }


                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })
    }



    //Function to get the json by calling "getJsonDataFromAsset" and then return an MutableList of objects from
    //Json using the Gson() method
    /*fun getProducts(): MutableList<Product> {
        //val jsonString = getJsonDataFromAsset(requireContext(),"products.json")

        //gsonObject = getProductOk() as JsonObject

        val listProductType = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(gsonObject, listProductType)
    }*/
}