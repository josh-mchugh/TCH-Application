package com.redrumming.thecreaturehub.util;

/**
 * Created by ME on 12/18/2015.
 *
 * If this class needs to be expanded it is probably best to create a call the the YouTube Data API
 * Service to retrieve additional information. Or if this data changes. Rest a call and retrieve all
 * the categories and put them in a model.
 */
public class CategoryFormatter {

    private static final int FILM_ANIMATION_VALUE = 1;
    private static final int AUTO_VEHICLES_VALUE = 2;
    private static final int MUSIC_VALUE = 10;
    private static final int PETS_ANIMALS_VALUE = 15;
    private static final int SPORTS_VALUE = 17;
    private static final int SHORT_MOVIES_VALUE = 18;
    private static final int TRAVEL_EVENTS_VALUE = 19;
    private static final int GAMING_VALUE = 20;
    private static final int VIDEO_BLOGGING_VALUE = 21;
    private static final int PEOPLE_BLOGS_VALUE = 22;
    private static final int COMEDY_VALUE = 23;
    private static final int ENTERTAINMENT_VALUE = 24;
    private static final int NEWS_POLITICS_VALUE = 25;
    private static final int HOWTO_STYLE_VALUE = 26;
    private static final int EDUCATION_VALUE = 27;
    private static final int SCIENCE_TECHNOLOGY_VALUE = 28;
    private static final int NONPROFIT_ACTIVISM_VALUE = 29;
    private static final int MOVIES_VALUE = 30;
    private static final int ANIME_ANIMATION_VALUE = 31;
    private static final int ACTION_ADVENTURE_VALUE = 32;
    private static final int CLASSICS_VALUE = 33;
    private static final int COMEDY_VALUE_2 = 34;
    private static final int DOCUMENTARY_VALUE = 35;
    private static final int DRAMA_VALUE = 36;
    private static final int FAMILY_VALUE = 37;
    private static final int FOREIGN_VALUE = 38;
    private static final int HORROR_VALUE = 39;
    private static final int SCIFI_FANTASY_VALUE = 40;
    private static final int THRILLER_VALUE = 41;
    private static final int SHORTS_VALUE = 42;
    private static final int SHOWS_VALUE = 43;
    private static final int TRAILERS_VALUE = 44;

    private static final String FILM_ANIMATION_READABLE = "Film & Animation";
    private static final String AUTO_VEHICLES_READABLE = "Autos & Vehicles";
    private static final String MUSIC_READABLE = "Music";
    private static final String PETS_ANIMALS_READABLE = "Pets & Animals";
    private static final String SPORTS_READABLE = "Sports";
    private static final String SHORT_MOVIES_READABLE = "Short Movies";
    private static final String TRAVEL_EVENTS_READABLE = "Travel & Events";
    private static final String GAMING_READABLE = "Gaming";
    private static final String VIDEO_BLOGGING_READABLE = "Videoblogging";
    private static final String PEOPLE_BLOGS_READABLE = "People & Blogs";
    private static final String COMEDY_READABLE = "Comedy";
    private static final String ENTERTAINMENT_READABLE = "Entertainment";
    private static final String NEWS_POLITICS_READABLE = "News & Politics";
    private static final String HOWTO_STYLE_READABLE = "Howto & Style";
    private static final String EDUCATION_READABLE = "Education";
    private static final String SCIENCE_TECHNOLOGY_READABLE = "Science & Technology";
    private static final String NONPROFIT_ACTIVISM_READABLE = "Nonprofits & Activism";
    private static final String MOVIES_READABLE = "Movies";
    private static final String ANIME_ANIMATION_READABLE = "Anime/Animation";
    private static final String ACTION_ADVENTURE_READABLE = "Action/Adventure";
    private static final String CLASSICS_READABLE = "Classics";
    private static final String COMEDY_READABLE_2 = "Comedy";
    private static final String DOCUMENTARY_READABLE = "Documentary";
    private static final String DRAMA_READABLE = "Drama";
    private static final String FAMILY_READABLE = "Family";
    private static final String FOREIGN_READABLE = "Foreign";
    private static final String HORROR_READABLE = "Horror";
    private static final String SCIFI_FANASTY_READABLE = "Sci-Fi/Fantasy";
    private static final String THRILLER_READABLE = "Thriller";
    private static final String SHORTS_READABLE = "Shorts";
    private static final String SHOWS_READABLE = "Shows";
    private static final String TRAILERS_READABLE = "Trailers";


