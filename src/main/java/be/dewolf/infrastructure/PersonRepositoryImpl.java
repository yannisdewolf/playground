package be.dewolf.infrastructure;

import org.apache.log4j.Logger;

/**
 * Created by yannis on 15/01/17.
 */
public class PersonRepositoryImpl implements PersonRepositoryCustom{

    private static final Logger LOGGER = Logger.getLogger(PersonRepositoryImpl.class);

    @Override
    public String zegHallo() {
        return "Hallo van productiecode";
    }
}
