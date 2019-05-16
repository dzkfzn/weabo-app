package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.DetailAnime;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(MasterAnime.class)
public class MasterAnime_ { 

    public static volatile SingularAttribute<MasterAnime, Integer> animeId;
    public static volatile SingularAttribute<MasterAnime, Date> createdDate;
    public static volatile SingularAttribute<MasterAnime, Date> lastModifiedDate;
    public static volatile SingularAttribute<MasterAnime, MasterUser> createdBy;
    public static volatile SingularAttribute<MasterAnime, MasterUser> lastModifiedBy;
    public static volatile SingularAttribute<MasterAnime, Integer> statusDelete;
    public static volatile CollectionAttribute<MasterAnime, DetailAnime> detailAnimeCollection;

}