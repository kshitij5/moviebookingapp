package com.moviebookingapp.techacadeemy.repository;

import lombok.Getter;

@Getter
public class RawData {
    static String movieList = """
            [
                {
                    "movieId": "KKBKKJ",
                    "movieName": "Kisi Ka Bhai Kisi Ka Jaan",
                    "moviePosterUrl": "https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/kisi-ka-bhai-kisi-ki-jaan-et00339118-1681730385.jpg",
                    "movieHours": "2h 25m",
                    "movieGenre": "Actiion, Comedy, Drama",
                    "movieLanguage": "Hindi",
                    "movieDescription": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet metus sed finibus lacinia. Ut orci libero, porttitor sit amet imperdiet id, finibus ut ex",
                    "movieRating": 6.6,
                    "movieDate": "2022-07-10T11:00:55.000+00:00"
                },
                {
                    "movieId": "PS2",
                    "movieName": "Ponniyin Selvan - Part 2",
                    "moviePosterUrl": "https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/ponniyin-selvan--part-2-et00348725-1680776467.jpg",
                    "movieHours": "2h 44m",
                    "movieGenre": "Action, Historical, Drama",
                    "movieLanguage": "Hindi",
                    "movieDescription": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet metus sed finibus lacinia. Ut orci libero, porttitor sit amet imperdiet id, finibus ut ex",
                    "movieRating": 6.6,
                    "movieDate": "2022-07-10T11:00:55.000+00:00"
                },
                {
                    "movieId": "EDR",
                    "movieName": "Evil Dead Rise",
                    "moviePosterUrl": "https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/evil-dead-rise-et00349309-1681275399.jpg",
                    "movieHours": "1h 44m",
                    "movieGenre": "Horror",
                    "movieLanguage": "Hindi",
                    "movieDescription": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet metus sed finibus lacinia. Ut orci libero, porttitor sit amet imperdiet id, finibus ut ex",
                    "movieRating": 7.5,
                    "movieDate": "2022-07-10T11:00:55.000+00:00"
                },
                {
                    "movieId": "FX",
                    "movieName": "Fast X",
                    "moviePosterUrl": "https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/fast-x-et00122562-1679316138.jpg",
                    "movieHours": "2h 14m",
                    "movieGenre": "Action, Drama, Crime",
                    "movieLanguage": "Hindi",
                    "movieDescription": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet metus sed finibus lacinia. Ut orci libero, porttitor sit amet imperdiet id, finibus ut ex",
                    "movieRating": 7.5,
                    "movieDate": "2022-07-10T11:00:55.000+00:00"
                },
                {
                    "movieId": "CZ",
                    "movieName": "Chengiz",
                    "moviePosterUrl": "https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/chengiz-et00345849-1669725861.jpg",
                    "movieHours": "2h 33m",
                    "movieGenre": "Action, Drama, Crime",
                    "movieLanguage": "Begali",
                    "movieDescription": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet metus sed finibus lacinia. Ut orci libero, porttitor sit amet imperdiet id, finibus ut ex",
                    "movieRating": 7.5,
                    "movieDate": "2022-07-10T11:00:55.000+00:00"
                },
                {
                    "movieId": "SS",
                    "movieName": "Sisu",
                    "moviePosterUrl": "https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/sisu-et00357229-1681298816.jpg",
                    "movieHours": "2h 33m",
                    "movieGenre": "Action, Drama, Crime",
                    "movieLanguage": "English",
                    "movieDescription": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet metus sed finibus lacinia. Ut orci libero, porttitor sit amet imperdiet id, finibus ut ex",
                    "movieRating": 7.5,
                    "movieDate": "2022-07-10T11:00:55.000+00:00"
                },
                {
                    "movieId": "BB",
                    "movieName": "Bad Boy",
                    "moviePosterUrl": "https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/bad-boy-hindi-et00103092-1679037134.jpg",
                    "movieHours": "2h 33m",
                    "movieGenre": "Action, Drama, Crime",
                    "movieLanguage": "English",
                    "movieDescription": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet metus sed finibus lacinia. Ut orci libero, porttitor sit amet imperdiet id, finibus ut ex",
                    "movieRating": 7.5,
                    "movieDate": "2022-07-10T11:00:55.000+00:00"
                }
            ]""";

    public static String getMovieList() {
        return movieList;
    }
}