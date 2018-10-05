package org.thesis.roulett.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;

import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class CheckboxView extends BaseView {
 
    private String[] selectedConsoles;
 
    @PostConstruct
    public void init() {
    }
 
    public String[] getSelectedConsoles() {
        return selectedConsoles;
    }
 
    public void setSelectedConsoles(String[] selectedConsoles) {
        this.selectedConsoles = selectedConsoles;
    }
    
    public void play() {
    	for (String s : selectedConsoles) {
    		System.out.println(s);
		}
    }
 
}
