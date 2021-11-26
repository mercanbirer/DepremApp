package com.example.deprem.view.deprem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deprem.base.BaseAdapter
import com.example.deprem.databinding.ItemDepremBinding
import com.example.deprem.model.Deprem


class DepremItemAdapter(
    private val context: Context,
    private val data: List<Deprem>,
) : BaseAdapter<Deprem>(context, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemDepremBinding
        if (convertView == null) {
            binding = ItemDepremBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ItemDepremBinding
        }

        binding.deprem = getItem(position)

        return binding.root
    }
}
