/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import jdk.internal.org.jline.utils.PumpReader;
import pojos.Company;
import pojos.Contact;

/**
 *
 * @author 10jon
 */
public class IO_Helper {
    public static List<Contact> getContacts(String filepath){
        List<Contact> contacts = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = new FileInputStream(filepath);
            JavaType contact_list = mapper.getTypeFactory().constructCollectionType(List.class, Contact.class);
            contacts = mapper.readValue(is, contact_list);
            for (Contact contact : contacts) {
                contact.getCompany().addContact(contact);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return contacts;
    }
    
    public static void exportContacts(List<Contact> contacts){
        ObjectMapper mapper = new ObjectMapper();
        String filepath = "C:\\Users\\10jon\\Desktop\\POS4\\Exa_106_ContactApp\\src\\main\\webapp\\res\\favourites.json";
        try {
            System.out.println(filepath);
            String content = mapper.writeValueAsString(contacts);
            File file = new File(filepath);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
