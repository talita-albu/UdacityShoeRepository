package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.InstructionsFragmentBinding
import com.udacity.shoestore.databinding.LoginFragmentBinding


class InstructionsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding : InstructionsFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.instructions_fragment,
            container,
            false
        )

        binding.goToListButton.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_instructionsFragment_to_shoesListFragment)
        }

        return binding.root
    }
}