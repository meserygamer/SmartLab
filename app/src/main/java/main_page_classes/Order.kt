package main_page_classes

import CatalogItem
import android.util.Log

interface IOnOrderListner {
    fun onItemChanged(item : CatalogItem, position : Int);

    fun onAddItem(item : CatalogItem, position: Int);

    fun onRemoveItem(item : CatalogItem, position: Int);

    fun onClearOrder(orderCompositionList : List<Pair<CatalogItem, Int>>);
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

    val OrderCompositionList : List<Pair<CatalogItem, Int>>
        public get() = OrderComposition.toList()

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
            onOrderListner!!.onAddItem(addingItem, SearchItemPosition(addingItem));
        }
    }

    public fun IncrementNumberOfItem(item: CatalogItem)
    {
        if(OrderComposition[item] == null)
        {
            return;
        }
        val itemNumber = OrderComposition[item]!! + 1;
        OrderComposition[item] = itemNumber;
        if(onOrderListner == null)
        {
            return
        }
        onOrderListner!!.onItemChanged(item, SearchItemPosition(item));
    }

    public fun DicrementNumberOfItem(item : CatalogItem)
    {
        if(OrderComposition[item] == null)
        {
            return;
        }
        val itemNumber = OrderComposition[item]!! - 1
        OrderComposition[item] = itemNumber;
        if(itemNumber == 0)
        {
            return RemoveItemFromOrder(item);
        }
        if(onOrderListner == null)
        {
            return
        }
        onOrderListner!!.onItemChanged(item, SearchItemPosition(item));
    }

    public fun RemoveItemFromOrder(removingItem : CatalogItem)
    {
        var itemIndex : Int = SearchItemPosition(removingItem);
        var itemCount : Int? = OrderComposition.remove(removingItem)

        if(itemCount != null)
        {
            if(onOrderListner != null) {
                onOrderListner!!.onRemoveItem(removingItem, itemIndex);
            }
        }
    }

    public fun clearOrderItems()
    {
        var copyList : List<Pair<CatalogItem, Int>> = OrderCompositionList;
        OrderComposition.clear()
        if(onOrderListner != null) {
            onOrderListner!!.onClearOrder(copyList);
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

    private fun SearchItemPosition(item : CatalogItem) : Int
    {
        var counter : Int = 0;
        for (searchingItem in OrderCompositionList)
        {
            if(searchingItem.first == item)
            {
                break;
            }
            counter++;
        }
        return counter;
    }
}

class OnOrderMultipleListner : IOnOrderListner {

    val ListnersCollection : MutableList<IOnOrderListner> = mutableListOf<IOnOrderListner>();


    public fun AddOnOrderListner(AddingLisner : IOnOrderListner) : Boolean {
        return ListnersCollection.add(AddingLisner);
    }

    public fun RemoveOnOrderListner(AddingLisner : IOnOrderListner) : Boolean{
        return ListnersCollection.remove(AddingLisner)
    }

    public fun ClearOnOrderListnersCollection() {
        ListnersCollection.clear()
    }

    override fun onItemChanged(item: CatalogItem, position: Int) {
        for (collectionItem in ListnersCollection)
        {
            collectionItem.onItemChanged(item, position);
        }
    }

    override fun onAddItem(item: CatalogItem, position: Int) {
        for (collectionItem in ListnersCollection)
        {
            collectionItem.onAddItem(item, position);
        }
    }

    override fun onRemoveItem(item: CatalogItem, position: Int) {
        for (collectionItem in ListnersCollection)
        {
            collectionItem.onRemoveItem(item, position);
        }
    }

    override fun onClearOrder(orderCompositionList: List<Pair<CatalogItem, Int>>) {
        for (collectionItem in ListnersCollection)
        {
            collectionItem.onClearOrder(orderCompositionList);
        }
    }

}