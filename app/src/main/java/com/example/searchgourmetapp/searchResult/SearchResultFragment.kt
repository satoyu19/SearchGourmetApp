package com.example.searchgourmetapp.searchResult

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchgourmetapp.R
import com.example.searchgourmetapp.databinding.FragmentSearchResultBinding


class SearchResultFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultBinding

    private lateinit var viewModel: SearchResultViewModel
    private lateinit var viewModelFactory: SearchResultFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchResultBinding>(
            inflater,
            R.layout.fragment_search_result, container, false
        )
        val args = SearchResultFragmentArgs.fromBundle(requireArguments())
        viewModelFactory =
            SearchResultFactory(args.latitude, args.longitude, args.range, args.keyword)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchResultViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchResponse.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()){   // 空である
                binding.shopList.visibility = View.GONE
                binding.indicator.visibility = View.GONE
                binding.emptyTxt.visibility = View.VISIBLE
            } else {    // 空でない
                binding.emptyTxt.visibility = View.GONE
                binding.indicator.visibility = View.GONE
                binding.shopList.visibility = View.VISIBLE
                binding.shopList.adapter = ShopListViewAdapter(it ,requireContext(), this)
                binding.shopList.layoutManager = LinearLayoutManager(requireContext())
                val dividerItemDecoration = DividerItemDecoration(requireContext() , LinearLayoutManager(requireContext()).orientation)
                binding.shopList.addItemDecoration(dividerItemDecoration)
            }
        })
}
}