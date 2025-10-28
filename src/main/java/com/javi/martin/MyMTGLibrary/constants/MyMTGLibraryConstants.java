package com.javi.martin.MyMTGLibrary.constants;

public class MyMTGLibraryConstants {

    //API
    public static final String SCRYFALL_BASE_PATH = "https://api.scryfall.com/";
    public static final String SCRYFALL_SVG_PATH = "https://svgs.scryfall.io/card-symbols/";
    public static final String SVG_EXTENSION = ".svg";
    public static final String SEARCH_PATH = "cards/search";
    public static final String ID_PATH = "cards/";
    public static final String SETS_PATH = "sets/";;

    //Filter
    public static final String TYPE_LINE_SEPARATOR = "—";
    public static final String[] TYPE_FILTERS = new String[]{
            "Creature", "Land", "Artifact", "Sorcery", "Instant",
            "Enchantment", "Legendary", "Planeswalker", "Vehicle"};
    public static final String[] COLOR_FILTERS = new String[]{
            "W","U","G","B","R"
    };
}
