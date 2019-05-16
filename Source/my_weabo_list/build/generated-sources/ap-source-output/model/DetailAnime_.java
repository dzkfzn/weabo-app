package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.CharactersDetailAnime;
import model.DetailEpisode;
import model.DetailPeople;
import model.Genres;
import model.Licensors;
import model.MasterAnime;
import model.MasterUser;
import model.Producers;
import model.Studios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(DetailAnime.class)
public class DetailAnime_ { 

    public static volatile SingularAttribute<DetailAnime, Integer> genreId;
    public static volatile SingularAttribute<DetailAnime, Studios> studioId;
    public static volatile SingularAttribute<DetailAnime, MasterAnime> animeId;
    public static volatile CollectionAttribute<DetailAnime, Licensors> licensorsCollection;
    public static volatile CollectionAttribute<DetailAnime, DetailEpisode> detailEpisodeCollection;
    public static volatile CollectionAttribute<DetailAnime, Genres> genresCollection;
    public static volatile SingularAttribute<DetailAnime, Integer> statusConfirm;
    public static volatile SingularAttribute<DetailAnime, Integer> totalEpisode;
    public static volatile SingularAttribute<DetailAnime, Integer> duration;
    public static volatile SingularAttribute<DetailAnime, Integer> airingStatus;
    public static volatile CollectionAttribute<DetailAnime, CharactersDetailAnime> charactersDetailAnimeCollection;
    public static volatile SingularAttribute<DetailAnime, String> nameEnglish;
    public static volatile SingularAttribute<DetailAnime, String> seriesType;
    public static volatile SingularAttribute<DetailAnime, Date> airingStartDate;
    public static volatile SingularAttribute<DetailAnime, String> airingDay;
    public static volatile SingularAttribute<DetailAnime, Integer> favorited;
    public static volatile CollectionAttribute<DetailAnime, Producers> producersCollection;
    public static volatile SingularAttribute<DetailAnime, String> nameJapan;
    public static volatile SingularAttribute<DetailAnime, String> thumbnail;
    public static volatile SingularAttribute<DetailAnime, String> airingTime;
    public static volatile SingularAttribute<DetailAnime, Integer> sysnopsis;
    public static volatile CollectionAttribute<DetailAnime, DetailPeople> detailPeopleCollection;
    public static volatile SingularAttribute<DetailAnime, Date> airingEndDate;
    public static volatile SingularAttribute<DetailAnime, Integer> detailAnimeId;
    public static volatile SingularAttribute<DetailAnime, Date> createdDate;
    public static volatile SingularAttribute<DetailAnime, String> sourceType;
    public static volatile SingularAttribute<DetailAnime, MasterUser> createdBy;
    public static volatile SingularAttribute<DetailAnime, Integer> background;
    public static volatile SingularAttribute<DetailAnime, Integer> statusActive;

}