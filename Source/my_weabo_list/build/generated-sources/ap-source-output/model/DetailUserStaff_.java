package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(DetailUserStaff.class)
public class DetailUserStaff_ { 

    public static volatile SingularAttribute<DetailUserStaff, Date> birthDay;
    public static volatile SingularAttribute<DetailUserStaff, String> phoneNumber;
    public static volatile SingularAttribute<DetailUserStaff, String> address;
    public static volatile SingularAttribute<DetailUserStaff, Integer> roleStaff;
    public static volatile SingularAttribute<DetailUserStaff, MasterUser> userId;
    public static volatile SingularAttribute<DetailUserStaff, Integer> staffId;

}