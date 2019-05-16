package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.DetailAnime;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(Producers.class)
public class Producers_ { 

    public static volatile SingularAttribute<Producers, Date> createdDate;
    public static volatile SingularAttribute<Producers, MasterUser> createdBy;
    public static volatile SingularAttribute<Producers, Integer> licensorId;
    public static volatile SingularAttribute<Producers, String> name;
    public static volatile SingularAttribute<Producers, Date> modifiedDate;
    public static volatile SingularAttribute<Producers, MasterUser> modifiedBy;
    public static volatile SingularAttribute<Producers, Integer> isActive;
    public static volatile CollectionAttribute<Producers, DetailAnime> detailAnimeCollection;

}