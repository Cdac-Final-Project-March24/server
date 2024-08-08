package com.app.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="suborder")
@Getter
@Setter
@ToString(exclude = {"order","offering"})
@NoArgsConstructor
@AllArgsConstructor
public class SubOrder extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="order_id", nullable =false)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="offering_id", nullable =false)
	private Offering offering;
	
	private int quantity;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((offering == null) ? 0 : offering.hashCode());
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
		SubOrder other = (SubOrder) obj;
		if (offering == null) {
			if (other.offering != null)
				return false;
		} else if (!offering.equals(other.offering))
			return false;
		return true;
	}

}
