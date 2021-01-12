/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.LocalDateDeserializer;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 10jon
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    @JsonProperty(value = "Title")
    private String title;
    
    @JsonProperty(value = "Year")
    private String year;
    
    @JsonProperty(value = "Rated")
    private String rated;
    
    @JsonProperty(value = "Released")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate released;
    
    @JsonProperty(value = "Runtime")
    private String runtime;
    
    @JsonProperty(value = "Genre")
    private String genre;
    
    @JsonProperty(value = "Director")
    private String director;
    
    @JsonProperty(value = "Writer")
    private String writer;
    
    @JsonProperty(value = "Actors")
    private String actors;
    
    @JsonProperty(value = "Plot")
    private String plot;
    
    @JsonProperty(value = "Language")
    private String language;
    
    @JsonProperty(value = "Country")
    private String country;
    
    @JsonProperty(value = "Awards")
    private String awards;
    
    @JsonProperty(value = "Poster")
    private String poster;
    
    @JsonProperty(value = "Ratings")
    private Rating[] ratings;
    
    @JsonProperty(value = "Metascore")
    private String metascore;
    
    @JsonProperty(value = "imdbRating")
    private String imdbRating;
    
    @JsonProperty(value = "imdbVotes")
    private String imdbVotes;
    
    @JsonProperty(value = "imdbID")
    private String imdbID;
    
    @JsonProperty(value = "Type")
    private String type;
    
    @JsonProperty(value = "DVD")
    private String dvd;
    
    @JsonProperty(value = "BoxOffice")
    private String boxOffice;
    
    @JsonProperty(value = "Website")
    private String website;
}
