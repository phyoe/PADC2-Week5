package xyz.phyoekhant.padc_week3.utils;

import java.util.HashMap;
import java.util.Map;

import xyz.phyoekhant.padc_week3.R;

/**
 * Created by Phyoe Khant on 6/24/2017.
 */
public class RestaurantsExtraData {

    public final Map<String, Integer> RESTAURANT_IMG_ARRAY = new HashMap<String, Integer>();
    public final Map<String, Integer> RESTAURANT_PRICEY_ARRAY = new HashMap<String, Integer>();
    public final Map<String, String[]> RESTAURANT_ICON_ARRAY = new HashMap<String, String[]>();
    public final Map<String, Integer> RESTAURANT_SORT_ARRAY = new HashMap<String, Integer>();

    public RestaurantsExtraData() {
        setRestaurantImgArray();
        setRestaurantPriceyArray();
        setRestaurantIconArray();
        setRestaurantSortArray();
    }

    private void setRestaurantImgArray() {
        RESTAURANT_IMG_ARRAY.put("bakerv", R.drawable.ic_bakerv);
        RESTAURANT_IMG_ARRAY.put("bapkoreanfood", R.drawable.ic_bapkoreanfood);
        RESTAURANT_IMG_ARRAY.put("bigbitezexpress", R.drawable.ic_bigbitezexpress);
        RESTAURANT_IMG_ARRAY.put("bigcharliegrill", R.drawable.ic_bigcharliegrill);
        RESTAURANT_IMG_ARRAY.put("boufeboutiquecafe", R.drawable.ic_boufeboutiquecafe);
        RESTAURANT_IMG_ARRAY.put("brindas", R.drawable.ic_brindas);
        RESTAURANT_IMG_ARRAY.put("fooksenggoldenhillchickenrice", R.drawable.ic_fooksenggoldenhillchickenrice);
        RESTAURANT_IMG_ARRAY.put("helenskoreanjapanese", R.drawable.ic_helenskoreanjapanese);
        RESTAURANT_IMG_ARRAY.put("jacksplace", R.drawable.ic_jacksplace);
        RESTAURANT_IMG_ARRAY.put("jadecrab", R.drawable.ic_jadecrab);
        RESTAURANT_IMG_ARRAY.put("jecrab", R.drawable.ic_jecrab);
        RESTAURANT_IMG_ARRAY.put("littlehouseofdreams", R.drawable.ic_littlehouseofdreams);
        RESTAURANT_IMG_ARRAY.put("mingenseafood", R.drawable.ic_mingenseafood);
        RESTAURANT_IMG_ARRAY.put("muthuscurry", R.drawable.ic_muthuscurry);
        RESTAURANT_IMG_ARRAY.put("nandos", R.drawable.ic_nandos);
        RESTAURANT_IMG_ARRAY.put("pulsepatisserie", R.drawable.ic_pulsepatisserie);
        RESTAURANT_IMG_ARRAY.put("roadhouse", R.drawable.ic_roadhouse);
        RESTAURANT_IMG_ARRAY.put("samyscurryrestaurant", R.drawable.ic_samyscurryrestaurant);
        RESTAURANT_IMG_ARRAY.put("sarpinos", R.drawable.ic_sarpinos);
        RESTAURANT_IMG_ARRAY.put("soithaisoinice", R.drawable.ic_soithaisoinice);
        RESTAURANT_IMG_ARRAY.put("thebakerychef", R.drawable.ic_thebakerychef);
        RESTAURANT_IMG_ARRAY.put("thecoffeebeantealeaf", R.drawable.ic_thecoffeebeantealeaf);
        RESTAURANT_IMG_ARRAY.put("thepantrysocialenterprisecafe", R.drawable.ic_thepantrysocialenterprisecafe);
        RESTAURANT_IMG_ARRAY.put("thewinecompany", R.drawable.ic_thewinecompany);
    }

    private void setRestaurantPriceyArray() {
        RESTAURANT_PRICEY_ARRAY.put("bakerv", 2);
        RESTAURANT_PRICEY_ARRAY.put("bapkoreanfood", 1);
        RESTAURANT_PRICEY_ARRAY.put("bigbitezexpress", 2);
        RESTAURANT_PRICEY_ARRAY.put("bigcharliegrill", 3);
        RESTAURANT_PRICEY_ARRAY.put("boufeboutiquecafe", 1);
        RESTAURANT_PRICEY_ARRAY.put("brindas", 2);
        RESTAURANT_PRICEY_ARRAY.put("fooksenggoldenhillchickenrice", 3);
        RESTAURANT_PRICEY_ARRAY.put("helenskoreanjapanese", 1);
        RESTAURANT_PRICEY_ARRAY.put("jacksplace", 2);
        RESTAURANT_PRICEY_ARRAY.put("jadecrab", 3);
        RESTAURANT_PRICEY_ARRAY.put("jecrab", 2);
        RESTAURANT_PRICEY_ARRAY.put("littlehouseofdreams", 2);
        RESTAURANT_PRICEY_ARRAY.put("mingenseafood", 3);
        RESTAURANT_PRICEY_ARRAY.put("muthuscurry", 1);
        RESTAURANT_PRICEY_ARRAY.put("nandos", 2);
        RESTAURANT_PRICEY_ARRAY.put("pulsepatisserie", 3);
        RESTAURANT_PRICEY_ARRAY.put("roadhouse", 1);
        RESTAURANT_PRICEY_ARRAY.put("samyscurryrestaurant", 2);
        RESTAURANT_PRICEY_ARRAY.put("sarpinos", 3);
        RESTAURANT_PRICEY_ARRAY.put("soithaisoinice", 1);
        RESTAURANT_PRICEY_ARRAY.put("thebakerychef", 2);
        RESTAURANT_PRICEY_ARRAY.put("thecoffeebeantealeaf", 2);
        RESTAURANT_PRICEY_ARRAY.put("thepantrysocialenterprisecafe", 1);
        RESTAURANT_PRICEY_ARRAY.put("thewinecompany", 2);
    }

