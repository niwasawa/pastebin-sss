package info.maigo.lab.pastebin.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PastebinService {

    private PasteDataRepository repository;

    @Autowired
    public PastebinService(PasteDataRepository repository) {
        this.repository = repository;
    }

    public List<PasteData> getPasteDataList() throws Exception {
        return repository.list();
    }

    public PasteData getPasteData(String id) throws Exception {
        return repository.get(id);
    }

    public PasteData addPasteData(PasteData data) throws Exception {
        return repository.add(data);
    }
}
