package com.sajin.virginmoneyapp.presentation.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sajin.virginmoneyapp.databinding.LayoutItemRoomBinding
import com.sajin.virginmoneyapp.domain.model.Result
import com.sajin.virginmoneyapp.domain.model.RoomsDataModel
import com.sajin.virginmoneyapp.domain.model.RoomsModel
import com.sajin.virginmoneyapp.utils.DateUtils

class RoomAdapter(
    private val roomModel: List<RoomsDataModel>,
    private val listener: (RoomsDataModel) -> Unit
) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    override fun getItemCount() = roomModel.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutItemRoomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(roomModel[position])
    }

    inner class ViewHolder(private val binding: LayoutItemRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(roomModel: RoomsDataModel) = with(itemView) {


            binding.apply {
                "Room Id :${roomModel.id}".also { roomID.text = it }
                ("Profile Created at: " +
                        DateUtils.getFormattedDate(
                            roomModel.getDateTimeObj()!!,
                            DateUtils.DIS_DATE_FORMAT_PATTERN1
                        )).also { timeID.text = it }
                ("Max Occupancy:" + roomModel.maxOccupancy.toString()).also { maxOccID.text = it }


                if (roomModel.isOccupied) {
                    "Occupied".also { checkBoxOccupied.text = it }
                    checkBoxOccupied.isChecked = false
                } else {
                    "Available".also { checkBoxOccupied.text = it }
                    checkBoxOccupied.isChecked = true
                }
            }
        }
    }

}