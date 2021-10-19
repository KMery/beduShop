package org.kmery.bedushop

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import org.json.JSONArray

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()

        //Aquí pondremos nuesto código para inicializar Realm
        //inicializamos Realm
        Realm.init(this)

        //guardar nuestro json en un JSON array
        val array = JSONArray(getJsonFile())

        //configuraciónn de nuestra base de datos
        /*val config = RealmConfiguration
            .Builder()
            //Aquí inicializamos los datos iterando cada objeto JSON
            .initialData { realm ->
                for ( i in 0 until array.length() ) {
                    //Seteando nuestros valores en Realm
                    val c = realm.createObject(Product::class.java, i)
                    c.title = array.getJSONObject(i).getString("title")
                    c.price = array.getJSONObject(i).getDouble("price")
                    c.description = array.getJSONObject(i).getString("description")
                    c.category = array.getJSONObject(i).getString("category")
                    c.image = array.getJSONObject(i).getString("image")
                }
            }
            .deleteRealmIfMigrationNeeded()
            .name("realmDB.realm") //seteando el nombre de la DB
            .build()

        //Seteamos los datos de configuración en nuestra clase
        Realm.setDefaultConfiguration(config)*/

    }

    fun getJsonFile(): String{

        return applicationContext
            .assets
            .open("products.json").bufferedReader().use {it.readText()}
    }

}

