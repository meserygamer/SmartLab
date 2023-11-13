package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ActivityChangeProfileBinding

class ChangeProfile : AppCompatActivity() {

    var binding : ActivityChangeProfileBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeProfileBinding.inflate(layoutInflater)
        setSpiner()
        setContentView(binding!!.root)
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