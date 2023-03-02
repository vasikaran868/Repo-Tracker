package com.example.a1stzoomassignment

import androidx.lifecycle.*
import com.example.a1stzoomassignment.DataLayer.RepoDao
import com.example.a1stzoomassignment.DataModels.Repo
import kotlinx.coroutines.launch

class RepoViewModel(private val repoDao: RepoDao): ViewModel() {

    val repoList : LiveData<List<Repo>> = repoDao.getRepos().asLiveData()

    fun addNewRepo(repo: Repo):Boolean{
        viewModelScope.launch {
            repoDao.insert(repo)
        }
        return true
    }

    fun updateRepo(editedRepo: Repo):Boolean{
        viewModelScope.launch {
            repoDao.update(editedRepo)
        }
        return true
    }

    fun deleteRepo(repo: Repo):Boolean{
        viewModelScope.launch {
            repoDao.delete(repo)
        }
        return true
    }

}


class RepoViewModelFactory(private val repoDao: RepoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RepoViewModel(repoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}