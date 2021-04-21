package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = IndustryType.GET_ALL_INDUSTRYTYPE_NAME, query = "SELECT i FROM IndustryType i order by i.typeId desc")
})
public class IndustryType implements Serializable{
	public static final String GET_ALL_INDUSTRYTYPE_NAME = "IndustryType.getAll";
	
	private int typeId;
	private String typeName;

	
	
	public IndustryType() {
		
	}

	public IndustryType(int typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}	

}
