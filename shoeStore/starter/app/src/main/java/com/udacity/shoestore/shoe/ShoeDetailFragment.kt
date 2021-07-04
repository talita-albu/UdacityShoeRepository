package com.udacity.shoestore.shoe

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailBinding
import com.udacity.shoestore.databinding.ShoesListFragmentBinding
import com.udacity.shoestore.models.Shoe


class ShoeDetailFragment : Fragment() {
    private val viewModel: ShoesViewModel  by activityViewModels()
    private lateinit var binding: ShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_detail,
            container,
            false
        )

        //viewModel = ViewModelProvider(this).get(ShoesViewModel::class.java)
        //binding.shoesListViewModel = viewModel

        binding.buttonSave.setOnClickListener { addShoe() }
        binding.buttonCancel.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_shoeDetailFragment_to_shoesListFragment))

        val args = ShoeDetailFragmentArgs.fromBundle(requireArguments())
        if(args?.shoe != null)
            showShoe(args.shoe)

        return binding.root

    }

    private fun showShoe(shoe: Shoe){
        binding.editTextShoeName.setText(shoe.name)
        binding.editTextShoeSize.setText(shoe.size.toString())
        binding.editTextShoeCompany.setText(shoe.company)
        binding.editTextShoeDescription.setText(shoe.description)

        binding.buttonSave.visibility = View.GONE
    }

    private fun addShoe() {
        var shoe = Shoe(
            binding.editTextShoeName.text.toString(),
            binding.editTextShoeSize.text.toString().toDouble(),
            binding.editTextShoeCompany.text.toString(),
            binding.editTextShoeDescription.text.toString()
        )

        viewModel.addShoe(shoe)
        view?.findNavController()?.navigate(R.id.action_shoeDetailFragment_to_shoesListFragment)
    }
}