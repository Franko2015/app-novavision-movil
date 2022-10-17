package com.example.novavision.Principal.ui.notifications

import android.app.AlertDialog
import com.example.novavision.R

class CargandoDialogoNotifications(val mActivity: NotificationsFragment) {
    private lateinit var isDialog:AlertDialog
    fun startLoading(){
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading,null)
        val builder  = AlertDialog.Builder(dialogView.context)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()
    }
    fun isDismiss(){
        isDialog.dismiss()
    }
}