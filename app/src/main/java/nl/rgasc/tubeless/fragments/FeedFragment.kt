package nl.rgasc.tubeless.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nl.rgasc.tubeless.R
import nl.rgasc.tubeless.adapters.VideoAdapter
import nl.rgasc.tubeless.databinding.FragmentFeedBinding
import nl.rgasc.tubeless.models.Channel
import nl.rgasc.tubeless.models.Video
import nl.rgasc.tubeless.viewmodels.VideoViewModel

/**
 * This shows a feed of all the users channels
 */
class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private val videos: ArrayList<Video> = arrayListOf()
    private lateinit var videoAdapter: VideoAdapter
    private val viewModel: VideoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.fragment_feed_title)

        initViews()
        observeVideos()
    }

    private fun initViews() {
        videoAdapter = VideoAdapter(videos, ::onVideoClick)

        binding.rvVideos.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvVideos.adapter = videoAdapter
    }

    private fun onVideoClick(video: Video) {
        viewModel.currentVideo = video
        findNavController().navigate(R.id.videoFragment)
    }

    private fun observeVideos() {
        viewModel.getVideos(
            arrayListOf(
                Channel("NileRed", "UCFhXFikryT4aFcLkLw2LBLA"),
                Channel("LGR", "UCLx053rWZxCiYWsBETgdKrQ"),
                Channel("A Friend", "UCNtQ6jbQgkV4kII043lU06w")
            )
        )

            viewModel.videos.observe(viewLifecycleOwner, Observer {
                videos.clear()
                videos.addAll(it.sortedByDescending { video -> video.uploaded })
                videoAdapter.notifyDataSetChanged()
            })
    }
}