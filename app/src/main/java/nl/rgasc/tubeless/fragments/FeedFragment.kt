package nl.rgasc.tubeless.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nl.rgasc.tubeless.R
import nl.rgasc.tubeless.adapters.VideoAdapter
import nl.rgasc.tubeless.databinding.FragmentFeedBinding
import nl.rgasc.tubeless.models.Video
import nl.rgasc.tubeless.views.MainActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * This shows a feed of all the users channels
 */
class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private val videos: ArrayList<Video> = arrayListOf()
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        videoAdapter = VideoAdapter(videos, activity as MainActivity)
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
        binding.rvVideos.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvVideos.adapter = videoAdapter
    }

    private fun observeVideos() {
        val newVideos = arrayListOf<Video>()

        for (i in 1..5) {
            newVideos.add(
                Video(
                    "https://www.youtube.com/watch?v=3rBLWM9GLnw",
                    "https://i4.ytimg.com/vi/3rBLWM9GLnw/hqdefault.jpg",
                    "Spider-Man Miles Morales Glitches - Son of a Glitch - Episode 100",
                    "A+Start",
                    "329371",
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+'ss:ss", Locale.ENGLISH)
                        .parse("2020-12-12T00:59:58+00:00")!!
                )
            )
        }

        videos.addAll(newVideos)
        videoAdapter.notifyDataSetChanged()
    }
}