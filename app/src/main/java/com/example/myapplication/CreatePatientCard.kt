package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

class CreatePatientCard : AppCompatActivity(), IPatientCardFieldListener {

    var _binding : ActivityCreatePatientCardBinding? = null

    var NameFieldListener : PatientCardFieldTextWatcher? = null;

    var PatronymicFieldListener : PatientCardFieldTextWatcher? = null;

    var SurnameFieldListener : PatientCardFieldTextWatcher? = null;

    var BirthDayFieldListener : PatientCardFieldTextWatcher? = null;

    var GenderField : String? = null;

    private lateinit var prefs : SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        _binding = ActivityCreatePatientCardBinding.inflate(layoutInflater)
        setSpiner();
        _binding!!.GenderSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position != 0)
                {
                    ((parent!!.getChildAt(0) as LinearLayout).getChildAt(0) as TextView).setTextColor(Color.BLACK)
                    GenderField = ((parent!!.getChildAt(0) as LinearLayout).getChildAt(0) as TextView).text.toString();
                    onChangeField()
                }
                else
                {
                    ((parent!!.getChildAt(0) as LinearLayout).getChildAt(0) as TextView).setTextColor(Color.parseColor("#939396"))
                    GenderField = null;
                    onChangeField()
                }
            }
        }
        setContentView(_binding!!.root)
        setTextListners();
        _binding!!.SkipPatientCardCreation.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {

                var intent = Intent(this@CreatePatientCard, MainPage::class.java)
                startActivity(intent)

            }

        })
        _binding!!.appCompatButton.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {

                (prefs.edit()).putBoolean("PatientCardWasCreated", true).apply();
                var intent = Intent(this@CreatePatientCard, MainPage::class.java)
                startActivity(intent)

            }

        })
    }

    fun setSpiner() {
        val GenderVarients = arrayOf("Пол", "Мужской", "Женский")
        var AdapterForSpiner : ArrayAdapter<String>
        AdapterForSpiner = ArrayAdapter<String>(this, R.layout.activity_create_patient_card_spinner_row_0_id_selected, R.id.Gender, GenderVarients);
        _binding!!.GenderSpiner.adapter = AdapterForSpiner;
    }

    fun setTextListners() {
        NameFieldListener = PatientCardFieldTextWatcher(_binding!!.NameField);
        NameFieldListener!!.Listener = this;
        _binding!!.NameField.addTextChangedListener(NameFieldListener)
        PatronymicFieldListener = PatientCardFieldTextWatcher(_binding!!.PatronymicField);
        PatronymicFieldListener!!.Listener = this;
        _binding!!.PatronymicField.addTextChangedListener(PatronymicFieldListener)
        SurnameFieldListener = PatientCardFieldTextWatcher(_binding!!.SurnameField)
        SurnameFieldListener!!.Listener = this
        _binding!!.SurnameField.addTextChangedListener(SurnameFieldListener)
        BirthDayFieldListener = PatientCardFieldTextWatcher(_binding!!.BirthDayField)
        BirthDayFieldListener!!.Listener = this
        _binding!!.BirthDayField.addTextChangedListener(BirthDayFieldListener)
    }

    class PatientCardFieldTextWatcher : TextWatcher{

        val Field : EditText

        public var Listener : IPatientCardFieldListener? = null

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
            if(Listener != null)
            {
                Listener!!.onChangeField();
            }
        }

    }

    override fun onChangeField() {
        _binding!!.appCompatButton.isEnabled = NameFieldListener!!.Field.text.trim().isNotEmpty() &&
                SurnameFieldListener!!.Field.text.trim().isNotEmpty() &&
                BirthDayFieldListener!!.Field.text.trim().isNotEmpty() &&
                GenderField != null
    }
}

interface IPatientCardFieldListener
{
    fun onChangeField()
}
