package com.example.searchgourmetapp.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.searchgourmetapp.R
import com.example.searchgourmetapp.databinding.FragmentShopDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShopDetailFragment : Fragment() {

    private lateinit var binding: FragmentShopDetailBinding

//    private lateinit var viewModelFactory: ShopDetailViewFactory
//    private lateinit var viewModel: ShopDetailViewModel
//    private val viewModel by lazy {
//        ViewModelProvider(this, viewModelFactory).get(ShopDetailViewModel::class.java)
//}

    private val args: ShopDetailFragmentArgs by navArgs()
    @Inject
    lateinit var viewModelFactory: ShopDetailViewModel.Factory

    private val viewModel: ShopDetailViewModel by lazy {
        viewModelFactory.create(args.shopState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<FragmentShopDetailBinding>(inflater,
            R.layout.fragment_shop_detail, container, false)
        val application = requireNotNull(this.activity).application

        val args = ShopDetailFragmentArgs.fromBundle(requireArguments())
//        viewModelFactory = ShopDetailViewFactory(args.shopState, application)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(ShopDetailViewModel::class.java)

        binding.mapBtn.setOnClickListener{
            val intent = viewModel.onMapSearchButtonClick()
            startActivity(intent)
        }

        //データバインディングでUI描画
        binding.viewModel = viewModel
        Glide.with(requireContext()).load(viewModel.shopState.pcL).into(binding.shopImage)

        binding.favBtn.setOnClickListener{
            //店舗idをもとにviewModelのフラグを変化させる
            viewModel.checkShopId()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isId.observe(viewLifecycleOwner) {
            if(it){
                Toast.makeText(requireContext(), "登録済みです", Toast.LENGTH_SHORT).show()
            } else{
                viewModel.addFavoriteShop()
                Toast.makeText(requireContext(), "お気に入りに追加しました", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

