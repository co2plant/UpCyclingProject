package com.example.upcyclingstore.View

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upcyclingstore.Controller.ReceiveProductData
import com.example.upcyclingstore.Controller.RecyclerAdapter
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.FragmentWasteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 * Use the [WasteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

interface WasteCallback {
    fun onFunctionCall(data: List<RecyclerAdapter.MyItem>)
}
class WasteFragment : Fragment(),WasteCallback {
    override fun onFunctionCall(data: List<RecyclerAdapter.MyItem>) {
        val adapter = RecyclerAdapter(data)
        binding.recycler.adapter = adapter
    }
    private lateinit var binding:FragmentWasteBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWasteBinding.inflate(inflater, container, false)
        val view = binding.root

        //Grid 설정
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recycler.layoutManager = layoutManager


        val jsonData = JSONObject()
        jsonData.put("query", "SELECT * FROM Product")

        ReceiveProductData.receive(jsonData,requireContext(),this)

        return view
    }


}