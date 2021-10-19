package org.kmery.bedushop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class SoldFragment: Fragment() {

    companion object {
        fun newInstance(): SoldFragment = SoldFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sold, container, false)
        val returnMenuBtn = view.findViewById<Button>(R.id.returnMenuBtn)
        returnMenuBtn.setOnClickListener {

            val intent= Intent(requireContext(), SecondMainActivity::class.java).apply {
                //putExtra("origen", "DETAIL")
            }
            startActivity(intent)
        }
        return view
    }
}