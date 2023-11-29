package A224149Q.com.example.movierater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_movie.voiChk
import kotlinx.android.synthetic.main.activity_add_movie.chkLay
import kotlinx.android.synthetic.main.activity_add_movie.desEt
import kotlinx.android.synthetic.main.activity_add_movie.engRad
import kotlinx.android.synthetic.main.activity_add_movie.lanRad
import kotlinx.android.synthetic.main.activity_add_movie.lanUsedChk
import kotlinx.android.synthetic.main.activity_add_movie.nameEt
import kotlinx.android.synthetic.main.activity_add_movie.nsfaaChk
import kotlinx.android.synthetic.main.activity_add_movie.relEt

class AddMovieActivity : AppCompatActivity() {
    private var languageType = "English"
    private var suitable: Boolean = false
    private var violence = ""
    private var language = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        main();
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.addmoviemenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.id.menu_add){
            var allFieldsFilled = true;
            if(nameEt.text.toString().isEmpty()){
                nameEt.error = "Field Empty"
                allFieldsFilled = false
            }
            if(desEt.text.toString().isEmpty()){
                desEt.error = "Field Empty"
                allFieldsFilled = false
            }
            if(relEt.text.toString().isEmpty()){
                relEt.error = "Field Empty"
                allFieldsFilled = false
            }
            val details = Intent(this, MovieDetail::class.java)
            details.putExtra("title", nameEt.text.toString())
            details.putExtra("overview",desEt.text.toString())
            details.putExtra("language",languageType)
            details.putExtra("date", relEt.text.toString())
            details.putExtra("nsfaa", nsfaaChk.isChecked.toString())
            details.putExtra("violence",voiChk.isChecked.toString())
            details.putExtra("languageUsed", lanUsedChk.isChecked.toString())
            if(allFieldsFilled){
                startActivity(details)
            }
        }else if(item?.itemId == R.id.menu_clear){
            Toast.makeText(this, "Entries Cleared", Toast.LENGTH_SHORT).show()
            nameEt.setText("")
            desEt.setText("")
            engRad.isChecked = true
            relEt.setText("")
            checkBoxVal()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun main(){
        nsfaaChk.setOnCheckedChangeListener { buttonView, isChecked ->
            if (nsfaaChk.isChecked) {
                chkLay.visibility = View.VISIBLE;
                suitable = true
            } else {
                checkBoxVal()
                chkLay.visibility = View.GONE;
                suitable = false
            }
        }

        voiChk.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(voiChk.isChecked){
                violence = "Violence"
            }else violence = ""
        }
        lanUsedChk.setOnCheckedChangeListener { buttonView, isChecked ->
            if(lanUsedChk.isChecked){
                language = "Language Used"
            }else language = ""
        }
        lanRad.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                if (checkedId == R.id.engRad) {
                    languageType = "English"
                } else if (checkedId == R.id.chiRad) {
                    languageType = "Chinese"
                } else if (checkedId == R.id.malRad) {
                    languageType = "Malay"
                } else if (checkedId == R.id.tamRad) {
                    languageType = "Tamil"
                }
            }
        })
    }
    private fun checkBoxVal(){
        nsfaaChk.isChecked = false
        voiChk.isChecked = false
        lanUsedChk.isChecked = false
    }
}