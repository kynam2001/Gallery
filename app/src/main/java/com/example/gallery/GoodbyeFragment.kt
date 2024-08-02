package com.example.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.gallery.databinding.GoodbyefragmentBinding

class GoodbyeFragment: Fragment(R.layout.goodbyefragment) {

    private lateinit var binding: GoodbyefragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GoodbyefragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load("https://i.pinimg.com/564x/06/f4/4e/06f44ebe186942a5083badcb43161bb9.jpg")
            .dontAnimate()
            .into(binding.image2View)
    }
}