package goope.rimu.caloriefittrainer.util

import android.content.Context
import android.content.SharedPreferences

class CaloriesManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "CaloriesSharedPreferences",
        Context.MODE_PRIVATE
    )

    fun saveCalories(activityType: String, calories: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(activityType, calories)
        editor.apply()
    }

    fun getCalories(activityType: String): Int {
        return sharedPreferences.getInt(activityType, 0)
    }
}
