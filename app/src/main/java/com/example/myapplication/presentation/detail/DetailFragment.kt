package com.example.myapplication.presentation.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import java.text.SimpleDateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.common.Constants
import com.example.myapplication.databinding.DetailFragmentBinding
import com.example.myapplication.presentation.detail.adapter.CompaniesAdapter
import com.example.myapplication.presentation.detail.adapter.GenresAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var companiesAdapter: CompaniesAdapter

    private val viewModel: DetailViewModel by viewModels()
    val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = args.movieId
        viewModel.getDetails(movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        collectData()
    }

    private fun setupViews() {
        genresAdapter = GenresAdapter()
        binding.genres.adapter = genresAdapter
        binding.genres.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        companiesAdapter = CompaniesAdapter()
        binding.companies.adapter = companiesAdapter
        binding.companies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.movie.collectLatest {
                    if (it != null) {
                        val movie = it
                        binding.releaseDate.text = formatDate(movie.release_date.orEmpty(),requireContext())
                        binding.originalName.text = movie.original_title.toString()
                        Glide.with(binding.root.context)
                            .load(Constants.IMAGE_PATH.plus(movie.poster_path))
                            .into(binding.poster)
                        binding.overview.text = movie.overview
                        binding.voteAverage.text =
                            binding.root.context.getString(
                                R.string.vote_average,
                                movie.vote_average.toString()
                            )
                        genresAdapter.submitList(movie.genres)
                        companiesAdapter.submitList(movie.production_companies)
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loading.collectLatest {
                    if (it) binding.progress.visibility =
                        View.VISIBLE else binding.progress.visibility = View.GONE
                }
            }
        }
    }

    fun formatDate(dateString: String, context: Context): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        val formattedDate = outputFormat.format(date!!)

        return formattedDate
    }

}