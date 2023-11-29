package A224149Q.com.example.movierater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_movie_detail_old.act_age
import kotlinx.android.synthetic.main.activity_movie_detail_old.act_language
import kotlinx.android.synthetic.main.activity_movie_detail_old.act_overview
import kotlinx.android.synthetic.main.activity_movie_detail_old.act_releasedate
import kotlinx.android.synthetic.main.activity_movie_detail_old.act_title

class MovieDetail_old : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail_old)
        val movieDetails = MovieEntity()
        movieDetails.title = "Venom"
        movieDetails.overview = "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life."
        movieDetails.language = "English"
        movieDetails.releaseDate = "03-10-2018"
        movieDetails.age = "Yes"


        act_title.text = movieDetails.title
        act_overview.text = movieDetails.overview
        act_language.text = movieDetails.language
        act_releasedate.text = movieDetails.releaseDate
        act_age.text = movieDetails.age
    }

    class MovieEntity{
        var title = ""
        var overview = ""
        var language = ""
        var releaseDate = ""
        var age = ""
    }
}