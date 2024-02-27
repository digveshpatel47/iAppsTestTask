package com.iapps.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.iapps.exts.LOG_TYPE_INFO
import com.iapps.exts.printLog

class BaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        javaClass.simpleName.printLog(LOG_TYPE_INFO, "created")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        javaClass.simpleName.printLog(LOG_TYPE_INFO, "onDestroyView")
    }
}