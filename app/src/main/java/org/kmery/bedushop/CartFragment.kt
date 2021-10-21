package org.kmery.bedushop

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cart.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

lateinit var shopJson: Any

class CartFragment: Fragment() {
    companion object {
        fun newInstance(): CartFragment = CartFragment()
    }

    private lateinit var mAdapter : RecyclerAdapterCart
    //private lateinit var listener : (Product) -> Unit

    private lateinit var cartBtn: Button

    private lateinit var jsonString: Any

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        container?.removeAllViews()
        //val str = savedInstanceState?.getString("product")

        jsonString = arguments?.get("product") ?: ""
        println(
            """
                On create CartFragment
               $jsonString 
            """
        )
        shopJson = jsonString
        println(
            """
                #########
                all saved?
               $shopJson
                #########
            """
        )


        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //cartBtn.visibility = VISIBLE
        cartBtn = view.findViewById(R.id.cartBtn)
        cartBtn.setOnClickListener {
            val bundle = bundleOf("product" to jsonString)
            /*findNavController().navigate(R.id.action_carritoFragment_to_paidFragment2, bundle)*/
            var fragment:Fragment = PaidFragment.newInstance()
            fragment.arguments = bundle
            val fragManager: FragmentManager? = this.getFragmentManager()
            val transaction = fragManager?.beginTransaction()
            //transaction.replace(R.id.fragmentList, fragment)
            transaction?.replace(R.id.second_activity, fragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (jsonString != "") {
            setUpRecyclerView()
        } else {
            cartBtn.visibility = GONE
        }
    }

    private fun setUpRecyclerView() {
        recyclerProductsCart.setHasFixedSize(true)
        recyclerProductsCart.layoutManager = LinearLayoutManager(activity)

        //setting the adapter
        mAdapter = RecyclerAdapterCart(requireActivity(), getProducts())
        //mAdapter = RecyclerAdapterCart(requireActivity(), mutableListOf(), listener)
        //assigning the adapter to RecyclerView
        recyclerProductsCart.adapter = mAdapter
    }

    fun getProducts(): MutableList<Product> {

        //val bundle = Bundle() //arguments
        //val jsonString = bundle?.getString("product")

        println(
            """
                jsonString
               $jsonString 
            """
        )

        return mutableListOf(jsonString as Product)
    }
}