package com.example.notesapplofcoding.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapplofcoding.R
import com.example.notesapplofcoding.VerticalItemDecoration
import com.example.notesapplofcoding.adapters.NotesAdapter
import com.example.notesapplofcoding.databinding.FragmentNotesListBinding
import com.example.notesapplofcoding.db.Note
import com.example.notesapplofcoding.viewmodel.NotesViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
    private lateinit var binding: FragmentNotesListBinding
    private lateinit var notestAdapter : NotesAdapter
    private lateinit var viewModel : NotesViewModel
    var deger : List<Note>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                  delay(150)
                  viewModel.notes.collect {
                      notestAdapter.differ.submitList(it)
                        deger = it
                   }
                 }
                launch {
                   viewModel.searchNotes.collect {
                       notestAdapter.differ.submitList(it)
                    }
                }
            }
        }

        binding.edSearch.addTextChangedListener {
            if(it.toString().isEmpty()){
            notestAdapter.differ.submitList(deger)
            /* lifecycleScope.launch {
                    viewModel.notes.collect {
                        notestAdapter.differ.submitList(it)
                        println("notlarCollect2")
                    }
                }*/
            }else{
                viewModel.searchNotes(it.toString().trim())
            }
        }


        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
        }
        binding.deleteAllbtn.setOnClickListener {
            viewModel.deleteAllNotes()
        }
        notestAdapter.onClick = {
            val bundle = Bundle().apply {
                putParcelable("note",it)
            }
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment,bundle)
        }
    }

    private fun setAdapter() {
        notestAdapter = NotesAdapter()
        binding.rvNotes.apply {
            layoutManager= LinearLayoutManager(context)
            adapter = notestAdapter
            addItemDecoration(VerticalItemDecoration(30))
        }
    }


}