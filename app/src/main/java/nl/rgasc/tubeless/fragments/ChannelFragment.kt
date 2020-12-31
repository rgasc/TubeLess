package nl.rgasc.tubeless.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                val dialogBuilder = AlertDialog.Builder(requireContext())

                dialogBuilder.setMessage("Are you sure you want to delete this channel?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { _, _ ->
                        channelViewModel.deleteChannel(channelViewModel.currentChannel)
                        findNavController().navigate(R.id.action_channelFragment_to_feedFragment)
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.cancel()
                    }

                val alert = dialogBuilder.create()
                alert.setTitle("Delete channel")
                alert.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        videoAdapter = VideoAdapter(videos, ::onVideoClick)

        binding.rvVideos.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvVideos.adapter = videoAdapter
    }

    private fun onVideoClick(video: Video) {
        videoViewModel.currentVideo = video
        findNavController().navigate(R.id.action_channelFragment_to_videoFragment)
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
                videoViewModel.error.value = false
                findNavController().navigate(R.id.action_channelFragment_to_feedFragment)
            }
        })
    }
}