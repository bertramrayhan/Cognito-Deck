package com.example.cognitodeck.data;

import com.example.cognitodeck.database.entity.LibraryListItem;
import com.example.cognitodeck.database.entity.Themes;
import com.example.cognitodeck.database.entity.TopicDisplayItem;
import com.example.cognitodeck.database.entity.Topics;

import java.util.ArrayList;
import java.util.List;

public class DummyLibraryData {

    public static List<LibraryListItem> getDummyLibraryItems() {
        List<LibraryListItem> dummyItems = new ArrayList<>();

        // --- Tema 1: JLPT ---
        Themes theme1 = new Themes(1, "UJIAN JLPT");
        dummyItems.add(theme1);
        Topics n5 = new Topics(1, "N5", 1);
        Topics n4 = new Topics(2, "N4", 1);
        dummyItems.add(new TopicDisplayItem(n5, TopicDisplayItem.PositionInGroup.FIRST));
        dummyItems.add(new TopicDisplayItem(n4, TopicDisplayItem.PositionInGroup.LAST));

        // --- Tema 2: Kehidupan Sehari-hari ---
        Themes theme2 = new Themes(2, "KEHIDUPAN SEHARI-HARI");
        dummyItems.add(theme2);
        Topics kuliner = new Topics(3, "Area Kuliner", 2);
        Topics transportasi = new Topics(4, "Area Transportasi", 2);
        Topics belanja = new Topics(5, "Area Belanja", 2);
        dummyItems.add(new TopicDisplayItem(kuliner, TopicDisplayItem.PositionInGroup.FIRST));
        dummyItems.add(new TopicDisplayItem(transportasi, TopicDisplayItem.PositionInGroup.MIDDLE));
        dummyItems.add(new TopicDisplayItem(belanja, TopicDisplayItem.PositionInGroup.LAST));

        // --- Tema 3: Hanya Satu Topik ---
        Themes theme3 = new Themes(3, "KOSAKATA KHUSUS");
        dummyItems.add(theme3);
        Topics slang = new Topics(6, "Bahasa Gaul (Slang)", 3);
        dummyItems.add(new TopicDisplayItem(slang, TopicDisplayItem.PositionInGroup.SINGLE));

        return dummyItems;
    }
}

