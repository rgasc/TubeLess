package nl.rgasc.tubeless.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nl.rgasc.tubeless.R
import nl.rgasc.tubeless.adapters.VideoAdapter
import nl.rgasc.tubeless.databinding.FragmentChannelBinding
import nl.rgasc.tubeless.models.Video
import nl.rgasc.tubeless.viewmodels.ChannelViewModel
import nl.rgasc.tubeless.viewmodels.VideoViewModel

/**
 * This fragment shows the videos of a single channel
 */
class ChannelFragment : Fragment() {

    private var _binding: FragmentChannelBinding? = null
    private val binding get() = _binding!!
    private val channelViewModel: ChannelViewModel by activityViewModels()
    private val videoViewModel: VideoViewModel by activityViewModels()
    private val videos: ArrayList<Video> = arrayListOf()
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChannelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.fragment_channel_title, channelViewModel.currentChannel.name)

        initViews()
        observeVideos()
    }

    private fun initViews() {
        videoAdapter = VideoAdapter(videos, ::onVideoClick)

        binding.rvVideos.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvVideos.adapter = videoAdapter
    }

    private fun onVideoClick(video: Video) {
        videoViewModel.currentVideo = video
        findNavController().navigate(R.id.videoFragment)
    }

    private fun observeVideos() {
        videoViewModel.getVideos(arrayListOf(channelViewModel.currentChannel))

        videoViewModel.videos.observe(viewLifecycleOwner, {
            videos.clear()
            videos.addAll(it.sortedByDescending { video -> video.uploaded })
            videoAdapter.notifyDataSetChanged()
        })

        videoViewModel.error.observe(viewLifecycleOwner, { error ->
            if (error) {
                Toast.makeText(activity, "Unable to obtain videos for this channel", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_channelFragment_to_feedFragment)
                videoViewModel.error.value = false
            }
        })
    }
}