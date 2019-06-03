package info.maigo.lab.pastebin.infrastructure;

import java.util.*;

import info.maigo.lab.pastebin.domain.PasteData;

public class VolatileDatabase {

    private LinkedList<PasteData> list = new LinkedList<PasteData>();
    private Map<String, PasteData> map = new HashMap<String, PasteData>();

    public synchronized PasteData get(String id) {
        return map.get(id);
    }

    public synchronized void add(PasteData data) {
        list.addFirst(data);
        map.put(data.id, data);
    }

    public List<PasteData> list() {
        return list;
    }
}
