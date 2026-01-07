package fr.ldnr.tiny.zoodroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.math.min

class AquariumActivity : Activity() {

    private var debut: Long = 0
    private var fin : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val carteView = CarteView(this)
        setContentView(carteView)
        Log.w("CarteActivity", "onCreate terminé")
        Toast.makeText(this, "Aquarium", Toast.LENGTH_LONG).show()
        debut = System.currentTimeMillis()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.actionMasked == MotionEvent.ACTION_DOWN){
            val i = Intent(this, PopcornActivity::class.java)
            fin = System.currentTimeMillis()
            val duree = fin - debut
            i.putExtra("tempsAquarium", duree)
            //startActivity(i)
            startActivityForResult(i,0)
        }
        return true
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        //Log.w("##########################", "#####################")
        Log.i("AquariumActivity", "Toast affiché : " + data?.getBooleanExtra("toastAffiche", false))
    }

    class CarteView(context: Context) : View(context) {
        private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.aquarium)

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            val scale = min(
                width.toFloat() / bitmap.width,
                height.toFloat() / bitmap.height
            )

            val newW = (bitmap.width * scale).toInt()
            val newH = (bitmap.height * scale).toInt()

            val scaled = Bitmap.createScaledBitmap(bitmap, newW, newH, true)

            val left = (width - newW) / 2f
            val top = (height - newH) / 2f

            canvas.drawBitmap(scaled, left, top, null)
        }
    }
}