package goope.rimu.caloriefittrainer.util

import android.text.InputFilter
import android.text.Spanned

class MaxValueInputFilter(private val maxValue: Int) : InputFilter {

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val input = (dest.toString() + source.toString()).toInt()
            if (input <= maxValue) {
                return null
            }
        } catch (e: NumberFormatException) {
            return ""
        }
        return ""
    }
}
