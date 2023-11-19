package main_page_classes

import CatalogItem
import android.util.Log

interface IOnOrderListner {
    fun onOrderCompositionChanged(Item : CatalogItem)
}

class Order {

    var SummaryCost : Int = 0
        public get(){
            var SummaryCost : Int = 0;
            for (item in OrderComposition.keys)
            {
                SummaryCost += item.price * OrderComposition.getValue(item);
            }
            return SummaryCost;
        }

    var OrderComposition : MutableMap<CatalogItem, Int> = HashMap<CatalogItem, Int>()
        public get() = field
        private set(value)
        {
            field = value
        }

    var onOrderListner : IOnOrderListner? = null
        public get() = field;
        public set(value)
        {
            field = value;
        }

    public fun AddItemInOrder(addingItem : CatalogItem)
    {
        OrderComposition.put(addingItem, 1);
        if(onOrderListner != null)
        {
            Log.println(Log.DEBUG, "Debug", onOrderListner.toString())
            onOrderListner!!.onOrderCompositionChanged(addingItem)
        }
    }

    public fun RemoveItemFromOrder(removingItem : CatalogItem)
    {
        var itemCount : Int? = OrderComposition.remove(removingItem)

        if(itemCount != null)
        {
            if(onOrderListner != null) {
                onOrderListner!!.onOrderCompositionChanged(removingItem)
            }
        }
    }

    public fun SearchItemInOrder(SeachingItem : CatalogItem) : Boolean
    {
        return OrderComposition.containsKey(SeachingItem)
    }

    public fun GetItemCount() : Int
    {
        return OrderComposition.count()
    }
}

class OnOrderMultipleListner : IOnOrderListner {

    val ListnersCollection : MutableList<IOnOrderListner> = mutableListOf<IOnOrderListner>();

    override fun onOrderCompositionChanged(Item: CatalogItem) {
        for (item in ListnersCollection)
        {
            item.onOrderCompositionChanged(Item);
        }
    }

    public fun AddOnOrderListner(AddingLisner : IOnOrderListner) : Boolean {
        return ListnersCollection.add(AddingLisner);
    }

    public fun RemoveOnOrderListner(AddingLisner : IOnOrderListner) : Boolean{
        return ListnersCollection.remove(AddingLisner)
    }

    public fun ClearOnOrderListnersCollection() {
        ListnersCollection.clear()
    }

}