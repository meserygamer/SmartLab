package main_page_classes;

public class PackingOrder {

    public static Order GetPackingOrder() {
        if(PackingOrder == null)
        {
            return PackingOrder = new Order();
        }
        return PackingOrder;
    }

    public static boolean GetIsOrderExists()
    {
        return PackingOrder != null;
    }


    private static Order PackingOrder;
}