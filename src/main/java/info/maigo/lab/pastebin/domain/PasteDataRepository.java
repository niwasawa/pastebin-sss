package info.maigo.lab.pastebin.domain;

import java.util.List;

public interface PasteDataRepository {

    PasteData get(String id) throws Exception;

    PasteData add(PasteData data) throws Exception;

    List<PasteData> list() throws Exception;
}
