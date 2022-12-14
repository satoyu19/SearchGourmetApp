package com.example.searchgourmetapp.search

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchgourmetapp.R
import com.example.searchgourmetapp.database.FavoriteShop
import com.example.searchgourmetapp.databinding.FragmentSearchBinding
import com.example.searchgourmetapp.database.FavoriteShopDatabase
//import com.example.searchgourmetapp.detail.ShopDetailViewFactory
import com.example.searchgourmetapp.detail.ShopDetailViewModel
import com.example.searchgourmetapp.searchResult.ShopListViewAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()
//    private lateinit var viewModelFactory: SearchShopViewFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search, container, false)

        val application = requireNotNull(this.activity).application

//        viewModelFactory = SearchShopViewFactory(application)
//        viewModel = ViewModelProvider(this,  viewModelFactory).get(SearchViewModel::class.java)

        //???????????????????????????
        viewModel.registrationLocation(requireActivity())

        //???????????????shop????????????
        viewModel.getFavoriteShops()

        //???????????????
        binding.searchBtn.setOnClickListener {
            viewModel.checkLocation(requireActivity())

            //????????????????????????????????????????????????
            if(viewModel.isLocation){
                val range = binding.spinner.selectedItemId.toString()
                val keyword = binding.searchWord.text.toString()

                val action = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(
                    viewModel.lat,
                    viewModel.lng,
                    range,
                    keyword
                )
                NavHostFragment.findNavController(this).navigate(action)

            }else{      //?????????????????????????????????????????????
                Toast.makeText(requireContext(), "????????????????????????????????????????????????????????????", Toast.LENGTH_SHORT).show()
            }
//        })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RecycleView???????????????
        viewModel.shops.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                binding.favEmptyTxt.visibility = View.VISIBLE
                binding.favoriteShopList.visibility = View.GONE
            } else {
                //recycleView?????????
                binding.favEmptyTxt.visibility = View.GONE
                binding.favoriteShopList.visibility = View.VISIBLE
                binding.favoriteShopList.adapter = FavoriteShopsAdapter(it, requireContext(), this)
                binding.favoriteShopList.layoutManager = LinearLayoutManager(requireContext())
                val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
                binding.favoriteShopList.addItemDecoration(dividerItemDecoration)
            }
        })
    }
}