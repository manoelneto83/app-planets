package com.example.appplanet.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.appplanet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetDetailsFragment : Fragment() {

    private val viewModel : PlanetDetailsViewModel by viewModels()
    private val args: PlanetDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planet_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPlanetDetails(args.planetid)

        viewModel.state.observe(viewLifecycleOwner) {
            state ->
            when(state) {
                is PlanetDetailsViewModel.State.Content -> {
                    val image = view.findViewById<ImageView>(R.id.imageView2)
                    val name = view.findViewById<TextView>(R.id.tv_planet_name)
                    val description = view.findViewById<TextView>(R.id.tv_detail_name)

                    name.text = state.planet.name
                    description.text = state.planet.description
                    Glide
                        .with(image)
                        .load(state.planet.url)
                        .fitCenter()
                        .into(image)

                }
                PlanetDetailsViewModel.State.Error -> {
//                    val loading = view.findViewById<ProgressBar>(R.id.loading)
//                    val error = view.findViewById<ImageView>(R.id.error)
//
//                    loading.isVisible = false
//                    error.isVisible = true

                }
                PlanetDetailsViewModel.State.Loading -> {
//                    val loading = view.findViewById<ProgressBar>(R.id.loading)
//                    val error = view.findViewById<ImageView>(R.id.error)
//
//                    loading.isVisible = true
//                    error.isVisible = false
                }
            }
        }
    }
}