    public static String formatCategory(String value){

        String category = "";
        int formattedValue = Integer.valueOf(value);

        switch (formattedValue){

            case FILM_ANIMATION_VALUE:

                category = FILM_ANIMATION_READABLE;
                break;

            case AUTO_VEHICLES_VALUE:

                category = AUTO_VEHICLES_READABLE;
                break;

            case MUSIC_VALUE:

                category = MUSIC_READABLE;
                break;

            case PETS_ANIMALS_VALUE:

                category = PETS_ANIMALS_READABLE;
                break;

            case SPORTS_VALUE:

                category = SPORTS_READABLE;
                break;

            case SHORT_MOVIES_VALUE:

                category = SHORT_MOVIES_READABLE;
                break;

            case TRAVEL_EVENTS_VALUE:

                category = TRAVEL_EVENTS_READABLE;
                break;

            case GAMING_VALUE:

                category = GAMING_READABLE;
                break;

            case VIDEO_BLOGGING_VALUE:

                category = VIDEO_BLOGGING_READABLE;
                break;

            case PEOPLE_BLOGS_VALUE:

                category = PEOPLE_BLOGS_READABLE;
                break;

            case COMEDY_VALUE:

                category = COMEDY_READABLE;
                break;

            case ENTERTAINMENT_VALUE:

                category = ENTERTAINMENT_READABLE;
                break;

            case NEWS_POLITICS_VALUE:

                category = NEWS_POLITICS_READABLE;
                break;

            case HOWTO_STYLE_VALUE:

                category = HOWTO_STYLE_READABLE;
                break;

            case EDUCATION_VALUE:

                category = EDUCATION_READABLE;
                break;

            case SCIENCE_TECHNOLOGY_VALUE:

                category = SCIENCE_TECHNOLOGY_READABLE;
                break;

            case NONPROFIT_ACTIVISM_VALUE:

                category = NONPROFIT_ACTIVISM_READABLE;
                break;

            case MOVIES_VALUE:

                category = MOVIES_READABLE;
                break;

            case ANIME_ANIMATION_VALUE:

                category = ANIME_ANIMATION_READABLE;
                break;

            case ACTION_ADVENTURE_VALUE:

                category = ACTION_ADVENTURE_READABLE;
                break;

            case CLASSICS_VALUE:

                category = CLASSICS_READABLE;
                break;

            case COMEDY_VALUE_2:

                category = COMEDY_READABLE_2;
                break;

            case DOCUMENTARY_VALUE:

                category = DOCUMENTARY_READABLE;
                break;

            case DRAMA_VALUE:

                category = DRAMA_READABLE;
                break;

            case FAMILY_VALUE:

                category = FAMILY_READABLE;
                break;

            case FOREIGN_VALUE:

                category = FOREIGN_READABLE;
                break;

            case HORROR_VALUE:

                category = HORROR_READABLE;
                break;

            case SCIFI_FANTASY_VALUE:

                category = SCIFI_FANASTY_READABLE;
                break;

            case THRILLER_VALUE:

                category = THRILLER_READABLE;
                break;

            case SHORTS_VALUE:

                category = SHORTS_READABLE;
                break;

            case SHOWS_VALUE:

                category = SHOWS_READABLE;
                break;

            case TRAILERS_VALUE:

                category = TRAILERS_READABLE;
                break;
        }

        return category;
    }
}
