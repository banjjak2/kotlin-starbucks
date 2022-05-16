package com.codesquad.kotlin_starbucks.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.codesquad.kotlin_starbucks.R
import com.codesquad.kotlin_starbucks.ViewModelFactory
import com.codesquad.kotlin_starbucks.databinding.FragmentSplashBinding

class SplashFragment : DialogFragment() {

    private lateinit var binding: FragmentSplashBinding

    private val viewModel by viewModels<SplashViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_full_screen)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerButtonsClickListener()
        setObserveLiveData()

        viewModel.getEventDetail(SplashViewModel.EVENT_JSON_URL)
    }

    private fun registerButtonsClickListener() {
        binding.btnJustClose.setOnClickListener {
            dismiss()
        }

        binding.btnDontShowAgain.setOnClickListener {
            val pref = requireActivity().getSharedPreferences(
                getString(R.string.event_pref_name),
                Context.MODE_PRIVATE
            )

            pref.edit {
                putBoolean(
                    getString(R.string.event_open_key),
                    false
                )
            }
            dismiss()
        }
    }

    private fun setObserveLiveData() {
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), requireContext().getString(it), Toast.LENGTH_SHORT)
                .show()
        }

        viewModel.eventDataDetail.observe(viewLifecycleOwner) {
            binding.eventDetail = it
            binding.executePendingBindings()
        }
    }
}