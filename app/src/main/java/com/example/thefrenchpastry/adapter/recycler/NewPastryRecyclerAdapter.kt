package com.example.thefrenchpastry.adapter.recycler

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thefrenchpastry.androidWrapper.PicassoHandler
import com.example.thefrenchpastry.data.remote.dataModel.PastriesModel
import com.example.thefrenchpastry.databinding.RecyclerItemMainHorizontalBinding
import com.example.thefrenchpastry.mvp.ext.OthersUtilities
import com.example.thefrenchpastry.ui.activity.DetailPastryActivity

class NewPastryRecyclerAdapter(
    private val pastries: ArrayList<PastriesModel>,
    private val context: Context
) : RecyclerView.Adapter<NewPastryRecyclerAdapter.NewPastryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NewPastryViewHolder {

        return NewPastryViewHolder(
            RecyclerItemMainHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return pastries.size
    }

    override fun onBindViewHolder(holder: NewPastryViewHolder, position: Int) {
        holder.setData(pastries[position])
    }

    inner class NewPastryViewHolder(
        private val binding: RecyclerItemMainHorizontalBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: PastriesModel) {

            binding.txtPastryName.text = data.title
            binding.txtMainPrice.text = OthersUtilities.changePrice(data.price)

            if (data.has_discount) {

                binding.txtMainPrice.paintFlags =
                    binding.txtMainPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.txtMainPrice.setTextColor(Color.GRAY)

                binding.txtOffPrice.text = OthersUtilities.changePrice(data.sale_price)
                binding.txtOff.text = data.discount
            } else {
                binding.off.visibility = View.GONE
                binding.txtMainPrice.visibility = View.VISIBLE
            }
            if (data.thumbnail.isNotEmpty())
                PicassoHandler.setPicasso(binding.imgPastry, data.thumbnail)

            binding.root.setOnClickListener {
                val intent = Intent(context, DetailPastryActivity::class.java)
                intent.putExtra(DetailPastryActivity.ID, data.ID)
                context.startActivity(intent)
            }
        }
    }
}