package auto.freemarker.template;

import java.util.List;

public class DomainClass {
	private List<DomainClassField> fieldList;
	private String name;

	public List<DomainClassField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<DomainClassField> fieldList) {
		this.fieldList = fieldList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
