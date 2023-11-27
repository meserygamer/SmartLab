package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ActivityChangeProfileBinding

class ChangeProfile : AppCompatActivity() {

    var binding : ActivityChangeProfileBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeProfileBinding.inflate(layoutInflater)
        setSpiner()
        setContentView(binding!!.root)
        binding!!.ProfileButtonNavigationBar.isSelected = true;
        binding!!.GoToTestButton.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                startActivity(Intent(this@ChangeProfile, MainPage::class.java));
            }

        })
    }

    fun setSpiner() {
        val GenderVarients = arrayOf("Мужской", "Женский")
        var AdapterForSpiner : ArrayAdapter<String>
        AdapterForSpiner = ArrayAdapter<String>(this
            ,R.layout.activity_create_patient_card_spinner_row_0_id_selected
            ,R.id.Gender
            ,GenderVarients);
        binding!!.GenderSpiner.adapter = AdapterForSpiner;
    }

}