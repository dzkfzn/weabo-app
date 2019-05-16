package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.CharactersDetailAnimePK;
import model.DetailAnime;
import model.MasterCharacters;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(CharactersDetailAnime.class)
public class CharactersDetailAnime_ { 

    public static volatile SingularAttribute<CharactersDetailAnime, Integer> role;
    public static volatile SingularAttribute<CharactersDetailAnime, DetailAnime> detailAnime;
    public static volatile SingularAttribute<CharactersDetailAnime, MasterCharacters> masterCharacters;
    public static volatile SingularAttribute<CharactersDetailAnime, CharactersDetailAnimePK> charactersDetailAnimePK;

}