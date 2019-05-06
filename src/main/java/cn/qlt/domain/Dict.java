package cn.qlt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import cn.qlt.utils.DomainObject;
@Entity
@Table(name="dict")
public class Dict extends DomainObject<Dict> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4767764380241019848L;
	@Id
	@Column(length=20, nullable=false,unique=true)
	@NotNull
	private String code;
	
	@Column(length=60,nullable=false)
	private String label;
	
	@Column(length=20, nullable=false)
	private String type;
	
	@Column(length=20)
	private String parent;
	
	@Column(length=6,nullable=false)
	private String state = "open";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Dict(String code) {
		super();
		this.code = code;
	}

	public Dict(String code, String label) {
		super();
		this.code = code;
		this.label = label;
	}

	public Dict() {
		super();
	}

	@Override
	public String toString() {
		return "[".concat(code).concat(":").concat(label).concat("]");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
		Dict other = (Dict) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}
	
}
