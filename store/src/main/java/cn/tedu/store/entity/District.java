package cn.tedu.store.entity;

import java.io.Serializable;
/**
 * 省市区实体类
 * @author JAVA
 *
 */
public class District implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3674364250490127944L;
	private Integer id;
	private String parent;
	private String code;
	private String name;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		District other = (District) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "District [id=" + id + ", parent=" + parent + ", code=" + code + ", name=" + name + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
	
}
