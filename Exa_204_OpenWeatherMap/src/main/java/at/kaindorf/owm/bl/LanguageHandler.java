/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.owm.bl;

import at.kaindorf.owm.beans.Language;
import at.kaindorf.owm.pojos.translation.Translation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 10jon
 */
public class LanguageHandler {
    public static Language getLanguage(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookie_array = request.getCookies();
        List<Cookie> cookies = cookie_array == null ? new ArrayList<>() : Arrays.asList(cookie_array);
        for (Cookie c : cookies) {
            if(c.getName().equals("lang")){
                return Language.valueOf(c.getValue().toUpperCase());
            }
        }
        Cookie c = new Cookie("lang", "DE");
        response.addCookie(c);
        return Language.valueOf(c.getValue().toUpperCase());
    }
}
