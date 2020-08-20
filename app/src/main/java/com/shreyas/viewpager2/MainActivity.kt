package com.shreyas.viewpager2

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.shreyas.viewpager2.App.APP
import com.shreyas.viewpager2.cwm.CWMAcivity
import com.shreyas.viewpager2.mutipleClicks.setSafeOnClickListner
import com.shreyas.viewpager2.viewpager_image.ImageViewPager
import com.shreyas.viewpager2.viewpager_video.VideoViewPager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.security.auth.callback.CallbackHandler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLcick.setOnClickListener {
//            startActivity(Intent(this, Dummy::class.java))
            val intent = Intent(Intent.ACTION_MAIN, null)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            val pkgList:List<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)
            Log.d("pkg_test", "qwert: "+pkgList)
        }

//        iv_image.setSafeOnClickListner {
//            //            startActivity(Intent(this, ImageViewPager::class.java))
////            errorAlertBox(this, root_main_screen)
////            startActivity(Intent(this, Dummy::class.java))
//
//            val pm = packageManager
//            val pkgList:List<ApplicationInfo>  = pm.getInstalledApplications(PackageManager.GET_META_DATA)
//            for (packageInfo in pkgList) {
//                Log.d("packageName_test", "qwe: "+packageInfo.packageName)
//                Log.d("sourceDir_test", "asd: "+packageInfo.sourceDir)
//                Log.d("getLaunch_test", "zxc: "+pm.getLaunchIntentForPackage(packageInfo.packageName))
//            }
//        }

//        rl_demo1.setOnClickListener {
            startActivity(Intent(this, VideoViewPager::class.java))
//            Toast.makeText(this, APP.getInstance().qwerty, Toast.LENGTH_SHORT).show()
//
////            startActivity(Intent(this, Dummy::class.java))
//        }

        rl_demo2.setOnClickListener {
            startActivity(Intent(this, CWMAcivity::class.java))
        }
    }

    fun errorAlertBox(context: Context, parent:ViewGroup) {
        val alertDialog = AlertDialog.Builder(context).create()
        val itemView = LayoutInflater.from(context).inflate(R.layout.alert_internet_error_layout, parent, false)
        val btn_ok = itemView.findViewById<Button>(R.id.btn_ok)
        alertDialog.setView(itemView)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btn_ok.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

}
