package com.example.myapplication

import Action_Event
import CatalogItem
import android.app.Dialog
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
import main_page_classes.PackingOrder

class BottomFragmentCatalogItem : BottomSheetDialogFragment() {

    private var _binding: BottomFragmentCatalogItemBinding? = null

    public val cancelEvent : Action_Event<Int> = Action_Event()

    private val binding get() = _binding!!

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    public lateinit var ShowingItem : CatalogItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = BottomFragmentCatalogItemBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.CloseBottomSheetDialogButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                dismiss()
            }
        })
        Log.println(Log.WARN, "Debug", requireArguments().toString())
        ShowingItem = requireArguments().getSerializable("item") as CatalogItem
        binding.DialogItemName.text = if (ShowingItem.name == null) "" else ShowingItem.name;
        binding.DialogItemDescription.text = if(ShowingItem.description == null) "" else ShowingItem.description;
        binding.DialogItemPrepare.text = if(ShowingItem.preparation == null) "" else ShowingItem.preparation;
        binding.BiomaterialField.text = if(ShowingItem.bio == null) "" else ShowingItem.bio;
        binding.ResultTimer.text = if(ShowingItem.timeResul == null) "" else ShowingItem.timeResul;
        if(PackingOrder.GetPackingOrder()!!.SearchItemInOrder(ShowingItem))
        {
            binding.DialogItemButton.text = "Убрать из заказа";
            binding.DialogItemButton.isSelected = true;
        }
        else
        {
            binding.DialogItemButton.text = "Добавить за " + ShowingItem.price + " ₽";
            binding.DialogItemButton.isSelected = false;
        }
        binding.DialogItemButton.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                AddInBucketButtonAction()
            }

        })
    }

    fun AddInBucketButtonAction()
    {
        if(binding.DialogItemButton.isSelected)
        {
            PackingOrder.GetPackingOrder().RemoveItemFromOrder(ShowingItem);
            binding.DialogItemButton.text = "Добавить за " + ShowingItem.price + " ₽";
            binding.DialogItemButton.isSelected = false;
        }
        else
        {
            PackingOrder.GetPackingOrder().AddItemInOrder(ShowingItem)
            binding.DialogItemButton.text = "Убрать из заказа";
            binding.DialogItemButton.isSelected = true;
        }
    }

    override fun dismiss() {
        cancelEvent.invoke(0);
        super.dismiss()
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