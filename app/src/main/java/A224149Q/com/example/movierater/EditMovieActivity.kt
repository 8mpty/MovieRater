package A224149Q.com.example.movierater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_movie.chkLay
import kotlinx.android.synthetic.main.activity_edit_movie.nsfaaChk
import kotlinx.android.synthetic.main.activity_edit_movie.desEt
import kotlinx.android.synthetic.main.activity_edit_movie.engRad
import kotlinx.android.synthetic.main.activity_edit_movie.lanRad
import kotlinx.android.synthetic.main.activity_edit_movie.lanUsedChk
import kotlinx.android.synthetic.main.activity_edit_movie.nameEt
import kotlinx.android.synthetic.main.activity_edit_movie.relEt
import kotlinx.android.synthetic.main.activity_edit_movie.voiChk

class EditMovieActivity : AppCompatActivity() {
    val movieClass = MovieEntity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        movieClass.name = "Venom"
        movieClass.description = "Overview"
        movieClass.language = true
        movieClass.releaseDate = "19-10-2018"
        movieClass.nsfaa = true
        movieClass.violence = true
        movieClass.languageUsed = false

        nameEt.setText(movieClass.name)
        desEt.setText(movieClass.description)
        engRad.isChecked = movieClass.language
        relEt.setText(movieClass.releaseDate)
        nsfaaChk.isChecked = movieClass.nsfaa
        voiChk.isChecked = movieClass.violence
        lanUsedChk.isChecked = movieClass.languageUsed

        if(nsfaaChk.isChecked) {
            chkLay.visibility = View.VISIBLE;
        }

        nsfaaChk.setOnCheckedChangeListener { buttonView, isChecked ->
            if (nsfaaChk.isChecked) {
                chkLay.visibility = View.VISIBLE;
            } else {
                nsfaaChk.isChecked = false
                voiChk.isChecked = false
                lanUsedChk.isChecked = false
                chkLay.visibility = View.GONE;
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editmoviemenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.id.save){
            val newName = nameEt.text.toString()
            val newDesc = desEt.text.toString()
            val newLan = lanRad.isSelected
            val newRelDate = relEt.text.toString()
            val newSuit = nsfaaChk.isChecked
            val newVio = voiChk.isChecked
            val newLanUsed = lanUsedChk.isChecked

            movieClass.name = newName
            movieClass.description = newDesc
            movieClass.language = newLan
            movieClass.releaseDate = newRelDate
            movieClass.nsfaa = newSuit
            movieClass.violence = newVio
            movieClass.languageUsed = newLanUsed

            Toast.makeText(this, "Entries Saved!!", Toast.LENGTH_SHORT).show()

        }else if(item?.itemId == R.id.cancel){
            nameEt.setText(movieClass.name)
            desEt.setText(movieClass.description)
            engRad.isSelected = movieClass.language
            relEt.setText(movieClass.releaseDate)
            nsfaaChk.isChecked = movieClass.nsfaa
            voiChk.isChecked = movieClass.violence
            lanUsedChk.isChecked = movieClass.languageUsed

            Toast.makeText(this, "Entries Reverted!!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    class MovieEntity {
        var name = ""
        var description = ""
        var language: Boolean = false
        var releaseDate = ""
        var nsfaa: Boolean = false
        var violence: Boolean = false
        var languageUsed: Boolean = false
    }
}