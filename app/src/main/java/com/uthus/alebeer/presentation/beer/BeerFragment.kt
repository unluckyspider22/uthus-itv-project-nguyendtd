package com.uthus.alebeer.presentation.beer

import android.os.Bundle
import android.transition.Visibility
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.uthus.alebeer.data.model.BeerModel

import com.uthus.alebeer.databinding.FragmentBeerBinding
import com.uthus.alebeer.presentation.adapter.ARG_OBJECT
import com.uthus.alebeer.presentation.adapter.binder.BeerBinder
import com.uthus.alebeer.presentation.adapter.binder.ButtonClickedListener
import com.uthus.alebeer.util.statemanagement.ResultState
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class BeerFragment : Fragment() {

    companion object {
        fun newInstance() = BeerFragment()
    }

    private var _binding: FragmentBeerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BeerViewModel

    private lateinit var adapter: MultiViewAdapter
    private var listSection = ListSection<BeerModel>()

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
        setupAdapter()
        getBeers()
        setupObserver()
    }

    private fun getBeers() {
        viewModel.getBeers()
    }

    private fun setupObserver() {
        viewModel.listBeerModel.observe(viewLifecycleOwner) { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    _binding?.progressCircular?.visibility = View.GONE
                    resultState.data?.let {
                        addAdapterSection(it)
                    }
                }
                is ResultState.Error -> {
                    //TODO: Handle error case here
                }
                is ResultState.Loading -> {
                    _binding?.progressCircular?.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun addAdapterSection(it: List<BeerModel>) {
        listSection.addAll(it.toMutableList())
        adapter.addSection(listSection)
    }

    private fun setupAdapter() {
        adapter = MultiViewAdapter()
        _binding?.rvBeer?.adapter = adapter
        adapter.registerItemBinders(BeerBinder(buttonClickedListener = object :
            ButtonClickedListener {
            override fun onButtonSaveClick(position: Int) {
                Toast.makeText(context, "aaaa : $position", Toast.LENGTH_SHORT).show()
            }

        }))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}