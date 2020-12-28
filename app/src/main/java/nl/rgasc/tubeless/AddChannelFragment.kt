package nl.rgasc.tubeless

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import nl.rgasc.tubeless.databinding.FragmentAddChannelBinding

/**
 * In this fragment a channel can be added
 */
class AddChannelFragment : Fragment() {

    private var _binding: FragmentAddChannelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddChannelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.fragment_add_channel_title)

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_addChannelFragment_to_channelFragment)
        }
    }
}