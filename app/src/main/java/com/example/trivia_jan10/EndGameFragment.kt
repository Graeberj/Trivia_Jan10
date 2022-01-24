package com.example.trivia_jan10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.trivia_jan10.databinding.FragmentWelcomeBinding

class EndGameFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding get() = _binding!!
    private val viewModel: TriviaViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val percentCorrect = viewModel.getPercentCorrect()
            introTv.text =
                if (percentCorrect >= 70) getString(R.string.you_won) else getString(R.string.you_lost)
            startBtn.setOnClickListener {
                viewModel.resetGame()
                val direction = EndGameFragmentDirections.actionEndGameFragmentPopUpToWelcomeFragment()
                findNavController().navigate(direction)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}