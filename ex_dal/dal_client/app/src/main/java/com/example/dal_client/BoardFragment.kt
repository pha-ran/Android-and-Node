package com.example.dal_client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BoardFragment : Fragment() {

    val mainActivity = activity as MainActivity

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        val boardFragment = inflater.inflate(R.layout.fragment_board, container, false)

        return boardFragment
    }
}