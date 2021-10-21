package org.kmery.bedushop

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.json.JSONException

import org.json.JSONObject




class PaidFragment: Fragment() {

    companion object {
        fun newInstance(): PaidFragment = PaidFragment()
    }

    private lateinit var paidBtn: Button
    private lateinit var paidSubtotal: TextView
    //private lateinit var paidSend: TextView
    private lateinit var paidTotal: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_paid, container, false)
        paidBtn = view.findViewById(R.id.paidBtn)
        val jsonString = arguments?.get("product")!!
        var price = arguments?.getDouble("price")
        var numPicker = arguments?.getDouble("numberPicker")

        val bundle = Bundle()
        bundle.getBundle(jsonString.toString())
        for (key in bundle.keySet()) {
            Log.d("myApplication", "$key is a key in the bundle")
            if (key == "price") {
                price = bundle.get(key) as Double?
            }
            if (key == "numPicker") {
                numPicker = bundle.get(key) as Double?
            }
        }



        println(
            """
                On create PaidFragment
               $jsonString  
               $price
               $numPicker
            """
        )
        /*val subtotal = arguments?.getDouble("price")?.times(arguments?.getDouble("numberPicker")!!)
        val total = subtotal?.plus(30)
        paidSubtotal.text = subtotal.toString()
        paidTotal.text = total.toString()*/
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paidBtn = view.findViewById(R.id.paidBtn)
        paidBtn.setOnClickListener {
            (activity as SecondMainActivity).shopNotification() //Llamar a la notificación
            /*Llamar al fragmento de post pago*/
            var fragment:Fragment = SoldFragment.newInstance()
            val fragManager: FragmentManager? = this.getFragmentManager()
            val transaction = fragManager?.beginTransaction()
            transaction?.replace(R.id.second_activity, fragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
    }
}