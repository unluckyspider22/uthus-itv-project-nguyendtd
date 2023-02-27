package com.uthus.alebeer.presentation.beer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.uthus.alebeer.data.datasource.AleBeerRemoteDataSource
import com.uthus.alebeer.data.datasource.AleBeerRemoteDataSourceImpl
import com.uthus.alebeer.data.repository.AleBeerRepository
import com.uthus.alebeer.data.repository.AleBeerRepositoryImpl
import com.uthus.alebeer.databinding.FragmentBeerBinding
import com.uthus.alebeer.domain.usecase.GetBeersUseCase
import com.uthus.alebeer.domain.usecase.GetBeersUseCaseImpl
import com.uthus.alebeer.presentation.adapter.ARG_OBJECT

class BeerFragment : Fragment() {

    companion object {
        fun newInstance() = BeerFragment()
    }

    private var _binding: FragmentBeerBinding? = null
    private val binding get() = _binding!!
    private val aleBeerRemoteDataSource: AleBeerRemoteDataSource = AleBeerRemoteDataSourceImpl()
    private val repository: AleBeerRepository = AleBeerRepositoryImpl(aleBeerRemoteDataSource = aleBeerRemoteDataSource)
    private val getBeersUseCase : GetBeersUseCase = GetBeersUseCaseImpl(repository = repository)
    private var viewModel: BeerViewModel = BeerViewModel(getBeersUseCase)

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