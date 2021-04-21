package controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.IndustryType;
import mBeans.TypeManagedBean;

@RequestScoped
@Named("searchType")
public class SearchType {
	@ManagedProperty(value ="#{typeManagedBean}")
	TypeManagedBean typemanagedBean;
	Application app;
	private IndustryType aType;
	private int intInput;
	
	public SearchType() {
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		app.updateTypeList();
	}
	
	public void searchTypeById(int id) {
		try {
			app.searchTypeById(id);
		}catch(Exception ex) {
    		
    	}
	}
	
	public void reset() {
		try {
    		app.typeSearchAll();;
    	}catch(Exception ex) {
    		
    	}
	}

	public IndustryType getaType() {
		return aType;
	}

	public void setaType(IndustryType aType) {
		this.aType = aType;
	}

	public int getIntInput() {
		return intInput;
	}

	public void setIntInput(int intInput) {
		this.intInput = intInput;
	}
	
	
}
