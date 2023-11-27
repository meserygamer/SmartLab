package com.example.myapplication

import CatalogItem
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityShoppingCartBinding
import main_page_classes.IOnOrderListner
import main_page_classes.OnOrderMultipleListner
import main_page_classes.Order
import main_page_classes.PackingOrder
import shopping_cart_classes.OrderRecyclerView

class shopping_cart : AppCompatActivity(),IOnOrderListner {

    private var _binding : ActivityShoppingCartBinding? = null;

    private val buildingOrder : Order = PackingOrder.GetPackingOrder()

    public val binding : ActivityShoppingCartBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOrderRecyclerView()
        (buildingOrder.onOrderListner as OnOrderMultipleListner).AddOnOrderListner(this);
        setOrderSum()
        binding.BackOnMainPageButton.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                (buildingOrder.onOrderListner as OnOrderMultipleListner).ClearOnOrderListnersCollection()
                startActivity(Intent(this@shopping_cart, MainPage::class.java))
            }

        })

        binding.imageView.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                buildingOrder.clearOrderItems()
            }

        })
        binding.GoToCheckoutButton.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                (buildingOrder.onOrderListner as OnOrderMultipleListner).ClearOnOrderListnersCollection()
                startActivity(Intent(this@shopping_cart, Order_Registration::class.java))
            }

        })
    }

    public fun setOrderRecyclerView()
    {
        binding.OrderBasketRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        var adapter : OrderRecyclerView = OrderRecyclerView(buildingOrder);
        binding.OrderBasketRecyclerView.adapter = adapter;
    }

    private fun setOrderSum()
    {
        if(buildingOrder.SummaryCost == 0)
        {
            (buildingOrder.onOrderListner as OnOrderMultipleListner).ClearOnOrderListnersCollection()
            startActivity(Intent(this@shopping_cart, MainPage::class.java))
        }
        binding.OrderSumTextView.text = buildingOrder.SummaryCost.toString() + " ₽"
    }

    override fun onItemChanged(item: CatalogItem, position: Int) {
        setOrderSum()
    }

    override fun onAddItem(item: CatalogItem, position: Int) {
        setOrderSum()
    }

    override fun onRemoveItem(item: CatalogItem, position: Int) {
        setOrderSum()
    }

    override fun onClearOrder(orderCompositionList: List<Pair<CatalogItem, Int>>) {
        setOrderSum()
    }

}