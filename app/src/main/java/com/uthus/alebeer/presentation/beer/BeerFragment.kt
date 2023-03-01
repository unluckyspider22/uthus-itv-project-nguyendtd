package com.uthus.alebeer.presentation.beer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.uthus.alebeer.databinding.FragmentBeerBinding
import com.uthus.alebeer.presentation.adapter.ARG_OBJECT
import mva2.adapter.MultiViewAdapter

class BeerFragment : Fragment() {

    companion object {
        fun newInstance() = BeerFragment()
    }

    private var _binding: FragmentBeerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BeerViewModel

    private lateinit var adapter : MultiViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(BeerViewModel::class.java)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            _binding?.tvTitle?.text = "Page " + getInt(ARG_OBJECT).toString()
        }
        viewModel.getBeers()
        viewModel.listBeerModel.observe(viewLifecycleOwner) {

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}