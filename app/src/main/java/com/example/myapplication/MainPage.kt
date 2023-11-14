package com.example.myapplication

import APINewsItem
import CatalogItem
import Common
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
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
import main_page_classes.MainPageStateMachine
import main_page_classes.NewsRecyclerAdapter
import main_page_classes.ProductInTheBasket
import main_page_classes.ProductNotInTheBasket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainPage : AppCompatActivity() {

    val mainPageState : MainPageStateMachine = MainPageStateMachine()

    var binding : ActivityMainPageBinding? = null;

    var news : MutableList<APINewsItem>? = null;

    var catalogItems : MutableList<CatalogItem>? = null;

    var categoriesList : MutableList<String> = mutableListOf("Популярные", "COVID", "Онкогенетические", "ЗОЖ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        SetAllAdapter()
        /*mainPageState.SetInstance(ProductInTheBasket(binding!!))
        updatePageWithState()*/
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
        var CatalogItemsAdapter : CatalogRecyclerViewAdapter = CatalogRecyclerViewAdapter(catalogItems!!)
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