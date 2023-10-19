package com.lahza.todo.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lahza.todo.BuildConfig
import com.lahza.todo.adapters.CategoriesAdapter
import com.lahza.todo.databinding.FragmentHomeBinding
import com.lahza.todo.models.Category
import com.lahza.todo.utils.JobSchedulerUseCase
import com.lahza.todo.viewModel.EncryptionViewModel
import com.lahza.todo.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    private val encryptionViewModel: EncryptionViewModel by lazy { ViewModelProvider(this)[EncryptionViewModel::class.java] }
    private val jobSchedulerUseCase: JobSchedulerUseCase by lazy { JobSchedulerUseCase(requireContext()) }
    private val categoriesAdapter: CategoriesAdapter by lazy { CategoriesAdapter() }
    private val originalString = BuildConfig.ORIGINAL_STRING


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val encryptedString: String = encryptionViewModel.encryptData(originalString)
        Log.d("Encrypted String", encryptedString)

        val decryptedString: String = encryptionViewModel.decryptData(encryptedString)
        Log.d("Decrypted String", decryptedString)

        prepareCategoriesRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                jobSchedulerUseCase.scheduleJob()
                viewModel.getCategories()
                observeCategories()
            }
        }
    }

    private fun observeCategories() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner) { categories ->
            categories?.let {
                categoriesAdapter.setCategories(it as ArrayList<Category>)
            }
        }
    }

    private fun prepareCategoriesRecyclerView() {
        binding.rvCategories.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvCategories.adapter = categoriesAdapter
    }
}
