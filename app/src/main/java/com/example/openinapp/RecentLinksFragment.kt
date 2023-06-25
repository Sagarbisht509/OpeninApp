package com.example.openinapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openinapp.adapters.LinkAdapter
import com.example.openinapp.databinding.FragmentRecentLinksBinding
import com.example.openinapp.utils.NetworkResult
import com.example.openinapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentLinksFragment : Fragment() {

    private lateinit var binding : FragmentRecentLinksBinding

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentLinksBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recentLinkRecyclerView.layoutManager = LinearLayoutManager(context)

        observer()
    }

    private fun observer() {
        mainViewModel.liveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Loading -> {
                    // show progress bar

                    Log.d("find-Me", "loading");
                }
                is NetworkResult.Success -> {
                    if(it.data != null) {
                        binding.recentLinkRecyclerView.adapter = LinkAdapter(it.data.data.top_links, it.data.data.recent_links, "recent")
                    }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

}