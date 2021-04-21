package controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.IndustryType;

@Named(value = "typeController")
@RequestScoped
public class TypeController {
	private int typeID;
	private IndustryType aType;
	
	public TypeController(){
		typeID = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("typeID"));
		
		aType = getaType();
		
	}
	

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public IndustryType getaType() {
		if(aType == null) {
            ELContext context = FacesContext.getCurrentInstance().getELContext();
			
			Application app = (Application) FacesContext.getCurrentInstance()
                    .getApplication()
                    .getELResolver()
                    .getValue(context, null, "CustomerManagementApplication");
			return app.getTypes().get(--typeID);
		}
		return aType;
	}

	public void setaType(IndustryType aType) {
		this.aType = aType;
	}
	
	

}
