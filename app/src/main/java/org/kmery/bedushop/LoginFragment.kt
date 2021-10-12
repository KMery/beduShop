package org.kmery.bedushop

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.button.MaterialButton
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import okhttp3.FormBody

import okhttp3.RequestBody
import android.widget.Toast







class LoginFragment : Fragment() {
    //User seteado a modo de prueba
    private val User = mapOf<String, String>(
        //"user@mail.com" to "pass123",
        "george.bluth@reqres.in" to "pass123",
        "janet.weaver@reqres.in" to "pass123",
        "emma.wong@reqres.in" to "pass123",
        "eve.holt@reqres.in" to "pass123",
        "charles.morris@reqres.in" to "pass123",
        "tracey.ramos@reqres.in" to "pass123",
        "michael.lawson@reqres.in" to "pass123",
        "lindsay.ferguson@reqres.in" to "pass123",
        "tobias.funke@reqres.in" to "pass123",
        "byron.fields@reqres.in" to "pass123",
        "george.edwards@reqres.in" to "pass123",
        "rachel.howell@reqres.in" to "pass123"
    )

    private val url = "https://reqres.in/api/login"

    //Animaciones
    val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    //Checkea si los datos de logeo son correctos
    private fun checkUserCorrect(mailEntered: String, passEntered:String): String = if (mailEntered !in User && passEntered !== User[mailEntered]) "Mail o Password incorrecto" else "Bienvenido!"

    //Obtiene los datos ingresados por el usuario
    private fun getInputData(view: View, requiredData:String): String {
        when (requiredData) {
            "mail" -> {
                val inputMail = view.findViewById<EditText>(R.id.editTextTextEmailAddress)
                val mailEntered = inputMail.text.toString()
                return mailEntered
            }
            "pass" -> {
                val inputPass = view.findViewById<EditText>(R.id.editTextTextPassword)
                val passEntered = inputPass.text.toString()
                return passEntered
            }
            else -> {
                return ""
            }
        }
    }

    fun checkLogin(mailEntered: String, passEntered: String) {
        //instanciando al cliente
        val okHttpClient = OkHttpClient()

        val formBody: RequestBody = FormBody.Builder()
            .add("email", mailEntered)
            .add("password", passEntered)
            .build()

        //El objeto Request contiene todos los parámetros de la petición (headers, url, body etc)
        val request = Request.Builder()
            .url(url)
            .post(formBody)
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
                        //val token = json.getString("token")

                        activity!!.runOnUiThread {
                            Toast.makeText(
                                activity,
                                "Bienvenido!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        view?.findNavController()
                            ?.navigate(R.id.action_loginFragment_to_secondMainActivity, null, options)

                    } catch (e: JSONException) {
                        activity!!.runOnUiThread {
                            Toast.makeText(
                                activity,
                                "Usuario o mail incorrecto",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        e.printStackTrace()
                    }
                }
            })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    //Antiguo getLogin hardcodeado
    private fun getLogin(mailEntered: String, passEntered: String) {
        //Verifica si mail y pass son correctos
        val logged = checkUserCorrect(mailEntered, passEntered)
        Toast.makeText(requireContext(), logged, Toast.LENGTH_SHORT).show()

        if (logged == "Bienvenido!") {
            view?.findNavController()
                ?.navigate(R.id.action_loginFragment_to_secondMainActivity, null, options)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var buttonIniciar = view.findViewById<Button>(R.id.buttonIniciar)
        buttonIniciar.setOnClickListener {
            //Obtiene valores ingresados en input para mail y password
            val mailEntered = getInputData(view, "mail")
            val passEntered = getInputData(view, "pass")
            //Thread {
                //login simple con lista hardcodeada
                //getLogin(mailEntered, passEntered)

                //llamar login OKHttp
                checkLogin(mailEntered, passEntered)
            //}.start()
        }

        val registerButton = view.findViewById<TextView>(R.id.register)
        registerButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment, null, options)//, null, options
        }

    }
}