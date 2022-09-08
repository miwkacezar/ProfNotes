package com.example.profnotes.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profnotes.data.model.MyNote
import com.example.profnotes.data.model.NewNote
import com.example.profnotes.data.model.util.ResponseWrapper
import com.example.profnotes.databinding.FragmentHomeBinding
import com.example.profnotes.ui.core.BaseFragment
import com.example.profnotes.ui.home.adapter.MyNotesAdapter
import com.example.profnotes.ui.home.adapter.NewNotesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override val viewModel by viewModels<HomeViewModel>()
    private val viewPagerAdapter by lazy { NewNotesAdapter() }
    private val myNotesAdapter by lazy { MyNotesAdapter() }


    override fun setupViews() {
        lifecycleScope.launchWhenStarted {
            viewModel.note.collect {
                when (it) {
                    is ResponseWrapper.Idle -> {}
                    is ResponseWrapper.Error -> {
                        Log.e("Error", "${it.code}")
                    }
                    is ResponseWrapper.Success -> {
                        Log.e("Success", "${it.body}")
                    }
                }
            }
        }
        viewModel.getNote()
        setupPager()
        setupMyNotes()
    }

    private fun setupPager() {
        with(binding) {
            viewPagerAdapter.setItems(listOf(
                NewNote(
                    id = 1,
                    title = "Sample title",
                    date = "today",
                    description = "Start learning"
                )
            ))

            vpNewNotes.adapter = viewPagerAdapter
        }
    }


    private fun setupMyNotes() {
        with(binding) {
            rvMyTasks.adapter = myNotesAdapter
            rvMyTasks.layoutManager = object : LinearLayoutManager(requireContext()) {
                override fun canScrollVertically() = false
            }
            myNotesAdapter.setItems(
                listOf(
                    MyNote(
                        id = 1,
                        title = "Test",
                        date = "Today",
                        status = "New",
                        description = "Test"
                    )
                )
            )
        }
    }


}