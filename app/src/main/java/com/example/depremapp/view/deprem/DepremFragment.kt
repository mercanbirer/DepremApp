package com.example.depremapp.view.deprem

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Binder
import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.example.depremapp.R
import com.example.depremapp.base.BaseFragment
import com.example.depremapp.databinding.FragmentDepremBinding
import com.example.depremapp.extension.hide
import com.example.depremapp.extension.show
import com.example.depremapp.view.deprem.adapter.DepremItemAdapter
import com.github.ajalt.timberkt.e
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_deprem.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.*


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
            if (!::depremItemAdapter.isInitialized && it.isNotEmpty()) {
                binding.circ.hide()
                depremItemAdapter = DepremItemAdapter(requireContext(), it)
                binding.listviewDeprem.adapter = depremItemAdapter
                depremItemAdapter.notifyDataSetChanged()
                binding.listviewDeprem.requestFocus()
                binding.refresh.setOnRefreshListener {
                    binding.refresh.isRefreshing = false
                    depremItemAdapter.notifyDataSetChanged()
                }
            } else if (binding.listviewDeprem.adapter == null) {
                e { "yok" }
                binding.circ.show()
                depremItemAdapter = DepremItemAdapter(requireContext(), it)
                binding.listviewDeprem.adapter = depremItemAdapter
                depremItemAdapter.notifyDataSetChanged()
            } else if (it.isNotEmpty()) {
                binding.circ.hide()
                depremItemAdapter.notifyDataSetChanged()
            }
        })
        depremVM.getDepremView("api")
        e { "deveefevev" }

    }


}