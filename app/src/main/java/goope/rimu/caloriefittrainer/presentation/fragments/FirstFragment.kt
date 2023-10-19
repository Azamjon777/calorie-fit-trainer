package goope.rimu.caloriefittrainer.presentation.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import goope.rimu.caloriefittrainer.R
import goope.rimu.caloriefittrainer.databinding.FragmentFirstBinding
import goope.rimu.caloriefittrainer.databinding.WeeklyPlanDialogBinding
import goope.rimu.caloriefittrainer.util.CaloriesManager

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var caloriesManager: CaloriesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicks()
    }

    private fun clicks() {
        binding.llExercises.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        caloriesManager = CaloriesManager(requireActivity())
        val savedBicycleCalories = caloriesManager.getCalories("Bicycle")
        val savedRunCalories = caloriesManager.getCalories("Run")
        val caloriesForSport = caloriesManager.getCalories("Sport")
        val allCalorie = savedBicycleCalories + savedRunCalories + caloriesForSport
        binding.calorieBurned.text = allCalorie.toString()
        binding.llCalorie.setOnClickListener {
            if (allCalorie != 0) {
                val alertDialogBuilder = AlertDialog.Builder(requireContext())
                alertDialogBuilder.setTitle(getString(R.string.calories))
                alertDialogBuilder.setMessage(getString(R.string.calories_burned) + allCalorie)
                alertDialogBuilder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                }

                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            } else {
                val alertDialogBuilder = AlertDialog.Builder(requireContext())
                alertDialogBuilder.setTitle(getString(R.string.you_haven_t_specified_what_sport_you_ve_played_yet))
                alertDialogBuilder.setMessage(getString(R.string.go_to_your_profile_to_set_this_data))
                alertDialogBuilder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                }

                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        }

        val mondayCaloriesTextView = binding.mondayCalories
        val tuesdayCaloriesTextView = binding.tuesdayCalories
        val wednesdayCaloriesTextView = binding.wednesdayCalories
        val thursdayCaloriesTextView = binding.thursdayCalories
        val fridayCaloriesTextView = binding.fridayCalories
        val saturdayCaloriesTextView = binding.saturdayCalories
        val sundayCaloriesTextView = binding.sundayCalories

        val sharedPrefs = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val mondayPlan = sharedPrefs.getString("MondayPlan", "")
        val tuesdayPlan = sharedPrefs.getString("TuesdayPlan", "")
        val wednesdayPlan = sharedPrefs.getString("WednesdayPlan", "")
        val thursdayPlan = sharedPrefs.getString("ThursdayPlan", "")
        val fridayPlan = sharedPrefs.getString("FridayPlan", "")
        val saturdayPlan = sharedPrefs.getString("SaturdayPlan", "")
        val sundayPlan = sharedPrefs.getString("SundayPlan", "")

        mondayCaloriesTextView.text = getString(R.string.monday) + mondayPlan
        tuesdayCaloriesTextView.text = getString(R.string.tuesday) + tuesdayPlan
        wednesdayCaloriesTextView.text = getString(R.string.wednesday) + wednesdayPlan
        thursdayCaloriesTextView.text = getString(R.string.thursday) + thursdayPlan
        fridayCaloriesTextView.text = getString(R.string.friday) + fridayPlan
        saturdayCaloriesTextView.text = getString(R.string.saturday) + saturdayPlan
        sundayCaloriesTextView.text = getString(R.string.sunday) + sundayPlan

        binding.llPlanToWeek.setOnClickListener {
            val dialogView = WeeklyPlanDialogBinding.inflate(layoutInflater)

            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle(getString(R.string.set_a_plan_for_the_week))
            alertDialogBuilder.setMessage(getString(R.string.enter_the_number_of_calories_from_1_to_1000))
            alertDialogBuilder.setView(dialogView.root)
            val sharedPrefs = requireActivity().getSharedPreferences(
                "MyPrefs",
                Context.MODE_PRIVATE
            )
            val editor = sharedPrefs.edit()

            alertDialogBuilder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                val monday = dialogView.mondayPlan.text.toString()
                val tuesday = dialogView.tuesdayPlan.text.toString()
                val wednesday = dialogView.wednesdayPlan.text.toString()
                val thursday = dialogView.thursdayPlan.text.toString()
                val friday = dialogView.fridayPlan.text.toString()
                val saturday = dialogView.saturdayPlan.text.toString()
                val sunday = dialogView.sundayPlan.text.toString()

                if (isWithinRange(monday) || isWithinRange(tuesday) || isWithinRange(wednesday) ||
                    isWithinRange(thursday) || isWithinRange(friday) || isWithinRange(saturday) || isWithinRange(
                        sunday
                    )
                ) {
                    if (monday.isNotEmpty()){
                        editor.putString("MondayPlan", monday)
                        editor.apply()
                    }
                    if (tuesday.isNotEmpty()){
                        editor.putString("TuesdayPlan", tuesday)
                        editor.apply()
                    }
                    if (wednesday.isNotEmpty()){
                        editor.putString("WednesdayPlan", wednesday)
                        editor.apply()
                    }
                    if (thursday.isNotEmpty()){
                        editor.putString("ThursdayPlan", thursday)
                        editor.apply()
                    }
                    if (friday.isNotEmpty()) {
                        editor.putString("FridayPlan", friday)
                        editor.apply()
                    }
                    if (saturday.isNotEmpty()){
                        editor.putString("SaturdayPlan", saturday)
                        editor.apply()
                    }
                    if (sunday.isNotEmpty()){
                        editor.putString("SundayPlan", sunday)
                        editor.apply()
                    }

                    val mondayPlan1 = sharedPrefs.getString("MondayPlan", "")
                    val tuesdayPlan1 = sharedPrefs.getString("TuesdayPlan", "")
                    val wednesdayPlan1 = sharedPrefs.getString("WednesdayPlan", "")
                    val thursdayPlan1 = sharedPrefs.getString("ThursdayPlan", "")
                    val fridayPlan1 = sharedPrefs.getString("FridayPlan", "")
                    val saturdayPlan1 = sharedPrefs.getString("SaturdayPlan", "")
                    val sundayPlan1 = sharedPrefs.getString("SundayPlan", "")

                    mondayCaloriesTextView.text = getString(R.string.monday) + mondayPlan1
                    tuesdayCaloriesTextView.text = getString(R.string.tuesday) + tuesdayPlan1
                    wednesdayCaloriesTextView.text = getString(R.string.wednesday) + wednesdayPlan1
                    thursdayCaloriesTextView.text = getString(R.string.thursday) + thursdayPlan1
                    fridayCaloriesTextView.text = getString(R.string.friday) + fridayPlan1
                    saturdayCaloriesTextView.text = getString(R.string.saturday) + saturdayPlan1
                    sundayCaloriesTextView.text = getString(R.string.sunday) + sundayPlan1
                    dialog.dismiss()
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.numbers_must_be_between_1_and_1000),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    private fun isWithinRange(number: String): Boolean {
        return try {
            val num = number.toInt()
            num in 1..1000
        } catch (e: NumberFormatException) {
            false
        }
    }
}