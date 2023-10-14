package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCreatePatientCardBinding

class CreatePatientCard : AppCompatActivity() {

    var _binding : ActivityCreatePatientCardBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCreatePatientCardBinding.inflate(layoutInflater)
        val GenderVarients = arrayOf("Пол", "Мужской", "Женский")
        var AdapterForSpiner : ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.activity_create_patient_card_spinner_row, R.id.Gender, GenderVarients);
        _binding!!.GenderSpiner.adapter = AdapterForSpiner;
        _binding!!.GenderSpiner.setSelection(0);
        setContentView(_binding!!.root)
    }
}