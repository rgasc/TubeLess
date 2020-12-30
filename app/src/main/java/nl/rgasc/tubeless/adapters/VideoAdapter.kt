package nl.rgasc.tubeless.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import nl.rgasc.tubeless.R
import nl.rgasc.tubeless.databinding.ItemChannelBinding
import nl.rgasc.tubeless.databinding.ItemVideoBinding
import nl.rgasc.tubeless.fragments.DrawerFragment
import nl.rgasc.tubeless.models.Channel
import nl.rgasc.tubeless.models.Video
import nl.rgasc.tubeless.views.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class VideoAdapter(
    private val videos: List<Video>
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemVideoBinding.bind(itemView)

        fun bind(video: Video) {
            Glide.with(itemView.context).load(video.thumbnailUrl).into(binding.ivThumbnail)
            binding.tvTitle.text = video.title

            binding.tvInfo.text = itemView.context.getString(
                R.string.video_info,
                video.channelName,
                video.views,
                SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(video.uploaded)
            )
        }
    }
}
