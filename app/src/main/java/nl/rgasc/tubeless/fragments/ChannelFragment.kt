package nl.rgasc.tubeless.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import nl.rgasc.tubeless.R
import nl.rgasc.tubeless.viewmodels.ChannelViewModel

/**
 * This fragment shows the videos of a single channel
 */
class ChannelFragment : Fragment() {

    private val viewModel: ChannelViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_channel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.fragment_channel_title, viewModel.currentChannel.name)
    }
}