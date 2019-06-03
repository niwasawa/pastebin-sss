package info.maigo.lab.pastebin.application;

import java.util.Calendar;

import info.maigo.lab.pastebin.domain.PasteData;

public class FormToData {

    public PasteData toPasteData(PostForm form) {
        PasteData data = new PasteData();
        data.title = form.getTitle();
        data.body = form.getBody();
        data.datetime = Calendar.getInstance().getTime();
        return data;
    }
}
