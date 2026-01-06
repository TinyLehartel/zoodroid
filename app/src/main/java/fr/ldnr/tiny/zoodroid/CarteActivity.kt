package fr.ldnr.tiny.zoodroid

import android.app.Activity
import android.view.View
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle

class CarteActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val carteView = CarteView(this)
        setContentView(carteView)
    }

    inner class CarteView(context: Context) : View(context) {
        private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.carte)

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawBitmap(bitmap, 0f, 0f, null)
        }
    }
}