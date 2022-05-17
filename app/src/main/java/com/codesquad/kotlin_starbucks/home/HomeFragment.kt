package com.codesquad.kotlin_starbucks.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codesquad.kotlin_starbucks.R
import com.codesquad.kotlin_starbucks.ViewModelFactory
import com.codesquad.kotlin_starbucks.databinding.FragmentHomeBinding
import com.codesquad.kotlin_starbucks.splash.SplashFragment
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

        viewModel.homeData.observe(viewLifecycleOwner) {
            binding.homedata = it
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