package org.kmery.bedushop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class PaidFragment: Fragment() {

    companion object {
        fun newInstance(): PaidFragment = PaidFragment()
    }

    private lateinit var paidBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_paid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paidBtn = view.findViewById(R.id.paidBtn)
        paidBtn.setOnClickListener {
            var fragment:Fragment = SoldFragment.newInstance()
            val fragManager: FragmentManager? = this.getFragmentManager()
            val transaction = fragManager?.beginTransaction()
            transaction?.replace(R.id.second_activity, fragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
    }
}