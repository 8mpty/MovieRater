package A224149Q.com.example.movierater

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_rating.review_title
import kotlinx.android.synthetic.main.activity_rating.share_view

class RatingActivity : AppCompatActivity() {
    companion object{
        val RETURN_REVIEW = "reviews"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        val def = review_title.text.toString()
        review_title.text = def + " Venom"
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home){
            Toast.makeText(this, "Back",Toast.LENGTH_SHORT).show()
            val detail = Intent()
            detail.putExtra(RETURN_REVIEW,share_view.text.toString())
            Log.e("TEST", "RA: " + share_view.text.toString())
            setResult(Activity.RESULT_OK, detail)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}