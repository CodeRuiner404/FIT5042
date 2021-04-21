package repository;

import java.util.List;

import javax.ejb.Remote;

import entities.IndustryType;

@Remote
public interface IndustryTypeRepository {
	
	public List<IndustryType> getAllIndustryType() throws Exception;
    public void addIndustryType(IndustryType theType) throws Exception;
    public void editIndustryType(IndustryType theType) throws Exception;
    public void deleteIndustryType(int id)throws Exception;
    public IndustryType searchIndustryTypeById(int id)throws Exception;
}
