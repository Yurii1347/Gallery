package com.vytivskyi.gallery.view

import android.os.Bundle
import android.system.Os.remove
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.vytivskyi.gallery.Constance
import com.vytivskyi.gallery.databinding.FragmentPhotoBinding


class PhotoFragment : Fragment() {

    private lateinit var binding: FragmentPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoBinding.inflate(inflater)

        Picasso.get().load(arguments?.getString(Constance.ID_KEY)).into(binding.imageView2)

        binding.button.setOnClickListener {
            activity?.
            supportFragmentManager?.
            beginTransaction()
                ?.remove(this)
                ?.commit()

        }

        Log.d("lol", "${arguments?.getString(Constance.ID_KEY)}")
        return binding.root

    }


}