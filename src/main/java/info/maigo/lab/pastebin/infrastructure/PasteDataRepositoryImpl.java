package info.maigo.lab.pastebin.infrastructure;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import info.maigo.lab.pastebin.domain.PasteData;
import info.maigo.lab.pastebin.domain.PasteDataRepository;

@Repository
public class PasteDataRepositoryImpl implements PasteDataRepository {

    private static VolatileDatabase db = new VolatileDatabase();

    @Override
    public PasteData get(String id) {
        return db.get(id);
    }

    @Override
    public PasteData add(PasteData data) throws Exception {
        data.datetime = Calendar.getInstance().getTime();
        data.id = generateId(data);
        db.add(data);
        return data;
    }

    @Override
    public List<PasteData> list() {
        return db.list();
    }

    private static String generateId(PasteData data) throws NoSuchAlgorithmException {
        String mdInput = data.title + data.body + data.datetime.toString();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] mdOutput = md.digest(mdInput.getBytes(StandardCharsets.UTF_8));
        return toHexString(mdOutput);
    }

    private static String toHexString(byte[] input) {
        StringBuilder output = new StringBuilder(input.length * 2);
        for (byte b : input) {
            output.append(String.format("%02x", b & 0xff));
        }
        return output.toString();
    }
}
