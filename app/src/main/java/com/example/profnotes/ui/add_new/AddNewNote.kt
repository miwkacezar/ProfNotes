package com.example.profnotes.ui.add_new

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import com.example.profnotes.R
import com.example.profnotes.core.active
import com.example.profnotes.core.gone
import com.example.profnotes.core.passive
import com.example.profnotes.core.visible
import com.example.profnotes.databinding.FragmentAddNewNoteBinding
import com.example.profnotes.databinding.FragmentRegistryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewNote : Fragment() {
    private var _binding: FragmentAddNewNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvDeadLine.gone()
            setDeadLine.gone()
            btnAddNote.active()
            tvFriends.visible()
            vpFriends.visible()
            calendarView.visible()

            btnAddTask.setOnClickListener {
                btnAddTask.active()
                btnAddNote.passive()
                tvFriends.gone()
                vpFriends.gone()
                calendarView.gone()
                tvDeadLine.visible()
                setDeadLine.visible()
            }
            btnAddNote.setOnClickListener {
                btnAddTask.passive()
                btnAddNote.active()
                tvFriends.visible()
                vpFriends.visible()
                calendarView.visible()
                tvDeadLine.gone()
                setDeadLine.gone()
            }
            back.setOnClickListener {
                findNavController().navigate(R.id.action_addNewNote_to_navigation_home)
            }
            btnCreate.setOnClickListener {

            }
        }

    }


}