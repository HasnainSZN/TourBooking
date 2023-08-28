package com.example.tourbooking

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.tourbooking.databinding.FragmentChooseTourTypeBinding


class ChooseTourTypeFragment : Fragment() {
    private val handler = Handler()
    private lateinit var imageSlideshowRunnable:Runnable
    private lateinit var binding: FragmentChooseTourTypeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_choose_tour_type,
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //View Pager

        val viewPager = binding.viewPager
        val indicator = binding.indicator
        val images = listOf(R.drawable.arangkel16, R.drawable.naran16, R.drawable.passu16)
        val adapter = ImagePagerAdapter(requireContext(), images)
        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)
        var currentPage = 0
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
        //
        imageSlideshowRunnable = object : Runnable {
            override fun run() {
                if (currentPage == images.size) {
                    currentPage = 0
                }
                viewPager.currentItem = currentPage
                currentPage++
                handler.postDelayed(this, 2000) // 5 seconds
            }
        }
        handler.postDelayed(imageSlideshowRunnable,0)
        //Data of Tour(Used in 1st RecyclerView)
        val imgSrcMain = arrayListOf(R.drawable.arangkel,R.drawable.naran,R.drawable.balochistan,R.drawable.gilgit,R.drawable.hunza)
        val tourName = arrayListOf("Kashmir Tour","Naran Tour","Balochistan Tour","Gilgit Tour","Hunza Tour")
        val imgSrcLocation = arrayListOf(R.drawable.ic_location_24,R.drawable.ic_location_24,R.drawable.ic_location_24,R.drawable.ic_location_24,R.drawable.ic_location_24)
        val startDestination = arrayListOf("Islamabad","Balakot","Quetta","Islamabad","Islamabad")
        val startDate = arrayListOf("September 4th","August 28th","November 1st","September 15th","September 20th")
        val newRecyclerView = binding.recyclerView
        newRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        newRecyclerView.setHasFixedSize(true)
        val newArrayList = arrayListOf<TourData>()
        // Putting all of the Data into 1 ArrayList
        for (i in imgSrcMain.indices) {
            val tourData = TourData(
                imgSrcMain[i],
                tourName[i],
                imgSrcLocation[i],
                startDestination[i],
                startDate[i],
                200,
                12000,
                "I-8 Markaz Islamabad,Pakistan",
                "8:30 Am",
                R.string.description_naran,
                arrayListOf(R.drawable.arangkel,R.drawable.arangkel16)
            )
            newArrayList.add(tourData)
        }
        //
        val adapter1 = RecyclerViewAdapter(newArrayList)
        newRecyclerView.adapter = adapter1
        adapter1.setOnItemClickListener(object : RecyclerViewAdapter.onItemClickListener {
            override fun onItemClick(tourItemDto: TourData) {
                val action = ChooseTourTypeFragmentDirections.actionChooseTourTypeFragmentToTourInformationFragment(tourItemDto)
                findNavController().navigate(action)
            }
        })
        // Recyclerview Adapter of Destination
        val imgSrcMain2= arrayListOf(R.drawable.arangkel,R.drawable.naran)
        val locationName= arrayListOf("Arangkel","Naran")
        val newRecyclerView2=binding.destinationRecyclerView
        newRecyclerView2.layoutManager =LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        newRecyclerView2.setHasFixedSize(true)
        val newArrayList2= arrayListOf<DestinationData>()
        //
        for (i in imgSrcMain2.indices) {
            val destinationData = DestinationData(
                imgSrcMain2[i],
                locationName[i]
            )
            newArrayList2.add(destinationData)
        }
        val destinationadapter = RecyclerViewAdapter2(newArrayList2)
        newRecyclerView2.adapter = destinationadapter
    }
    override fun onDestroyView() {
        handler.removeCallbacks(imageSlideshowRunnable)
        super.onDestroyView()
    }
}