package fr.ldnr.tiny.zoodroid

import android.app.Activity
import android.view.View
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast

class CarteActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val carteView = CarteView(this)
        setContentView(carteView)
        Log.w("CarteActivity", "onCreate terminé")
        Toast.makeText(this, "Carte", Toast.LENGTH_LONG).show()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.actionMasked == MotionEvent.ACTION_DOWN){
            val i = Intent(this, AquariumActivity::class.java)
            startActivity(i)
        }
        return true;
    }


    class CarteView(context: Context) : View(context) {
        val paint = Paint()
        private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.carte)

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            val viewRatio = width.toFloat() / height.toFloat()
            val bitmapRatio = bitmap.width.toFloat() / bitmap.height.toFloat()

            val destRect = if (bitmapRatio > viewRatio) {
                val scaledHeight = (width / bitmapRatio).toInt()
                Rect(0, (height - scaledHeight) / 2, width, (height + scaledHeight) / 2)
            } else {
                val scaledWidth = (height * bitmapRatio).toInt()
                Rect((width - scaledWidth) / 2, 0, (width + scaledWidth) / 2, height)
            }

            canvas.drawBitmap(bitmap, null, destRect, paint)
        }
    }
}