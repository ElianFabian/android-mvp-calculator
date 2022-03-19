package com.elian.mvpcalculator.ui.operationlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elian.mvpcalculator.R
import com.elian.mvpcalculator.data.model.Operation
import com.elian.mvpcalculator.databinding.ItemOperationBinding

class OperationAdapter(
    private val operations: ArrayList<Operation>,
    private val listener: IOnManageList,
) :
    RecyclerView.Adapter<OperationAdapter.OperationViewHolder>()
{
    interface IOnManageList
    {
        fun onShowOperation(operation: Operation)
    }

    public fun load(list: List<Operation>)
    {
        operations.clear()
        operations.addAll(list)

        notifyDataSetChanged()
    }

    //region RecyclerView.Adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)

        return OperationViewHolder(layoutInflater.inflate(R.layout.item_operation, parent, false))
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int)
    {
        val operationItem = operations[position]

        holder.render(operationItem)
    }

    override fun getItemCount(): Int = operations.size

    //endregion

    inner class OperationViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        private val binding = ItemOperationBinding.bind(view)

        fun render(operation: Operation) = with(binding)
        {
            tvId.text = operation.id.toString()
            tvOperation.text = operation.toString()

            listener.onShowOperation(operation)
        }
    }
}