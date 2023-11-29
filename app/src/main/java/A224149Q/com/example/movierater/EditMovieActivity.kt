package A224149Q.com.example.movierater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_add_movie_old.chkLay
import kotlinx.android.synthetic.main.activity_edit_movie.chiRad
import kotlinx.android.synthetic.main.activity_edit_movie.desEt
import kotlinx.android.synthetic.main.activity_edit_movie.engRad
import kotlinx.android.synthetic.main.activity_edit_movie.nameEt
import kotlinx.android.synthetic.main.activity_edit_movie.nameTv
import kotlinx.android.synthetic.main.activity_edit_movie.nsfaaChk
import kotlinx.android.synthetic.main.activity_edit_movie.relEt
import kotlinx.android.synthetic.main.activity_edit_movie.voiChk

class EditMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        val movieClass = MovieEntity()
        movieClass.name = "Venom"
        movieClass.description = "Overview"
        movieClass.language = true
        movieClass.releaseDate = "19-10-2018"
        movieClass.nsfaa = true
        movieClass.violence = true

        nameEt.setText(movieClass.name)
        desEt.setText(movieClass.description)
        engRad.isChecked = movieClass.language
        relEt.setText(movieClass.releaseDate)
        nsfaaChk.isChecked = movieClass.nsfaa
        voiChk.isChecked = movieClass.violence

        if(nsfaaChk.isChecked){
            chkLay.visibility = View.VISIBLE;
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editmoviemenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    class MovieEntity {
        var name = ""
        var description = ""
        var language: Boolean = false
        var releaseDate = ""
        var nsfaa: Boolean = false
        var violence: Boolean = false
    }
}