/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.odskart.backend;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Jiří
 */
public class Category {
    private Integer id;
    private List<Medium> mediums;
    
    public Category(Integer id, List<Medium> mediums) {
        this.id = id;
        this.mediums = mediums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Medium> getMediums() {
        return mediums;
    }

    public void setMediums(List<Medium> mediums) {
        this.mediums = mediums;
    }
    public Integer findMovie(String name){
        if (name == null) throw new IllegalArgumentException("Movie name is null");
        
        throw new UnsupportedOperationException();
    }
    
    
    
}
