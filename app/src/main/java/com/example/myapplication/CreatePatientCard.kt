package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCreatePatientCardBinding

class CreatePatientCard : AppCompatActivity() {

    var _binding : ActivityCreatePatientCardBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCreatePatientCardBinding.inflate(layoutInflater)
        setSpiner();
        _binding!!.GenderSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position != 0)
                {
                    ((parent!!.getChildAt(0) as LinearLayout).getChildAt(0) as TextView).setTextColor(Color.BLACK)
                }
                else
                {
                    ((parent!!.getChildAt(0) as LinearLayout).getChildAt(0) as TextView).setTextColor(Color.parseColor("#939396"))
                }
            }
        }
        setContentView(_binding!!.root)
        setTextListners();
    }

    fun setSpiner() {
        val GenderVarients = arrayOf("Пол", "Мужской", "Женский")
        var AdapterForSpiner : ArrayAdapter<String>
        AdapterForSpiner = ArrayAdapter<String>(this, R.layout.activity_create_patient_card_spinner_row_0_id_selected, R.id.Gender, GenderVarients);
        _binding!!.GenderSpiner.adapter = AdapterForSpiner;
    }

    fun setTextListners() {
        _binding!!.NameField.addTextChangedListener(PatientCardFieldTextWatcher(_binding!!.NameField))
        _binding!!.PatronymicField.addTextChangedListener(PatientCardFieldTextWatcher(_binding!!.PatronymicField))
        _binding!!.SurnameField.addTextChangedListener(PatientCardFieldTextWatcher(_binding!!.SurnameField))
        _binding!!.BirthDayField.addTextChangedListener(PatientCardFieldTextWatcher(_binding!!.BirthDayField))
    }

    class PatientCardFieldTextWatcher : TextWatcher {
        val Field : EditText

        constructor(eT : EditText)
        {
            Field = eT;
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            if(Field.text.isNotEmpty())
            {
                Field.background.level = 1;
            }
            if(Field.text.isEmpty())
            {
                Field.background.level = 0;
            }
        }

    }
}
