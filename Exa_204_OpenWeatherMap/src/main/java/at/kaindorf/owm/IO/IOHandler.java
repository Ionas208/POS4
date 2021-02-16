/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.owm.IO;

import at.kaindorf.owm.pojos.translation.Translation;
import at.kaindorf.owm.pojos.translation.Translations;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXB;

/**
 *
 * @author 10jon
 */
public class IOHandler {
    public static Map<String, Translation> getTranslations(String path){
        Translations trans = JAXB.unmarshal(new File(path), Translations.class);
        List<Translation> transList = trans.getTranslations();
        Map<String, Translation> translations = new HashMap<>();
        for (Translation t : transList) {
            translations.put(t.getKey(), t);
        }
        return translations;
    }
}
