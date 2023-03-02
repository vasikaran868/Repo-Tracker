package com.example.a1stzoomassignment.RepoFragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.a1stzoomassignment.DataModels.Repo
import com.example.a1stzoomassignment.R
import com.example.a1stzoomassignment.RepoApplication
import com.example.a1stzoomassignment.RepoViewModel
import com.example.a1stzoomassignment.RepoViewModelFactory
import com.example.a1stzoomassignment.databinding.FragmentAddRepoBinding


class AddRepo : Fragment() {

    private var _binding : FragmentAddRepoBinding? = null
    val binding get() = _binding!!
    var actionBar : ActionBar? = null

    val viewModel : RepoViewModel by activityViewModels {
        RepoViewModelFactory(
            (activity?.application as RepoApplication).database.repoDao()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        actionBar = (activity as AppCompatActivity?)!!.supportActionBar!!
        actionBar!!.show()
        actionBar!!.customView.apply {
            findViewById<TextView>(R.id.title).text = "Add Repository"
            findViewById<TextView>(R.id.title).gravity = Gravity.CENTER
            findViewById<ImageView>(R.id.back_btn).visibility = View.VISIBLE
            findViewById<ImageView>(R.id.back_btn).setOnClickListener {
                activity?.findNavController(R.id.repo_frag_container)?.navigateUp()
            }
            findViewById<ConstraintLayout>(R.id.add_btn).visibility = View.INVISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRepoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addBtn.setOnClickListener {
            if (validateInput()){
                viewModel.addNewRepo(
                    Repo(
                        ownerName = binding.repoOwnerNameEt.text.toString(),
                        repoName = binding.repoNameEt.text.toString(),
                        repoDescription = binding.repoDescEt.text.toString().trimEnd()
                    )
                )
                activity?.findNavController(R.id.repo_frag_container)?.navigateUp()
            }
        }
    }

    fun validateInput(): Boolean{
        if (binding.repoOwnerNameEt.text.isNullOrBlank()){
            binding.repoOwnerNameEt.setError("Enter Valid Owner Name")
            return false
        }
        else if (binding.repoNameEt.text.isNullOrBlank()){
            binding.repoNameEt.setError("Enter Valid Repository Name")
            return false
        }
        else if (binding.repoDescEt.text.isNullOrBlank()){
            binding.repoDescEt.setError("Enter Valid Repository Description")
            return false
        }
        return true
    }

}