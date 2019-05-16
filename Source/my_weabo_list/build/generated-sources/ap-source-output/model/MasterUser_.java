package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.DetailAnime;
import model.DetailCharacter;
import model.DetailPeople;
import model.DetailUserCustomer;
import model.DetailUserStaff;
import model.Genres;
import model.Licensors;
import model.MasterAnime;
import model.MasterCharacters;
import model.MasterPeople;
import model.Producers;
import model.Studios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(MasterUser.class)
public class MasterUser_ { 

    public static volatile CollectionAttribute<MasterUser, Studios> studiosCollection;
    public static volatile CollectionAttribute<MasterUser, DetailUserCustomer> detailUserCustomerCollection;
    public static volatile CollectionAttribute<MasterUser, Licensors> licensorsCollection;
    public static volatile CollectionAttribute<MasterUser, MasterAnime> masterAnimeCollection;
    public static volatile SingularAttribute<MasterUser, Integer> isActive;
    public static volatile CollectionAttribute<MasterUser, DetailAnime> detailAnimeCollection;
    public static volatile CollectionAttribute<MasterUser, Genres> genresCollection;
    public static volatile SingularAttribute<MasterUser, String> password;
    public static volatile CollectionAttribute<MasterUser, MasterAnime> masterAnimeCollection1;
    public static volatile CollectionAttribute<MasterUser, MasterPeople> masterPeopleCollection;
    public static volatile CollectionAttribute<MasterUser, MasterPeople> masterPeopleCollection1;
    public static volatile CollectionAttribute<MasterUser, DetailCharacter> detailCharacterCollection;
    public static volatile SingularAttribute<MasterUser, String> email;
    public static volatile SingularAttribute<MasterUser, Date> lastOnlineDate;
    public static volatile CollectionAttribute<MasterUser, Producers> producersCollection;
    public static volatile SingularAttribute<MasterUser, String> thumbnail;
    public static volatile CollectionAttribute<MasterUser, Studios> studiosCollection1;
    public static volatile CollectionAttribute<MasterUser, Producers> producersCollection1;
    public static volatile SingularAttribute<MasterUser, String> userId;
    public static volatile CollectionAttribute<MasterUser, DetailUserStaff> detailUserStaffCollection;
    public static volatile CollectionAttribute<MasterUser, DetailPeople> detailPeopleCollection;
    public static volatile SingularAttribute<MasterUser, Date> createdDate;
    public static volatile CollectionAttribute<MasterUser, MasterCharacters> masterCharactersCollection1;
    public static volatile CollectionAttribute<MasterUser, Licensors> licensorsCollection1;
    public static volatile SingularAttribute<MasterUser, String> name;
    public static volatile CollectionAttribute<MasterUser, MasterCharacters> masterCharactersCollection;
    public static volatile CollectionAttribute<MasterUser, Genres> genresCollection1;
    public static volatile SingularAttribute<MasterUser, Integer> roleUser;
    public static volatile SingularAttribute<MasterUser, String> username;

}