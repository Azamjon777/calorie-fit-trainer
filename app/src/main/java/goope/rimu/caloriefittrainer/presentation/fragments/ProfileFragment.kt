package goope.rimu.caloriefittrainer.presentation.fragments

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import goope.rimu.caloriefittrainer.R
import goope.rimu.caloriefittrainer.databinding.FragmentProfileBinding
import goope.rimu.caloriefittrainer.databinding.WeeklyPlanDialogBinding
import goope.rimu.caloriefittrainer.util.CaloriesManager
import goope.rimu.caloriefittrainer.util.MaxValueInputFilter

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var caloriesManager: CaloriesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mondayCaloriesTextView = binding.mondayCalories
        val tuesdayCaloriesTextView = binding.tuesdayCalories
        val wednesdayCaloriesTextView = binding.wednesdayCalories
        val thursdayCaloriesTextView = binding.thursdayCalories
        val fridayCaloriesTextView = binding.fridayCalories
        val saturdayCaloriesTextView = binding.saturdayCalories
        val sundayCaloriesTextView = binding.sundayCalories

        binding.llBicycle.setOnClickListener {
            showBicycleCaloriesDialog()
        }
        binding.llRun.setOnClickListener {
            showRunCaloriesDialog()
        }
        binding.llSimpleTraining.setOnClickListener {
            showSportCaloriesDialog()
        }
        binding.llHeight.setOnClickListener {
            showHeightInputDialog()
        }
        binding.llWeight.setOnClickListener {
            showWeightInputDialog()
        }
        binding.llYearOld.setOnClickListener {
            showAgeInputDialog()
        }
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        caloriesManager = CaloriesManager(requireActivity())
        val savedBicycleCalories = caloriesManager.getCalories("Bicycle")
        val savedRunCalories = caloriesManager.getCalories("Run")
        val caloriesForSport = caloriesManager.getCalories("Sport")
        val userWeight = sharedPreferences.getInt("user_weight", 0)
        val userHeight = sharedPreferences.getInt("user_height", 0)
        val userAge = sharedPreferences.getInt("user_age", 0)

        binding.sportCalorie.text = "$caloriesForSport " + getString(R.string.kcal)
        binding.bicycleCalorie.text = "$savedBicycleCalories " + getString(R.string.kcal)
        binding.runCalorie.text = "$savedRunCalories kcal"
        binding.tvWeight.text = userWeight.toString() + getString(R.string.kg)
        binding.tvHeight.text = userHeight.toString()
        binding.tvAge.text = userAge.toString()

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

    private fun showAgeInputDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enter age")

        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_NUMBER
        builder.setView(input)
        builder.setMessage("10 to 100")

        builder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            val ageStr = input.text.toString().toIntOrNull()
            if (ageStr != null && ageStr >= 10 && ageStr <= 100) {
                val sharedPreferences =
                    requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().putInt("user_age", ageStr).apply()
                binding.tvAge.text = getString(R.string.age1) + ageStr.toString()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.invalid_age_value_valid_range_is_10_to_100),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun showWeightInputDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.enter_your_weight_kg))
        builder.setMessage(getString(R.string.from_40_kg_to_150_kg))

        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_NUMBER
        builder.setView(input)

        builder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            val weightStr = input.text.toString().toIntOrNull()
            if (weightStr != null && weightStr in 40..150) {
                val sharedPreferences =
                    requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().putInt("user_weight", weightStr).apply()
                binding.tvWeight.text = "$weightStr kg"
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.wrong_weight_value),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun showHeightInputDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.enter_your_height))
        builder.setMessage(getString(R.string.from_70_cm_to_200_cm))

        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_NUMBER
        builder.setView(input)

        builder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            val heightStr = input.text.toString()
            val height = heightStr.toIntOrNull()

            if (height != null && height in 70..200) {
                val sharedPreferences =
                    requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putInt("user_height", height)
                editor.apply()

                binding.tvHeight.text = "$height " + getString(R.string.cm)
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.wrong_growth_value),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun showBicycleCaloriesDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.enter_the_number))
        builder.setMessage(getString(R.string.up_to_1000))

        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_NUMBER
        input.filters = arrayOf(InputFilter.LengthFilter(4), MaxValueInputFilter(1000))
        builder.setView(input)

        builder.setPositiveButton(getString(R.string.ok)) { _, _ ->
            val kilometers = input.text.toString().toDoubleOrNull()
            if (kilometers != null) {
                val caloriesForBicycle = calculateCaloriesForBicycle(kilometers)
                caloriesManager.saveCalories("Bicycle", caloriesForBicycle)
                binding.bicycleCalorie.text = "$caloriesForBicycle " + getString(R.string.kcal)
            } else {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.you_have_not_entered),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun showRunCaloriesDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.enter_the_number_of_kilometers_you_have_run))
        builder.setMessage(getString(R.string.up_to_1000))

        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_NUMBER
        input.filters = arrayOf(InputFilter.LengthFilter(4), MaxValueInputFilter(1000))
        builder.setView(input)

        builder.setPositiveButton(getString(R.string.ok)) { _, _ ->
            val kilometers = input.text.toString().toDoubleOrNull()
            if (kilometers != null) {
                val calories = calculateCaloriesForRun(kilometers)
                caloriesManager.saveCalories("Run", calories)
                binding.runCalorie.text = "$calories " + getString(R.string.kcal)
            } else {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.you_have_not_entered),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun showSportCaloriesDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.enter_duration))

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                val totalMinutes = hourOfDay * 60 + minute

                val calories = calculateCaloriesForSport(totalMinutes)
                caloriesManager.saveCalories("Sport", calories)
                binding.sportCalorie.text = "$calories " + getString(R.string.kcal)
            },
            0,
            0,
            true
        )

        builder.setPositiveButton(getString(R.string.ok)) { _, _ ->
            timePickerDialog.show()
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun calculateCaloriesForSport(minutes: Int): Int {
        val caloriesPerMinute = 5
        return minutes * caloriesPerMinute
    }

    private fun calculateCaloriesForRun(kilometers: Double): Int {
        val caloriesPerKilometer = 50
        return (kilometers * caloriesPerKilometer).toInt()
    }

    private fun calculateCaloriesForBicycle(kilometers: Double): Int {
        val caloriesPerKilometer = 40
        return (kilometers * caloriesPerKilometer).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}