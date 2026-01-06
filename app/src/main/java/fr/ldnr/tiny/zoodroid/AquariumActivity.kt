package fr.ldnr.tiny.zoodroid

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class AquariumActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val carteView = CarteView(this)
        setContentView(carteView)
        Log.w("CarteActivity", "onCreate terminé")
        Toast.makeText(this, "Bonjour", Toast.LENGTH_LONG).show()
    }

    inner class CarteView(context: Context) : View(context) {
        private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.aquarium)

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawBitmap(bitmap, 0f, 0f, null)
        }
    }
}