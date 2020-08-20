package com.shreyas.viewpager2.viewpager_image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.shreyas.viewpager2.R
import kotlinx.android.synthetic.main.image_view_pager.*
import kotlinx.android.synthetic.main.single_image_snippet.*
import kotlinx.android.synthetic.main.single_image_snippet.view.*

class ImageViewPager : AppCompatActivity(), PlayerInterface {

    lateinit var imageList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_view_pager)

        imageList = listOf<Int>(
            R.drawable.one,
            R.drawable.two,
            R.drawable.belagavi,
            R.drawable.bangalore,
            R.drawable.bellary,
            R.drawable.bhadravati,
            R.drawable.fox,
            R.drawable.hassan,
            R.drawable.kalaburagi,
            R.drawable.mangalore,
            R.drawable.snarl
        )

        vp_image.apply {
                            adapter = ViewPageImageAdapter(
                    imageList,
                    this@ImageViewPager
                )
        }
    }

    override fun playerInterface(holder: ViewPageImageAdapter.ViewPagerHolder, position: Int) {
        Log.d("vp_test", "position:Main ${position}")

        holder.itemView.vp_iv_image.setImageResource(imageList[position])
        holder.itemView.setOnClickListener {
//            Toast.makeText(this@ImageViewPager, position.toString(), Toast.LENGTH_SHORT).show()
            showAlertDialog("this is image of ${position} position")
        }

        holder.itemView.share.setOnClickListener {
            showAlertDialog("this is image of ${position} position from share")
        }

        holder.itemView.back.setOnClickListener {
            finish()
        }
    }

    private fun showAlertDialog(msg: String) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Position")
        builder.setMessage(msg)
            builder.setPositiveButton("ok") { dialog, which ->
                    dialog.dismiss()
            }
        builder.show()
        }
    }
