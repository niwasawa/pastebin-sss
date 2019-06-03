package info.maigo.lab.pastebin.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import info.maigo.lab.pastebin.domain.PastebinService;
import info.maigo.lab.pastebin.domain.PasteData;

@Controller
public class PastebinController {

    private final PastebinService pastebinService;

    @Autowired
    public PastebinController(PastebinService pastebinService) {
        this.pastebinService = pastebinService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String item(Model model) throws Exception {
        List<PasteData> pasteDataList = pastebinService.getPasteDataList();
        model.addAttribute("items", pasteDataList);
        return "index";
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String item(@PathVariable("id") String id, Model model) throws Exception {
        PasteData pasteData = pastebinService.getPasteData(id);
        model.addAttribute("title", pasteData.title);
        model.addAttribute("body", pasteData.body);
        model.addAttribute("datetime", pasteData.datetime);
        return "item";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post(@ModelAttribute @Validated PostForm form, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "invalid";
        }
        PasteData input = new FormToData().toPasteData(form);
        PasteData data = pastebinService.addPasteData(input);
        return "redirect:./item/" + data.id;
    }
}
