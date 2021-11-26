package com.example.deprem.extension

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.example.deprem.R
import com.example.deprem.databinding.DetailsDialogBinding
import com.github.ajalt.timberkt.e

fun Fragment.navigateSafe(
    @IdRes resId: Int,
    bundle: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    try {
        findNavController().navigate(
            resId,
            bundle,
            navOptions,
            navigatorExtras
        )
    } catch (exp: Exception) {
        e(exp)
    }
}
data class StoreDialog(val binding: DetailsDialogBinding, val dialog: Dialog)


fun Fragment.detailsDialog(): StoreDialog {
    val dialog = Dialog(requireContext(), R.style.DialogTheme)
    val binding = DetailsDialogBinding.inflate(LayoutInflater.from(requireContext()))
    dialog.window?.setWindowAnimations(R.style.DialogAnimation)
    dialog.setContentView(binding.root)
    dialog.show()

    dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    return StoreDialog(binding, dialog)
}