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
@Named("addType")
public class AddType {
	@ManagedProperty(value ="#{typeManagedBean}")
	TypeManagedBean typeManagedBean;
	
	Application app;
	private String typeName;
	
	public AddType() {
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		typeManagedBean = (TypeManagedBean)FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "typeManagedBean");
		
		typeName = getTypeName();
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public void addType() {
		try {
			IndustryType atype = new IndustryType();
			atype.setTypeName(typeName);
			typeManagedBean.addIndustryType(atype);
			app.typeSearchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Type has been added succesfully"));
		}catch(Exception ex) {
			
		}
	}
	
	
	

}
