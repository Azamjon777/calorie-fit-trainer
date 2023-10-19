package goope.rimu.caloriefittrainer.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import goope.rimu.caloriefittrainer.R
import goope.rimu.caloriefittrainer.databinding.FragmentThirdBinding
import goope.rimu.caloriefittrainer.presentation.DetailActivity

class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private lateinit var checkBox: CheckBox
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainingModel = ThirdFragmentArgs.fromBundle(requireArguments()).trainingModel

        Glide.with(binding.root)
            .load(trainingModel.imageUrl)
            .into(binding.trainingImage)
        binding.trainTitle.text = trainingModel.title
        binding.trainDesc.text = trainingModel.desc
        binding.trainLessons.text = getString(R.string.lessons) + trainingModel.lessons.toString()
        binding.btnViewProgram.setOnClickListener {
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra("trainingModel", trainingModel)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}