package com.example.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gallery.databinding.HellofragmentBinding


class HelloFragment: Fragment(R.layout.hellofragment) {
    private lateinit var binding: HellofragmentBinding
    private lateinit var listImage: MutableList<String>
    private var currentIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HellofragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createImage()
        loadImage(currentIndex)
        binding.image1View.setOnClickListener {
            currentIndex++
            if(currentIndex == listImage.size){
                currentIndex = 0
            }
            loadImage(currentIndex)
        }

    }

    private fun loadImage(index: Int){
        Glide.with(this).load("").preload()
        Glide.with(this)
            .load(listImage.get(index))
            .apply(RequestOptions().centerCrop())
            .into(binding.image1View)


    }

    private fun createImage(){
        listImage = mutableListOf()
        listImage.add("https://i.pinimg.com/564x/e8/e5/29/e8e529f6cfb56408a4bcee86fcdc53bf.jpg")
        listImage.add("https://i.pinimg.com/564x/9e/f1/00/9ef100c0ff45570299f01062d83deb40.jpg")
        listImage.add("https://i.pinimg.com/564x/78/b8/1d/78b81d1624f75ff16d93c0c55da5639b.jpg")
        listImage.add("https://i.pinimg.com/564x/45/e3/5e/45e35e74dccee6c6dd0b390d17832740.jpg")
        listImage.add("https://i.pinimg.com/736x/5d/4e/72/5d4e72313c0a9e6c9728c9a1d66d8abd.jpg")
        listImage.add("https://i.pinimg.com/736x/ae/94/3c/ae943cfce12b0d0fc50717f83bfde98a.jpg")
        listImage.add("https://i.pinimg.com/736x/21/2d/c2/212dc2b91fde0f13ff3247795a54bc73.jpg")
        listImage.add("https://i.pinimg.com/564x/ad/17/67/ad17678bdb9a6607d558dac26e071ac7.jpg")
        listImage.add("https://i.pinimg.com/564x/e8/ce/1e/e8ce1e64768614acfe79516be7c32b2a.jpg")
        listImage.add("https://i.pinimg.com/564x/ba/ff/ba/baffba2950ac4851d395531c8c8a8c99.jpg")
        listImage.add("https://i.pinimg.com/564x/9f/47/aa/9f47aa2f7acc9b8528146e6218aa8968.jpg")
    }
}