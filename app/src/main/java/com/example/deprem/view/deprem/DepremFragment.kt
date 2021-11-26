package com.example.deprem.view.deprem

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.example.deprem.R
import com.example.deprem.base.BaseFragment
import com.example.deprem.databinding.FragmentDepremBinding
import com.example.deprem.extension.detailsDialog
import com.example.deprem.extension.hide
import com.example.deprem.extension.show
import com.example.deprem.model.Deprem
import com.example.deprem.service.ForegroundService
import com.example.deprem.view.deprem.adapter.DepremItemAdapter
import com.github.ajalt.timberkt.e
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_deprem.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.*
import kotlin.collections.ArrayList


@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class DepremFragment : BaseFragment<FragmentDepremBinding>(R.layout.fragment_deprem) {

    lateinit var depremItemAdapter: DepremItemAdapter
    var city = ArrayList<Deprem>()

    private val depremVM: DepremVM by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        depremVM.apiDeprem.observe(viewLifecycleOwner, {
            if (!::depremItemAdapter.isInitialized && it.isNotEmpty()) {
                city = it as ArrayList<Deprem>
                binding.circ.hide()
                depremItemAdapter = DepremItemAdapter(requireContext(), city)
                binding.listviewDeprem.adapter = depremItemAdapter
                depremItemAdapter.notifyDataSetChanged()
                binding.listviewDeprem.requestFocus()
                binding.refresh.setOnRefreshListener {
                    binding.refresh.isRefreshing = false
                    depremItemAdapter.notifyDataSetChanged()
                }
                for (i in it.indices){
                    if (it[i].buyukluk >= 1.0.toString()){
                        ForegroundService.startService(requireContext(), "Tab to add to...")
                    }
                }

            } else if (binding.listviewDeprem.adapter == null) {
                e { "yok" }
                binding.circ.show()
                depremItemAdapter = DepremItemAdapter(requireContext(), city)
                binding.listviewDeprem.adapter = depremItemAdapter
                depremItemAdapter.notifyDataSetChanged()
            } else if (it.isNotEmpty()) {
                binding.circ.hide()
                depremItemAdapter.notifyDataSetChanged()
            }
        })
        depremVM.getDepremView("api")
        e { "deveefevev" }

        binding.listviewDeprem.setOnItemClickListener { parent, view, position, id ->
            val dialog = detailsDialog()
            dialog.binding.untop.text = city[position].yer
            dialog.binding.appinfo.text = city[position].tarih
            dialog.binding.saat.text = city[position].saat
            dialog.binding.btnok.requestFocus()
            dialog.binding.btnok.setOnClickListener {
                dialog.dialog.dismiss()
            }

        }

    }


}