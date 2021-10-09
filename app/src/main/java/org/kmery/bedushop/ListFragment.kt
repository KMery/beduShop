package org.kmery.bedushop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_second_main.*
import kotlinx.android.synthetic.main.fragment_list_products.*
import java.io.IOException
import kotlin.reflect.javaType


class ListFragment : Fragment() {
    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }

    //Animaciones
    val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    private lateinit var mAdapter : RecyclerAdapter
    private lateinit var listener : (Product) -> Unit
    private lateinit var recyclerProducts : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerProducts = view.findViewById(R.id.recyclerProducts)
        //Setting the listener that will be executed any time a product is clicked
        //then navigates to Detail Fragment passing the product information by safeArgs
        listener = {
            val direction = ListFragmentDirections.actionInicioFragmentToDetailFragment(it)
            findNavController().navigate(direction)

            /*val intent= Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("origen", "DETAIL")
            }
            startActivity(intent)*/
        }
        setUpRecyclerView()
    }

    /*override fun onResume() {
        super.onResume()
        SecondMainActivity.hideBottomNav()
    }

    override fun onStop() {
        super.onStop()
        SecondMainActivity.showBottomNav()
    }*/

    private fun setUpRecyclerView(){
        recyclerProducts.setHasFixedSize(true)
        recyclerProducts.layoutManager = LinearLayoutManager(activity)
        //setting the adapter
        mAdapter = RecyclerAdapter(requireActivity(), getProducts(), listener)
        //assigning the adapter to RecyclerView
        recyclerProducts.adapter = mAdapter
    }
    //Function to get and open the Json file located on assets directory.
    //This JSON contains every product information
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
    //Function to get the json by calling "getJsonDataFromAsset" and then return an MutableList of objects from
    //Json using the Gson() method
    fun getProducts(): MutableList<Product> {
        val jsonString = getJsonDataFromAsset(requireContext(),"products.json")
        val listProductType = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(jsonString, listProductType)
    }
}