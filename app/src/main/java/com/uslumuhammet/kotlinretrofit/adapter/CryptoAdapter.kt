package com.uslumuhammet.kotlinretrofit.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uslumuhammet.kotlinretrofit.R
import com.uslumuhammet.kotlinretrofit.databinding.RecyclerRowBinding
import com.uslumuhammet.kotlinretrofit.model.Crypto
import com.uslumuhammet.kotlinretrofit.view.MainActivity
import kotlinx.android.synthetic.main.recycler_row.view.*

class CryptoAdapter(val cryptoList: ArrayList<Crypto>, private val listener: Listener) : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>(){
    interface Listener
    {
        fun OnItemClick(crypto: Crypto)
    }

    private val colors : Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

    class  CryptoHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(crypto : Crypto, colors:Array<String>, position: Int, listener:Listener)
        {
            itemView.setOnClickListener {
                listener.OnItemClick(crypto)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.cryptoNameText.text = crypto.currency
            itemView.cryptoPriceText.text = crypto.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.OnItemClick(cryptoList.get(position))
        }
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))

        holder.binding.cryptoNameText.text = cryptoList.get(position).currency
        holder.binding.cryptoPriceText.text = cryptoList.get(position).price
    }

    override fun getItemCount(): Int {
        return  cryptoList.size
    }
}