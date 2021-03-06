/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 *
 * @author 10jon
 */
public class LanguageSelect {
    public static Language getLanguage(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            if(c.getName().equals("lang")){
                return Language.valueOf(c.getValue().toUpperCase());
            }
        }
        Cookie c = new Cookie("lang", "DE");
        response.addCookie(c);
        return Language.valueOf(c.getValue());
    }
}
