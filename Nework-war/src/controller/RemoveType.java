package controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.IndustryType;
import mBeans.TypeManagedBean;

@RequestScoped
@Named("removeType")
public class RemoveType {
	@ManagedProperty(value ="#{typeManagedBean}")
	TypeManagedBean typemanagedBean;
	
	Application app;
	private IndustryType aType;
	
	public RemoveType() {
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		app.updateTypeList();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		typemanagedBean = (TypeManagedBean)FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "typeManagedBean");
	}
	
	public void removeType(int id) {
		try {			
			typemanagedBean.deleteIndustryType(id);;
			app.typeSearchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Type has been deleted succesfully"));
		}catch(Exception ex) {
			
		}
	}

}
