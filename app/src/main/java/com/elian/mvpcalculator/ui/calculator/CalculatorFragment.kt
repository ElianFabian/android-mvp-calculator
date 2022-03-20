package com.elian.mvpcalculator.ui.calculator

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.elian.mvpcalculator.R
import com.elian.mvpcalculator.data.model.Operation
import com.elian.mvpcalculator.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment(), CalculatorContract.IView
{
    private lateinit var binding: FragmentCalculatorBinding
    private var presenter: CalculatorContract.IPresenter? = null

    //region Fragment Methods

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        presenter = CalculatorPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View
    {
        binding = FragmentCalculatorBinding.inflate(inflater)
        return binding.root
    }

    override fun onStart()
    {
        super.onStart()

        initOperationButtons()
        presenter = CalculatorPresenter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        inflater.inflate(R.menu.menu_calculator, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.menu_delete -> emptyTextFields()
            R.id.menu_list   ->
            {
                presenter?.onDestroy()
                presenter = null
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_calculatorFragment_to_operationListFragment)
            }
            else             -> return false
        }
        return true
    }

    //endregion

    //region Methods

    private fun initOperationButtons()
    {
        binding.apply()
        {
            btnAdd.setOnClickListener { operationOnClickListener(Operation.Type.ADDITION) }
            btnSubtract.setOnClickListener { operationOnClickListener(Operation.Type.SUBTRACTION) }
            btnMultiply.setOnClickListener { operationOnClickListener(Operation.Type.MULTIPLICATION) }
            btnDivide.setOnClickListener { operationOnClickListener(Operation.Type.DIVISION) }
        }
    }

    private fun operationOnClickListener(type: Operation.Type)
    {
        val operation = Operation(
            binding.tieNumber1.text.toString(),
            binding.tieNumber2.text.toString(),
            type
        )
        presenter?.onValidateData(operation)
    }

    private fun emptyTextFields()
    {
        binding.apply()
        {
            tieNumber1.text = null
            tieNumber2.text = null
            tvResult.setText(R.string.tvResult_text)
        }
    }

    //endregion

    //region CalculatorContract.IView

    override fun setNumber1EmptyError()
    {
        binding.tilNumber1.error = getString(R.string.error_emptyField)
    }

    override fun setNumber2EmptyError()
    {
        binding.tilNumber2.error = getString(R.string.error_emptyField)
    }

    override fun setResultError(message: String)
    {
        binding.tvResult.text = message
    }

    override fun cleanInputFieldsErrors()
    {
        binding.tilNumber1.error = null
        binding.tilNumber2.error = null
    }

    override fun onSuccess(operation: Operation)
    {
        binding.tvResult.text = operation.toString()
    }

    override fun onFailure()
    {

    }

    //endregion
}