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
import com.bigcake.bmicalculator.ui.main.ValueCardView.OnActionButtonClickedListener.Action

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

        binding.weightCard.onActionButtonClickedListener =
            object : ValueCardView.OnActionButtonClickedListener {
                override fun onActionButtonClicked(action: Action) {
                    viewModel.onWeightChangedBy(if (action == Action.PLUS) 1 else -1)
                }
            }
        binding.heightCard.onActionButtonClickedListener =
            object : ValueCardView.OnActionButtonClickedListener {
                override fun onActionButtonClicked(action: Action) {
                    viewModel.onHeightChangedBy(if (action == Action.PLUS) 1 else -1)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}