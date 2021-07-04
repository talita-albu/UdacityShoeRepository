package com.udacity.shoestore.shoe

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoesListFragmentBinding
import com.udacity.shoestore.models.Shoe


class ShoesListFragment : Fragment() {


    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: ShoesListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoes_list_fragment,
            container,
            false
        )

        //binding.shoesListViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.shoesList.observe(viewLifecycleOwner,
            Observer { shoesList ->
                shoesList.forEach { shoe ->
                    var myTextShoeName = createShoeNameView(shoe)
                    binding.ListShoesItem.addView(myTextShoeName)
                }
            })

        binding.buttonAddShoe.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_shoesListFragment_to_shoeDetailFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }


    private fun createShoeNameView(shoe: Shoe): View {
        val myTextShoeName = TextView(context)


        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        params.setMargins(12, 24, 12, 24)

        myTextShoeName.layoutParams = params
        myTextShoeName.text = " Shoe Name: ${shoe.name} - Company: ${shoe.company} " +
                "- Size: ${shoe.size} - Description: ${shoe.description} "
        myTextShoeName.setTextAppearance(R.style.ShoeListItem)
        myTextShoeName.setPadding(12,24,12,24)

        var backGroundColor = context?.let { ContextCompat.getColor(it, R.color.colorAccent) }
        backGroundColor?.let { myTextShoeName.setBackgroundColor(it) }

        myTextShoeName.setOnClickListener {
            view?.findNavController()?.navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment(shoe))
        }
        return myTextShoeName
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}