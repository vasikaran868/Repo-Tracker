package com.example.a1stzoomassignment.RepoFragments

import android.content.Intent
import android.net.Uri
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
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1stzoomassignment.Adapters.RepoRecyclerViewAdapter
import com.example.a1stzoomassignment.DataModels.Repo
import com.example.a1stzoomassignment.Helpers.Constants
import com.example.a1stzoomassignment.R
import com.example.a1stzoomassignment.RepoApplication
import com.example.a1stzoomassignment.RepoViewModel
import com.example.a1stzoomassignment.RepoViewModelFactory
import com.example.a1stzoomassignment.databinding.FragmentRepoListBinding


class RepoList : Fragment() {

    private var _binding :FragmentRepoListBinding? = null
    val binding get() = _binding!!
    var actionBar : ActionBar? = null
    lateinit var dialog: RepoVhActionDialog

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
            findViewById<TextView>(R.id.title).text = "Repo Tracker"
            findViewById<TextView>(R.id.title).gravity = Gravity.CENTER
            findViewById<ImageView>(R.id.back_btn).visibility = View.INVISIBLE
            findViewById<ConstraintLayout>(R.id.add_btn).visibility = View.VISIBLE
            findViewById<ConstraintLayout>(R.id.add_btn).setOnClickListener {
                val action = RepoListDirections.actionRepoListToAddRepo()
                activity?.findNavController(R.id.repo_frag_container)?.navigate(action)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RepoRecyclerViewAdapter(
            requireContext(),
            showDialog = {
                dialog = RepoVhActionDialog(it,
                    editAction = {
                        dialog.dismiss()
                        val action = RepoListDirections.actionRepoListToEditRepo(it)
                        activity?.findNavController(R.id.repo_frag_container)?.navigate(action)

                    },
                    shareAction = {
                        dialog.dismiss()
                        val shareIntent = Intent.createChooser(Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, Constants.GITHUB_BASE_URL + it.ownerName + "/" + it.repoName)
                            type = "text/plain"
                        },"share")
                        startActivity(shareIntent)
                    },
                    deleteAction = {
                        dialog.dismiss()
                        viewModel.deleteRepo(it)
                    },
                    redirectAction = {
                        dialog.dismiss()
                        val intent = Intent("android.intent.action.VIEW", Uri.parse(Constants.GITHUB_BASE_URL + it.ownerName + "/" + it.repoName))
                        startActivity(intent)
                    }
                )
                dialog.show(childFragmentManager, "repo action")
            }
        )
        binding.repoListRecView.adapter = adapter
        binding.repoListRecView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.repoList.observe(viewLifecycleOwner){
            adapter.submitList(it)
            if (it.size > 0){
                binding.apply {
                    trackTv.visibility = View.INVISIBLE
                    floatAddBtn.visibility = View.INVISIBLE
                }
            }
            else{
                binding.apply {
                    trackTv.visibility = View.VISIBLE
                    floatAddBtn.visibility = View.VISIBLE
                }
            }
        }

        binding.floatAddBtn.setOnClickListener {
            val action = RepoListDirections.actionRepoListToAddRepo()
            activity?.findNavController(R.id.repo_frag_container)?.navigate(action)
        }
    }
}