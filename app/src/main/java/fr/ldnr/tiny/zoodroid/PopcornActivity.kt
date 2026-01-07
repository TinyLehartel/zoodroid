package fr.ldnr.tiny.zoodroid

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class PopcornActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val carteView = CarteView(this)
        setContentView(carteView)
        Log.w("CarteActivity", "onCreate terminé")
        val duree = intent.getLongExtra("tempsAquarium", 0)
        if (duree > 1000) {
            val secondes = (duree / 1000).toInt()
            Toast.makeText(
                this,
                "Ne pas donner de popcorn aux poisson " + "(" + secondes + ")",
                Toast.LENGTH_LONG
            ).show()
            val result = Intent()
            result.putExtra("toastAffiche", true)
            setResult(0, result)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.actionMasked == MotionEvent.ACTION_DOWN){
            val largeurEcran = findViewById<View>(android.R.id.content).width
            if (event.x < largeurEcran / 2){
                try {
                    val page = "https://fr.wikipedia.org/wiki/pop-corn"
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(page))
                    startActivity(i)
                }
                catch (e: ActivityNotFoundException){
                    Log.e("PopcornActivity", "Pas de navigateur !")
                }
            }
            else{
                val  i = Intent(this, CarteActivity::class.java)
                i.addFlags((Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT))
                startActivity(i)
            }

        }
        return true
    }

    class CarteView(context: Context) : View(context) {
        private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.popcorn)

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawBitmap(bitmap, 0f, 0f, null)
        }


    }
}