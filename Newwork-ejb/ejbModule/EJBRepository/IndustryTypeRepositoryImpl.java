package EJBRepository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.IndustryType;
import repository.IndustryTypeRepository;

@Stateless
public class IndustryTypeRepositoryImpl implements IndustryTypeRepository{
	@PersistenceContext(unitName= "Assignment_C_ejb")
	private EntityManager entityManager;

	@Override
	public List<IndustryType> getAllIndustryType() throws Exception {
		return entityManager.createNamedQuery(IndustryType.GET_ALL_INDUSTRYTYPE_NAME).getResultList();
	}

	@Override
	public void addIndustryType(IndustryType theType) throws Exception {
		List<IndustryType> types = entityManager.createNamedQuery(IndustryType.GET_ALL_INDUSTRYTYPE_NAME).getResultList();
		if(types.size()==0) {
			theType.setTypeId(1);
		}else {
			theType.setTypeId(types.get(0).getTypeId()+1);
		}
		entityManager.persist(theType);
		
	}

	@Override
	public void editIndustryType(IndustryType theType) throws Exception {
		try {
			entityManager.merge(theType);
		} catch(Exception ex) {
			
		}
		
	}

	@Override
	public void deleteIndustryType(int id) throws Exception {
		IndustryType aType = searchIndustryTypeById(id);
		if(aType != null) {
			entityManager.remove(aType);
		}
		
	}

	@Override
	public IndustryType searchIndustryTypeById(int id) throws Exception {
		IndustryType aType = entityManager.find(IndustryType.class, id);
		return aType;
	}

}
