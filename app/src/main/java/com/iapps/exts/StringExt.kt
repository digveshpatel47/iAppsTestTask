package com.iapps.exts

import android.text.Html
import android.util.Log

// Extensions for String
const val LOG_TYPE_REQUEST = "log request:"
const val LOG_TYPE_RESPONSE = "log response:"
const val LOG_TYPE_ERROR = "log error:"
const val LOG_TYPE_INFO = "log info:"

// Extensions for print log
fun String.printLog(type: String, tag: String) {
    when (type) {
        LOG_TYPE_REQUEST -> Log.d(tag, "$type $this")
        LOG_TYPE_RESPONSE -> Log.d(tag, "$type $this")
        LOG_TYPE_ERROR -> Log.e(tag, "$type $this")
    }
}

// Extensions for Html String
fun String?.fromHtml() = Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)!!













