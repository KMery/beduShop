package org.kmery.bedushop

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class AddressFragment: BottomSheetDialogFragment() {

    companion object{
        const val PERMISSION_ID = 33
        const val TAG = "AddressFragment"
    }
    private lateinit var locationAddressDescription : TextView

    lateinit var mFusedLocationClient: FusedLocationProviderClient

    private lateinit var closeAddress: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet_location,container,false)
        locationAddressDescription = view.findViewById(R.id.locationAddressDescription)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        closeAddress = view.findViewById(R.id.locationCloseButton)

        closeAddress.setOnClickListener {
            this.dismiss()
        }

        //Actualizar la localización al entrar
        //getLocation()
        val updateAddress = view.findViewById<Button>(R.id.updateAddress).setOnClickListener {
            getLocation()
        }
    }

    private fun checkGranted(permission: String): Boolean{
        return ActivityCompat.checkSelfPermission(requireActivity(), permission) == PackageManager.PERMISSION_GRANTED
    }


    private fun checkPermissions(): Boolean {
        if ( checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) &&
            checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) ){
            return true
        }
        return false
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }
    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    /*
    Funcion que trae la localización
    */
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (checkPermissions()) { //verificamos si tenemos permisos
            if (isLocationEnabled()) { //localizamos sólo si el GPS está encendido

                mFusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
                    println(
                        """
                           $location 
                        """
                    )
                    //-31.4201, -64.1888
                    //-31.417146, -64.183362
                    //location.latitude = if (location.latitude != null) -31.4201 else location.latitude
                    //location.longitude = if (location.longitude != null) -64.1888 else location.longitude

                    val geocoder = Geocoder(requireActivity(), Locale.getDefault())
                    val addresses: List<Address> = geocoder.getFromLocation(-31.417146, -64.183362, 1) // location.latitude?: location.longitude?:
                    //val addresses = geocoder.getFromLocation(latitude, longitude, 1)


                    val addressItem = addresses.first()
                    val addressFragments = (0 .. addressItem.maxAddressLineIndex).map { i ->
                        addressItem.getAddressLine(i)
                    }

                    locationAddressDescription.text = addressFragments.first()

                }
            }
        } else{
            //si no se tiene permiso, pedirlo
            requestPermissions()
        }
    }
}
