package mBeans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.IndustryType;
import repository.IndustryTypeRepository;

@ManagedBean(name = "typeManagedBean")
@SessionScoped
public class TypeManagedBean implements Serializable{
	@EJB
	IndustryTypeRepository typeRepository;
	
	public TypeManagedBean(){
		
	}
	
	public List<IndustryType> getAllIndustryType(){
		try {
			List<IndustryType> iType = typeRepository.getAllIndustryType();
			return iType;
		} catch(Exception ex) {
			Logger.getLogger(TypeManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public void addIndustryType(IndustryType theType) {
        try {
        	typeRepository.addIndustryType(theType);
		} catch(Exception ex) {
			Logger.getLogger(TypeManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	public void editIndustryType(IndustryType theType) {
        try {
        	typeRepository.editIndustryType(theType);
		} catch(Exception ex) {
			Logger.getLogger(TypeManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	public void deleteIndustryType(int id) {
        try {
        	typeRepository.deleteIndustryType(id);
		} catch(Exception ex) {
			Logger.getLogger(TypeManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	public IndustryType searchIndustryTypeById(int id) {
        try {
			return typeRepository.searchIndustryTypeById(id);
		} catch(Exception ex) {
			Logger.getLogger(TypeManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
        return null;
	}

}
