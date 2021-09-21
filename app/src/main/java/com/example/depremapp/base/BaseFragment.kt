package com.example.depremapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope

open class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
) : Fragment() {

    private var _binding: T? = null
    val binding: T get() = _binding!!
    val scope: LifecycleCoroutineScope = lifecycleScope

    open fun T.initialize() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.initialize()
//        EventBus.getDefault().register(this)
        return _binding!!.root
    }

    open fun onPauseExtra() {}

    override fun onPause() {
        super.onPause()
//        EventBus.getDefault().unregister(this)
        onPauseExtra()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}