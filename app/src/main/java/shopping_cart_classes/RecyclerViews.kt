package shopping_cart_classes

import CatalogItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainPageNewsItemBinding
import com.example.myapplication.databinding.ActivityShoppingCartBinding
import com.example.myapplication.databinding.ActivityShoppingCartOrderRowBinding
import main_page_classes.Order

class OrderRecyclerView(public var BuildingOrder : Order) : RecyclerView.Adapter<OrderRowViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderRowViewHolder {
        return OrderRowViewHolder(ActivityShoppingCartOrderRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return BuildingOrder.OrderComposition.count()
    }

    override fun onBindViewHolder(holder: OrderRowViewHolder, position: Int) {
        val OutputedItemKey : CatalogItem = BuildingOrder.OrderComposition.keys.toList()[position];
        //
        holder.SetItem(OutputedItemKey,BuildingOrder.OrderComposition[OutputedItemKey]!!);
    }

}

class OrderRowViewHolder(public var binding: ActivityShoppingCartOrderRowBinding) : RecyclerView.ViewHolder(binding.root) {

    public lateinit var item : CatalogItem

    public var count : Int = 0;

    public lateinit var onRemoveOrderItemListner : View.OnClickListener

    public lateinit var onAddNumberOrderItemListner : View.OnClickListener

    public lateinit var onDecreaseNumberOrderItemListner : View.OnClickListener


    public fun SetItem(item : CatalogItem, count : Int) {

        this.item = item;
        this.count = count;
        binding.OrderItemTitle.text = item.name
        binding.OrderItemPrice.text = item.price.toString() + " ₽";
        binding.OrderItemPatientsCounter.text = GetPatientCountInString(count);
        binding.OrderItemRemoveButton.setOnClickListener(onRemoveOrderItemListner)
        binding.AddNumberOrderItem.setOnClickListener(onAddNumberOrderItemListner)
        binding.DecreaseNumberOrderItem.setOnClickListener(onDecreaseNumberOrderItemListner)

    }


    private fun GetPatientCountInString(count : Int) : String {

        return when(count)
        {
            1 -> return count.toString() + " пациент";
            2, 3, 4 -> count.toString() + " пациента";
            else -> count.toString() + " пациентов";
        }

    }

}