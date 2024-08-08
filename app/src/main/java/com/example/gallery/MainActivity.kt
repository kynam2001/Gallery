package com.example.gallery

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.gallery.databinding.ActivityMainBinding
import android.Manifest
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore.Images.Media
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var PERMISSIONS_REQUEST_READ_MEDIA_IMAGES = 1
    private val imagePaths = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermisson()
//        binding.fragment1Btn.setOnClickListener{
//            fragmentReplace(HelloFragment())
//        }
//
//        binding.fragment2Btn.setOnClickListener {
//            fragmentReplace(GoodbyeFragment())
//        }

    }

    private fun fragmentReplace(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragment, fragment)
        fragmentTransaction.commit()
    }

    private fun checkPermisson(){
        // check READ_EXTERNAL_REQUEST_PERMISSION
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                PERMISSIONS_REQUEST_READ_MEDIA_IMAGES)
        }
        else{
            loadImages()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSIONS_REQUEST_READ_MEDIA_IMAGES){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Permisson granted
                loadImages()
            }
        }
        else{
                // Permisson denied
            Toast.makeText(this, "Permission denied to read external storage", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadImages() {
        val projection = arrayOf(Media.DATA)
        val uri: Uri = Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, null)
        cursor?.let {
            while(cursor.moveToNext()){
                val imagePath = cursor.getString(cursor.getColumnIndexOrThrow(Media.DATA))
                imagePaths.add(imagePath)
            }
            cursor.close()
        }
        if(imagePaths.isNotEmpty()) {
            display()
        }
        else {
            Toast.makeText(this, "No images found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadImage(index: Int){
        Glide.with(this)
            .load(imagePaths[index])
            .apply(RequestOptions().centerCrop())
            .into(binding.imageView)
    }

    private fun display(){
        var currentindex = 0
        loadImage(currentindex)
        binding.imageView.setOnClickListener {
            currentindex++
            if(currentindex == imagePaths.size) {
                currentindex = 0
            }
            Log.e("click","CurrentIndex: $currentindex")
            loadImage(currentindex)
        }
        binding.imageView.setOnTouchListener(object : OnSwipeTouchListener(this) {
            override fun onSwipeLeft() {
                currentindex--
                if(currentindex == -1) {
                    currentindex = imagePaths.size-1
                }
                Log.e("swipeLeft","CurrentIndex: $currentindex")
                loadImage(currentindex)
            }

            override fun onSwipeRight() {
                currentindex++
                if(currentindex == imagePaths.size) {
                    currentindex = 0
                }
                Log.e("swipeRight","CurrentIndex: $currentindex")
                loadImage(currentindex)
            }

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                super.onTouch(v, event)
                currentindex++
                if(currentindex == imagePaths.size) {
                    currentindex = 0
                }
                Log.e("click","CurrentIndex: $currentindex")
                loadImage(currentindex)
                return true
            }
        })
    }
}