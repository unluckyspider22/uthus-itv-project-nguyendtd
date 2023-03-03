package com.uthus.alebeer.presentation.beer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.uthus.alebeer.AleBeerApplication
import com.uthus.alebeer.R
import com.uthus.alebeer.data.model.BeerModel

import com.uthus.alebeer.databinding.FragmentBeerBinding
import com.uthus.alebeer.presentation.adapter.binder.BeerBinder
import com.uthus.alebeer.presentation.adapter.binder.ButtonClickedListener
import com.uthus.alebeer.util.BeerViewModelFactory
import com.uthus.alebeer.util.statemanagement.ResultState
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class BeerFragment : Fragment() {

    companion object {
        fun newInstance() = BeerFragment()
    }

    private var _binding: FragmentBeerBinding? = null
    private val binding get() = _binding!!
    private val application by lazy {
        activity?.application as AleBeerApplication
    }
    private val viewModel: BeerViewModel by viewModels {
        BeerViewModelFactory(
            application.getBeersUseCase, application.saveFavoriteUseCase
        )
    }

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
        setupAdapter()
        getBeers()
        setupObserver()
    }

    private fun getBeers() {
        viewModel.getBeers()
    }

    private fun setupObserver() = with(viewModel) {
        listBeerModel.observe(viewLifecycleOwner) { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    _binding?.progressCircular?.visibility = View.GONE

                        //Add data to adapter
                        addAdapterSection(resultState.data.data)

                }
                is ResultState.Error -> {
                    //TODO: Handle error case here
                }
                is ResultState.Loading -> {
                    _binding?.progressCircular?.visibility = View.VISIBLE
                }
            }
        }
        onSaveFavorite.observe(viewLifecycleOwner) { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    if(resultState.data != null && resultState.data > 0) {
                        Toast.makeText(requireContext(), R.string.saved,Toast.LENGTH_SHORT).show()
                    }
                }
                is ResultState.Error -> {
                    //TODO: Handle error case here
                }
                is ResultState.Loading -> {

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
            override fun onButtonSaveClick(position: Int, beerModel: BeerModel) {
                viewModel.saveFavorite(beerModel, position)
            }
        }))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}