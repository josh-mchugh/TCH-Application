package com.redrumming.thecreaturehub.util;

/**
 * Created by ME on 12/18/2015.
 *
 * If this class needs to be expanded it is probably best to create a call the the YouTube Data API
 * Service to retrieve additional information. Or if this data changes. Rest a call and retrieve all
 * the categories and put them in a model.
 */
public class CategoryFormatter {

    private final int FILM_ANIMATION_VALUE = 1;
    private final int AUTO_VEHICLES_VALUE = 2;
    private final int MUSIC_VALUE = 10;
    private final int PETS_ANIMALS_VALUE = 15;
    private final int SPORTS_VALUE = 17;
    private final int SHORT_MOVIES_VALUE = 18;
    private final int TRAVEL_EVENTS_VALUE = 19;
    private final int GAMING_VALUE = 20;
    private final int VIDEO_BLOGGING_VALUE = 21;
    private final int PEOPLE_BLOGS_VALUE = 22;
    private final int COMEDY_VALUE = 23;
    private final int ENTERTAINMENT_VALUE = 24;
    private final int NEWS_POLITICS_VALUE = 25;
    private final int HOWTO_STYLE_VALUE = 26;
    private final int EDUCATION_VALUE = 27;
    private final int SCIENCE_TECHNOLOGY_VALUE = 28;
    private final int NONPROFIT_ACTIVISM_VALUE = 29;
    private final int MOVIES_VALUE = 30;
    private final int ANIME_ANIMATION_VALUE = 31;
    private final int ACTION_ADVENTURE_VALUE = 32;
    private final int CLASSICS_VALUE = 33;
    private final int COMEDY_VALUE_2 = 34;
    private final int DOCUMENTARY_VALUE = 35;
    private final int DRAMA_VALUE = 36;
    private final int FAMILY_VALUE = 37;
    private final int FOREIGN_VALUE = 38;
    private final int HORROR_VALUE = 39;
    private final int SCIFI_FANTASY_VALUE = 40;
    private final int THRILLER_VALUE = 41;
    private final int SHORTS_VALUE = 42;
    private final int SHOWS_VALUE = 43;
    private final int TRAILERS_VALUE = 44;

    private final String FILM_ANIMATION_READABLE = "Film & Animation";
    private final String AUTO_VEHICLES_READABLE = "Autos & Vehicles";
    private final String MUSIC_READABLE = "Music";
    private final String PETS_ANIMALS_READABLE = "Pets & Animals";
    private final String SPORTS_READABLE = "Sports";
    private final String SHORT_MOVIES_READABLE = "Short Movies";
    private final String TRAVEL_EVENTS_READABLE = "Travel & Events";
    private final String GAMING_READABLE = "Gaming";
    private final String VIDEO_BLOGGING_READABLE = "Videoblogging";
    private final String PEOPLE_BLOGS_READABLE = "People & Blogs";
    private final String COMEDY_READABLE = "Comedy";
    private final String ENTERTAINMENT_READABLE = "Entertainment";
    private final String NEWS_POLITICS_READABLE = "News & Politics";
    private final String HOWTO_STYLE_READABLE = "Howto & Style";
    private final String EDUCATION_READABLE = "Education";
    private final String SCIENCE_TECHNOLOGY_READABLE = "Science & Technology";
    private final String NONPROFIT_ACTIVISM_READABLE = "Nonprofits & Activism";
    private final String MOVIES_READABLE = "Movies";
    private final String ANIME_ANIMATION_READABLE = "Anime/Animation";
    private final String ACTION_ADVENTURE_READABLE = "Action/Adventure";
    private final String CLASSICS_READABLE = "Classics";
    private final String COMEDY_READABLE_2 = "Comedy";
    private final String DOCUMENTARY_READABLE = "Documentary";
    private final String DRAMA_READABLE = "Drama";
    private final String FAMILY_READABLE = "Family";
    private final String FOREIGN_READABLE = "Foreign";
    private final String HORROR_READABLE = "Horror";
    private final String SCIFI_FANASTY_READABLE = "Sci-Fi/Fantasy";
    private final String THRILLER_READABLE = "Thriller";
    private final String SHORTS_READABLE = "Shorts";
    private final String SHOWS_READABLE = "Shows";
    private final String TRAILERS_READABLE = "Trailers";


    public String formatCategory(String value){

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
