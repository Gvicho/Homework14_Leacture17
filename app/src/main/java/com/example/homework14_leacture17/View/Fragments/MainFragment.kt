package com.example.homework14_leacture17.View.activitys.Fragments

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework14_leacture17.View.activitys.Fragments.BaseFragments.BaseFragment
import com.example.homework14_leacture17.View.activitys.recycler_adapters.ItemListener
import com.example.homework14_leacture17.model.data.Mushroom
import com.example.homework14_leacture17.View.activitys.recycler_adapters.MyRecyclerAdapter
import com.example.homework14_leacture17.viewmodels.SharedViewModel
import com.example.homework14_leacture17.databinding.FragmentMainBinding
import kotlinx.coroutines.launch


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate), ItemListener {

    private lateinit var myAdaper: MyRecyclerAdapter
    private val viewModel : SharedViewModel by activityViewModels()

    override fun setUp() {

        myAdaper = MyRecyclerAdapter(this)

        bindings()
    }

    private fun bindings(){

        binding.apply {

            swipeLayout.setOnRefreshListener {
                // something here maybe
                binding.swipeLayout.isRefreshing = false
            }

            addItem1Btn.setOnClickListener{
                addEmptyElement()
            }

            recycler.apply {
                setHasFixedSize(false)
                layoutManager = GridLayoutManager(context,2)
                adapter = myAdaper
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.items.collect { itemList ->
                myAdaper.submitList(itemList)
            }
        }
    }

    private fun addEmptyElement(){
        viewModel.addEmptyMushroom()
    }

    override fun initData() {

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun removeItem(mushroom: Mushroom) {
        // will delete item from list
        /*val currentList = myAdaper.currentList.toMutableList()
        currentList.remove(mushroom)
        myAdaper.submitList(currentList)*/

        viewModel.removeItem(mushroom.id)

        // we can't resubmit modified version of list because :
        //When you pass the same list instance (even if its contents have changed), DiffUtil might not recognize the changes correctly, leading to crashes or freezes.
        //submitlist needs new instance
    }

    override fun editItem(mushroom: Mushroom) {
        val action = MainFragmentDirections.actionMainFragmentToEditFragment(mushroom.id)
        findNavController().navigate(action)
    }

}