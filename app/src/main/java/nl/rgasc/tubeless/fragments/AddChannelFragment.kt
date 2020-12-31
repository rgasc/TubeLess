package nl.rgasc.tubeless.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import nl.rgasc.tubeless.R
import nl.rgasc.tubeless.databinding.FragmentAddChannelBinding
import nl.rgasc.tubeless.models.Channel
import nl.rgasc.tubeless.viewmodels.ChannelViewModel

/**
 * In this fragment a channel can be added
 */
class AddChannelFragment : Fragment() {

    private var _binding: FragmentAddChannelBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChannelViewModel by activityViewModels()

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
            onAddChannel()
        }
    }

    private fun onAddChannel() {
        viewModel.insertChannel(Channel("test", binding.etChannel.text.toString()))

        viewModel.error.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        })

        viewModel.success.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_addChannelFragment_to_channelFragment)
        })
    }
}