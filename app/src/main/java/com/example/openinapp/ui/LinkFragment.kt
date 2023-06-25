package com.example.openinapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.openinapp.FragmentPageAdapter
import com.example.openinapp.R
import com.example.openinapp.adapters.ItemAdapter
import com.example.openinapp.databinding.FragmentLinkBinding
import com.example.openinapp.models.ApiResponse
import com.example.openinapp.models.Item
import com.example.openinapp.models.OverallUrlChart
import com.example.openinapp.utils.NetworkResult
import com.example.openinapp.viewmodel.MainViewModel
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class LinkFragment : Fragment() {

    private lateinit var binding: FragmentLinkBinding
    private lateinit var adapter: FragmentPageAdapter

    private val mainViewModel by viewModels<MainViewModel>()

    lateinit var array: List<Item>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLinkBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setGreeting()
        observer()
        setupTabLayoutAndViewPager()
        setupRecyclerView()
    }

    private fun setGreeting() {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        binding.greeting.text =
            if (currentHour <= 12) "Good morning" else if (currentHour < 16) "Good afternoon" else "Good evening"
    }

    private fun setupLineChart(overallUrlChart: OverallUrlChart) {
        val list: ArrayList<BarEntry> = ArrayList()

        list.add(BarEntry(101f, overallUrlChart.`2023-05-25` + 0f))
        list.add(BarEntry(102f, overallUrlChart.`2023-05-26` + 0f))
        list.add(BarEntry(103f, overallUrlChart.`2023-05-27` + 0f))
        list.add(BarEntry(104f, overallUrlChart.`2023-05-28` + 0f))
        list.add(BarEntry(105f, overallUrlChart.`2023-05-29` + 0f))
        list.add(BarEntry(106f, overallUrlChart.`2023-05-30` + 0f))
        list.add(BarEntry(107f, overallUrlChart.`2023-05-31` + 0f))
        list.add(BarEntry(108f, overallUrlChart.`2023-06-01` + 0f))
        list.add(BarEntry(109f, overallUrlChart.`2023-06-02` + 0f))
        list.add(BarEntry(110f, overallUrlChart.`2023-06-03` + 0f))
        list.add(BarEntry(111f, overallUrlChart.`2023-06-04` + 0f))
        list.add(BarEntry(112f, overallUrlChart.`2023-06-05` + 0f))
        list.add(BarEntry(113f, overallUrlChart.`2023-06-06` + 0f))
        list.add(BarEntry(114f, overallUrlChart.`2023-06-07` + 0f))
        list.add(BarEntry(115f, overallUrlChart.`2023-06-08` + 0f))
        list.add(BarEntry(116f, overallUrlChart.`2023-06-09` + 0f))
        list.add(BarEntry(117f, overallUrlChart.`2023-06-10` + 0f))
        list.add(BarEntry(118f, overallUrlChart.`2023-06-11` + 0f))
        list.add(BarEntry(119f, overallUrlChart.`2023-06-12` + 0f))
        list.add(BarEntry(120f, overallUrlChart.`2023-06-13` + 0f))
        list.add(BarEntry(121f, overallUrlChart.`2023-06-14` + 0f))
        list.add(BarEntry(122f, overallUrlChart.`2023-06-15` + 0f))
        list.add(BarEntry(123f, overallUrlChart.`2023-06-16` + 0f))
        list.add(BarEntry(124f, overallUrlChart.`2023-06-17` + 0f))
        list.add(BarEntry(125f, overallUrlChart.`2023-06-18` + 0f))
        list.add(BarEntry(126f, overallUrlChart.`2023-06-19` + 0f))
        list.add(BarEntry(127f, overallUrlChart.`2023-06-20` + 0f))
        list.add(BarEntry(128f, overallUrlChart.`2023-06-21` + 0f))
        list.add(BarEntry(129f, overallUrlChart.`2023-06-22` + 0f))
        list.add(BarEntry(130f, overallUrlChart.`2023-06-23` + 0f))

        val barDataSet = BarDataSet(list, "")

        //  barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        // barDataSet.valueTextColor=Color.BLACK

        val barData = BarData(barDataSet)

        binding.apply {
            barChart.setFitBars(true)

            barChart.data = barData

            barChart.description.text = ""

            barChart.animateY(2000)
        }
    }

    private fun setupRecyclerView() {
        binding.itemRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun storeData(body: ApiResponse) {
        array = listOf(
            Item(R.drawable.click, "Total clicks", body.total_clicks.toString()),
            Item(R.drawable.click, "Today's clicks", body.today_clicks.toString()),
            Item(R.drawable.link, "Total links", body.total_links.toString()),
            Item(
                R.drawable.placeholder,
                "Top location",
                if (body.top_location == "") "N/A" else body.top_location
            ),
            Item(
                R.drawable.earth,
                "Top source",
                if (body.top_source == "") "N/A" else body.top_source
            ),
            Item(R.drawable.clock, "Best Time", if (body.startTime == "") "N/A" else body.startTime)
        )
    }

    private fun setupTabLayoutAndViewPager() {
        adapter = FragmentPageAdapter(parentFragmentManager, lifecycle)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Top Links"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Recent Links"))

        binding.viewPager2.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }

    private fun observer() {
        mainViewModel.liveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Loading -> {
                    // show progress bar
                    Log.d("find-Me", "loading");
                }

                is NetworkResult.Success -> {
                    if (it.data != null) {
                        setupLineChart(it.data.data.overall_url_chart)
                        storeData(it.data)
                        binding.itemRecyclerView.adapter = ItemAdapter(array)
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