package com.example.appplanet.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.appplanet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: PlanetListViewModel by viewModels()

    private val clickListener = { planet: PlanetData ->
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToPlanetDetailsFragment(planet.id))
    }

    private val adapter = PlanetsAdapter(clickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = view.findViewById<RecyclerView>(R.id.list)
        val loading = view.findViewById<ProgressBar>(R.id.loading)
        val error = view.findViewById<ImageView>(R.id.error)

        list.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.state.observe(viewLifecycleOwner) {viewState ->
            when(viewState) {
                is State.Content -> {
                    adapter.setData(viewState.list)
                    list.adapter = adapter
                    loading.isVisible = false
                    error.isVisible = false
                }
                State.Error -> {
                    loading.isVisible = false
                    error.isVisible = true

                }
                State.Loading -> {
                    loading.isVisible = true
                    error.isVisible = false
                }
            }
        }

        viewModel.loadData()
    }
}