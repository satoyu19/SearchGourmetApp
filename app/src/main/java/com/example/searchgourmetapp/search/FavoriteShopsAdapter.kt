package com.example.searchgourmetapp.search

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.example.searchgourmetapp.database.FavoriteShop
import java.net.URLEncoder

class FavoriteShopsAdapter(private val favList: List<FavoriteShop>, private val context: Context, private val fragment: Fragment): RecyclerView.Adapter<FavoriteShopsAdapter.FavoriteShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteShopViewHolder {
        val shopHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.shop_list_view, parent, false)
        return FavoriteShopViewHolder(shopHolder)
    }

    override fun onBindViewHolder(holder: FavoriteShopViewHolder, position: Int) {
        //店情報
        val favShop = favList[position]
        holder.shopAccess.text = favShop.access
        holder.shopAddress.text = favShop.address
        holder.shopName.text = favShop.name
        Glide.with(context).load(favShop.mobileS).circleCrop().into(holder.shopLogo)

        // クリック処理、地図アプリに委託
        holder.itemView.setOnClickListener {
            AlertDialog.Builder(context) // FragmentではActivityを取得して生成
                .setTitle("経路表示")
                .setMessage("この店舗までの経路案内を開始しますか?")
                .setPositiveButton("OK") { _, _ ->
                    val searchAddress = URLEncoder.encode(favShop.address, "UTF-8")
                    // 地図アプリと連携するURI文字列を生成
                    val uriStr = "geo:0:0?q=${searchAddress}"
                    val uri = Uri.parse(uriStr)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    it.context.startActivity(intent)
                }
                .setNegativeButton("No") { _, _ ->
                    return@setNegativeButton
                }
                .show()
        }
    }

    override fun getItemCount(): Int = favList.size

    //viewHolder
    class FavoriteShopViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val shopLogo: ImageView = item.findViewById(R.id.logo_img)
        val shopName: TextView = item.findViewById(R.id.shop_name)
        val shopAddress: TextView = item.findViewById(R.id.address)
        val shopAccess: TextView = item.findViewById(R.id.access)
    }
}