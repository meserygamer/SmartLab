package main_page_classes

import Action_Event
import CatalogItem

class Order {

    var SummaryCost : Int = 0
        public get() = field;
        private set(value)
        {
            field = value
        }

    var OrderComposition : MutableMap<CatalogItem, Int> = HashMap<CatalogItem, Int>()
        public get() = field
        private set(value)
        {
            field = value
        }

    val NotifyAboutOrderCompositionWasChanged : Action_Event<CatalogItem> = Action_Event()
        public get() = field

    public fun AddItemInOrder(addingItem : CatalogItem)
    {
        OrderComposition.put(addingItem, 1);
        SummaryCost += addingItem.price;
        NotifyAboutOrderCompositionWasChanged.invoke(addingItem)
    }

    public fun RemoveItemFromOrder(removingItem : CatalogItem)
    {
        var itemCount : Int? = OrderComposition.remove(removingItem)

        if(itemCount != null)
        {
            SummaryCost -= removingItem.price * itemCount
            NotifyAboutOrderCompositionWasChanged.invoke(removingItem)
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