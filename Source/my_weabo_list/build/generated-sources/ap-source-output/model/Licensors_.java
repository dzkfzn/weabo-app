package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.DetailAnime;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(Licensors.class)
public class Licensors_ { 

    public static volatile SingularAttribute<Licensors, Date> createdDate;
    public static volatile SingularAttribute<Licensors, MasterUser> createdBy;
    public static volatile SingularAttribute<Licensors, Integer> licensorId;
    public static volatile SingularAttribute<Licensors, String> name;
    public static volatile SingularAttribute<Licensors, Date> modifiedDate;
    public static volatile SingularAttribute<Licensors, MasterUser> modifiedBy;
    public static volatile SingularAttribute<Licensors, Integer> isActive;
    public static volatile CollectionAttribute<Licensors, DetailAnime> detailAnimeCollection;

}