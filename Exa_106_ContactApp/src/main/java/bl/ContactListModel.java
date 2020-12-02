/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Filter;
import beans.Gender;
import beans.Sorter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.PatternSyntaxException;
import pojos.Contact;

/**
 *
 * @author 10jon
 */
public class ContactListModel {
    private List<Contact> contacts;
    private List<Contact> contacts_filtered;
    
    private Map<Sorter, Boolean> comparators = new LinkedHashMap<>();
    
    public ContactListModel(List<Contact> contacts){
        this.contacts = contacts;
        this.contacts_filtered = new ArrayList<>();
        this.contacts_filtered.addAll(contacts);
    }
   
    public List<Contact> getContacts(){
        return contacts_filtered;
    }
    
    public void reset(){
        contacts_filtered.clear();
        contacts_filtered.addAll(contacts);
        sort(comparators);
    }
    
    
    
    public void filter(Filter filterby, String filter) throws PatternSyntaxException{
        reset();
        switch(filterby){
            case COMPANY:
                contacts_filtered.removeIf(c -> !c.getCompany().getName().equals(filter));
                break;
            case GENDER:
                contacts_filtered.removeIf(c -> !c.getGender().equals(Gender.valueOf(filter)));
                break;
            case NAME:
                contacts_filtered.removeIf(c -> !(c.getFirstname()+""+c.getLastname()).toUpperCase().contains(filter.toUpperCase()));
                break;
            case REGEX:
                contacts_filtered.removeIf(c -> !(c.getFirstname()+""+c.getLastname()).matches(filter) );
                break;
        }
    }
    
    public void sort(Map<Sorter, Boolean> comparators){
        if(comparators == null || comparators.size()==0){
            return;
        }
        Comparator<Contact> c = null;
        for (Entry<Sorter, Boolean> e : comparators.entrySet()) {
            if( c == null){
                c = getComparator(e.getKey(), e.getValue());
            }
            else{
                c.thenComparing(getComparator(e.getKey(), e.getValue()));
            }
        }
        this.comparators.clear();
        this.comparators.putAll(comparators);
        contacts_filtered.sort(c);
    }
    
    public void delete(List<Integer> ids){
        for (Integer id : ids) {
            contacts.removeIf(c -> c.getId() == id);
        }
    }
    
    public void favourite(List<Integer> ids){
        List<Contact> favourite_contacts = new ArrayList<>(contacts);
        favourite_contacts.forEach(c -> c.setFavourite(false));
        favourite_contacts.removeIf(c -> !ids.contains(c.getId()));
        for (Contact favourite_contact : favourite_contacts) {
            favourite_contact.setFavourite(true);
        }
        reset();
    }
    
    private Comparator<Contact> getComparator(Sorter type, boolean reversed){
        Comparator<Contact> c = null;
        switch(type){
            case FIRSTNAME:
                c = ((c1, c2) -> c1.getFirstname().compareTo(c2.getFirstname()));
                break;
            case LASTNAME:
                c = ((c1, c2) -> c1.getLastname().compareTo(c2.getLastname()));
                break;
            case COMPANY:
                c = ((c1, c2) -> c1.getCompany().compareTo(c2.getCompany()));
                break;
            case AGE:
                c = ((c1, c2) -> c1.getAge().compareTo(c2.getAge()));
                break;
        }
        return reversed ? c.reversed() : c;
    }
    
    public List<Contact> getFavourites(){
        List<Contact> favourites = new ArrayList<>();
        for (Contact contact : contacts) {
            if(contact.isFavourite()){
                favourites.add(contact);
            }
        }
        return favourites;
    }
}
