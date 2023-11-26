package com.example.myapplication

import APINewsItem
import CatalogItem
import Common
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainPageBinding
import com.example.myapplication.databinding.ActivityMainPageCatalogCategoryItemBinding
import com.example.myapplication.databinding.ActivityMainPageCatalogItemBinding
import com.example.myapplication.databinding.ActivityMainPageNewsItemBinding
import com.squareup.picasso.Picasso
import main_page_classes.CatalogRecyclerViewAdapter
import main_page_classes.CategoriesRecyclerViewAdapter
import main_page_classes.IOnChangedSelectedItemListner
import main_page_classes.IOnOrderListner
import main_page_classes.MainPageStateMachine
import main_page_classes.NewsRecyclerAdapter
import main_page_classes.OnOrderMultipleListner
import main_page_classes.Order
import main_page_classes.PackingOrder
import main_page_classes.ProductInTheBasket
import main_page_classes.ProductNotInTheBasket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant


class MainPage : AppCompatActivity(), IOnChangedSelectedItemListner<Int>, IOnOrderListner {

    var binding : ActivityMainPageBinding? = null;

    lateinit var mainPageState : MainPageStateMachine;

    var news : MutableList<APINewsItem>? = null;

    var catalogItems : MutableList<CatalogItem>? = null;

    var categoriesList : MutableList<String> = mutableListOf("Популярные", "COVID", "Онкогенетические", "ЗОЖ")


    override fun onItemChanged(item: CatalogItem, position: Int) {
        checkingForSwitchActivityMode()
    }

    override fun onAddItem(item: CatalogItem, position: Int) {
        checkingForSwitchActivityMode()
    }

    override fun onRemoveItem(item: CatalogItem, position: Int) {
        checkingForSwitchActivityMode()
    }

    override fun onClearOrder(orderCompositionList: List<Pair<CatalogItem, Int>>) {
        checkingForSwitchActivityMode()
    }

    override fun OnItemChanged(ItemID: Int) {
        var showingFragment = BottomFragmentCatalogItem()
        var selectedItem = Bundle()
        selectedItem.putSerializable("item", catalogItems!![ItemID])
        showingFragment.arguments = selectedItem;
        Log.println(Log.INFO, "arg", showingFragment.arguments.toString())
        showingFragment.cancelEvent.plusAssign {
            binding!!.MainPageBlackout.visibility = View.GONE
        }
        showingFragment.show(supportFragmentManager, "tag");
        binding!!.MainPageBlackout.visibility = View.VISIBLE;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SetRecyclerViewListner()
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        SetAllAdapter()
        SetMainState()
        binding!!.GoToBasketButton.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                (PackingOrder.GetPackingOrder().onOrderListner as OnOrderMultipleListner).ClearOnOrderListnersCollection()
                startActivity(Intent(this@MainPage, shopping_cart::class.java));
            }

        })
    }

    fun SetMainState()
    {
        mainPageState = MainPageStateMachine(ProductNotInTheBasket(binding!!))
        checkingForSwitchActivityMode()
    }

    fun SetRecyclerViewListner()
    {
        if(PackingOrder.GetPackingOrder().onOrderListner != null
            && PackingOrder.GetPackingOrder().onOrderListner is OnOrderMultipleListner)
        {
            (PackingOrder.GetPackingOrder().onOrderListner as OnOrderMultipleListner).AddOnOrderListner(this)
            return;
        }
        val MultipleListner : OnOrderMultipleListner = OnOrderMultipleListner();
        MultipleListner.AddOnOrderListner(this);
        PackingOrder.GetPackingOrder().onOrderListner = MultipleListner
    }

    fun checkingForSwitchActivityMode()
    {
        if (PackingOrder.GetPackingOrder().GetItemCount() > 0 && (mainPageState.GetInstance() is ProductNotInTheBasket)) {
            mainPageState.SetInstance(ProductInTheBasket(binding!!))
            updatePageWithState()
        }
        if (PackingOrder.GetPackingOrder().GetItemCount() == 0 && (mainPageState.GetInstance() is ProductInTheBasket))
        {
            mainPageState.SetInstance(ProductNotInTheBasket(binding!!))
            updatePageWithState()
        }
        binding!!.OrderPrice.text = PackingOrder.GetPackingOrder().SummaryCost.toString() + " ₽"
    }

    fun updatePageWithState()
    {
        binding!!.BasketButton.visibility = mainPageState.GetInstance().BasketButtonVisibile
        binding!!.MainInfoScrollView.layoutParams = mainPageState.GetInstance().MainInfoScrollViewLayoutParams
        binding!!.CatalogItemsRecyclerView.layoutParams = mainPageState.GetInstance().CatalogItemsRecyclerViewLayoutParams
    }

    //region recyclerViews builders methods

    private fun SetAllAdapter()
    {
        GetNewsFromAPIAndSetAdapter();
        UpdateCategoryRecyclerView()
        GetCatalogItemsFromAPIAndSetAdapter();
    }

    private fun GetNewsFromAPIAndSetAdapter()
    {
        Common.retrofitService.GetAllNewsForUser().enqueue(object : Callback<MutableList<APINewsItem>>
        {
            override fun onResponse(
                call: Call<MutableList<APINewsItem>>,
                response: Response<MutableList<APINewsItem>>
            ) {
                if(response.code() == 200)
                {
                    news = response.body()
                    UpdateNewsRecyclerView();
                }
            }

            override fun onFailure(call: Call<MutableList<APINewsItem>>, t: Throwable) {
                Toast.makeText(this@MainPage,  t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun UpdateNewsRecyclerView()
    {
        binding!!.StockRecycleView.layoutManager = LinearLayoutManager(this
            , LinearLayoutManager.HORIZONTAL, false);
        var newsAdapter : NewsRecyclerAdapter = NewsRecyclerAdapter(news!!)
        binding!!.StockRecycleView.adapter = newsAdapter
    }

    private fun GetCatalogItemsFromAPIAndSetAdapter()
    {
        Common.retrofitService.GetAllCatalogItems().enqueue(object : Callback<MutableList<CatalogItem>>
        {
            override fun onResponse(
                call: Call<MutableList<CatalogItem>>,
                response: Response<MutableList<CatalogItem>>
            ) {
                if(response.code() == 200)
                {
                    catalogItems = response.body()
                    UpdateCatalogRecyclerView();
                }
            }

            override fun onFailure(call: Call<MutableList<CatalogItem>>, t: Throwable) {
                Toast.makeText(this@MainPage,  t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun UpdateCatalogRecyclerView()
    {
        binding!!.CatalogItemsRecyclerView.layoutManager = LinearLayoutManager(this
            , LinearLayoutManager.VERTICAL, false);
        var CatalogItemsAdapter : CatalogRecyclerViewAdapter = CatalogRecyclerViewAdapter(catalogItems!!,
            PackingOrder.GetPackingOrder())
        CatalogItemsAdapter.OnChangedSelectedItemListner = this;
        binding!!.CatalogItemsRecyclerView.adapter = CatalogItemsAdapter
    }

    private fun UpdateCategoryRecyclerView()
    {
        binding!!.CategoryRecyclerView.layoutManager = LinearLayoutManager(this
            , LinearLayoutManager.HORIZONTAL, false);
        var CategoriesItemsAdapter : CategoriesRecyclerViewAdapter = CategoriesRecyclerViewAdapter(categoriesList)
        binding!!.CategoryRecyclerView.adapter = CategoriesItemsAdapter
    }

    //endregion
}