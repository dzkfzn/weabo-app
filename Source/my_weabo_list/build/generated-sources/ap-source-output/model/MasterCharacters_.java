package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.CharactersDetailAnime;
import model.DetailCharacter;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(MasterCharacters.class)
public class MasterCharacters_ { 

    public static volatile SingularAttribute<MasterCharacters, Date> createdDate;
    public static volatile CollectionAttribute<MasterCharacters, CharactersDetailAnime> charactersDetailAnimeCollection;
    public static volatile SingularAttribute<MasterCharacters, Date> lastModifiedDate;
    public static volatile SingularAttribute<MasterCharacters, MasterUser> createdBy;
    public static volatile SingularAttribute<MasterCharacters, MasterUser> lastModifiedBy;
    public static volatile SingularAttribute<MasterCharacters, Integer> statusDelete;
    public static volatile CollectionAttribute<MasterCharacters, DetailCharacter> detailCharacterCollection;
    public static volatile SingularAttribute<MasterCharacters, Integer> characterId;

}