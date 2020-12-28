package nl.rgasc.tubeless.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nl.rgasc.tubeless.databinding.FragmentDrawerBinding
import nl.rgasc.tubeless.databinding.FragmentFeedBinding
import nl.rgasc.tubeless.model.Channel

/**
 * This shows a feed of all the users channels
 */
class DrawerFragment : Fragment() {

    private var _binding: FragmentDrawerBinding? = null
    private val binding get() = _binding!!
    private val channels: ArrayList<Channel> = arrayListOf()
    private val channelAdapter: ChannelAdapter = ChannelAdapter(channels)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrawerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeChannels()
    }

    private fun initViews() {
        binding.rvChannels.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvChannels.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        binding.rvChannels.adapter = channelAdapter
    }

    private fun observeChannels() {
        channels.add(Channel("Channel #1", "123"))
        channels.add(Channel("Channel #2", "123"))
        channels.add(Channel("Channel #3", "123"))
        channels.add(Channel("Channel #4", "123"))

        channelAdapter.notifyDataSetChanged()
    }
}