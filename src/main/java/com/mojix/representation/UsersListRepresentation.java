package com.mojix.representation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by mmv on 7/8/15.
 */
public class UsersListRepresentation {
    private List<UsersRepresentation> list;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "users")
    public List<UsersRepresentation> getList() {
        if (list == null) {
            list = new ArrayList<UsersRepresentation>();
        }
        return list;
    }

    public void setList(List<UsersRepresentation> list) {
        this.list = list;
    }

}
