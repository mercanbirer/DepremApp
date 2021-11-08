package com.example.depremapp.view.deprem

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.navGraphViewModels
import com.example.depremapp.R
import com.example.depremapp.base.BaseFragment
import com.example.depremapp.databinding.FragmentDepremBinding
import com.example.depremapp.model.Deprem
import com.example.depremapp.view.deprem.adapter.DepremItemAdapter
import com.github.ajalt.timberkt.e
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_deprem.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class DepremFragment : BaseFragment<FragmentDepremBinding>(R.layout.fragment_deprem) {

    lateinit var depremItemAdapter: DepremItemAdapter

    private val depremVM: DepremVM by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        depremVM.apiDeprem.observe(viewLifecycleOwner, {
            if (!::depremItemAdapter.isInitialized && it != null) {
                e { "dede" }
                depremItemAdapter = DepremItemAdapter(requireContext(), it)
                binding.listviewDeprem.adapter = depremItemAdapter
                depremItemAdapter.notifyDataSetChanged()
                binding.listviewDeprem.requestFocus()
            } else if (binding.listviewDeprem.adapter == null) {
                e { "yok" }
                depremItemAdapter = DepremItemAdapter(requireContext(), it)
                binding.listviewDeprem.adapter = depremItemAdapter
                depremItemAdapter.notifyDataSetChanged()
            } else if (it != null) {
                depremItemAdapter.notifyDataSetChanged()
            }
        })
        depremVM.getDepremView("api")
        e { "deveefevev" }

    }

}