package A224149Q.com.example.movierater

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_movie_detail.act_reason
import kotlinx.android.synthetic.main.activity_movie_detail.act_reviews
import kotlinx.android.synthetic.main.activity_movie_detail.act_age
import kotlinx.android.synthetic.main.activity_movie_detail.act_language
import kotlinx.android.synthetic.main.activity_movie_detail.act_overview
import kotlinx.android.synthetic.main.activity_movie_detail.act_releasedate
import kotlinx.android.synthetic.main.activity_movie_detail.act_title

class MovieDetail : AppCompatActivity() {
    val RATING_CODE = 1;
    val movieEntityClass = MovieEntity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        registerForContextMenu(act_reviews)
        main();
        Log.e("TEST","REVIEWS START: " + movieEntityClass.reviews)
    }
    private fun main(){
        val intentTitle = intent.getStringExtra("title")
        val intentOverview = intent.getStringExtra("overview")
        var intentReviews = intent.getStringExtra("reviews")
        val intentLanguage = intent.getStringExtra("language")
        val intentDate = intent.getStringExtra("date")
        val intentAge = intent.getStringExtra("nsfaa")
        val intentViolence = intent.getStringExtra("violence")
        val intentLanguageUsed = intent.getStringExtra("languageUsed")
        if(intentTitle != null){
            movieEntityClass.title = intentTitle.toString()
        }
        if(intentOverview != null){
            movieEntityClass.overview = intentOverview.toString()
        }
        if(intentReviews != null){
            movieEntityClass.reviews = intentReviews.toString()
        }else{
            movieEntityClass.reviews = "No Reviews yet.\nLong press here to add your review."
        }
        if(intentLanguage != null){
            movieEntityClass.language = intentLanguage.toString()
        }
        if(intentDate != null){
            movieEntityClass.releaseDate = intentDate.toString()
        }

        if(intentAge != null){
            if(intentAge == "true"){
                movieEntityClass.age = "No"
            }else{
                movieEntityClass.age = "Yes"
            }
        }
        if(intentAge != null && intentAge == "true"){
            if(intentViolence != null && intentViolence == "true"){
                movieEntityClass.violence = "Violence"
            }else{
                movieEntityClass.violence = ""
            }
        }else if(intentAge != null && intentAge == "false"){
            movieEntityClass.violence = ""
        }

        if(intentAge != null && intentAge == "true"){
            if(intentLanguageUsed != null && intentLanguageUsed == "true"){
                movieEntityClass.languageUsed = "Language Used"
            }else{
                movieEntityClass.languageUsed = ""
            }
        }else if(intentAge != null && intentAge == "false"){
            movieEntityClass.languageUsed = ""
        }

        act_title.text = movieEntityClass.title
        act_overview.text = movieEntityClass.overview
        act_reviews.text = movieEntityClass.reviews
        act_language.text = movieEntityClass.language
        act_releasedate.text = movieEntityClass.releaseDate
        act_age.text = movieEntityClass.age
        act_reason.text = reasons(movieEntityClass.age, movieEntityClass.violence, movieEntityClass.languageUsed)
    }

    private fun reasons(age: String, violence: String, language: String): String{
        var output = ""
        if(age != "Yes") {
            if (!violence.isEmpty() && language.isEmpty()) {
                output = violence
            } else if (violence.isEmpty() && !language.isEmpty()) {
                output = language
            } else if (!violence.isEmpty() && !language.isEmpty()) {
                output = "$violence, $language"
            }
            return " ($output)"
        }else{
            return output
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        if(v?.id == R.id.act_reviews){
            menu?.add(1,1001,1,"Add Review")
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == 1001){
            val ratingsAct = Intent(this,RatingActivity::class.java)
            startActivityForResult(ratingsAct, RATING_CODE)
        }
        return super.onContextItemSelected(item)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RATING_CODE){
            if(resultCode == Activity.RESULT_OK) {
                val getReview = data?.getStringExtra(RatingActivity.RETURN_REVIEW) as String
                movieEntityClass.reviews = getReview
                act_reviews.text = "\" " + movieEntityClass.reviews + " \""
            }
        }
    }

    class MovieEntity{
        var title = ""
        var overview = ""
        var reviews = ""
        var language = ""
        var releaseDate = ""
        var age = ""
        var violence = ""
        var languageUsed = ""
    }
}