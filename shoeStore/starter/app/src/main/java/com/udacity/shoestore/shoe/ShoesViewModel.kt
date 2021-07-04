package com.udacity.shoestore.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe


class ShoesViewModel : ViewModel() {

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe


    private var _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    init {
        var shoeInitial = Shoe("",0.0, "","")
        _shoe.value = shoeInitial

        if (_shoesList.value == null) {
            val list = mutableListOf<Shoe>()
            _shoesList.value = list
            resetList()
        }
    }


    fun addShoe(shoe: Shoe) {
        _shoesList.value?.add(shoe)
    }

    fun resetList() {
        val list = mutableListOf<Shoe>()
        _shoesList.value = list

        for (i in 1..5) {
            var newShow = Shoe("Name $i", i.toDouble(), "Company Name $i", "Description Of Show $i",
            null)

            _shoesList.value?.add(newShow)
        }
    }
}