package nl.rgasc.tubeless.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nl.rgasc.tubeless.databinding.FragmentDrawerBinding
import nl.rgasc.tubeless.models.Channel
import nl.rgasc.tubeless.adapters.ChannelAdapter
import nl.rgasc.tubeless.views.MainActivity

/**
 * This shows a feed of all the users channels
 */
class DrawerFragment : Fragment() {

    private var _binding: FragmentDrawerBinding? = null
    private val binding get() = _binding!!
    private val channels: ArrayList<Channel> = arrayListOf()
    private lateinit var channelAdapter: ChannelAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        channelAdapter = ChannelAdapter(channels, activity as MainActivity)
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
        val newChannels = arrayListOf<Channel>()
        var c = 'z'

        for (i in 1..20) {
            newChannels.add(Channel("Channel #$c", "0"))
            --c
        }

        channels.addAll(newChannels.sortedBy { it.name })

        channelAdapter.notifyDataSetChanged()
    }
}