package com.example.atm

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.atm.R

class Fragment1 : Fragment(R.layout.activity_fragment1) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }
    }
}