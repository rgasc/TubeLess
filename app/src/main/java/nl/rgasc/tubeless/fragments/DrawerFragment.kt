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
    private val _viewModel: ChannelViewModel by activityViewModels()

    val viewModel: ChannelViewModel get() = _viewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        channelAdapter = ChannelAdapter(channels, this)
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

        binding.btnFeed.setOnClickListener {
            navigate(R.id.feedFragment)
        }

        binding.btnAdd.setOnClickListener {
            navigate(R.id.addChannelFragment)
        }
    }

    private fun observeChannels() {
        val newChannels = arrayListOf<Channel>()
        var c = 'z'

        for (i in 1..26) {
            newChannels.add(Channel("Channel #$c", "0"))
            --c
        }

        channels.addAll(newChannels.sortedBy { it.name })

        channelAdapter.notifyDataSetChanged()
    }

    private fun navigate(destination: Int) {
        findNavController().navigate(destination)
        (activity as MainActivity).binding.drawerLayout.closeDrawer(GravityCompat.START)
    }
}