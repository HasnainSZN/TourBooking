package com.example.tourbooking

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.tourbooking.databinding.FragmentTourInformationBinding
class TourInformationFragment : Fragment() {
    val args :TourInformationFragmentArgs by navArgs()
    val handler = Handler()
    private lateinit var binding: FragmentTourInformationBinding
    private lateinit var imageSlideshowRunnable:Runnable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tour_information,
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            //view Pager and indicator
            val indicator = indicator
            val viewPager = viewPagerTour
            val images = args.tourData.imagesList
            val adapter = ImagePagerAdapter(requireContext(),images)
            viewPager.adapter=adapter
            indicator.setViewPager(viewPager)
            //
            var currentPage = 0
            imageSlideshowRunnable = object : Runnable {
                override fun run() {
                    if (currentPage == args.tourData.imagesList.size) {
                        currentPage = 0
                    }
                    viewPager.currentItem = currentPage
                    currentPage++
                    handler.postDelayed(this, 5000) // 5 seconds
                }
            }
            //
            handler.postDelayed(imageSlideshowRunnable, 2000) // Initial Delay
            //
            tourName.setText(args.tourData.tourName)
            //
            val starcount:String = args.tourData.starCount.toString()
            val starcount2 ="($starcount)"
            starRatingCount.setText(starcount2)
            //
            startDestinationCompleteData.setText(args.tourData.properStartDestination)
            //
            val deTime = args.tourData.departureTime
            val deDate = args.tourData.startDate
            departureTime.setText(deTime)
            departureDate.setText(deDate)
            //
            description.setText(args.tourData.description)
            //
            val cst = args.tourData.costOftour.toString()
            cost.setText("$cst PKR per person")
        }
        binding.backButtonLayout.setOnClickListener{
            findNavController().popBackStack()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(imageSlideshowRunnable)
    }
}