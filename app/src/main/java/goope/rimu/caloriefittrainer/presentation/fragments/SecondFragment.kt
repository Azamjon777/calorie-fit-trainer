package goope.rimu.caloriefittrainer.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import goope.rimu.caloriefittrainer.R
import goope.rimu.caloriefittrainer.adapter.TrainingAdapter
import goope.rimu.caloriefittrainer.databinding.FragmentSecondBinding
import goope.rimu.caloriefittrainer.model.TrainingModel
import goope.rimu.caloriefittrainer.viewmodel.TrainingViewModel
import goope.rimu.caloriefittrainer.viewmodel.TrainingViewModelFactory

class SecondFragment : Fragment(), TrainingAdapter.OnTrainingItemClickListener {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var trainingAdapter: TrainingAdapter
    private val viewModel: TrainingViewModel by viewModels {
        TrainingViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val urlContainer = binding.urlContainer
        val youtubeUrl = "https://youtu.be/q99CRoYybvg?si=r3o8Ior7rPO-wEhp"

        urlContainer.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.video_is_not_available_now),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        Glide.with(requireActivity())
            .load("https://luna-askmen-images.askmen.com/1080x540/2016/07/04-045346-the_show_off_workout.jpg")
            .into(binding.urlImg)

        trainingAdapter = TrainingAdapter(viewModel.trainingList, this, requireActivity())
        binding.trainTypeRecyclerView.adapter = trainingAdapter
    }

    override fun onTrainingItemClick(training: TrainingModel) {
        val action = SecondFragmentDirections.actionSecondFragmentToThirdFragment(training)
        findNavController().navigate(action)
    }
}
