package nl.rgasc.tubeless.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nl.rgasc.tubeless.R
import nl.rgasc.tubeless.databinding.FragmentDrawerBinding
import nl.rgasc.tubeless.models.Channel
import nl.rgasc.tubeless.adapters.ChannelAdapter
import nl.rgasc.tubeless.viewmodels.ChannelViewModel
import nl.rgasc.tubeless.views.MainActivity

/**
 * This shows a feed of all the users channels
 */
class DrawerFragment : Fragment() {

    private var _binding: FragmentDrawerBinding? = null
    private val binding get() = _binding!!
    private val channels: ArrayList<Channel> = arrayListOf()
    private lateinit var channelAdapter: ChannelAdapter
    private val viewModel: ChannelViewModel by activityViewModels()

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
        channelAdapter = ChannelAdapter(channels, ::onChannelClick)

        binding.rvChannels.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvChannels.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        binding.rvChannels.adapter = channelAdapter

        binding.btnFeed.setOnClickListener {
            navigate(R.id.feedFragment)
        }

        binding.btnAdd.setOnClickListener {
            navigate(R.id.addChannelFragment)
        }
    }

    private fun onChannelClick(channel: Channel) {
        viewModel.currentChannel = channel
        navigate(R.id.channelFragment)
    }

    private fun observeChannels() {
        viewModel.channels.observe(viewLifecycleOwner, {
            channels.clear()
            channels.addAll(it.sortedBy { channel -> channel.name })
            channelAdapter.notifyDataSetChanged()
        })
    }

    private fun navigate(destination: Int) {
        findNavController().navigate(destination)
        (activity as MainActivity).binding.drawerLayout.closeDrawer(GravityCompat.START)
    }
}