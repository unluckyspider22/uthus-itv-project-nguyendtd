package com.uthus.alebeer.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.uthus.alebeer.AleBeerApplication
import com.uthus.alebeer.R
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.databinding.FragmentFavoriteBinding
import com.uthus.alebeer.presentation.adapter.binder.BeerBinder
import com.uthus.alebeer.presentation.adapter.binder.FavoriteBinder
import com.uthus.alebeer.presentation.adapter.binder.OnButtonClickedListener
import com.uthus.alebeer.util.Action
import com.uthus.alebeer.util.EventBus
import com.uthus.alebeer.util.FavoriteViewModelFactory
import com.uthus.alebeer.util.ToastExt
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val application by lazy {
        activity?.application as AleBeerApplication
    }
    private val viewModel: FavoriteViewModel by viewModels {
        FavoriteViewModelFactory(
            application.getLocalBeersUseCase,
            application.deleteFavoriteBeerUseCase,
            application.updateFavoriteBeerUseCase
        )
    }

    private lateinit var adapter: MultiViewAdapter
    private var listSection = ListSection<BeerModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        _binding?.root?.requestLayout()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        getBeers()
        setupObserver()
        subscribeOnInsert()
    }

    private fun subscribeOnInsert() {
        lifecycleScope.launchWhenStarted {
            EventBus.events.filter { action -> action == Action.SAVE }.collectLatest {
                getBeers()
            }
        }
    }

    private fun getBeers() {
        viewModel.getLocalBeers()
    }

    private fun setupObserver() = with(viewModel) {
        listBeers.observe(viewLifecycleOwner) { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    _binding?.progressCircular?.visibility = View.GONE

                    //Add data to adapter
                    addAdapterSection(resultState.data)

                }
                is ResultState.Error -> {
                    //TODO: Handle error case here
                }
                is ResultState.Loading -> {
                    _binding?.progressCircular?.visibility = View.VISIBLE
                }
            }
        }

        onDeleteBeer.observe(viewLifecycleOwner) { resultState ->
            when(resultState) {
                is ResultState.Success -> {
                    if(resultState.data != null && resultState.data > 0) {
                        listSection.remove(currentDeletePos)
                        adapter.notifyItemRemoved(currentDeletePos)
                        ToastExt.showToast(requireContext(), R.string.deleted)

                    } else {
                        ToastExt.showToast(requireContext(), R.string.error)

                    }
                }
                is ResultState.Error -> {
                    ToastExt.showToast(requireContext(), R.string.error)

                }
            }
        }

        onUpdateBeer.observe(viewLifecycleOwner) { resultState ->
            when(resultState) {
                is ResultState.Success -> {
                    if(resultState.data != null && resultState.data > 0) {
                        listSection[currentUpdatePos].note = noteChanged
                        adapter.notifyItemChanged(currentUpdatePos)
                        ToastExt.showToast(requireContext(), R.string.updated)

                    } else {
                        ToastExt.showToast(requireContext(), R.string.error)

                    }
                }
                is ResultState.Error -> {
                    ToastExt.showToast(requireContext(), R.string.error)
                }
            }
        }
    }

    private fun addAdapterSection(it: List<BeerModel>) {
        listSection.clear()
        adapter.removeAllSections()
        listSection.addAll(it.toMutableList())
        adapter.addSection(listSection)
    }

    private fun setupAdapter() {
        adapter = MultiViewAdapter()
        _binding?.rvBeer?.adapter = adapter
        adapter.registerItemBinders(FavoriteBinder(onButtonClickedListener = object :
            OnButtonClickedListener {
            override fun onButtonSaveClick(position: Int, beerModel: BeerModel) {
            }

            override fun onButtonDeleteClick(position: Int, beerModel: BeerModel) {
                viewModel.deleteFavoriteBeer(beerModel.id, position)
            }

            override fun onButtonUpdateClicked(position: Int, beerModel: BeerModel) {
                viewModel.updateFavoriteBeer(beerModel.note.orEmpty(), beerModel.id, position)
            }
        }))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}