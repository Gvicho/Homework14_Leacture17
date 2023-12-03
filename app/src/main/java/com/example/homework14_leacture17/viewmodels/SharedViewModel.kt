package com.example.homework14_leacture17.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homework14_leacture17.model.data.EDIBLE
import com.example.homework14_leacture17.model.data.HABITAT
import com.example.homework14_leacture17.model.data.Mushroom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Mushroom>>(emptyList())
    val items: StateFlow<List<Mushroom>> = _items.asStateFlow()

    fun updateItem(updatedItem: Mushroom) {
        _items.value = _items.value.map { if (it.id == updatedItem.id) updatedItem else it }
    }

    fun addItem(item: Mushroom) {
        _items.value = _items.value + item
    }

    fun removeItem(itemId: Int) {
        _items.value = _items.value.filter { it.id != itemId }
    }

    var id = 8

    init {
        addItem(Mushroom(1, EDIBLE.DANGEROUS,"Fly agaric", listOf<HABITAT>()))
        addItem(
            Mushroom(2,
                EDIBLE.SAFE,"Lions Mushroom", listOf<HABITAT>(HABITAT.MIXED_FORES, HABITAT.CONIFEROUS_FOREST))
        )
        addItem(Mushroom(3, EDIBLE.DANGEROUS,"Fly agaric", listOf<HABITAT>()))
        addItem(Mushroom(4, EDIBLE.DANGEROUS,"Fly agaric", listOf<HABITAT>()))
        addItem(
            Mushroom(5,
                EDIBLE.SAFE,"Fly agaric", listOf<HABITAT>(HABITAT.MIXED_FORES, HABITAT.CONIFEROUS_FOREST))
        )
        addItem(Mushroom(6, EDIBLE.DANGEROUS,"Fly agaric", listOf<HABITAT>()))
        addItem(Mushroom(7, EDIBLE.DANGEROUS,"Fly agaric", listOf<HABITAT>()))
        addItem(
            Mushroom(8,
                EDIBLE.SAFE,"Fly agaric", listOf<HABITAT>(
                    HABITAT.MIXED_FORES,
                    HABITAT.CONIFEROUS_FOREST,
                    HABITAT.REGULAR_FORES))
        )
    }

    private fun increaseId(){
        id++
    }

    fun addEmptyMushroom(){
        increaseId()
        val mushy1 = Mushroom(id, EDIBLE.SAFE,"Empty", listOf<HABITAT>())
        addItem(mushy1)
    }
}