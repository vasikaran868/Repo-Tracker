package com.example.a1stzoomassignment.RepoFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.a1stzoomassignment.DataModels.Repo
import com.example.a1stzoomassignment.R
import com.example.a1stzoomassignment.databinding.FragmentRepoVhActionDialogBinding


class RepoVhActionDialog(repo: Repo, val editAction:(Repo)-> Unit, val deleteAction:(Repo)->Unit, val redirectAction:(Repo)->Unit, val shareAction:(Repo)->Unit) : DialogFragment() {

    private var _binding :FragmentRepoVhActionDialogBinding? = null
    val binding get() = _binding!!
    val repo = repo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.dialog?.window?.setDimAmount(.3f)

    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            ownerNameTv.text = repo.ownerName
            repoNameTv.text = repo.repoName
            descTv.text = repo.repoDescription
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoVhActionDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editBtn.setOnClickListener {
            editAction(repo)
        }
        binding.deleteBtn.setOnClickListener {
            deleteAction(repo)
        }
        binding.shareBtn.setOnClickListener {
            shareAction(repo)
        }
        binding.viewRepoBtn.setOnClickListener {
            redirectAction(repo)
        }
    }
}