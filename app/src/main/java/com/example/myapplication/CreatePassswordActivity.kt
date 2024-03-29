package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCreatePassswordBinding


class CreatePassswordActivity : AppCompatActivity() {

    var binding : ActivityCreatePassswordBinding? = null;

    private lateinit var prefs : SharedPreferences;

    private var password : String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        binding = ActivityCreatePassswordBinding.inflate(layoutInflater);
        binding!!.skipPassordCreation.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {

                if(prefs.contains("PatientCardWasCreated"))
                {
                    var intent = Intent(this@CreatePassswordActivity, MainPage::class.java)
                    startActivity(intent)
                    return;
                }
                var intent = Intent(this@CreatePassswordActivity, CreatePatientCard::class.java)
                startActivity(intent)
                return;

            }

        })
        setContentView(binding!!.root);
    }

    private fun showPasswordStateOnScreen(){
        when(password.length){
            1 -> {
                binding!!.DotOne.isChecked = true
                binding!!.DotTwo.isChecked = false
                binding!!.DotThree.isChecked = false
                binding!!.DotFour.isChecked = false
            }
            2 -> {
                binding!!.DotOne.isChecked = true
                binding!!.DotTwo.isChecked = true
                binding!!.DotThree.isChecked = false
                binding!!.DotFour.isChecked = false
            }
            3 -> {
                binding!!.DotOne.isChecked = true
                binding!!.DotTwo.isChecked = true
                binding!!.DotThree.isChecked = true
                binding!!.DotFour.isChecked = false
            }
            4 -> {
                binding!!.DotOne.isChecked = true
                binding!!.DotTwo.isChecked = true
                binding!!.DotThree.isChecked = true
                binding!!.DotFour.isChecked = true
            }
            else ->{
                binding!!.DotOne.isChecked = false
                binding!!.DotTwo.isChecked = false
                binding!!.DotThree.isChecked = false
                binding!!.DotFour.isChecked = false
            }
        }
    }

    private fun AddCharInPassword(Number : Char){
        password += Number
        showPasswordStateOnScreen();
        if(password.length == 4){
            (prefs.edit()).putString("Password", password).apply();
            if(prefs.contains("PatientCardWasCreated"))
            {
                var intent = Intent(this@CreatePassswordActivity, MainPage::class.java)
                startActivity(intent)
                return;
            }
            var intent = Intent(this@CreatePassswordActivity, CreatePatientCard::class.java)
            startActivity(intent)
            return;
        }
    }

    fun clickOnNumber(v: View) {
        when(v.id)
        {
            R.id.Number1 ->{
                AddCharInPassword('1');
            }

            R.id.Number2 ->{
                AddCharInPassword('2');
            }

            R.id.Number3 ->{
                AddCharInPassword('3');
            }

            R.id.Number4 ->{
                AddCharInPassword('4');
            }

            R.id.Number5 ->{
                AddCharInPassword('5');
            }

            R.id.Number6 ->{
                AddCharInPassword('6');
            }

            R.id.Number7 ->{
                AddCharInPassword('7');
            }

            R.id.Number8 ->{
                AddCharInPassword('8');
            }

            R.id.Number9 ->{
                AddCharInPassword('9');
            }

            R.id.Number0 ->{
                AddCharInPassword('0');
            }
        }
    }

    fun clickOnBackButton(v: View) {
        if(password.isEmpty()) return;
        password = password.substring(0 until password.length - 1)
        showPasswordStateOnScreen()
    }
}