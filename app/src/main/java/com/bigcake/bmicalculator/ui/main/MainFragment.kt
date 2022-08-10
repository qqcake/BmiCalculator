package com.bigcake.bmicalculator.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bigcake.bmicalculator.R
import com.bigcake.bmicalculator.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.view_value_card.view.*

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.weightCard.plus.setOnClickListener { viewModel.onWeightChangedBy(1) }
        binding.weightCard.minus.setOnClickListener { viewModel.onWeightChangedBy(-1) }
        binding.heightCard.plus.setOnClickListener { viewModel.onHeightChangedBy(1) }
        binding.heightCard.minus.setOnClickListener { viewModel.onHeightChangedBy(-1) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}