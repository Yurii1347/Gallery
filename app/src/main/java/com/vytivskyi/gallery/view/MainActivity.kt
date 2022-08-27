package com.vytivskyi.gallery.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.vytivskyi.gallery.Constance
import com.vytivskyi.gallery.R
import com.vytivskyi.gallery.databinding.ActivityMainBinding
import com.vytivskyi.gallery.view.adapter.PhotoAdapter
import com.vytivskyi.gallery.viewmodel.PhotosVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter = PhotoAdapter()

    private val viewModel: PhotosVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            viewModel.getPhotos()
        }

        adapter.itemClickListener = ::onClick

        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = GridLayoutManager(this@MainActivity, 1)

        viewModel.observePhotos().observe(this@MainActivity) {
            adapter.mainL = it
        }
    }

    private fun onClick(id: String) {
        val bundle = Bundle()
        bundle.putString(Constance.ID_KEY, id)
        val fragment = PhotoFragment()
        fragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, fragment)
            .commit()
    }

}