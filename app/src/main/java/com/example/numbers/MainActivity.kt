package com.example.numbers

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.log
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var textInputEditText: TextInputEditText
    private lateinit var textView: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textInputEditText = findViewById(R.id.textInputEditText)
        textInputEditText.inputType = InputType.TYPE_CLASS_NUMBER
        //textInputEditText.inputType = InputType.TYPE_CLASS_TEXT

        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)
        textView4 = findViewById(R.id.textView4)

        var temp=1

        fun calculate(){
            //println("KLIKANE!!!")
            //println(temp)
            //textInputEditText.setOnEditorActionListener { _, _, _ ->
                when (temp) {
                    1 -> calculateConversions1()
                    2 -> calculateConversions2()
                    3 -> calculateConversions3()
                    4 -> calculateConversions4()
                    else -> false
                }

                //calculateConversions1()
            //    false
            //}
        }

        // Obserwator dla RadioButtonów
        val radioButtonGroup = findViewById<RadioGroup>(R.id.Group)
        radioButtonGroup.setOnCheckedChangeListener { group, checkedId ->
            val checkedRadioButton = group.findViewById<RadioButton>(checkedId)
            temp = when (checkedRadioButton) {
                findViewById<RadioButton>(R.id.radioButton1) -> 1
                findViewById<RadioButton>(R.id.radioButton2) -> 2
                findViewById<RadioButton>(R.id.radioButton3) -> 3
                findViewById<RadioButton>(R.id.radioButton4) -> 4
                else -> temp // Domyślnie zachowaj obecną wartość temp
            }
            textInputEditText.text = null
            calculate()
            //textInputEditText.text = null
        }

        textInputEditText.setOnEditorActionListener { _, _, _ ->
        when (temp) {
            1 -> calculateConversions1()
            2 -> calculateConversions2()
            3 -> calculateConversions3()
            4 -> calculateConversions4()
            else -> false
        }

        //calculateConversions1()
            false
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calculateConversions1() {
        val inputText = textInputEditText.text.toString()
        val str = getString(R.string.wyn2)
        if(true){
            textView.text = getString(R.string.welcome1)
            textView2.text = str
            textView3.text = getString(R.string.wyn16)
            textView4.text = getString(R.string.wyn8)
        }

        textInputEditText.inputType = InputType.TYPE_CLASS_NUMBER

        if (inputText.isNotEmpty()) {
            val inputValue = inputText.toInt()
            var wyn16 = Integer.toHexString(inputValue)
            val wyn2 = Integer.toBinaryString(inputValue)
            val wyn8 = Integer.toOctalString(inputValue)
            wyn16 = wyn16.uppercase()
            val text1 = textView2.text
            val text2 = textView3.text
            val text3 = textView4.text


            textView2.text = "$text1 $wyn2"
            textView3.text = "$text2 $wyn16"
            textView4.text = "$text3 $wyn8"




        } else {
            // Obsłuż sytuację, gdy pole tekstowe jest puste
        }
    }
    private fun calculateConversions2() {
        val inputText = textInputEditText.text.toString()
        val str = getString(R.string.wyn10)
        if(textView2.text != str){
            textView.text = getString(R.string.welcome2)
            textView2.text = str
            textView3.text = getString(R.string.wyn16)
            textView4.text = getString(R.string.wyn8)
        }

        textInputEditText.inputType = InputType.TYPE_CLASS_NUMBER

        val binaryFilter = InputFilter { source, _, _, _, _, _ ->
            val validChars = "01"
            for (i in source.indices) {
                if (!validChars.contains(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }

        textInputEditText.filters = arrayOf(InputFilter.LengthFilter(5), binaryFilter)

        fun binaryToDecimal(binary: String): Int{
            val reversedDigits = binary.reversed().toCharArray().map{it.digitToInt()}
            var decimalNumber = 0
            var i = 0

            for (n in reversedDigits) {
                decimalNumber += (n * 2.0.pow(i)).toInt()
                ++i
            }
            return decimalNumber
        }

        if (inputText.isNotEmpty()) {
            val inputValue = inputText.toInt()
            val wyn10 = binaryToDecimal((inputValue.toString()))
            var wyn16 = Integer.toHexString(wyn10)
            val wyn8 = Integer.toOctalString(wyn10)
            wyn16 = wyn16.uppercase()
            val text1 = textView2.text
            val text2 = textView3.text
            val text3 = textView4.text


            textView2.text = "$text1 $wyn10"
            textView3.text = "$text2 $wyn16"
            textView4.text = "$text3 $wyn8"




        } else {
            // Obsłuż sytuację, gdy pole tekstowe jest puste
        }
    }
    private fun calculateConversions3() {
        val inputText = textInputEditText.text.toString()
        val str = getString(R.string.wyn10)
        if(textView3.text != str){
            textView.text = getString(R.string.welcome3)
            textView2.text = getString(R.string.wyn2)
            textView3.text = str
            textView4.text = getString(R.string.wyn8)
        }

        textInputEditText.inputType = InputType.TYPE_CLASS_TEXT

        val hexFilter = InputFilter { source, _, _, _, _, _ ->
            val validChars = "0123456789ABCDEFabcdef"
            for (i in source.indices) {
                if (!validChars.contains(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }

        textInputEditText.filters = arrayOf(InputFilter.LengthFilter(5), hexFilter)


        if (inputText.isNotEmpty()) {
            val inputValue = inputText.uppercase()
            val wyn10 = inputValue.toLong(radix = 16).toInt()
            val wyn2 = Integer.toBinaryString(wyn10)
            val wyn8 = Integer.toOctalString(wyn10)
            //wyn16 = wyn16.uppercase()
            val text1 = textView2.text
            val text2 = textView3.text
            val text3 = textView4.text


            textView2.text = "$text1 $wyn2"
            textView3.text = "$text2 $wyn10"
            textView4.text = "$text3 $wyn8"




        } else {
            // Obsłuż sytuację, gdy pole tekstowe jest puste
        }
    }
    private fun calculateConversions4() {
        val inputText = textInputEditText.text.toString()
        val str = getString(R.string.wyn10)
        if(textView4.text != str){
            textView.text = getString(R.string.welcome4)
            textView2.text = getString(R.string.wyn2)
            textView3.text = getString(R.string.wyn16)
            textView4.text = str
        }

        textInputEditText.inputType = InputType.TYPE_CLASS_NUMBER

        val octFilter = InputFilter { source, _, _, _, _, _ ->
            val validChars = "01234567"
            for (i in source.indices) {
                if (!validChars.contains(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }

        textInputEditText.filters = arrayOf(InputFilter.LengthFilter(5), octFilter)

        if (inputText.isNotEmpty()) {
            val inputValue = inputText.toInt()
            val wyn10 = inputText.toLong(radix = 8).toInt()
            var wyn16 = Integer.toHexString(wyn10)
            val wyn2 = Integer.toBinaryString(wyn10)
            wyn16 = wyn16.uppercase()
            val text1 = textView2.text
            val text2 = textView3.text
            val text3 = textView4.text


            textView2.text = "$text1 $wyn2"
            textView3.text = "$text2 $wyn16"
            textView4.text = "$text3 $wyn10"




        } else {
            // Obsłuż sytuację, gdy pole tekstowe jest puste
        }
    }
}
