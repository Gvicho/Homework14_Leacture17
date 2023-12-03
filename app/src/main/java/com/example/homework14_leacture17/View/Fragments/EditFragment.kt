package com.example.homework14_leacture17.View.activitys.Fragments

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homework14_leacture17.model.data.EDIBLE
import com.example.homework14_leacture17.View.activitys.Fragments.BaseFragments.BaseFragment
import com.example.homework14_leacture17.viewmodels.SharedViewModel
import com.example.homework14_leacture17.databinding.FragmentEditBinding


class EditFragment : BaseFragment<FragmentEditBinding>(FragmentEditBinding::inflate) {

    private val args :EditFragmentArgs by navArgs()
    private val viewModel : SharedViewModel by activityViewModels()

    override fun setUp() {
        val itemId = args.mushroomId
        binding.idNum.text = itemId.toString()

        binding.saveBtn.setOnClickListener{
            val name = binding.edtName.text.toString()
            val type = binding.edtType.text.toString()
            var typeNum = -1
            val item = viewModel.items.value.find { it.id == itemId }
            try {
                typeNum = type.toInt()
            }catch (e:NumberFormatException){

            }
            var ifEdible = EDIBLE.SAFE
            item?.let { it1 ->
                if(typeNum == 2)ifEdible  = EDIBLE.DANGEROUS
                viewModel.updateItem(it1.copy(name = name, ifEdible = ifEdible ))
            }
            findNavController().popBackStack()
        }
    }

    override fun initData() {

    }

}