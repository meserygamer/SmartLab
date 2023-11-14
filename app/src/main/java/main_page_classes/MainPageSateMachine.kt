package main_page_classes

import android.content.res.Resources
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import com.example.myapplication.databinding.ActivityMainPageBinding

interface IMainPageState
{
    public val MainInfoScrollViewLayoutParams : ConstraintLayout.LayoutParams

    public val CatalogItemsRecyclerViewLayoutParams : LinearLayout.LayoutParams

    public val BasketButtonVisibile : Int
}

class MainPageStateMachine()
{
    private var _instance : IMainPageState? = null

    fun SetInstance(instance : IMainPageState)
    {
        this._instance = instance;
    }

    fun GetInstance() : IMainPageState
    {
        return _instance!!
    }
}

class ProductInTheBasket(binding : ActivityMainPageBinding) : IMainPageState
{
    override val MainInfoScrollViewLayoutParams: ConstraintLayout.LayoutParams
        get() = _mainInfoScrollViewLayoutParams
    override val CatalogItemsRecyclerViewLayoutParams: LinearLayout.LayoutParams
        get() = _catalogItemsRecyclerViewLayoutParams
    override val BasketButtonVisibile: Int
        get() = View.VISIBLE

    private val _mainInfoScrollViewLayoutParams : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT
        , ConstraintLayout.LayoutParams.MATCH_PARENT)

    private val _catalogItemsRecyclerViewLayoutParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT
        , (400 * Resources.getSystem().getDisplayMetrics().density).toInt())

    init {
        _mainInfoScrollViewLayoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        _mainInfoScrollViewLayoutParams.topToBottom = binding!!.editText.id
        _mainInfoScrollViewLayoutParams.bottomMargin = (binding!!.MainInfoScrollView.layoutParams
                as ConstraintLayout.LayoutParams).bottomMargin + (104 * Resources.getSystem().getDisplayMetrics().density).toInt() ;
        _mainInfoScrollViewLayoutParams.topMargin = (binding!!.MainInfoScrollView.layoutParams
                as ConstraintLayout.LayoutParams).topMargin;
        _catalogItemsRecyclerViewLayoutParams.topMargin = binding!!.CatalogItemsRecyclerView.marginTop
    }
}

class ProductNotInTheBasket(binding : ActivityMainPageBinding) : IMainPageState
{
    override val MainInfoScrollViewLayoutParams: ConstraintLayout.LayoutParams
        get() = _mainInfoScrollViewLayoutParams

    override val CatalogItemsRecyclerViewLayoutParams: LinearLayout.LayoutParams
        get() = _catalogItemsRecyclerViewLayoutParams

    override val BasketButtonVisibile: Int
        get() = View.GONE

    private val _mainInfoScrollViewLayoutParams : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT
        , ConstraintLayout.LayoutParams.MATCH_PARENT)

    private val _catalogItemsRecyclerViewLayoutParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT
        , (504 * Resources.getSystem().getDisplayMetrics().density).toInt())

    init {
        _mainInfoScrollViewLayoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        _mainInfoScrollViewLayoutParams.topToBottom = binding!!.editText.id
        _mainInfoScrollViewLayoutParams.bottomMargin = (binding!!.MainInfoScrollView.layoutParams
                as ConstraintLayout.LayoutParams).bottomMargin - (104 * Resources.getSystem().getDisplayMetrics().density).toInt() ;
        _mainInfoScrollViewLayoutParams.topMargin = (binding!!.MainInfoScrollView.layoutParams
                as ConstraintLayout.LayoutParams).topMargin;
        _catalogItemsRecyclerViewLayoutParams.topMargin = binding!!.CatalogItemsRecyclerView.marginTop
    }
}