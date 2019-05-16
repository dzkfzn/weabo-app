package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.DetailAnime;
import model.MasterPeople;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(DetailPeople.class)
public class DetailPeople_ { 

    public static volatile SingularAttribute<DetailPeople, String> lastName;
    public static volatile SingularAttribute<DetailPeople, Date> birthDay;
    public static volatile SingularAttribute<DetailPeople, String> thumbnail;
    public static volatile SingularAttribute<DetailPeople, Integer> detailPeopleId;
    public static volatile SingularAttribute<DetailPeople, String> about;
    public static volatile CollectionAttribute<DetailPeople, DetailAnime> detailAnimeCollection;
    public static volatile SingularAttribute<DetailPeople, Integer> statusConfirm;
    public static volatile SingularAttribute<DetailPeople, String> firstName;
    public static volatile SingularAttribute<DetailPeople, Date> createdDate;
    public static volatile SingularAttribute<DetailPeople, MasterPeople> peopleId;
    public static volatile SingularAttribute<DetailPeople, MasterUser> createdBy;
    public static volatile SingularAttribute<DetailPeople, Integer> statusActive;
    public static volatile SingularAttribute<DetailPeople, Integer> favorited;

}