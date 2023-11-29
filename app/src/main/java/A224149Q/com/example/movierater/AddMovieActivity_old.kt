package A224149Q.com.example.movierater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_movie_old.btnSubmit
import kotlinx.android.synthetic.main.activity_add_movie_old.chkLay
import kotlinx.android.synthetic.main.activity_add_movie_old.desEt
import kotlinx.android.synthetic.main.activity_add_movie_old.lanRad
import kotlinx.android.synthetic.main.activity_add_movie_old.lanUsedChk
import kotlinx.android.synthetic.main.activity_add_movie_old.nameEt
import kotlinx.android.synthetic.main.activity_add_movie_old.nsfaaChk
import kotlinx.android.synthetic.main.activity_add_movie_old.relEt
import kotlinx.android.synthetic.main.activity_add_movie_old.voiChk

class AddMovieActivity_old : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie_old)
        main();
    }
    private fun main(){
        var languageType = "English"
        var suitable = false
        var violence = ""
        var language = ""
        nsfaaChk.setOnCheckedChangeListener { buttonView, isChecked ->
            if (nsfaaChk.isChecked) {
                chkLay.visibility = View.VISIBLE;
                suitable = true
            } else {
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
        btnSubmit.setOnClickListener {
            val title = nameEt.text.toString()
            val description = desEt.text.toString()
            val releaseDate = relEt.text.toString()

            var allFieldsFilled = true;
            if (nameEt.text.toString().isEmpty()) {
                nameEt.error = "Field Empty"
                allFieldsFilled = false
            }
            if (desEt.text.toString().isEmpty()) {
                desEt.error = "Field Empty"
                allFieldsFilled = false
            }
            if (relEt.text.toString().isEmpty()) {
                relEt.error = "Field Empty"
                allFieldsFilled = false
            }

            if (allFieldsFilled) {
                displayToast(
                    "Title = $title\n" +
                    "Overview = $description\n" +
                    "Release date = $releaseDate\n" +
                    "Language = $languageType\n" +
                    "Not suitable for all ages = $suitable ${reasons(suitable,violence, language)}"
                )
            }
        }
    }

    private fun reasons(suitable: Boolean, violence: String, language: String): String{
        var output =  ""
        if(suitable){
            if(!violence.isEmpty() && language.isEmpty()){
                output = violence
            }else if(violence.isEmpty() && !language.isEmpty()){
                output = language
            }else if(!violence.isEmpty() && !language.isEmpty()){
                output = "$violence\n$language"
            }
            return "\nReasons: \n$output"
        }
        else{
            return output
        }
    }

    private fun displayToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}