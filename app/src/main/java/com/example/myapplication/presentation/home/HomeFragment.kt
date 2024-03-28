package com.example.myapplication.presentation.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.HomeFragmentBinding
import com.example.myapplication.presentation.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        collectData()
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movies.collectLatest {
                    movieAdapter.submitList(it)
                }
            }
        }
    }

    private fun setupViews() {
        movieAdapter = MovieAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        binding.movieRecycler.adapter = movieAdapter
        binding.movieRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.search.doOnTextChanged { text, start, before, count ->
            viewModel.search(text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}