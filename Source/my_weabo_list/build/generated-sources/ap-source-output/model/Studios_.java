package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.DetailAnime;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(Studios.class)
public class Studios_ { 

    public static volatile SingularAttribute<Studios, Integer> studioId;
    public static volatile SingularAttribute<Studios, Date> createdDate;
    public static volatile SingularAttribute<Studios, MasterUser> createdBy;
    public static volatile SingularAttribute<Studios, String> name;
    public static volatile SingularAttribute<Studios, Date> modifiedDate;
    public static volatile SingularAttribute<Studios, MasterUser> modifiedBy;
    public static volatile SingularAttribute<Studios, Integer> isActive;
    public static volatile CollectionAttribute<Studios, DetailAnime> detailAnimeCollection;

}