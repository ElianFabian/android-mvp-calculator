package com.elian.mvpcalculator.ui.operationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elian.mvpcalculator.data.model.Operation
import com.elian.mvpcalculator.databinding.FragmentOperationListBinding
import com.elian.mvpcalculator.ui.operationlist.adapter.OperationAdapter

class OperationListFragment : Fragment(),
    IOperationListContract.IView,
    OperationAdapter.IOnManageList
{
    private lateinit var binding: FragmentOperationListBinding
    private lateinit var presenter: IOperationListContract.IPresenter
    private lateinit var adapter: OperationAdapter

    //region Fragment Methods

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View
    {
        binding = FragmentOperationListBinding.inflate(inflater)
        return binding.root
    }

    override fun onStart()
    {
        super.onStart()

        presenter = OperationListPresenter(this)
        initRecyclerView()
        presenter.load()
    }

    override fun onDestroy()
    {
        super.onDestroy()

        presenter.onDestroy()
    }

    //endregion

    //region Methods

    private fun initRecyclerView()
    {
        adapter = OperationAdapter(ArrayList(), this)
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.rvOperations.layoutManager = layoutManager
        binding.rvOperations.adapter = adapter
    }

    //endregion

    //region IOperationListContract.IView

    override fun onSuccess(list: List<Operation>)
    {
        adapter.load(list)
    }

    //endregion

    //region IOperationAdapter.IOnManageList

    override fun onShowOperation(operation: Operation)
    {
        //Toast.makeText(context, operation.toString(), Toast.LENGTH_SHORT).show()
    }

    //endregion
}