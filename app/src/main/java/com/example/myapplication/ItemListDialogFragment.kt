package com.example.myapplication

import Action_Event
import CatalogItem
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle.Event
import com.example.myapplication.databinding.BottomFragmentCatalogItemBinding

class BottomFragmentCatalogItem : BottomSheetDialogFragment() {

    private var _binding: BottomFragmentCatalogItemBinding? = null

    public val cancelEvent : Action_Event<Int> = Action_Event()


    private val binding get() = _binding!!
    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = BottomFragmentCatalogItemBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.println(Log.WARN, "Debug", requireArguments().toString())
        var item : CatalogItem = requireArguments().getSerializable("item") as CatalogItem
        binding.DialogItemName.text = if (item.name == null) "" else item.name;
        binding.DialogItemDescription.text = if(item.description == null) "" else item.description;
        binding.DialogItemPrepare.text = if(item.preparation == null) "" else item.preparation;
        binding.BiomaterialField.text = if(item.bio == null) "" else item.bio;
        binding.ResultTimer.text = if(item.timeResul == null) "" else item.timeResul;
        binding.DialogItemButton.text = "Добавить за " + item.price + " ₽";
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        cancelEvent.invoke(0);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}