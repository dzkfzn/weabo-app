package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.DetailPeople;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(MasterPeople.class)
public class MasterPeople_ { 

    public static volatile SingularAttribute<MasterPeople, Date> createdDate;
    public static volatile SingularAttribute<MasterPeople, Integer> peopleId;
    public static volatile SingularAttribute<MasterPeople, Date> lastModifiedDate;
    public static volatile SingularAttribute<MasterPeople, MasterUser> createdBy;
    public static volatile SingularAttribute<MasterPeople, MasterUser> lastModifiedBy;
    public static volatile SingularAttribute<MasterPeople, Integer> statusDelete;
    public static volatile CollectionAttribute<MasterPeople, DetailPeople> detailPeopleCollection;

}