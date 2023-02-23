package com.uthus.alebeer.ui.beer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uthus.alebeer.R
import com.uthus.alebeer.databinding.FragmentBeerBinding
import com.uthus.alebeer.ui.adapter.ARG_OBJECT

class BeerFragment : Fragment() {

    companion object {
        fun newInstance() = BeerFragment()
    }

    private var _binding: FragmentBeerBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: BeerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            _binding?.tvTitle?.text = "Page " + getInt(ARG_OBJECT).toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}