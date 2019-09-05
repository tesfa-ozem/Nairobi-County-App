package com.aw.epayments.utility

class Formaters {
    companion object {
        fun AddThousandSeparetor(value: Double): String {
            return try {
                /*val i: Int = Integer.parseInt(value)*/

                val str: String = String.format("%,.2f\n")
                str
            } catch (ex: NumberFormatException) { // handle your exception
                return ex.message.toString()
            }
        }
    }
}