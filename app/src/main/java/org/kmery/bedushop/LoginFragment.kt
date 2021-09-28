package org.kmery.bedushop

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment() {
    //User seteado a modo de prueba
    val User = mapOf<String, String>("user@mail.com" to "pass123")

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var buttonIniciar = view.findViewById<Button>(R.id.buttonIniciar)
        buttonIniciar.setOnClickListener {
            val mailEntered = getInputData(view, "mail")
            val passEntered = getInputData(view, "pass")

            val logged = checkUserCorrect(mailEntered, passEntered)
            Toast.makeText(requireContext(), logged, Toast.LENGTH_SHORT).show()

            if (logged == "Bienvenido!") {
                view.findNavController().navigate(R.id.action_loginFragment_to_secondMainActivity, null, options)
            }
        }

        val registerButton = view.findViewById<TextView>(R.id.register)
        registerButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment, null, options)//, null, options
        }

    }
}