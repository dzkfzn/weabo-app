package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.DetailAnime;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(Genres.class)
public class Genres_ { 

    public static volatile SingularAttribute<Genres, Integer> genreId;
    public static volatile SingularAttribute<Genres, Date> createdDate;
    public static volatile SingularAttribute<Genres, MasterUser> createdBy;
    public static volatile SingularAttribute<Genres, String> name;
    public static volatile SingularAttribute<Genres, Date> modifiedDate;
    public static volatile SingularAttribute<Genres, MasterUser> modifiedBy;
    public static volatile SingularAttribute<Genres, Integer> isActive;
    public static volatile CollectionAttribute<Genres, DetailAnime> detailAnimeCollection;

}