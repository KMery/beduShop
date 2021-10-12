package org.kmery.bedushop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import org.kmery.bedushop.classes.UserData
import java.io.IOException

class ProfileFragment: Fragment() {
    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

    private val url = "https://reqres.in/api/users/${(1..12).random()}"

    private lateinit var profileImage: ImageView
    private lateinit var profileName: TextView
    private lateinit var profileMail: TextView
    private lateinit var profileAddresses: Button
    private lateinit var profilePaidMethods: Button
    private lateinit var profileOrders: Button
    private lateinit var profileNotifications: Button
    private lateinit var profileChangePassword: Button
    private lateinit var profileCloseSession: Button

    private val addressFragment = AddressFragment()
    //private lateinit var closeAddress: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container?.removeAllViews()
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)

        profileImage = view.findViewById(R.id.profileImage)
        profileName = view.findViewById(R.id.profileName)
        profileMail = view.findViewById(R.id.profileEmail)
        profileAddresses = view.findViewById(R.id.profileAddresses)
        profilePaidMethods = view.findViewById(R.id.profilePaidMethod)
        profileOrders = view.findViewById(R.id.profileOrders)
        profileNotifications = view.findViewById(R.id.profileNotifications)
        profileChangePassword = view.findViewById(R.id.profileChangePassword)
        profileCloseSession = view.findViewById(R.id.profileCloseSession)

        return view
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileAddresses.setOnClickListener {
            println("profileAddress click!")
            addressFragment.show(parentFragmentManager,AddressFragment.TAG)

            /*closeAddress = view.findViewById(R.id.locationCloseButton)

            closeAddress.setOnClickListener {
                dismiss()
            }*/
        }


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
                    Log.d("Error",e.toString())
                }

                //el callback a ejectutar cuando obtuvimos una respuesta
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()?.string()
                    try {
                        val json = JSONObject(body)
                        val data = json.getJSONObject("data")
                        //println(data.toString())
                        val user = Gson().fromJson(data.toString(),UserData::class.java)
                        println(user.toString())

                        activity!!.runOnUiThread {
                            Picasso.get()
                                .load(user.avatar)
                                .placeholder(R.drawable.cabecera)
                                .into(profileImage)
                            profileName.text = user.first_name
                            profileMail.text = user.email
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })

    }
}