package com.example.cryptocurrencyapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.ViewModel.CurrencyExchangeViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ExchangeTabFragment : Fragment() {
    private val viewModel: CurrencyExchangeViewModel by viewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layoutView = inflater.inflate(R.layout.fragment_exchange_tab, container, false)
        return layoutView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view?.findViewById<TextView>(R.id.currency_listing)

        Log.d("statusValue", "in fragment")
        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            Log.d("statusValue", "infragment: $status")
            textView!!.text = status.toString()
        }

        )
    }

    @Composable
    fun itemView(
        currencyCode: String = "USD",
        currencyName: String = "Currency",
        isNegative: Boolean = true,
        amount: String = "$6563567.87",
        percent: String = "2.5%",
    ){
        val currencyStatusColor = if(isNegative) Color.Red else Color.Green
        Column {
            Row {
                Image(
                    painter = painterResource(R.drawable.ic_globe_tab_icon),
                    contentDescription = "Image",
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_4))
                ) {
                    Text(
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_4)),
                        text = currencyCode,
                        fontSize = dimensionResource(R.dimen.dimen_text_size_medium).value.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_4)),
                        text = currencyName,
                        fontSize = dimensionResource(R.dimen.dimen_text_size_normal).value.sp,
                        color = colorResource(R.color.clr_text_grey)
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.ic_negative_graph),
                    contentDescription = "Image",
                    modifier = Modifier.fillMaxSize(),
                    tint = currencyStatusColor
                )
                Column(
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_4))
                ) {
                    Text(
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_4)),
                        text = amount,
                        fontSize = dimensionResource(R.dimen.dimen_text_size_medium).value.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_4)),
                        text = percent,
                        fontSize = dimensionResource(R.dimen.dimen_text_size_normal).value.sp,
                        color = currencyStatusColor
                    )
                }
            }
        }
    }

}