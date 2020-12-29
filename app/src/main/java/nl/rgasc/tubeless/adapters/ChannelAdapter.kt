package nl.rgasc.tubeless.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import nl.rgasc.tubeless.R
import nl.rgasc.tubeless.databinding.ItemChannelBinding
import nl.rgasc.tubeless.fragments.DrawerFragment
import nl.rgasc.tubeless.models.Channel
import nl.rgasc.tubeless.views.MainActivity

class ChannelAdapter(
    private val channels: List<Channel>,
    private val drawerFragment: DrawerFragment
) : RecyclerView.Adapter<ChannelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_channel, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return channels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(channels[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemChannelBinding.bind(itemView)

        fun bind(channel: Channel) {
            binding.channel.setOnClickListener {
                drawerFragment.viewModel.currentChannel = channel

                val activity = drawerFragment.activity as MainActivity

                activity.findNavController(R.id.nav_host_fragment).navigate(R.id.channelFragment)
                activity.binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            binding.tvChannelName.text = channel.name
        }
    }
}