    private void setRestaurantIconArray() {
        RESTAURANT_ICON_ARRAY.put("bakerv", new String[]{"halal", "percent", "file"});
        RESTAURANT_ICON_ARRAY.put("bapkoreanfood", new String[]{"percent", "halal", "file"});
        RESTAURANT_ICON_ARRAY.put("bigbitezexpress", new String[]{"halal", "file"});
        RESTAURANT_ICON_ARRAY.put("bigcharliegrill", new String[]{"file"});
        RESTAURANT_ICON_ARRAY.put("boufeboutiquecafe", new String[]{"file"});
        RESTAURANT_ICON_ARRAY.put("brindas", new String[]{"percent", "halal", "file"});
        RESTAURANT_ICON_ARRAY.put("fooksenggoldenhillchickenrice", new String[]{"percent", "halal"});
        RESTAURANT_ICON_ARRAY.put("helenskoreanjapanese", new String[]{"file"});
        RESTAURANT_ICON_ARRAY.put("jacksplace", new String[]{"halal"});
        RESTAURANT_ICON_ARRAY.put("jadecrab", new String[]{"percent"});
        RESTAURANT_ICON_ARRAY.put("jecrab", new String[]{"file"});
        RESTAURANT_ICON_ARRAY.put("littlehouseofdreams", new String[]{"percent", "file"});
        RESTAURANT_ICON_ARRAY.put("mingenseafood", new String[]{"percent", "halal", "file"});
        RESTAURANT_ICON_ARRAY.put("muthuscurry", new String[]{"percent"});
        RESTAURANT_ICON_ARRAY.put("nandos", new String[]{"halal", "file"});
        RESTAURANT_ICON_ARRAY.put("pulsepatisserie", new String[]{"percent"});
        RESTAURANT_ICON_ARRAY.put("roadhouse", new String[]{"halal", "file"});
        RESTAURANT_ICON_ARRAY.put("samyscurryrestaurant", new String[]{"percent", "halal"});
        RESTAURANT_ICON_ARRAY.put("sarpinos", new String[]{"halal", "percent"});
        RESTAURANT_ICON_ARRAY.put("soithaisoinice", new String[]{"percent"});
        RESTAURANT_ICON_ARRAY.put("thebakerychef", new String[]{"halal"});
        RESTAURANT_ICON_ARRAY.put("thecoffeebeantealeaf", new String[]{"percent", "halal", "file"});
        RESTAURANT_ICON_ARRAY.put("thepantrysocialenterprisecafe", new String[]{"percent", "halal", "file"});
        RESTAURANT_ICON_ARRAY.put("thewinecompany", new String[]{"file"});
    }

    private void setRestaurantSortArray() {
        RESTAURANT_SORT_ARRAY.put("thecoffeebeantealeaf", 10);
        RESTAURANT_SORT_ARRAY.put("nandos", 20);
        RESTAURANT_SORT_ARRAY.put("helenskoreanjapanese", 30);
        RESTAURANT_SORT_ARRAY.put("jecrab", 40);
        RESTAURANT_SORT_ARRAY.put("fooksenggoldenhillchickenrice", 50);
        RESTAURANT_SORT_ARRAY.put("soithaisoinice", 60);
        RESTAURANT_SORT_ARRAY.put("bapkoreanfood", 70);
        RESTAURANT_SORT_ARRAY.put("bigbitezexpress", 80);
        RESTAURANT_SORT_ARRAY.put("jacksplace", 90);
        RESTAURANT_SORT_ARRAY.put("mingenseafood", 100);
        RESTAURANT_SORT_ARRAY.put("jadecrab", 110);
        RESTAURANT_SORT_ARRAY.put("boufeboutiquecafe", 120);
        RESTAURANT_SORT_ARRAY.put("bigcharliegrill", 130);
        RESTAURANT_SORT_ARRAY.put("thepantrysocialenterprisecafe", 140);
        RESTAURANT_SORT_ARRAY.put("muthuscurry", 150);
        RESTAURANT_SORT_ARRAY.put("littlehouseofdreams", 160);
        RESTAURANT_SORT_ARRAY.put("bakerv", 170);
        RESTAURANT_SORT_ARRAY.put("samyscurryrestaurant", 180);
        RESTAURANT_SORT_ARRAY.put("roadhouse", 190);
        RESTAURANT_SORT_ARRAY.put("thebakerychef", 200);
        RESTAURANT_SORT_ARRAY.put("brindas", 210);
        RESTAURANT_SORT_ARRAY.put("thewinecompany", 220);
        RESTAURANT_SORT_ARRAY.put("pulsepatisserie", 230);
        RESTAURANT_SORT_ARRAY.put("sarpinos", 240);
    }

    public int getImage(String key) {
        if (RESTAURANT_IMG_ARRAY.get(key) == null)
            return 0;
        else
            return RESTAURANT_IMG_ARRAY.get(key);
    }

    public int getPricey(String key) {
        if (RESTAURANT_PRICEY_ARRAY.get(key) == null)
            return 0;
        else
            return RESTAURANT_PRICEY_ARRAY.get(key);
    }

    public String[] getIcons(String key) {
        if (RESTAURANT_ICON_ARRAY.get(key) == null)
            return null;
        else
            return RESTAURANT_ICON_ARRAY.get(key);
    }

    public int getSort(String key) {
        if (RESTAURANT_SORT_ARRAY.get(key) == null)
            return 0;
        else
            return RESTAURANT_SORT_ARRAY.get(key);
    }
}
