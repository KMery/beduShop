package org.kmery.bedushop

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_second_main.*
import kotlinx.android.synthetic.main.fragment_register.*
//import org.kmery.bedushop.databinding.ActivitySecondMainBinding

val CHANNEL_OTHERS = "OTROS"

//Actividad post logeo
class SecondMainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var products: List<Product>

    //Transition setUps
    private lateinit var sceneOne: Scene
    private lateinit var sceneTwo: Scene
    private lateinit var currentScene: Scene

    //private lateinit var binding: ActivitySecondMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_main)

       /* val realm = Realm.getDefaultInstance()
        products = realm.where(Product::class.java).findAll()

        Log.d("Respuesta","$products")*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }


        //Setea hacia que fragmento va según opción elegida
        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            var fragment: Fragment
            when (menuItem.itemId) {
                R.id.home_item -> {
                    fragment = ListFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.cart_item -> {
                    fragment = CartFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.profile_item -> {
                    fragment = ProfileFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        bottom_navigation_view.selectedItemId = R.id.home_item

        //Setea el navhost para obtener el navController
        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.second_activity) as NavHostFragment? ?: return

        navController = navHostFragment.navController
        //Esconder bottomNavMenu si no encuentra opción en bottomNavMenu items
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.detailFragment ->
                    bottom_navigation_view.visibility = View.GONE
                else -> bottom_navigation_view.visibility = View.VISIBLE
            }
        }
    }
    //Se levanta topNavBar
    override fun onCreateOptionsMenu(menu: Menu?):Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //TopNav --> se setea de acuerdo a item presionado su acción
    //Actualmente existen dos opciones
    //Lupa: --> toast con mensaje de búsqueda desactivada
    //Help: --> redirije a página de bedu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.option_1 -> {
                Toast.makeText(this, "Búsqueda deshabilitada", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.option_2 -> {
                val urlIntent = Intent(Intent.ACTION_VIEW)
                urlIntent.data = Uri.parse("https://www.bedu.org")
                startActivity(urlIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //BottomNav --> permite mostrar el fragmento correspondiente al item elegido
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        //transaction.replace(R.id.fragmentList, fragment)
        transaction.replace(R.id.second_activity, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotificationChannel(){
        val name = getString(R.string.agregarCarrito)
        val descriptionText = getString(R.string.app_name)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_OTHERS, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

    fun shopNotification() {


        val intent = Intent(this, SecondMainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("origen", "COMPRA")
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        //se buildea notificación
        var builder = NotificationCompat.Builder(this, CHANNEL_OTHERS)
            .setSmallIcon(R.drawable.cabecera) //icono del push notification
            .setContentTitle(getString(R.string.notifTitle)) //título de la notificación
            .setContentText(getString(R.string.notifBody)) //cuerpo de la notificación
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //Con prioridad por defecto
            .setContentIntent(pendingIntent) //Content intent
            .setAutoCancel(true)

        //ejecutar notificación
        with(NotificationManagerCompat.from(this)) {
            notify(20, builder.build()) //en este caso pusimos un id genérico
        }
    }
}
