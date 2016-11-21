package org.openape.api.usercontext;
/**
Copyright 2016 Hochschule der Medien - Stuttgart Media University

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
 
import java.util.ArrayList;
import javax.xml.bind.annotation.*;

import org.openape.api.DatabaseObject;

import java.util.List;

public class Context extends DatabaseObject {
    private static final long serialVersionUID = -8602234372848554234L;
    
    private String id;
    private String name;
    private List<Preference> preferences = new ArrayList<Preference>();

    public Context(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }

    public void addPreference(String key, String value) {
        Preference newPreference = new Preference(key, value);
        preferences.add(newPreference);
    }

}
