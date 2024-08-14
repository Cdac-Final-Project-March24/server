package com.app.entity;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CollectionTable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"business"})
@NoArgsConstructor
@AllArgsConstructor
public class Offering extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    private String image;

    @Enumerated(EnumType.STRING)
    private OfferingType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    private Long orderCount = 0L;

    @Column(length = 255) // Adjust length as needed
    private String functionality;

    @Column(length = 255) // Adjust length as needed
    private String usefulness;

    @Column(nullable = false)
    private double rating; // Non-integral type for fractional values, use double or float

    @ElementCollection
    @CollectionTable(name = "offering_benefits", joinColumns = @JoinColumn(name = "offering_id"))
    @Column(name = "benefit")
    private List<String> benefits;
    
    
    @OneToMany(mappedBy = "offering", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfferingReview> reviews;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Offering other = (Offering) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
