package com.codesquad.kotlin_starbucks.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.codesquad.kotlin_starbucks.R
import com.codesquad.kotlin_starbucks.ViewModelFactory
import com.codesquad.kotlin_starbucks.databinding.FragmentHomeBinding
import com.codesquad.kotlin_starbucks.splash.SplashFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel> { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkShowEventScreen()) {
            SplashFragment().show(
                parentFragmentManager,
                "SplashEvent"
            )
        }

        val itemDecoration = ItemDecoration(32)

        val recommendListAdapter = ItemListAdapter()
        binding.recommendList.adapter = recommendListAdapter
        binding.recommendList.addItemDecoration(itemDecoration)

        val recommendCurrentAdapter = ItemListAdapter()
        binding.recommendCurrentMenu.adapter = recommendCurrentAdapter
        binding.recommendCurrentMenu.addItemDecoration(itemDecoration)

        val homeEventsAdapter = ItemListAdapter()
        binding.eventsList.adapter = homeEventsAdapter
        binding.eventsList.addItemDecoration(itemDecoration)

        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                viewModel.displayName.collect {
                    binding.displayname = it
                }
            }

            launch {
                viewModel.mainEventImagePath.collect {
                    binding.mainEventImageUrl = it
                }
            }

            launch {
                viewModel.homeEvents.collect {
                    homeEventsAdapter.submitList(it)
                }
            }

            launch {
                viewModel.yourRecommendItems.collect {
                    recommendListAdapter.submitList(it)
                }
            }

            launch {
                viewModel.nowRecommendItems.collect {
                    recommendCurrentAdapter.submitList(it)
                }
            }
        }

        viewModel.getHomeData()
    }

    private fun checkShowEventScreen(): Boolean {
        val pref = requireActivity().getSharedPreferences(
            getString(R.string.event_pref_name),
            Context.MODE_PRIVATE
        )

        val savedDay = pref.getInt(
            getString(R.string.event_saved_day_key),
            1
        )

        val openCheck = pref.getBoolean(
            getString(R.string.event_open_key),
            true
        )

        val today = SimpleDateFormat("dd", Locale.KOREA)
            .format(
                Date(System.currentTimeMillis())
            )
            .toInt()

        if (savedDay != today) {
            pref.edit {
                putBoolean(
                    getString(R.string.event_open_key),
                    true
                )
                putInt(
                    getString(R.string.event_saved_day_key),
                    today
                )
            }
            return true
        }

        return openCheck
    }
}