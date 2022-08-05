package com.example.searchgourmetapp.searchResult

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchgourmetapp.R
import com.example.searchgourmetapp.ShopState

class ShopListViewAdapter(private val list: List<ShopState>, private val context: Context, private val fragment: Fragment): RecyclerView.Adapter<ShopListViewAdapter.ShopListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val shopHolder = LayoutInflater.from(parent.context).inflate(R.layout.shop_list_view, parent, false)
        return ShopListViewHolder(shopHolder)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        //店情報
        val shop = list[position]
        holder.shopAccess.text = shop.access
        holder.shopAddress.text = shop.address
        holder.shopName.text = shop.name
        Glide.with(context).load(shop.mobileS).circleCrop().into(holder.shopLogo)

        //タップ処理、画面遷移
       holder.itemView.setOnClickListener {
           val action = SearchResultFragmentDirections.actionSearchResultFragmentToShopDetailFragment2(shop)
           NavHostFragment.findNavController(fragment).navigate(action)
       }
    }

    override fun getItemCount(): Int = list.size

    //viewHolder
    class ShopListViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val shopLogo: ImageView = item.findViewById(R.id.logo_img)
        val shopName: TextView = item.findViewById(R.id.shop_name)
        val shopAddress: TextView = item.findViewById(R.id.address)
        val shopAccess: TextView = item.findViewById(R.id.access)
    }
}
