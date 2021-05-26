package com.malkinfo.qrcodegenerator

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var qrCodeIV:ImageView
    private lateinit var dataEdt:EditText
    private lateinit var generateQrBtn: MaterialButton

    var bitmap :Bitmap? = null
    var qrgEnCoder:QRGEncoder?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**set find Id*/
        qrCodeIV = findViewById(R.id.idIVQrcode)
        dataEdt = findViewById(R.id.idEdt)
        generateQrBtn = findViewById(R.id.idBtnGenerateQR)

        generateQrBtn.setOnClickListener {
            if (TextUtils.isEmpty(dataEdt.text.toString())){
                Toast.makeText(this@MainActivity,
                    "Enter Some text to generate QR Code",Toast.LENGTH_SHORT).show()
            }else{
                val manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val display = manager.defaultDisplay
                val point = Point()
                display.getSize(point)
                val width = point.x
                val height = point.y
                var dimen = if (width< height) width else height

                dimen = dimen *3/4

                qrgEnCoder = QRGEncoder(dataEdt.text.toString(),
                    null,QRGContents.Type.TEXT,dimen)

                try {
                    bitmap = qrgEnCoder!!.encodeAsBitmap()
                    qrCodeIV.setImageBitmap(bitmap)

                }catch (e:Exception){
                    Log.e("Tag",e.toString())
                }

            }
        }

    }
    /**ok now run it */

}