package com.example.wingstofly.utils

import com.google.android.material.textfield.TextInputLayout

class Validator {
    fun validateInputText(input: TextInputLayout): Boolean{
        return if (input.editText?.text?.isEmpty() == true){
            input.apply {
                isErrorEnabled = true
                error = "This field cannot be empty"
            }
            false

        }else{
            input.apply {
                isErrorEnabled = false
            }
            true
        }
    }
}