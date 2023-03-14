package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment


class SummaryFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().finish()
    }

}