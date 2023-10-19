package goope.rimu.caloriefittrainer.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import goope.rimu.caloriefittrainer.R
import goope.rimu.caloriefittrainer.databinding.FragmentOnboard1Binding

class Onboard1Fragment : Fragment() {
    private lateinit var binding: FragmentOnboard1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboard1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSkip.setOnClickListener {
            findNavController().navigate(R.id.action_onboard1Fragment_to_onboard2Fragment)
        }
    }
}
