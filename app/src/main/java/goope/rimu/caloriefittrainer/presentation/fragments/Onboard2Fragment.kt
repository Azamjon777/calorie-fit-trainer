package goope.rimu.caloriefittrainer.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import goope.rimu.caloriefittrainer.databinding.FragmentOnboard2Binding
import goope.rimu.caloriefittrainer.presentation.MainActivity

class Onboard2Fragment : Fragment() {
    private lateinit var binding: FragmentOnboard2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboard2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSkip.setOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }
    }
}
