package com.example.profnotes.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.profnotes.R
import com.example.profnotes.databinding.FragmentLoginBinding
import com.example.profnotes.databinding.FragmentRegistryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistryFragment : Fragment() {
    private var _binding: FragmentRegistryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnRegistry.apply {
                isEnabled = true
                setOnClickListener {
                    findNavController().navigate(R.id.action_registryFragment_to_loginFragment)
                }
            }
            tvLoginWithAccount.setOnClickListener {
                findNavController().navigate(R.id.action_registryFragment_to_loginFragment)
            }
        }
    }
}