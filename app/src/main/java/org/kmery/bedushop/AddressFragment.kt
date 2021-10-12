package org.kmery.bedushop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddressFragment: BottomSheetDialogFragment() {

    private lateinit var closeAddress: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_bottom_sheet_location, container, false)

    companion object {
        const val TAG = "AddressFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        closeAddress = view.findViewById(R.id.locationCloseButton)

        closeAddress.setOnClickListener {
            this.dismiss()
        }
    }
}
