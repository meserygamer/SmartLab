package main_page_classes

import APINewsItem
import CatalogItem
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainPageCatalogCategoryItemBinding
import com.example.myapplication.databinding.ActivityMainPageCatalogItemBinding
import com.example.myapplication.databinding.ActivityMainPageNewsItemBinding
import com.squareup.picasso.Picasso

//region NewsRecyclerAdapter
class NewsRecyclerAdapter(private val news: MutableList<APINewsItem>)
    : RecyclerView.Adapter<NewsViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityMainPageNewsItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return news.size;
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        var Item : APINewsItem = news[position];


        var back : Drawable = ContextCompat.getDrawable(holder.binding.root.context, R.drawable.activity_main_page_news_background)!!
        back.setLevel(position % 3)
        /*var back : ImageView = ImageView(holder.binding.root.context)
        back.setImageResource(R.drawable.activity_main_page_news_background)
        back.setImageLevel(position % 3)*/
        holder.binding.root.background = back
        holder.binding.HeaderNews.text = Item.name;
        holder.binding.DescriptionNews.text = Item.description;
        holder.binding.PriceNews.text = Item.price.toString();
        if (Item.image == null)
        {
            return;
        }
        Picasso.get().load(Item.image).into(holder.binding.BackgroundImage);
    }
}

class NewsViewHolder(val binding: ActivityMainPageNewsItemBinding) : RecyclerView.ViewHolder(binding.root)
//endregion

//region CatalogCategoriesRecyclerAdapter

class CategoriesRecyclerViewAdapter(private val catalogCategories : MutableList<String>)
    : RecyclerView.Adapter<CategoriesViewHolder>() {

    private var selectedCategory : CategoriesViewHolder? = null

    //region RecyclerView.Adapter realize
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        var layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        var binding : ActivityMainPageCatalogCategoryItemBinding = ActivityMainPageCatalogCategoryItemBinding.inflate(layoutInflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.binding.CategoryName.text = catalogCategories[position]
        holder.binding.root.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                changeSelectedItem(holder)
            }
        })
    }

    override fun getItemCount(): Int {
        return catalogCategories.size
    }
    //endregion

    fun changeSelectedItem(newSelectItem : CategoriesViewHolder)
    {
        if(selectedCategory != null) selectedCategory!!.binding.root.isSelected = false
        newSelectItem.binding.root.isSelected = true
        selectedCategory = newSelectItem
    }
}

class CategoriesViewHolder(val binding: ActivityMainPageCatalogCategoryItemBinding)
    : RecyclerView.ViewHolder(binding.root)

//endregion

//region CatalogRecyclerAdapter

class CatalogRecyclerViewAdapter(private val AllCatalogItems : MutableList<CatalogItem>) : RecyclerView.Adapter<CatalogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityMainPageCatalogItemBinding.inflate(inflater, parent, false)
        return CatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        var bindingCatalogItem : CatalogItem = AllCatalogItems[position]

        if(bindingCatalogItem.name != null)
        {
            holder.binding.CatalogItemName.text = bindingCatalogItem.name!!
            holder.binding.CatalogItemTimeResult.text = bindingCatalogItem.timeResul!!
            holder.binding.CatalogItemPrice.text = bindingCatalogItem.price.toString()
        }

    }

    override fun getItemCount(): Int {
        return AllCatalogItems.size
    }
}

class CatalogViewHolder(val binding: ActivityMainPageCatalogItemBinding) : RecyclerView.ViewHolder(binding.root)

//endregion