package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(DetailUserCustomer.class)
public class DetailUserCustomer_ { 

    public static volatile SingularAttribute<DetailUserCustomer, Date> birthDay;
    public static volatile SingularAttribute<DetailUserCustomer, Integer> customerId;
    public static volatile SingularAttribute<DetailUserCustomer, MasterUser> userId;

}