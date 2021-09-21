package com.example.depremapp.extension

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
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